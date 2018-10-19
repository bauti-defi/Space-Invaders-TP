package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.components.views.GameLeaderboardView;
import com.austral.spaceinvaders.components.views.GameMenuView;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements GlobalConfiguration {

	private final GameSession gameSession;
	private final GameMenuView gameMenuView;
	private GameLeaderboardView leaderboardView;

	public GameFrame(GameSession gameSession) {
		super("Space Invaders - " + gameSession.getPlayerName());
		this.gameSession = gameSession;
		this.gameMenuView = new GameMenuView(this);
		this.getContentPane().add(gameMenuView, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(frameWidth, frameHeight);
		setResizable(false);
	}

	public <T extends JPanel> void setView(T view) {
		this.getContentPane().removeAll();
		this.getContentPane().add(view, BorderLayout.CENTER);
		revalidate();
		repaint();
		setLocationRelativeTo(null);
	}

	public void play() {
		gameSession.playGame();
	}

	public void showMainMenu() {
		setView(gameMenuView);
	}

	public void showLeaderboard() {
		this.leaderboardView = new GameLeaderboardView(this, gameSession.getHiscores());
		setView(leaderboardView);
	}
}
