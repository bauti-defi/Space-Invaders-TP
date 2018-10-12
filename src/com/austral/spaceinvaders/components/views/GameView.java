package com.austral.spaceinvaders.components.views;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.components.GameSession;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GlobalConfiguration {

	private final GameSession gameSession;

	public GameView(GameSession gameSession) {
		this.gameSession = gameSession;
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
	}

	private void renderGround(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(0, groundY, frameWidth, groundY);
	}

	private void renderScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Puntos: " + gameSession.getCurrentPoints(), 10, 15);
	}

	private void renderVitals(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Vidas: " + gameSession.getPlayerHealth(), frameWidth - 60, 15);
	}

	private void renderGameModifier(Graphics g) {
		final String gameModifierName = gameSession.getActiveGameModifierName();
		if (gameModifierName.length() > 0) {
			g.setColor(Color.RED);
			g.drawString(gameModifierName, (frameWidth / 2) - gameModifierName.length(), 50);
		}
	}

	private void renderGameOver(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, frameWidth, frameHeight);

		g.setColor(new Color(0, 32, 48));
		g.fillRect(50, frameWidth / 2 - 30, frameWidth - 100, 50);
		g.setColor(Color.white);
		g.drawRect(50, frameWidth / 2 - 30, frameWidth - 100, 50);

		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(gameSession.getGameOverMessage(), (frameWidth - metr.stringWidth(gameSession.getGameOverMessage())) / 2, frameWidth / 2);
	}

	public void renderSprites(Graphics g) {
		gameSession.getGameObjects().forEach(sprite -> sprite.render(g));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (gameSession.inGame()) {
			renderScore(g);
			renderVitals(g);
			renderGround(g);
			renderGameModifier(g);
			renderSprites(g);
		} else {
			renderGameOver(g);
		}
	}

}
