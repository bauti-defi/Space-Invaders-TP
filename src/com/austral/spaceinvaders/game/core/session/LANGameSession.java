package com.austral.spaceinvaders.game.core.session;

import com.austral.spaceinvaders.models.LANPlayer;

public class LANGameSession extends GameSession {

	public LANGameSession(final LANPlayer lanPlayer) {
		super(lanPlayer);
		//Endpoint.publish('session/playerId', new GameClientRemoteAdapter(this));
	}

	public boolean authenticate(String playerAuthToken) {
		return ((LANPlayer) gamePlayer).getPlayerAuthToken().equals(playerAuthToken);
	}
}
