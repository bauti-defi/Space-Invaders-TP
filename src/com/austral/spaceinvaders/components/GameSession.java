package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.models.GamePlayer;
import com.austral.spaceinvaders.models.PlayerHiscore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class GameSession implements GlobalConfiguration {

	private final GameFrame gameFrame;
	private final GamePlayer gamePlayer;
	private final File hiscoresFile;
	private GameEnvironment gameEnvironment;
	private Thread gameThread;


	public GameSession(final GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
		this.gameFrame = new GameFrame(this);
		this.hiscoresFile = new File(leadboardFilePath);
		if (!hiscoresFile.exists()) {
			try {
				hiscoresFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Error creating hiscores file.");
			}
		}
	}

	public void start() {
		this.gameFrame.setVisible(true);
	}

	public String getPlayerName() {
		return gamePlayer.getName();
	}

	private void shutdownGame() {
		if (gameThread.isAlive()) {
			gameThread.interrupt();
		}
	}

	public void victorious() {
		gameFrame.showVictory();
		closeGame();
	}

	public void defeated() {
		gameFrame.showDefeat();
		closeGame();
	}

	public void invaded() {
		gameFrame.showInvasion();
		closeGame();
	}

	public void quit() {
		shutdownGame();
		gameFrame.showMainMenu();
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
		try {
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
		}
		return new String[0];
	}

	public int getCurrentPoints() {
		return gamePlayer.getPoints();
	}

	public void awardPoints(int points) {
		gamePlayer.incrementPoints(points);
	}

	public void playGame() {
		this.gamePlayer.resetPoints();
		this.gameEnvironment = new GameEnvironment(this);
		this.gameThread = new Thread(gameEnvironment);
		gameFrame.setSize(frameWidth, frameHeight);
		gameFrame.setView(gameEnvironment.getView());
		gameThread.start();
	}

}
