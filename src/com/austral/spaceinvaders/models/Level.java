package com.austral.spaceinvaders.models;

public enum Level {

	FIRST(4, 3, 2, 1),
	SECOND(3, 3, 3, 2),
	THIRD(2, 3, 4, 3),
	FOURTH(1, 3, 5, 4),
	FIFTH(0, 3, 6, 5);

	private int initialShieldCount;
	private int initialLiveCount;
	private int alienCount;
	private int alienDifficultyMultiplier;

	Level(final int initialShieldCount, final int initialLiveCount, final int alienCount, final int alienDifficultyMultiplier) {
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

	public int getAlienDifficultyMultiplier() {
		return alienDifficultyMultiplier;
	}
}
