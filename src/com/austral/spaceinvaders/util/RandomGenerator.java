package com.austral.spaceinvaders.util;

import com.austral.spaceinvaders.physics.Direction;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomGenerator {

	private final static Random generator = new Random();

	public static int getRandom(int upperbound) {
		return generator.nextInt(upperbound);
	}

	public static int getRandomIntBetween(int lowerBound, int upperBound) {
		return generator.nextInt(upperBound - lowerBound) + lowerBound;
	}

	public static IntStream getRandomIntStream(int streamSize, int lowerBound, int upperbound) {
		return generator.ints(streamSize, lowerBound, upperbound);
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
