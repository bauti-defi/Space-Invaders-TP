package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GameController;
import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.components.views.GameLeaderboardView;
import com.austral.spaceinvaders.components.views.GameMenuView;
import com.austral.spaceinvaders.models.LocalPlayer;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements GlobalConfiguration {

	private final LocalPlayer localPlayer;
	private final GameController gameController;
	private final GameMenuView gameMenuView;
	private JPanel currentView;
	private GameSession gameSession;
	private Thread gameThread;

	public GameFrame(GameController gameController, LocalPlayer localPlayer) {
		super("Space Invaders - " + localPlayer.getName());
		this.gameController = gameController;
		this.localPlayer = localPlayer;
		currentView = this.gameMenuView = new GameMenuView(this);
		add(gameMenuView, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(150, 150);
		setResizable(false);
		setVisible(true);
	}

	private <T extends JPanel> void setView(T view) {
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
		setSize(frameWidth, frameHeight);
		this.gameSession = new GameSession(localPlayer);
		setView(gameSession.getGameView());
		if (gameThread != null && gameThread.isAlive()) {
			gameThread.interrupt();
		}
		gameThread = new Thread(gameSession);
		gameThread.start();
	}

	public void showMainMenu() {
		if (gameThread != null && gameThread.isAlive()) {
			gameSession.quit();
			gameThread.interrupt();
		}
		setSize(150, 150);
		setView(gameMenuView);
	}


	public void showLeaderboard() {
		setSize(150, 400);
		setView(new GameLeaderboardView(this, gameController.getPlayerHistory()));
	}
}
