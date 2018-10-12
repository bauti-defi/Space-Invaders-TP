package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.components.views.GameView;
import com.austral.spaceinvaders.models.GamePlayer;
import com.austral.spaceinvaders.models.Level;
import com.austral.spaceinvaders.models.gameobjects.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class GameSession implements GlobalConfiguration, Runnable {

	private final GameEnvironment gameEnvironment;
	private final GameRemoteAdapter gameRemoteAdapter;
	private final GameView gameView;
	private final GamePlayer gamePlayer;
	private boolean inGame;
	private Level currentLevel;
	private String gameOverMessage;


	public GameSession(final GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
		this.gameEnvironment = new GameEnvironment(this);
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

			gameEnvironment.executeNextAnimationCycle();
			gameView.repaint();

			try {
				Thread.sleep(getGameTickWithLagCompensation(renderStartTime));
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}
		}
	}

	public void quit() {
		inGame = false;
	}

	public void victory() {
		switch (currentLevel) {
			case FIRST:
				gameEnvironment.initiateLevel(Level.SECOND);
				break;
			case SECOND:
				gameEnvironment.initiateLevel(Level.THIRD);
				break;
			case THIRD:
				gameEnvironment.initiateLevel(Level.FOURTH);
				break;
			case FOURTH:
				gameEnvironment.initiateLevel(Level.FIFTH);
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
				gameEnvironment.notifyLeftArrowPressed();
				break;
			case KeyEvent.VK_RIGHT:
				gameEnvironment.notifyRightArrowPressed();
				break;
			case KeyEvent.VK_SPACE:
				gameEnvironment.notifySpaceBarPressed();
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
				gameEnvironment.notifyArrowUnPressed();
				break;
		}
	}

	public String getActiveGameModifierName() {
		return gameEnvironment.getActiveGameModifierName();
	}

	public int getPlayerHealth() {
		return gameEnvironment.getPlayer().getHealth();
	}

	public int getCurrentPoints() {
		return gamePlayer.getPoints();
	}

	public void awardPoints(int points) {
		gamePlayer.incrementPoints(points);
	}

	public boolean inGame() {
		return inGame;
	}

	public String getGameOverMessage() {
		return gameOverMessage;
	}

	public List<GameObject> getGameObjects() {
		return gameEnvironment.getGameObjects();
	}


}
