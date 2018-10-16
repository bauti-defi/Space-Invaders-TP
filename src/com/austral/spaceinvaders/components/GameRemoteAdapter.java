package com.austral.spaceinvaders.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameRemoteAdapter extends KeyAdapter {

	private final GameEnvironment gameEnvironment;

	public GameRemoteAdapter(GameEnvironment gameEnvironment) {
		this.gameEnvironment = gameEnvironment;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		gameEnvironment.notifyKeyPressed(e);
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		gameEnvironment.notifyKeyReleased(e);
	}
}
