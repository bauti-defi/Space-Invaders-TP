package com.austral.spaceinvaders.game.core.session;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.game.core.GameEnvironment;
import com.austral.spaceinvaders.game.core.GameFrame;
import com.austral.spaceinvaders.models.GamePlayer;
import com.austral.spaceinvaders.models.PlayerHiscore;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

public abstract class GameSession implements GlobalConfiguration {

	protected final GamePlayer gamePlayer;
	protected final GameFrame gameFrame;
	private final File hiscoresFile;
	private GameEnvironment gameEnvironment;
	private Thread gameThread;
	private GameState currentGameState = GameState.MAIN_MENU;

	public GameSession(GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
		this.hiscoresFile = new File(leadboardFilePath);
		this.gameFrame = new GameFrame(this, gamePlayer.getName());
	}

	private void shutdownGame() {
		if (gameThread != null) {
			gameEnvironment.pause();
			gameThread.interrupt();
			gameThread = null;
		}
	}

	public void victorious() {
		gameFrame.showVictory();
		closeGame();
		currentGameState = GameState.END_GAME;
	}

	public void defeated() {
		gameFrame.showDefeat();
		closeGame();
		currentGameState = GameState.END_GAME;
	}

	public void invaded() {
		gameFrame.showInvasion();
		closeGame();
		currentGameState = GameState.END_GAME;
	}

	public void quit() {
		shutdownGame();
		gameFrame.showMainMenu();
		currentGameState = GameState.MAIN_MENU;
	}

	public void playGame() {
		this.gamePlayer.resetPoints();
		this.gameEnvironment = new GameEnvironment(this);
		this.gameThread = new Thread(gameEnvironment);
		gameFrame.showGameView(gameEnvironment.getView());
		gameThread.start();
		currentGameState = GameState.PLAYING;
	}

	private void closeGame() {
		shutdownGame();
		savePlayerHiscore(gamePlayer.getHiscore());
	}

	private void savePlayerHiscore(PlayerHiscore playerHiscore) {
		try (FileWriter writer = new FileWriter(hiscoresFile, true)) {
			writer.write(playerHiscore.toString() + System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error writing to hiscores file.");
		}
	}

	public String[] getFormattedHiscores() {
		GameState gameStateSave = currentGameState;
		try {
			currentGameState = GameState.LEADERBOARD;
			return Files.lines(hiscoresFile.toPath()).map(PlayerHiscore::parse)
					.sorted((player1, player2) -> {
						if (player1.getScore() < player2.getScore()) {
							return 1;
						} else if (player1.getScore() > player2.getScore()) {
							return -1;
						}
						return 0;
					}).map(PlayerHiscore::getFormatted).limit(10).toArray(String[]::new);
		} catch (IOException e) {
			e.printStackTrace();
			currentGameState = gameStateSave;
		}
		return new String[0];
	}

	public int getCurrentPoints() {
		return this.gamePlayer.getPoints();
	}

	public void awardPoints(int points) {
		this.gamePlayer.incrementPoints(points);
	}

	public boolean isPlayingGame() {
		return currentGameState == GameState.PLAYING;
	}

	public boolean isViewingLeaderboard() {
		return currentGameState == GameState.LEADERBOARD;
	}

	public boolean isViewingEndGame() {
		return currentGameState == GameState.END_GAME;
	}

	public void notifyKeyPressed(char key) {
		switch (key) {
			case '1':
				if (!isPlayingGame() && !isViewingLeaderboard()) {
					playGame();
				}
			case '4':
			case '2':
				if (!isPlayingGame() && !isViewingLeaderboard()) {
					gameFrame.showLeaderboard();
				}
				break;
			case '3':
			case '6':
				if (isViewingLeaderboard() || isViewingEndGame()) {
					gameFrame.showMainMenu();
					currentGameState = GameState.MAIN_MENU;
				}
				break;
			case '5':
				if (isViewingEndGame()) {
					playGame();
				}
				break;
			case 'a':
			case 'd':
			case 's':
				gameEnvironment.notifyKeyPressed(key);
				break;
			case 'e':
				if (isPlayingGame()) {
					quit();
				}
				break;
			case 'q':
				if (isPlayingGame()) {
					gameEnvironment.pause();
					if (JOptionPane.showConfirmDialog(gameEnvironment.getView(), "Abandonar?", null, YES_NO_OPTION) == YES_OPTION) {
						quit();
					} else {
						gameEnvironment.resume();
					}
				}
				break;
		}
	}

	public void notifyKeyReleased(char key) {
		gameEnvironment.notifyKeyReleased(key);
	}


}
