package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.components.views.GameView;
import com.austral.spaceinvaders.models.GamePlayer;
import com.austral.spaceinvaders.models.Level;
import com.austral.spaceinvaders.models.sprites.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class GameSession implements GlobalConfiguration, Runnable {

	private final GameEnvironment gameEnvironment;
	private final GameRemoteAdapter gameRemoteAdapter;
	private final GameView gameView;
	private final GamePlayer gamePlayer;
	private boolean inGame;


	public GameSession(final GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
		this.gameEnvironment = new GameEnvironment(this);
		this.gameRemoteAdapter = new GameRemoteAdapter(this);
		this.gameView = new GameView(this);
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
		gameEnvironment.initiateLevel(Level.FIRST);

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
		inGame = false;
		System.out.println("Victory");
	}

	public void defeat() {
		inGame = false;
		System.out.println("Defeat");
		//TODO: post death mechanics
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

	public String getActivePowerUpName() {
		return gameEnvironment.getActivePowerUpName();
	}

	public int getPlayerHealth() {
		return gameEnvironment.getPlayer().getHealth();
	}

	public int getPlayerShieldCount() {
		return gameEnvironment.getPlayer().getShield();
	}

	public int getCurrentPoints() {
		return gamePlayer.getPoints();
	}

	public void awardPoints(int points) {
		gamePlayer.incrementPoints(points);
	}

	public List<Sprite> getSprites() {
		return gameEnvironment.getSprites();
	}


}
