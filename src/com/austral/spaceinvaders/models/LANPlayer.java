package com.austral.spaceinvaders.models;

public class LANPlayer extends GamePlayer {

	private final String playerAuthToken;

	public LANPlayer(final String playerAuthToken, final String name) {
		super(name);
		this.playerAuthToken = playerAuthToken;
	}

	public String getPlayerAuthToken() {
		return playerAuthToken;
	}
}
