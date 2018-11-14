package com.austral.spaceinvaders.input;

import com.austral.spaceinvaders.game.core.session.LocalGameSession;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LocalJoystickAdapter extends KeyAdapter {

	private final LocalGameSession gameSession;

	public LocalJoystickAdapter(LocalGameSession gameSession) {
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
			default:
				gameSession.notifyKeyPressed(e.getKeyChar());
				break;
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				gameSession.notifyKeyReleased('a');
				break;
			case KeyEvent.VK_RIGHT:
				gameSession.notifyKeyReleased('d');
				break;
			default:
				gameSession.notifyKeyReleased(e.getKeyChar());
				break;
		}
	}

}
