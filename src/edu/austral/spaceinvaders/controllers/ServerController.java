package edu.austral.spaceinvaders.controllers;

import edu.austral.spaceinvaders.GlobalConfiguration;
import edu.austral.spaceinvaders.game.core.session.GameSession;
import edu.austral.spaceinvaders.game.core.session.LANGameSession;
import edu.austral.spaceinvaders.game.core.session.LocalGameSession;
import edu.austral.spaceinvaders.input.GameClientRemoteAdapter;
import edu.austral.spaceinvaders.models.LANPlayer;
import edu.austral.spaceinvaders.models.LocalPlayer;
import edu.austral.spaceinvaders.util.RandomGenerator;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("ValidExternallyBoundObject")
@WebService(endpointInterface = "edu.austral.spaceinvaders.controllers.ServerSocketAdapter")
public class ServerController implements GlobalConfiguration, ServerSocketAdapter {

	private final HashMap<Integer, GameSession> lanGameSessions = new HashMap<>();
	private final LocalPlayer localPlayer;
	private LocalGameSession localGameSession;

	public ServerController(LocalPlayer localPlayer) {
		this.localPlayer = localPlayer;
		final File hiscoresFile = new File(leadboardFilePath);
		if (!hiscoresFile.exists()) {
			try {
				hiscoresFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error creating hiscores file.");
			}
		}
		this.localGameSession = new LocalGameSession(localPlayer);
		Endpoint.publish("http://localhost:7779/ws/server", this);
	}

	@Override
	public int handshake(final int playerAuthToken, final String playerName) {
		final LANPlayer lanPlayer = new LANPlayer(playerAuthToken, playerName);
		final int sessionId = RandomGenerator.getRandom(1000000);
		final LANGameSession newLANGameSession = new LANGameSession(lanPlayer);

		this.lanGameSessions.put(sessionId, newLANGameSession);
		Endpoint.publish("http://localhost:7779/ws/" + sessionId, new GameClientRemoteAdapter(newLANGameSession));
		return lanGameSessions.containsKey(sessionId) ? sessionId : -1;
	}
}
