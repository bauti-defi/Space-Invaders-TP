package com.austral.spaceinvaders.game.views;

import com.austral.spaceinvaders.game.core.GameFrame;

import javax.swing.*;
import java.awt.*;

public class GameLeaderboardView extends JPanel {

	private final JList<String> leaderboard;
	private final JButton back;


	public GameLeaderboardView(GameFrame gameFrame, String[] playerHiscores) {
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel title = new JLabel("<html><font color='white'>Leaderboard</font></html>", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		add(title, BorderLayout.PAGE_START);

		this.leaderboard = new JList<>(playerHiscores);
		leaderboard.setFont(new Font("Arial", Font.BOLD, 14));
		leaderboard.setBackground(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(leaderboard);
		scrollPane.setBorder(BorderFactory.createDashedBorder(Color.WHITE));
		add(scrollPane, BorderLayout.CENTER);

		this.back = new JButton("Volver (3)");
		back.setFont(new Font("Arial", Font.BOLD, 30));
		back.addActionListener((e) -> {
			gameFrame.showMainMenu();
		});

		add(back, BorderLayout.PAGE_END);
	}

}
