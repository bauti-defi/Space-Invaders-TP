package edu.austral.spaceinvaders;

import edu.austral.spaceinvaders.controllers.ClientController;
import edu.austral.spaceinvaders.controllers.ServerController;
import edu.austral.spaceinvaders.models.LocalPlayer;

public class SpaceInvaders {

	public static final void main(String... args) {
		final String playerName = args[1];
		if (args.length > 0 && args[0].equalsIgnoreCase("client")) {
			new ClientController(playerName);
		} else {
			new ServerController(new LocalPlayer(playerName));
		}
	}
}
