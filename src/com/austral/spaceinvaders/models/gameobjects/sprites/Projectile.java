package com.austral.spaceinvaders.models.gameobjects.sprites;

import com.austral.spaceinvaders.physics.Velocity;

public abstract class Projectile extends Sprite {

	private int damage;

	public Projectile(final int x, final int y, final Velocity xVelocity, final Velocity yVelocity, final int damage, final String imagePath) {
		super(x, y, xVelocity, yVelocity, true, imagePath);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}
}
