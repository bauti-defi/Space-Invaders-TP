package com.austral.spaceinvaders.models.gameobjects.sprites;

import com.austral.spaceinvaders.physics.Direction;
import com.austral.spaceinvaders.physics.Velocity;

public class Shot extends Projectile {

	private static final String SHOT_IMAGE = "src/images/shot.png";
	private final int id;

	public Shot(final int x, final int y, final int id) {
		super(x, y, new Velocity(), new Velocity(5, Direction.NORTH), 50, SHOT_IMAGE);
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
