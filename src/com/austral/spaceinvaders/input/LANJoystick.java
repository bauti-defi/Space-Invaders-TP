package com.austral.spaceinvaders.input;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.controllers.ServerController;
import com.austral.spaceinvaders.models.LANPlayer;

import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class LANJoystick extends JFrame implements GlobalConfiguration {

	private final String HANDSHAKE_URL = "http://localhost:7779/tp/server";
	private final String BASE_CONNECTION_URL = "http://localhost:7779/tp/";
	private final LANPlayer lanPlayer;
	private GameClientRemoteAdapter remoteAdapter;

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


		try {
			if (handshake()) {
				remoteAdapter = establishConnection();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private final boolean handshake() throws MalformedURLException {
		final URL url = new URL(HANDSHAKE_URL);

		final QName qName = new QName("http://controllers.spaceinvaders.austral.com/", "ServerController");

		final Service service = Service.create(url, qName);
		final ServerController serverController = service.getPort(ServerController.class);

		return serverController.handshake(lanPlayer.getPlayerAuthToken(), lanPlayer.getName());
	}

	private final GameClientRemoteAdapter establishConnection() throws MalformedURLException {
		final URL url = new URL(BASE_CONNECTION_URL + lanPlayer.getId());

		final QName qName = new QName("no se", "GameClientRemoteAdapter");
		final Service service = Service.create(url, qName);

		return service.getPort(GameClientRemoteAdapter.class);
	}

	public void sendKeyPressed(char key) {
		remoteAdapter.keyPressReceived(lanPlayer.getPlayerAuthToken(), key);
	}

	public void sendKeyReleased(char key) {
		remoteAdapter.keyReleaseReceived(lanPlayer.getPlayerAuthToken(), key);
	}

}
