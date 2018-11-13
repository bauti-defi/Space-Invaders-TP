package com.austral.spaceinvaders.game.views;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.game.core.GameEnvironment;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements GlobalConfiguration {

	private final Image HEART_IMAGE = new ImageIcon("src/images/heart.png").getImage();
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
		g.drawString("Puntos: " + gameEnvironment.getCurrentPoints(), 5, 15);
	}

	private void renderVitals(Graphics g) {
		g.setColor(Color.WHITE);
		for (int lives = 0; lives < gameEnvironment.getPlayerHealth(); lives++) {
			g.drawImage(HEART_IMAGE, frameWidth - 50 + (15 * lives), 5, this);
		}
	}

	private void renderGameModifier(Graphics g) {
		final String gameModifierName = gameEnvironment.getActiveGameModifierName();
		if (gameModifierName.length() > 0) {
			g.setColor(Color.RED);
			g.drawString(gameModifierName, (frameWidth / 2) - gameModifierName.length(), 50);
		}
	}


	private void renderGameObjects(Graphics g) {
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
		}
	}

}
