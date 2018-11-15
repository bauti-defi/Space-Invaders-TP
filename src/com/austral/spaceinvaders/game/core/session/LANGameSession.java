package com.austral.spaceinvaders.game.core.session;

import com.austral.spaceinvaders.input.GameClientRemoteAdapter;
import com.austral.spaceinvaders.models.LANPlayer;

import javax.xml.ws.Endpoint;

public class LANGameSession extends GameSession {

	public LANGameSession(final LANPlayer lanPlayer) {
		super(lanPlayer);
		Endpoint.publish("http://localhost:7779/tp/" + lanPlayer.getId(), new GameClientRemoteAdapter(this));
	}

	public boolean authenticate(String playerAuthToken) {
		return ((LANPlayer) gamePlayer).getPlayerAuthToken().equals(playerAuthToken);
	}
}
