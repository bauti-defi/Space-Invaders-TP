package com.austral.spaceinvaders.input;

import com.austral.spaceinvaders.game.core.session.LANGameSession;

//@WebService(endpointInterface = "com.austral.spaceinvaders.input.GameClientSocketAdapter")
public class GameClientRemoteAdapter implements GameClientSocketAdapter {

	private final LANGameSession gameSession;

	public GameClientRemoteAdapter(final LANGameSession gameSession) {
		this.gameSession = gameSession;
	}

	@Override
	public void keyPressReceived(final String playerAuthToken, final char key) {
		if (gameSession.authenticate(playerAuthToken)) {
			gameSession.notifyKeyPressed(key);
		}
	}

	@Override
	public void keyReleaseReceived(final String playerAuthToken, final char key) {
		if (gameSession.authenticate(playerAuthToken)) {
			gameSession.notifyKeyReleased(key);
		}
	}
}
