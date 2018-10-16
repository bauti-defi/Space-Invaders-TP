package com.austral.spaceinvaders.models.gameobjects;

import com.austral.spaceinvaders.physics.Collidable;

import javax.swing.*;
import java.awt.*;

public abstract class GameObject implements Renderable, Collidable {

	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected Image image;
	protected boolean visible;

	public GameObject(final int x, final int y, final String imagePath, final boolean visible) {
		this.x = x;
		this.y = y;
		this.image = new ImageIcon(imagePath).getImage();
		this.height = image.getHeight(null);
		this.width = image.getWidth(null);
		this.visible = visible;
		if (this.x - width < 0) {
			this.x += width;
		}
		if (this.y - height < 0) {
			this.y += height;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void updateImage(String imagePath) {
		this.image = new ImageIcon(imagePath).getImage();
		this.height = image.getHeight(null);
		this.width = image.getWidth(null);
	}

	public Image getImage() {
		return image;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public void setVisible(final boolean visible) {
		this.visible = visible;
	}

	@Override
	public Rectangle getCollisionBox() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void render(final Graphics graphics) {
		graphics.drawImage(image, x, y, null);
	}

	@Override
	public boolean collided(final GameObject gameObject) {
		return getCollisionBox().intersects(gameObject.getCollisionBox());
	}
}
