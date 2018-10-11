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

	public void renderGameOver(String message){
		Graphics g = this.getGraphics();

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
		g.drawString(message, (frameWidth - metr.stringWidth(message)) / 2, frameWidth / 2);
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
