package com.austral.spaceinvaders.controllers;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.input.LANJoystick;
import com.austral.spaceinvaders.models.GamePlayer;
import com.austral.spaceinvaders.models.LANPlayer;

public class ClientController extends GameController implements GlobalConfiguration {

	private LANJoystick lanJoystick;

	public ClientController(final GamePlayer gamePlayer) {
		super(gamePlayer);
		startGame();
	}

	public void startGame() {
		this.lanJoystick = new LANJoystick((LANPlayer) gamePlayer);
		lanJoystick.setVisible(true);
	}
}
