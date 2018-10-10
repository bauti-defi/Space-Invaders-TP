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

	private void renderScoreBoard(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Puntos: " + gameSession.getCurrentPoints(), 0, 15);
	}

	private void renderVitals(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Vidas: " + gameSession.getPlayerHealth(), 5, frameHeight - 30);
		g.drawString("Escudos: " + gameSession.getPlayerShieldCount() / 50, frameWidth - 100, frameHeight - 30);
	}

	private void renderGameModifier(Graphics g) {
		final String powerUpName = gameSession.getActivePowerUpName();
		if (powerUpName.length() > 0) {
			g.setColor(Color.RED);
			g.drawString(powerUpName, (frameWidth / 2) - powerUpName.length(), 50);
		}
	}

	public void renderSprites(Graphics g) {
		gameSession.getSprites().forEach(sprite -> sprite.render(g));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		renderScoreBoard(g);
		renderVitals(g);
		renderGround(g);
		renderGameModifier(g);
		renderSprites(g);
	}

}
