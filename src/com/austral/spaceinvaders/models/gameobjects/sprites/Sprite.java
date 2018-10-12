package com.austral.spaceinvaders.models.gameobjects.sprites;

import com.austral.spaceinvaders.models.gameobjects.GameObject;
import com.austral.spaceinvaders.physics.Velocity;

public abstract class Sprite extends GameObject {

	private Velocity xVelocity, yVelocity;

	public Sprite(final int x, final int y, final Velocity xVelocity, final Velocity yVelocity, final boolean visible, final String imagePath) {
		super(x, y, imagePath, visible);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	public Sprite(final int x, final int y, final String imagePath) {
		this(x, y, new Velocity(), new Velocity(), true, imagePath);
	}


	public Velocity getxVelocity() {
		return xVelocity;
	}

	public Velocity getyVelocity() {
		return yVelocity;
	}

	public void setxVelocity(final Velocity xVelocity) {
		this.xVelocity = xVelocity;
	}

	public void setyVelocity(final Velocity yVelocity) {
		this.yVelocity = yVelocity;
	}


	public void animate() {
		if (xVelocity.isSignificant()) {
			this.x += xVelocity.getVector();
		}
		if (yVelocity.isSignificant()) {
			this.y += yVelocity.getVector();
		}
	}
}
