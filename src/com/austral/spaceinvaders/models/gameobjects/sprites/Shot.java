package com.austral.spaceinvaders.models.gameobjects.sprites;

import com.austral.spaceinvaders.physics.Direction;
import com.austral.spaceinvaders.physics.Velocity;

import java.util.concurrent.atomic.AtomicInteger;

public class Shot extends Projectile {

	private static final String SHOT_IMAGE = "src/images/shot.png";
	private static final AtomicInteger idGenerator = new AtomicInteger(0);
	private final int id = idGenerator.getAndIncrement();

	public Shot(final int x, final int y) {
		super(x, y, new Velocity(), new Velocity(5, Direction.NORTH), 50, SHOT_IMAGE);
	}

	public int getId() {
		return id;
	}
}
