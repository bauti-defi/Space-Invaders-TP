package edu.austral.spaceinvaders.input;

import edu.austral.spaceinvaders.GlobalConfiguration;
import edu.austral.spaceinvaders.controllers.ClientController;

import javax.swing.*;
import java.awt.*;

public class LANJoystick extends JFrame implements GlobalConfiguration {

	private final ClientController clientController;

	public LANJoystick(ClientController clientController, String name) {
		super("Space Invaders Joystick - " + name);
		this.clientController = clientController;
		final JLabel label = new JLabel("<html><font color='white'>JOYSTICK</font></html>");
		label.setFont(new Font("Arial", Font.PLAIN, 40));
		this.getContentPane().add(label, SwingConstants.CENTER);
		setSize(300, 150);
		this.getContentPane().setBackground(Color.BLACK);
		setLocationRelativeTo(null);
		setFocusable(true);
		setResizable(false);
		this.addKeyListener(new LANJoystickAdapter(this));
		this.requestFocusInWindow();
	}


	public void notifyKeyPressed(char key) {
		clientController.sendKeyPressed(key);
	}

	public void notifyKeyReleased(char key) {
		clientController.sendKeyReleased(key);
	}

}
