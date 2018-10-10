package com.austral.spaceinvaders.models.sprites;

import com.austral.spaceinvaders.models.Controllable;
import com.austral.spaceinvaders.physics.Direction;
import com.austral.spaceinvaders.physics.Velocity;

public class Player extends Character implements Controllable {

	private static final String PLAYER_IMAGE = "src/images/player.png";
	private int shield;

	public Player(int x, int y, int health, int shieldCount) {
		super(x, y, health, PLAYER_IMAGE);
		this.shield = shieldCount * 50;
	}


	@Override
	public void takeDamage(final int damage) {
		if (!isImmune()) {
			if (shield > 0) {
				shield -= damage;
			}
			if (shield < 0) {
				super.takeDamage(shield * -1);
				shield = 0;
				return;
			}
			super.takeDamage(damage);
		}
	}

	public int getShield() {
		return shield;
	}

	@Override
	public Shot fire() {
		return new Shot(getX() + (getWidth() / 2), getY());
	}

	@Override
	public void moveLeft() {
		setxVelocity(new Velocity(5, Direction.WEST));
	}

	@Override
	public void moveRight() {
		setxVelocity(new Velocity(5, Direction.EAST));
	}

	@Override
	public void stop() {
		setxVelocity(new Velocity(0, Direction.SOUTH));
	}
}
