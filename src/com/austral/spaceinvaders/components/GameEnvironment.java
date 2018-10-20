package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.components.views.GameView;
import com.austral.spaceinvaders.models.Level;
import com.austral.spaceinvaders.models.gameobjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

public class GameEnvironment implements GlobalConfiguration, Runnable {

	private final GameEngine gameEngine;
	private final GameRemoteAdapter gameRemoteAdapter;
	private final GameView gameView;
	private final GameSession gameSession;
	private boolean inGame;
	private Level currentLevel;
	private String gameOverMessage;
	private boolean pause;

	public GameEnvironment(final GameSession gameSession) {
		this.gameSession = gameSession;
		this.gameEngine = new GameEngine(this);
		this.gameRemoteAdapter = new GameRemoteAdapter(this);
		this.gameView = new GameView(this);
		this.currentLevel = Level.FIRST;
	}

	@Override
	public void run() {
		inGame = true;
		gameView.addKeyListener(gameRemoteAdapter);
		gameView.requestFocusInWindow();

		long renderStartTime;
		while (inGame) {
			renderStartTime = System.currentTimeMillis();

			if (!pause) {
				gameEngine.executeNextAnimationCycle();
				gameView.repaint();
			}

			try {
				long durationToSleep = getGameTickWithLagCompensation(renderStartTime);
				if (durationToSleep > 0) {
					Thread.sleep(durationToSleep);
				}
			} catch (InterruptedException e) {
				System.out.println("Game loop interrupted.");
			}
		}
	}

	private long getGameTickWithLagCompensation(long renderStartTime) {
		long renderLagDuration = System.currentTimeMillis() - renderStartTime;
		return gameTickDuration - renderLagDuration;
	}

	private void quit() {
		inGame = false;
		gameSession.quitGame();
	}


	public void victory() {
		switch (currentLevel) {
			case FIRST:
				currentLevel = Level.SECOND;
				break;
			case SECOND:
				currentLevel = Level.THIRD;
				break;
			case THIRD:
				currentLevel = Level.FOURTH;
				break;
			case FOURTH:
				currentLevel = Level.FIFTH;
				break;
			case FIFTH:
				if (inGame) {
					inGame = false;
					gameOverMessage = "Victory";
					gameSession.closeGame();
				}
				return;
		}
		gameEngine.initiateLevel(currentLevel);
	}

	public void defeat() {
		if (inGame) {
			inGame = false;
			gameOverMessage = "Defeat";
			System.out.println(gameOverMessage);
			gameSession.closeGame();
		}
	}

	public void invasion() {
		if (inGame) {
			inGame = false;
			gameOverMessage = "Invasion";
			System.out.println(gameOverMessage);
			gameSession.closeGame();
		}
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
				pause = true;
				if (JOptionPane.showConfirmDialog(gameView, "Abandonar?", null, YES_NO_OPTION) == YES_OPTION) {
					quit();
				}
				pause = false;
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

	public boolean isRectangleOnScreen(Rectangle rectangle) {
		return gameView.getViewRectangle().contains(rectangle);
	}

	public JPanel getView() {
		return gameView;
	}

}
