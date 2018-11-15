package edu.austral.spaceinvaders.models;

public class LANPlayer extends GamePlayer {

	private final int playerAuthToken;

	public LANPlayer(final int playerAuthToken, final String name) {
		super(name);
		this.playerAuthToken = playerAuthToken;
	}

	public int getPlayerAuthToken() {
		return playerAuthToken;
	}
}
