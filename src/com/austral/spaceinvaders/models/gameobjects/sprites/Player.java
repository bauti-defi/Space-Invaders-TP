package com.austral.spaceinvaders.models.gameobjects.sprites;

import com.austral.spaceinvaders.models.gameobjects.Controllable;
import com.austral.spaceinvaders.physics.Direction;
import com.austral.spaceinvaders.physics.Velocity;

import java.util.concurrent.atomic.AtomicInteger;

public class Player extends Character implements Controllable {

	private static final String PLAYER_IMAGE = "src/images/player.png";
	private final AtomicInteger shotIdGenerator = new AtomicInteger(0);

	public Player(int x, int y, int health) {
		super(x, y, health, PLAYER_IMAGE);
	}

	@Override
	public Shot fire() {
		return new Shot(getX() + (getWidth() / 2), getY(), shotIdGenerator.getAndIncrement());
	}

	public Shot secondaryFire() {
		return new Shot(getX() + (getWidth() / 4), getY(), shotIdGenerator.getAndIncrement());
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
