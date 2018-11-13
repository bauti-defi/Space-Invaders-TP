package com.austral.spaceinvaders.game.core;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.game.core.session.GameSession;
import com.austral.spaceinvaders.game.views.GameLeaderboardView;
import com.austral.spaceinvaders.game.views.GameMenuView;
import com.austral.spaceinvaders.game.views.GameOverView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements GlobalConfiguration {

	private final GameSession gameSession;
	private GameMenuView gameMenuView;
	private GameLeaderboardView leaderboardView;

	public GameFrame(GameSession gameSession, String playerName) {
		super("Space Invaders - " + playerName);
		this.gameSession = gameSession;
		this.gameMenuView = new GameMenuView(this);
		this.getContentPane().add(gameMenuView, BorderLayout.CENTER);
		setLocation(frameWidth + 200, frameHeight / 4);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(frameWidth, frameHeight);
		setResizable(false);
		setVisible(true);
	}

	@Override
	public synchronized void addKeyListener(final KeyListener adapter) {
		super.addKeyListener(adapter);
		this.requestFocusInWindow();
	}

	private <T extends JPanel> void setView(T view) {
		this.getContentPane().removeAll();
		this.getContentPane().add(view, BorderLayout.CENTER);
		revalidate();
		repaint();
		this.requestFocusInWindow();
	}

	private <T extends JPanel> void setView(T view, int width, int height) {
		setSize(width, height);
		setView(view);
	}

	public void showVictory() {
		setView(GameOverView.createVictoryView(this));
	}

	public void showDefeat() {
		setView(GameOverView.createDefeatView(this));
	}

	public void showInvasion() {
		setView(GameOverView.createInvasionView(this));
	}

	public void play() {
		gameSession.playGame();
	}

	public void showGameView(JPanel gameView) {
		setView(gameView, frameWidth, frameHeight);
		setLocation(frameWidth + 200, frameHeight / 4);
		setLocationRelativeTo(null);
	}

	public void showMainMenu() {
		setView(gameMenuView);
	}

	public void showLeaderboard() {
		this.leaderboardView = new GameLeaderboardView(this, gameSession.getFormattedHiscores());
		setView(leaderboardView);
	}
}
