package com.austral.spaceinvaders.controllers;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.models.GamePlayer;

import java.io.File;
import java.io.IOException;

public abstract class GameController implements GlobalConfiguration {

	protected final GamePlayer gamePlayer;

	public GameController(GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
		final File hiscoresFile = new File(leadboardFilePath);
		if (!hiscoresFile.exists()) {
			try {
				hiscoresFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error creating hiscores file.");
			}
		}
	}

	public abstract void startGame();
}
