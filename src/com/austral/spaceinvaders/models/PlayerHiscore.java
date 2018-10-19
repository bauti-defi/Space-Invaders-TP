package com.austral.spaceinvaders.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlayerHiscore {

	private final static String SEPERATOR = "::";
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
		return name + SEPERATOR + score + SEPERATOR + date;
	}

	public String getFormatted() {
		StringBuilder builder = new StringBuilder("<html><font color='white'>");
		builder.append(score).append(" - - - - - - ");
		builder.append(name).append(" - - - - - - ");
		builder.append(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		builder.append("</font></html>");
		return builder.toString();
	}

	public static PlayerHiscore parse(String line) {
		final String[] parts = line.split(SEPERATOR);
		return new PlayerHiscore(parts[0], Integer.parseInt(parts[1]), LocalDateTime.parse(parts[2]));
	}
}
