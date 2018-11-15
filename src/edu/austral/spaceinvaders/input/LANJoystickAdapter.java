package edu.austral.spaceinvaders.input;

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
				lanJoystick.notifyKeyPressed('a');
				break;
			case KeyEvent.VK_RIGHT:
				lanJoystick.notifyKeyPressed('d');
				break;
			case KeyEvent.VK_SPACE:
				lanJoystick.notifyKeyPressed('s');
				break;
			case KeyEvent.VK_ESCAPE:
			case 'q':
				lanJoystick.notifyKeyPressed('e');
				break;
			default:
				lanJoystick.notifyKeyPressed(e.getKeyChar());
				break;
		}
	}

	@Override
	public void keyReleased(final KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				lanJoystick.notifyKeyReleased('a');
				break;
			case KeyEvent.VK_RIGHT:
				lanJoystick.notifyKeyReleased('d');
				break;
			default:
				lanJoystick.notifyKeyReleased(e.getKeyChar());
				break;
		}
	}
}