package com.austral.spaceinvaders.models.gameobjects.sprites.aliens;

import com.austral.spaceinvaders.physics.Direction;
import com.austral.spaceinvaders.physics.Velocity;
import com.austral.spaceinvaders.util.RandomGenerator;

public class AlienFactory {

	private final static String UFO_IMAGE = "src/images/ufo.png";
	private final static String SMALL_ALIEN_IMAGE = "src/images/small_alien.png";
	private final static String MEDIUM_ALIEN_IMAGE = "src/images/medium_alien.png";
	private final static String LARGE_ALIEN_IMAGE = "src/images/large_alien.png";

	public static Alien createSmall(int x, int y, double difficultyMultiplier) {
		return new Alien(x, y, new Velocity(1 * difficultyMultiplier, RandomGenerator.getRandomXDirection()), new Velocity(2 * difficultyMultiplier, Direction.SOUTH), 50, new Velocity(4 * difficultyMultiplier, Direction.SOUTH), 30, SMALL_ALIEN_IMAGE);
	}

	public static Alien createMedium(int x, int y, double difficultyMultiplier) {
		return new Alien(x, y, new Velocity(1.5 * difficultyMultiplier, RandomGenerator.getRandomXDirection()), new Velocity(1.5 * difficultyMultiplier, Direction.SOUTH), 80, new Velocity(3 * difficultyMultiplier, Direction.SOUTH), 20, MEDIUM_ALIEN_IMAGE);
	}

	public static Alien createLarge(int x, int y, double difficultyMultiplier) {
		return new Alien(x, y, new Velocity(1 * difficultyMultiplier, RandomGenerator.getRandomXDirection()), new Velocity(1 * difficultyMultiplier, Direction.SOUTH), 120, new Velocity(2 * difficultyMultiplier, Direction.SOUTH), 10, LARGE_ALIEN_IMAGE);
	}

	public static Alien createUFO(int x, int y, int randomReward) {
		return new Alien(x, y, new Velocity(1, RandomGenerator.getRandomXDirection()), new Velocity(1, Direction.EAST), 120, new Velocity(4, Direction.SOUTH), randomReward, UFO_IMAGE);
	}

}
