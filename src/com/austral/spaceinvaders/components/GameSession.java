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

	public void quitGame() {
		if (gameThread.isAlive()) {
			gameThread.interrupt();
		}
		gameFrame.showMainMenu();
	}

	public int getCurrentPoints() {
		return gamePlayer.getPoints();
	}

	public void awardPoints(int points) {
		gamePlayer.incrementPoints(points);
	}

	public void playGame() {
		this.gameEnvironment = new GameEnvironment(this);
		this.gameThread = new Thread(gameEnvironment);
		gameFrame.setSize(frameWidth, frameHeight);
		gameFrame.setView(gameEnvironment.getGameView());
		gameThread.start();
	}


}
