package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.models.GamePlayer;

public class GameSession implements GlobalConfiguration {


	private final GameFrame gameFrame;
	private final GamePlayer gamePlayer;
	private GameEnvironment gameEnvironment;
	private Thread gameThread;


	public GameSession(final GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
		this.gameFrame = new GameFrame(this);
	}

	public void start() {
		this.gameFrame.setVisible(true);
	}

	public String getPlayerName() {
		return gamePlayer.getName();
	}


	public void playGame() {
		this.gameEnvironment = new GameEnvironment(gamePlayer);
		this.gameThread = new Thread(gameEnvironment);
		gameFrame.setSize(frameWidth, frameHeight);
		gameFrame.setView(gameEnvironment.getGameView());
		gameThread.start();
	}


}
