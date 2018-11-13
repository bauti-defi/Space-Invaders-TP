package com.austral.spaceinvaders.models;

import com.austral.spaceinvaders.util.RandomGenerator;

public abstract class GamePlayer {

	private final String name;
	private final int id = RandomGenerator.getRandom(1000000);
	private int points;

	public GamePlayer(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public int getId() {
		return id;
	}

	public void incrementPoints(final int points) {
		this.points += points;
	}

	public void resetPoints() {
		this.points = 0;
	}

	public PlayerHiscore getHiscore() {
		return new PlayerHiscore(name, points);
	}

}
