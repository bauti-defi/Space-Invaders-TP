package com.austral.spaceinvaders.util;

import java.util.Random;

public class RandomGenerator {

	private final static Random generator = new Random();

	public static int getRandomIntBetween(int lowerBound, int upperBound) {
		return generator.nextInt(upperBound - lowerBound) + lowerBound;
	}
}
