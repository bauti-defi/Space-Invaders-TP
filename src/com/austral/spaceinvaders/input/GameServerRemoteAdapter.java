package com.austral.spaceinvaders.input;

import com.austral.spaceinvaders.game.core.session.LocalGameSession;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameServerRemoteAdapter extends KeyAdapter {

	private final LocalGameSession gameSession;

	public GameServerRemoteAdapter(LocalGameSession gameSession) {
		this.gameSession = gameSession;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				gameSession.notifyKeyPressed('a');
				break;
			case KeyEvent.VK_RIGHT:
				gameSession.notifyKeyPressed('d');
				break;
			case KeyEvent.VK_SPACE:
				gameSession.notifyKeyPressed('s');
				break;
			case KeyEvent.VK_ESCAPE:
				gameSession.notifyKeyPressed('q');
				break;
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gameSession.notifyKeyReleased('a');
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameSession.notifyKeyReleased('d');
		}
	}

}
