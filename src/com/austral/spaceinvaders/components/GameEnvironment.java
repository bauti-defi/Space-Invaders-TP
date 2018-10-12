package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.models.Level;
import com.austral.spaceinvaders.models.gameobjects.GameObject;
import com.austral.spaceinvaders.models.gameobjects.Shield;
import com.austral.spaceinvaders.models.gameobjects.sprites.Player;
import com.austral.spaceinvaders.models.gameobjects.sprites.Shot;
import com.austral.spaceinvaders.models.gameobjects.sprites.Sprite;
import com.austral.spaceinvaders.models.gameobjects.sprites.aliens.Alien;
import com.austral.spaceinvaders.models.gameobjects.sprites.aliens.AlienFactory;
import com.austral.spaceinvaders.models.gameobjects.sprites.aliens.Bomb;
import com.austral.spaceinvaders.physics.CollisionFlag;
import com.austral.spaceinvaders.physics.Direction;
import com.austral.spaceinvaders.physics.Velocity;
import com.austral.spaceinvaders.util.RandomGenerator;

import java.awt.*;
import java.util.ArrayList;

public class GameEnvironment implements GlobalConfiguration {

	private final GameSession gameSession;
	private Player player;
	private ArrayList<Alien> aliens = new ArrayList<>();
	private ArrayList<Shot> shots = new ArrayList<>();
	private ArrayList<Bomb> bombs = new ArrayList<>();
	private ArrayList<Shield> shields = new ArrayList<>();
	private final GameModifierService gameModifierService;
	private int gameTicksSinceUFOSpawn;
	private long randomTimeUFO;

	public GameEnvironment(GameSession gameSession) {
		this.gameSession = gameSession;
		this.gameModifierService = new GameModifierService(this);
		initiateLevel(Level.FIRST);
	}

	public Player getPlayer() {
		return player;
	}

	private void reset() {
		aliens.clear();
		shots.clear();
		shields.clear();
		bombs.clear();
		gameModifierService.forceDeactivateModifier();
		randomTimeUFO = RandomGenerator.getRandomIntBetween(minimumUFOSpawnDelay, maxUFOSPawnDelay) * 1000;
	}

	public void initiateLevel(Level level) {
		reset();
		this.player = new Player(playerStartX, playerStartY, level.getInitialLiveCount());
		spawnAliens(level.getAlienCount(), level.getAlienDifficultyMultiplier());
		spawnShields(level.getInitialShieldCount());
	}

	private void spawnShields(int shieldCount) {
		for (int count = 0; count < shieldCount; ++count) {
			shields.add(new Shield((frameWidth / shieldCount) * count, frameHeight - 150, shieldHealth));
		}
	}

	private void spawnAliens(int alienCount, int difficultyMultiplier) {
		for (int count = 0; count < alienCount; count++) {
			aliens.add(AlienFactory.createSmall(RandomGenerator.getRandomIntBetween(60, frameWidth - 120), 10, difficultyMultiplier));
			aliens.add(AlienFactory.createMedium(RandomGenerator.getRandomIntBetween(60, frameWidth - 120), 10, difficultyMultiplier));
			aliens.add(AlienFactory.createLarge(RandomGenerator.getRandomIntBetween(60, frameWidth - 120), 10, difficultyMultiplier));
		}
	}

	private void flipVelocityDirection(Velocity velocity) {
		switch (velocity.getDirection()) {
			case SOUTH:
				velocity.setDirection(Direction.NORTH);
				break;
			case EAST:
				velocity.setDirection(Direction.WEST);
				break;
			case WEST:
				velocity.setDirection(Direction.EAST);
				break;
			case NORTH:
				velocity.setDirection(Direction.SOUTH);
				break;
		}
	}

	private void animateAliens() {
		aliens.forEach(alien -> {
			alien.animate();
			if (!isGameObjectOnScreen(alien)) {
				flipVelocityDirection(alien.getxVelocity());
			}
		});
	}

	private void animateShots() {
		shots.forEach(Sprite::animate);
		shots.removeIf(shot -> !isGameObjectOnScreen(shot));
	}

	private void animateBombs() {
		bombs.forEach(Sprite::animate);
		bombs.removeIf(bomb -> !isGameObjectOnScreen(bomb));
	}

	private boolean isGameObjectOnScreen(GameObject gameObject) {
		return isRectangleOnScreen(gameObject.getCollisionBox());
	}

	private boolean isRectangleOnScreen(Rectangle rectangle) {
		return gameSession.getGameViewRectangle().contains(rectangle);
	}

	private boolean isValidPlayerMove() {
		final Rectangle playerCollisionBox = player.getCollisionBox();
		playerCollisionBox.translate(player.getxVelocity().getVector(), 0);
		return isRectangleOnScreen(playerCollisionBox);
	}

	public void executeNextAnimationCycle() {
		//Delete all alien and shot collision flags
		getAlienCollisions().forEach(hit -> {
			hit.getAlphaCollider().takeDamage(hit.getBetaCollider().getDamage());
			if (hit.getAlphaCollider().isDead()) {
				gameSession.awardPoints(hit.getAlphaCollider().getRewardPoints());
				aliens.remove(hit.getAlphaCollider());
			}
			shots.remove(hit.getBetaCollider());
		});

		//No aliens, level complete
		if (aliens.isEmpty()) {
			gameSession.victory();
		}

		//Aliens have invaded
		aliens.forEach(alien -> {
			if (alien.getY() + 20 >= player.getY()) {
				gameSession.invasion();
			}
		});

		//Delete all player and bomb collision flags
		//Player takes damage
		getPlayerCollisions().forEach(hit -> {
			hit.getAlphaCollider().takeDamage(hit.getBetaCollider().getDamage());
			if (hit.getAlphaCollider().isDead()) {
				gameSession.defeat();
			}
			bombs.remove(hit.getBetaCollider());
		});

		//Delete all shield and bomb collision flags
		//Shields take damage/de-spawn
		getShieldCollisions().forEach(hit -> {
			hit.getAlphaCollider().takeDamage(hit.getBetaCollider().getDamage());
			if (hit.getAlphaCollider().isDestroyed()) {
				shields.remove(hit.getAlphaCollider());
			}
		});

		//Spawn UFO
		if (gameTicksSinceUFOSpawn > 0 && (gameTicksSinceUFOSpawn * 30) % randomTimeUFO == 0) {
			spawnUFO();
		}

		//Animate sprites for next game tick
		animateAliens();
		animateBombs();
		animateShots();

		if (isValidPlayerMove()) {
			player.animate();
		}

		//increment ufo game tick
		gameTicksSinceUFOSpawn++;

		//spawn new alien bombs
		dropAlienBombs();

		gameModifierService.ping();
	}

	private void spawnUFO() {
		aliens.add(AlienFactory.createUFO(RandomGenerator.getRandomIntBetween(60, frameWidth - 120), 10, RandomGenerator.getRandomIntBetween(50, 300)));
		randomTimeUFO = RandomGenerator.getRandomIntBetween(minimumUFOSpawnDelay, maxUFOSPawnDelay) * 1000;
	}

	private void dropAlienBombs() {
		int[] randoms = RandomGenerator.getRandomIntStream(aliens.size(), 0, 201).toArray();
		for (int alienIndex = 0; alienIndex < aliens.size(); alienIndex++) {
			if (randoms[alienIndex] == 200) {
				bombs.add(aliens.get(alienIndex).fire());
			}
		}
	}

	private ArrayList<CollisionFlag<Alien, Shot>> getAlienCollisions() {
		final ArrayList<CollisionFlag<Alien, Shot>> hits = new ArrayList<>();

		int consecutiveHits = 0;
		//collisionFlags between aliens and shots
		for (Shot shot : shots) {
			int initialConsecutiveHits = consecutiveHits;
			for (Alien alien : aliens) {
				if (alien.collided(shot)) {
					hits.add(new CollisionFlag<>(alien, shot));
					if (!gameModifierService.isGameModifierActive() && ++consecutiveHits >= consecutiveHitsForModifier) {
						gameModifierService.activateModifier();
					}
				}
			}
			if (initialConsecutiveHits == consecutiveHits) {
				consecutiveHits = 0;
			}
		}

		return hits;
	}

	private ArrayList<CollisionFlag<Shield, Bomb>> getShieldCollisions() {
		final ArrayList<CollisionFlag<Shield, Bomb>> hits = new ArrayList<>();

		//collisionFlags between bombs and shields
		bombs.forEach(bomb -> {
			shields.forEach(shield -> {
				if (bomb.collided(shield)) {
					hits.add(new CollisionFlag<>(shield, bomb));
				}
			});
		});

		return hits;
	}

	private ArrayList<CollisionFlag<Player, Bomb>> getPlayerCollisions() {
		final ArrayList<CollisionFlag<Player, Bomb>> hits = new ArrayList<>();

		//collisionFlags between bombs and player
		bombs.forEach(bomb -> {
			if (bomb.collided(player)) {
				hits.add(new CollisionFlag<>(player, bomb));
			}
		});

		return hits;
	}

	public void notifyRightArrowPressed() {
		player.moveRight();
	}

	public void notifyLeftArrowPressed() {
		player.moveLeft();
	}

	public void notifyArrowUnPressed() {
		player.stop();
	}

	public void notifySpaceBarPressed() {
		shots.add(player.fire());
	}

	public String getActiveGameModifierName() {
		return gameModifierService.getActiveGameModifier();
	}

	public ArrayList<GameObject> getGameObjects() {
		final ArrayList<GameObject> objectList = new ArrayList<>();
		objectList.addAll(aliens);
		objectList.addAll(bombs);
		objectList.addAll(shots);
		objectList.addAll(shields);
		objectList.add(player);
		return objectList;
	}
}
