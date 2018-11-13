package com.austral.spaceinvaders.game.core.session;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.game.core.GameEnvironment;
import com.austral.spaceinvaders.game.core.GameFrame;
import com.austral.spaceinvaders.models.GamePlayer;
import com.austral.spaceinvaders.models.PlayerHiscore;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public abstract class GameSession implements GlobalConfiguration {

	protected final GamePlayer gamePlayer;
	protected final GameFrame gameFrame;
	private GameEnvironment gameEnvironment;
	private Thread gameThread;

	public GameSession(GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
		this.gameFrame = new GameFrame(this, gamePlayer.getName());
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

	public void playGame() {
		this.gamePlayer.resetPoints();
		this.gameEnvironment = new GameEnvironment(this);
		this.gameThread = new Thread(gameEnvironment);
		gameFrame.showGameView(gameEnvironment.getView());
		gameThread.start();
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
		return this.gamePlayer.getPoints();
	}

	public void awardPoints(int points) {
		this.gamePlayer.incrementPoints(points);
	}

	public void notifyKeyPressed(char key) {
		switch (key) {
			case 'a':
			case 'd':
			case 's':
			case 'e':
			case 'q':
				gameEnvironment.notifyKeyPressed(key);
				break;
		}
	}

	public void notifyKeyReleased(char key) {
		switch (key) {
			case 'a':
			case 'd':
				gameEnvironment.notifyKeyReleased(key);
				break;
		}
	}


}
