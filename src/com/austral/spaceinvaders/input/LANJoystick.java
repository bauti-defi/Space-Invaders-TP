package com.austral.spaceinvaders.input;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.models.LANPlayer;

import javax.swing.*;
import java.awt.*;

public class LANJoystick extends JFrame implements GlobalConfiguration {

	private final LANPlayer lanPlayer;

	public LANJoystick(final LANPlayer lanPlayer) {
		super("Space Invaders Joystick - " + lanPlayer.getName());
		this.lanPlayer = lanPlayer;
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

		//make end point connection
	}

	public void sendKeyPressed(char key) {
		System.out.println(" send " + key);
	}

	public void sendKeyReleased(char key) {

	}

}
