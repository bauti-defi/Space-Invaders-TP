package com.austral.spaceinvaders.models.sprites;

import com.austral.spaceinvaders.models.Renderable;
import com.austral.spaceinvaders.physics.Collidable;
import com.austral.spaceinvaders.physics.Velocity;

import javax.swing.*;
import java.awt.*;

public abstract class Sprite implements Renderable, Collidable {

	private int x;
	private int y;
	private int height;
	private int width;
	private Velocity xVelocity, yVelocity;
	private final Image image;
	private boolean visible;

	public Sprite(final int x, final int y, final Velocity xVelocity, final Velocity yVelocity, final boolean visible, final String imagePath) {
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
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

	public Sprite(final int x, final int y, final String imagePath) {
		this(x, y, new Velocity(), new Velocity(), true, imagePath);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Velocity getxVelocity() {
		return xVelocity;
	}

	public Velocity getyVelocity() {
		return yVelocity;
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

	public void setxVelocity(final Velocity xVelocity) {
		this.xVelocity = xVelocity;
	}

	public void setyVelocity(final Velocity yVelocity) {
		this.yVelocity = yVelocity;
	}

	public void setVisible(final boolean visible) {
		this.visible = visible;
	}

	public void animate() {
		if (xVelocity.isSignificant()) {
			this.x += xVelocity.getVectorially();
		}
		if (yVelocity.isSignificant()) {
			this.y += yVelocity.getVectorially();
		}
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
	public boolean collided(final Sprite sprite) {
		return getCollisionBox().intersects(sprite.getCollisionBox());
	}
}
