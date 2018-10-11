package com.austral.spaceinvaders.util;

import com.austral.spaceinvaders.physics.Direction;

import java.util.Random;

public class RandomGenerator {

	private final static Random generator = new Random();

	public static int getRandomIntBetween(int lowerBound, int upperBound) {
		return generator.nextInt(upperBound - lowerBound) + lowerBound;
	}

	public static Direction getRandomXDirection() {
		switch (generator.nextInt(2)) {
			case 1:
				return Direction.EAST;
			default:
				return Direction.WEST;
		}
	}
}
