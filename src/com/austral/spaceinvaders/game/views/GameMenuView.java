package com.austral.spaceinvaders.game.views;

import com.austral.spaceinvaders.game.core.GameFrame;

import javax.swing.*;
import java.awt.*;

public class GameMenuView extends JPanel {

	private final JButton play, leaderboard;

	public GameMenuView(GameFrame gameFrame) {
		setLayout(null);
		setBackground(Color.BLACK);

		this.play = new JButton("Jugar (1)");
		this.play.addActionListener((e) -> {
			gameFrame.play();
		});
		play.setBounds(45, 100, 300, 100);
		play.setBackground(Color.ORANGE);
		play.setFont(new Font("Arial", Font.BOLD, 30));
		add(play);

		JLabel titleLabel = new JLabel("SPACE INVADERS");
		titleLabel.setBounds(60, 250, 300, 100);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		add(titleLabel);

		this.leaderboard = new JButton("Leaderboard (2)");
		this.leaderboard.addActionListener((e) -> {
			gameFrame.showLeaderboard();
		});
		leaderboard.setBounds(45, 400, 300, 100);
		leaderboard.setBackground(Color.ORANGE);
		leaderboard.setFont(new Font("Arial", Font.BOLD, 30));
		add(leaderboard);
	}

}
