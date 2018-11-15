package edu.austral.spaceinvaders.game.core.session;

import edu.austral.spaceinvaders.models.LANPlayer;

public class LANGameSession extends GameSession {

	public LANGameSession(final LANPlayer lanPlayer) {
		super(lanPlayer);
	}

	public boolean authenticate(int playerAuthToken) {
		return ((LANPlayer) gamePlayer).getPlayerAuthToken() == playerAuthToken;
	}
}
