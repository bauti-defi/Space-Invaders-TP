package com.austral.spaceinvaders.controllers;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.game.core.session.GameSession;
import com.austral.spaceinvaders.game.core.session.LANGameSession;
import com.austral.spaceinvaders.game.core.session.LocalGameSession;
import com.austral.spaceinvaders.models.LANPlayer;
import com.austral.spaceinvaders.models.LocalPlayer;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.HashMap;

@SuppressWarnings("ValidExternallyBoundObject")
@WebService(endpointInterface = "com.austral.spaceinvaders.controllers.ServerAdapter")
public class ServerController extends GameController implements GlobalConfiguration, ServerAdapter {

	private final HashMap<Integer, GameSession> lanGameSessions = new HashMap<>();
	private LocalGameSession localGameSession;

	public ServerController(LocalPlayer localPlayer) {
		super(localPlayer);
		startGame();
		Endpoint.publish("http://localhost:7779/tp/server", this);
	}

	@Override
	public void startGame() {
		this.localGameSession = new LocalGameSession((LocalPlayer) gamePlayer);
	}

	@Override
	public boolean handshake(final String playerAuthToken, final String playerName) {
		final LANPlayer lanPlayer = new LANPlayer(playerAuthToken, playerName);
		this.lanGameSessions.put(lanPlayer.getId(), new LANGameSession(lanPlayer));
		return lanGameSessions.containsKey(lanPlayer.getId());
	}

}
