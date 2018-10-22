package com.austral.spaceinvaders.models;

public enum Level {

	FIRST(4, 3, 2, 1),
	SECOND(3, 3, 3, 1.2),
	THIRD(2, 3, 4, 1.6),
	FOURTH(1, 3, 5, 2),
	FIFTH(0, 3, 6, 2.5);

	private int initialShieldCount;
	private int initialLiveCount;
	private int alienCount;
	private double alienDifficultyMultiplier;

	Level(final int initialShieldCount, final int initialLiveCount, final int alienCount, final double alienDifficultyMultiplier) {
		this.initialShieldCount = initialShieldCount;
		this.initialLiveCount = initialLiveCount;
		this.alienCount = alienCount;
		this.alienDifficultyMultiplier = alienDifficultyMultiplier;
	}

	public int getInitialShieldCount() {
		return initialShieldCount;
	}

	public int getInitialLiveCount() {
		return initialLiveCount;
	}


	public int getAlienCount() {
		return alienCount;
	}

	public double getAlienDifficultyMultiplier() {
		return alienDifficultyMultiplier;
	}
}
