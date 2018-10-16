package com.austral.spaceinvaders.components.views;

import com.austral.spaceinvaders.components.GameFrame;
import com.austral.spaceinvaders.models.GamePlayer;

import javax.swing.*;
import java.awt.*;

public class GameLeaderboardView extends JPanel {

	private final JList<GamePlayer> leaderboard;
	private final JButton back;


	public GameLeaderboardView(GameFrame gameFrame) {
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel title = new JLabel("Leaderboard");
		add(title, BorderLayout.PAGE_START);

		this.leaderboard = new JList<>();
		JScrollPane scrollPane = new JScrollPane(leaderboard);
		add(scrollPane, BorderLayout.CENTER);

		this.back = new JButton("Volver");
		this.back.addActionListener((e) -> {
			gameFrame.showMainMenu();
		});
		add(back, BorderLayout.PAGE_END);
	}

}
