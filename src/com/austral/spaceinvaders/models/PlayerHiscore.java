package com.austral.spaceinvaders.models;

import java.time.LocalDateTime;

public class PlayerHiscore {

	private final String name;
	private final int score;
	private final LocalDateTime date;

	public PlayerHiscore(final String name, final int score) {
		this.name = name;
		this.score = score;
		this.date = LocalDateTime.now();
	}

	private PlayerHiscore(final String name, final int score, final LocalDateTime date) {
		this.name = name;
		this.score = score;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public LocalDateTime getDate() {
		return date;
	}

	@Override
	public String toString() {
		return score + "::" + name + "::" + date;
	}

	public static PlayerHiscore parse(String line) {
		final String[] parts = line.split("::");
		return new PlayerHiscore(parts[1], Integer.parseInt(parts[0]), LocalDateTime.parse(parts[2]));
	}
}
