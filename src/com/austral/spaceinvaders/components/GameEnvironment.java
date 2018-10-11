package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.components.modifiers.GodMode;
import com.austral.spaceinvaders.models.Level;
import com.austral.spaceinvaders.models.sprites.Player;
import com.austral.spaceinvaders.models.sprites.Shot;
import com.austral.spaceinvaders.models.sprites.Sprite;
import com.austral.spaceinvaders.models.sprites.aliens.Alien;
import com.austral.spaceinvaders.models.sprites.aliens.AlienFactory;
import com.austral.spaceinvaders.models.sprites.aliens.Bomb;
import com.austral.spaceinvaders.physics.CollisionFlag;
import com.austral.spaceinvaders.physics.Direction;
import com.austral.spaceinvaders.physics.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameEnvironment implements GlobalConfiguration {

	private final GameSession gameSession;
	private final Player player;
	private ArrayList<Alien> aliens = new ArrayList<>();
	private ArrayList<Shot> shots = new ArrayList<>();
	private ArrayList<Bomb> bombs = new ArrayList<>();
	private final GameModifierService gameModifierService;
	private Level currentLevel;
	private long finishCycleTickAmount;
	private int randomTimeUFO;

	public GameEnvironment(GameSession gameSession) {
		this.gameSession = gameSession;
		this.gameModifierService = new GameModifierService(this);
		this.gameModifierService.supplyGameModifier(new GodMode());
		this.player = new Player(playerStartX, playerStartY, 3, 4);
		this.finishCycleTickAmount = 30;
		this.randomTimeUFO = 4000;
	}

	public Player getPlayer() {
		return player;
	}

	private void disposeLevel() {
		this.aliens.clear();
		this.shots.clear();
		this.bombs.clear();
	}

	void initiateLevel(Level level) {
		disposeLevel();
		this.currentLevel = level;
		final Random randomGenerator = new Random();
		for (int alienCount = 0; alienCount < level.getAlienCount(); alienCount++) {
			aliens.add(AlienFactory.createSmall(randomGenerator.nextInt(frameWidth - 120) + 60, 10));
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
			if (!isSpriteOnScreen(alien)) {
				flipVelocityDirection(alien.getxVelocity());
			}
		});
	}

	private void animateShots() {
		shots.forEach(Sprite::animate);
		shots.removeIf(shot -> !isSpriteOnScreen(shot));
	}

	private void animateBombs() {
		bombs.forEach(Sprite::animate);
		bombs.removeIf(bomb -> !isSpriteOnScreen(bomb));
	}

	private boolean isSpriteOnScreen(Sprite sprite) {
		return isRectangleOnScreen(sprite.getCollisionBox());
	}

	private boolean isRectangleOnScreen(Rectangle rectangle) {
		return gameSession.getGameViewRectangle().contains(rectangle);
	}

	private boolean isValidPlayerMove() {
		final Rectangle playerCollisionBox = player.getCollisionBox();
		playerCollisionBox.translate(player.getxVelocity().getVectorially(), 0);
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
				gameSession.defeat();
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

		//spawn UFO
		if(finishCycleTickAmount%4500 == 0){
			randomTimeUFO = generateRandomNumberBetween(60, 45) * 100;//lo multiplico por 100 para darle mayor probabilidad de que el resto sea 0
		}

		if(finishCycleTickAmount%randomTimeUFO == 0){
			spawnUFO();
		}

		//Animate sprites for next game tick
		animateAliens();
		animateBombs();
		animateShots();

		if (isValidPlayerMove()) {
			player.animate();
		}

		//spawn new alien bombs
		dropAlienBombs();
		gameModifierService.ping();

		//add a finish cycle
		finishCycleTickAmount += 30;
	}

	private void spawnUFO(){
		final Random randomGenerator = new Random();

		aliens.add(AlienFactory.createUFO(randomGenerator.nextInt(frameWidth - 120) + 60, 10, generateRandomNumberBetween(300,50)));
	}

	private int generateRandomNumberBetween(int high, int low){
		final Random randomGenerator = new Random();
		return randomGenerator.nextInt(high - low) + low;
	}

	private void dropAlienBombs() {
		final Random randomGenerator = new Random();
		int[] randoms = randomGenerator.ints(aliens.size(), 0, 201).toArray();
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
					if (!gameModifierService.isGameModifierActive() && ++consecutiveHits >= 4) {
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

	public String getActivePowerUpName() {
		return gameModifierService.getActiveGameModifier();
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

	public ArrayList<Sprite> getSprites() {
		final ArrayList<Sprite> spriteList = new ArrayList<>();
		spriteList.addAll(aliens);
		spriteList.addAll(bombs);
		spriteList.addAll(shots);
		spriteList.add(player);
		return spriteList;
	}
}
