package com.austral.spaceinvaders.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LANJoystickAdapter extends KeyAdapter {

	private final LANJoystick lanJoystick;

	public LANJoystickAdapter(LANJoystick lanJoystick) {
		this.lanJoystick = lanJoystick;
	}

	@Override
	public void keyPressed(final KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				lanJoystick.sendKeyPressed('a');
				break;
			case KeyEvent.VK_RIGHT:
				lanJoystick.sendKeyPressed('d');
				break;
			case KeyEvent.VK_SPACE:
				lanJoystick.sendKeyPressed('s');
				break;
			case KeyEvent.VK_ESCAPE:
				lanJoystick.sendKeyPressed('q');
				break;
			default:
				lanJoystick.sendKeyPressed(e.getKeyChar());
				break;
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				lanJoystick.sendKeyReleased('a');
				break;
			case KeyEvent.VK_RIGHT:
				lanJoystick.sendKeyReleased('d');
				break;
			default:
				lanJoystick.sendKeyReleased(e.getKeyChar());
				break;
		}
	}
}