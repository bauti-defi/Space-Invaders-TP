package com.austral.spaceinvaders;

import com.austral.spaceinvaders.controllers.ClientController;
import com.austral.spaceinvaders.controllers.ServerController;
import com.austral.spaceinvaders.models.LANPlayer;
import com.austral.spaceinvaders.models.LocalPlayer;

public class SpaceInvaders {

	public static final void main(String... args) {
		final String playerName = args[1];
		if (args.length > 0 && args[0].equalsIgnoreCase("client")) {
			new ClientController(new LANPlayer(playerName, playerName));
		} else {
			new ServerController(new LocalPlayer(playerName));
		}
	}
}
