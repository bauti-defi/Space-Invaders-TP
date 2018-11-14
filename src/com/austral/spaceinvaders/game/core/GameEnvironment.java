package com.austral.spaceinvaders.game.core;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.game.core.session.GameSession;
import com.austral.spaceinvaders.game.views.GameView;
import com.austral.spaceinvaders.models.Level;
import com.austral.spaceinvaders.models.gameobjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameEnvironment implements GlobalConfiguration, Runnable {

	private final GameEngine gameEngine;
	private final GameView gameView;
	private final GameSession gameSession;
	private boolean inGame;
	private Level currentLevel;
	private boolean pause;

	public GameEnvironment(final GameSession gameSession) {
		this.gameSession = gameSession;
		this.gameEngine = new GameEngine(this);
		this.gameView = new GameView(this);
		this.currentLevel = Level.FIRST;
	}

	@Override
	public void run() {
		inGame = true;

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
					gameSession.victorious();
				}
				return;
		}
		gameEngine.initiateLevel(currentLevel);
	}

	public void defeat() {
		if (inGame) {
			inGame = false;
			gameSession.defeated();
		}
	}

	public void invasion() {
		if (inGame) {
			inGame = false;
			gameSession.invaded();
		}
	}

	public void pause() {
		this.pause = true;
	}

	public void resume() {
		this.pause = false;
	}


	public void notifyKeyPressed(char key) {
		switch (key) {
			case 'a':
				gameEngine.notifyLeftArrowPressed();
				break;
			case 'd':
				gameEngine.notifyRightArrowPressed();
				break;
			case 's':
				gameEngine.notifySpaceBarPressed();
				break;
		}
	}

	public void notifyKeyReleased(char key) {
		switch (key) {
			case 'a':
			case 'd':
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
