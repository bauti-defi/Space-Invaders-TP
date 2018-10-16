package com.austral.spaceinvaders;

import com.austral.spaceinvaders.components.GameSession;
import com.austral.spaceinvaders.models.LANPlayer;
import com.austral.spaceinvaders.models.LocalPlayer;

import java.util.ArrayList;

public class GameController implements GlobalConfiguration {

	private final ArrayList<GameSession> gameSessions = new ArrayList<GameSession>();

	public void createNewLocalGameSession(String playerName) {
		gameSessions.add(new GameSession(new LocalPlayer(playerName)));
	}

	public void createNewLANGameSession(String playerName) {
		gameSessions.add(new GameSession(new LANPlayer(playerName)));
	}

	public void startGameSessions() {
		gameSessions.forEach(gameSession -> gameSession.start());
	}

}
