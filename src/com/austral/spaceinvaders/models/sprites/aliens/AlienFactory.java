package com.austral.spaceinvaders.models.sprites.aliens;

import com.austral.spaceinvaders.physics.Direction;
import com.austral.spaceinvaders.physics.Velocity;

public class AlienFactory {

	private final static String UFO_IMAGE = "src/images/alien.png";
	private final static String SMALL_ALIEN_IMAGE = "src/images/small_alien.png";
	private final static String MEDIUM_ALIEN_IMAGE = "src/images/medium_alien.png";
	private final static String LARGE_ALIEN_IMAGE = "src/images/large_alien.png";

	public static Alien createSmall(int x, int y) {
		return new Alien(x, y, new Velocity(1, Direction.WEST), new Velocity(1, Direction.SOUTH), 50, new Velocity(6, Direction.SOUTH), 30, SMALL_ALIEN_IMAGE);
	}

	public static Alien createMedium(int x, int y) {
		return new Alien(x, y, new Velocity(3, Direction.SOUTH), new Velocity(3, Direction.EAST), 80, new Velocity(5, Direction.SOUTH), 20, MEDIUM_ALIEN_IMAGE);
	}

	public static Alien createLarge(int x, int y) {
		return new Alien(x, y, new Velocity(2, Direction.SOUTH), new Velocity(2, Direction.EAST), 120, new Velocity(4, Direction.SOUTH), 10, LARGE_ALIEN_IMAGE);
	}

	public static Alien createUFO(int x, int y, int randomReward) {
		return new Alien(x, y, new Velocity(2, Direction.SOUTH), new Velocity(2, Direction.EAST), 120, new Velocity(4, Direction.SOUTH), randomReward, UFO_IMAGE);
	}

}
