package com.austral.spaceinvaders.controllers;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.models.GamePlayer;

public class ClientController extends GameController implements GlobalConfiguration {


	public ClientController(final GamePlayer gamePlayer) {
		super(gamePlayer);
	}

	public void startGame() {

	}
}
