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
	private JPanel currentView;

	public GameFrame(GameSession gameSession) {
		super("Space Invaders - " + gameSession.getPlayerName());
		this.gameSession = gameSession;
		currentView = this.gameMenuView = new GameMenuView(this);
		add(gameMenuView, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(frameWidth, frameHeight);
		setResizable(false);
	}

	public <T extends JPanel> void setView(T view) {
		if (currentView != null) {
			remove(currentView);
		}
		add(view, BorderLayout.CENTER);
		currentView = view;
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
