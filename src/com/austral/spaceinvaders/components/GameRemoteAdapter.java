package com.austral.spaceinvaders.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameRemoteAdapter extends KeyAdapter {

	private final GameSession gameSession;

	public GameRemoteAdapter(GameSession gameSession) {
		this.gameSession = gameSession;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		gameSession.notifyKeyPressed(e);
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		gameSession.notifyKeyReleased(e);
	}
}
