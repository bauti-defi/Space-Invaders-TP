package edu.austral.spaceinvaders.input;

import edu.austral.spaceinvaders.game.core.session.LANGameSession;

import javax.jws.WebService;

@SuppressWarnings("ValidExternallyBoundObject")
@WebService(endpointInterface = "edu.austral.spaceinvaders.input.GameClientSocketAdapter")
public class GameClientRemoteAdapter implements GameClientSocketAdapter {

	private final LANGameSession gameSession;

	public GameClientRemoteAdapter(final LANGameSession gameSession) {
		this.gameSession = gameSession;
	}

	@Override
	public void keyPressReceived(final int playerAuthToken, final char key) {
		if (gameSession.authenticate(playerAuthToken)) {
			gameSession.notifyKeyPressed(key);
		}
	}

	@Override
	public void keyReleaseReceived(final int playerAuthToken, final char key) {
		if (gameSession.authenticate(playerAuthToken)) {
			gameSession.notifyKeyReleased(key);
		}
	}
}
