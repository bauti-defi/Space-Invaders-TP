package com.austral.spaceinvaders.components.views;

import com.austral.spaceinvaders.components.GameFrame;

import javax.swing.*;
import java.awt.*;

public class GameMenuView extends JPanel {

	private final JButton play, leaderboard;

	public GameMenuView(GameFrame gameFrame) {
		setLayout(new BorderLayout());

		this.play = new JButton("Jugar");
		this.play.addActionListener((e) -> {
			gameFrame.play();
		});
		add(play, BorderLayout.PAGE_START);

		JLabel titleLabel = new JLabel("SPACE INVADERS");
		add(titleLabel, BorderLayout.CENTER);

		this.leaderboard = new JButton("Leaderboard");
		this.leaderboard.addActionListener((e) -> {
			gameFrame.showLeaderboard();
		});
		add(leaderboard, BorderLayout.SOUTH);
	}

}
