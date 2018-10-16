package com.austral.spaceinvaders.components.views;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.components.GameEnvironment;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GlobalConfiguration {

	private final GameEnvironment gameEnvironment;

	public GameView(GameEnvironment gameEnvironment) {
		this.gameEnvironment = gameEnvironment;
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
	}

	public Rectangle getViewRectangle() {
		return new Rectangle(0, 0, getWidth(), getHeight());
	}

	private void renderGround(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(0, groundY, frameWidth, groundY);
	}

	private void renderScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Puntos: " + gameEnvironment.getCurrentPoints(), 10, 15);
	}

	private void renderVitals(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Vidas: " + gameEnvironment.getPlayerHealth(), frameWidth - 60, 15);
	}

	private void renderGameModifier(Graphics g) {
		final String gameModifierName = gameEnvironment.getActiveGameModifierName();
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
		g.drawString(gameEnvironment.getGameOverMessage(), (frameWidth - metr.stringWidth(gameEnvironment.getGameOverMessage())) / 2, frameWidth / 2);
	}

	public void renderGameObjects(Graphics g) {
		gameEnvironment.getGameObjects().forEach(gameObject -> gameObject.render(g));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (gameEnvironment.inGame()) {
			renderScore(g);
			renderVitals(g);
			renderGround(g);
			renderGameModifier(g);
			renderGameObjects(g);
		} else {
			renderGameOver(g);
		}
	}

}
