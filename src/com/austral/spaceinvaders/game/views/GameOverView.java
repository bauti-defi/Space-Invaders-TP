package com.austral.spaceinvaders.game.views;

import com.austral.spaceinvaders.game.core.GameFrame;

import javax.swing.*;
import java.awt.*;

public class GameOverView extends JPanel {

	private final GameFrame gameFrame;
	private final JButton leaderboard, playAgain, mainMenu;

	private GameOverView(GameFrame gameFrame, String message) {
		this.gameFrame = gameFrame;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		StringBuilder formattedMessage = new StringBuilder("<html><font color='white'>").append(message).append("</font></html>");

		final JLabel messageLabel = new JLabel(formattedMessage.toString(), SwingConstants.CENTER);
		messageLabel.setFont(new Font("Arial", Font.PLAIN, 80));
		add(messageLabel, BorderLayout.CENTER);

		final JPanel buttonPannel = new JPanel();
		buttonPannel.setBackground(Color.BLACK);

		this.leaderboard = new JButton("Leaderboard (4)");
		leaderboard.addActionListener((e) -> gameFrame.showLeaderboard());
		buttonPannel.add(leaderboard, BorderLayout.NORTH);

		this.playAgain = new JButton("Play Again (5)");
		playAgain.addActionListener((e) -> gameFrame.play());
		buttonPannel.add(playAgain, BorderLayout.CENTER);

		this.mainMenu = new JButton("Exit (6)");
		mainMenu.addActionListener((e) -> gameFrame.showMainMenu());
		buttonPannel.add(mainMenu, BorderLayout.SOUTH);

		add(buttonPannel, BorderLayout.SOUTH);
	}

	public static GameOverView createDefeatView(GameFrame gameFrame) {
		return new GameOverView(gameFrame, "Defeat!");
	}

	public static GameOverView createVictoryView(GameFrame gameFrame) {
		return new GameOverView(gameFrame, "Victory!");
	}

	public static GameOverView createInvasionView(GameFrame gameFrame) {
		return new GameOverView(gameFrame, "Invasion!");
	}
}
