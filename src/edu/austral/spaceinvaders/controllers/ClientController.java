package edu.austral.spaceinvaders.controllers;

import edu.austral.spaceinvaders.GlobalConfiguration;
import edu.austral.spaceinvaders.input.GameClientSocketAdapter;
import edu.austral.spaceinvaders.input.LANJoystick;
import edu.austral.spaceinvaders.util.RandomGenerator;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientController implements GlobalConfiguration {

	private final String HANDSHAKE_URL = "http://localhost:7779/ws/server?wsdl";
	private final String BASE_CONNECTION_URL = "http://localhost:7779/ws/";
	private final String name;
	private final int playerAuthToken;
	private int sessionId;
	private GameClientSocketAdapter remoteAdapter;
	private final LANJoystick lanJoystick;

	public ClientController(String name) {
		this.name = name;
		this.playerAuthToken = RandomGenerator.getRandom(1000000); //change

		try {
			if (handshake(name, playerAuthToken)) {
				remoteAdapter = establishConnection();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		this.lanJoystick = new LANJoystick(this, name);
		lanJoystick.setVisible(true);
	}

	private final boolean handshake(String name, int playerAuthToken) throws MalformedURLException {
		final URL url = new URL(HANDSHAKE_URL);

		final QName qName = new QName("http://controllers.spaceinvaders.austral.edu/", "ServerControllerService");

		final Service service = Service.create(url, qName);
		final ServerSocketAdapter serverSocketAdapter = service.getPort(ServerSocketAdapter.class);

		final int idResponse = serverSocketAdapter.handshake(playerAuthToken, name);

		if (idResponse == -1) {
			return false;
		} else {
			this.sessionId = idResponse;
			return true;
		}
	}

	private final GameClientSocketAdapter establishConnection() throws MalformedURLException {
		final URL url = new URL(BASE_CONNECTION_URL + this.sessionId + "?wsdl");

		final QName qName = new QName("http://input.spaceinvaders.austral.edu/", "GameClientRemoteAdapterService");
		final Service service = Service.create(url, qName);

		return service.getPort(GameClientSocketAdapter.class);
	}

	public void sendKeyPressed(char key) {
		remoteAdapter.keyPressReceived(playerAuthToken, key);
	}

	public void sendKeyReleased(char key) {
		remoteAdapter.keyReleaseReceived(playerAuthToken, key);
	}
}
