package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.components.views.GameView;
import com.austral.spaceinvaders.models.Level;
import com.austral.spaceinvaders.models.gameobjects.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class GameEnvironment implements GlobalConfiguration, Runnable {


	private final GameEngine gameEngine;
	private final GameRemoteAdapter gameRemoteAdapter;
	private final GameView gameView;
	private final GameSession gameSession;
	private boolean inGame;
	private Level currentLevel;
	private String gameOverMessage;


	public GameEnvironment(final GameSession gameSession) {
		this.gameSession = gameSession;
		this.gameEngine = new GameEngine(this);
		this.gameRemoteAdapter = new GameRemoteAdapter(this);
		this.gameView = new GameView(this);
		this.currentLevel = Level.FIRST;
	}

	public Rectangle getGameViewRectangle() {
		return new Rectangle(0, 0, gameView.getWidth(), gameView.getHeight());
	}

	public GameView getGameView() {
		return gameView;
	}

	@Override
	public void run() {
		inGame = true;
		gameView.addKeyListener(gameRemoteAdapter);
		gameView.requestFocusInWindow();

		long renderStartTime;
		while (inGame) {
			renderStartTime = System.currentTimeMillis();

			gameEngine.executeNextAnimationCycle();
			gameView.repaint();

			try {
				Thread.sleep(getGameTickWithLagCompensation(renderStartTime));
			} catch (InterruptedException e) {
				System.out.println("Game loop interrupted.");
			}
		}
	}

	private void quit() {
		inGame = false;
		gameSession.quitGame();
	}

	public void victory() {
		switch (currentLevel) {
			case FIRST:
				gameEngine.initiateLevel(Level.SECOND);
				break;
			case SECOND:
				gameEngine.initiateLevel(Level.THIRD);
				break;
			case THIRD:
				gameEngine.initiateLevel(Level.FOURTH);
				break;
			case FOURTH:
				gameEngine.initiateLevel(Level.FIFTH);
				break;
			case FIFTH:
				inGame = false;
				gameOverMessage = "Victory";
				//TODO: saving
				break;

		}
	}

	public void defeat() {
		inGame = false;
		gameOverMessage = "Defeat";
		System.out.println(gameOverMessage);
		//TODO: post death mechanics (saving)
	}

	public void invasion() {
		inGame = false;
		gameOverMessage = "Invasion";
		System.out.println(gameOverMessage);
		//TODO: post death mechanics (saving)
	}

	private long getGameTickWithLagCompensation(long renderStartTime) {
		long renderLagDuration = System.currentTimeMillis() - renderStartTime;
		return gameTickDuration - renderLagDuration;
	}

	public void notifyKeyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				gameEngine.notifyLeftArrowPressed();
				break;
			case KeyEvent.VK_RIGHT:
				gameEngine.notifyRightArrowPressed();
				break;
			case KeyEvent.VK_SPACE:
				gameEngine.notifySpaceBarPressed();
				break;
			case KeyEvent.VK_ESCAPE:
				quit();
				break;
		}
	}

	public void notifyKeyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
				gameEngine.notifyArrowUnPressed();
				break;
		}
	}

	public String getActiveGameModifierName() {
		return gameEngine.getActiveGameModifierName();
	}

	public int getPlayerHealth() {
		return gameEngine.getPlayer().getHealth();
	}

	public int getCurrentPoints() {
		return gameSession.getCurrentPoints();
	}

	public void awardPoints(int points) {
		gameSession.awardPoints(points);
	}

	public boolean inGame() {
		return inGame;
	}

	public String getGameOverMessage() {
		return gameOverMessage;
	}

	public List<GameObject> getGameObjects() {
		return gameEngine.getGameObjects();
	}

}
