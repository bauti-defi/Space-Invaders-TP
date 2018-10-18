package com.austral.spaceinvaders.models;

public abstract class GamePlayer {
	private final String name;
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
