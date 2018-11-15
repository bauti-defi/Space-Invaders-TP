package edu.austral.spaceinvaders.models.gameobjects.sprites.aliens;

import edu.austral.spaceinvaders.models.gameobjects.sprites.Projectile;
import edu.austral.spaceinvaders.physics.Velocity;

public class Bomb extends Projectile {

	private static final String BOMB_IMAGE = "src/images/bomb.png";

	public Bomb(final int x, final int y, int damage, Velocity yVelocity) {
		super(x, y, new Velocity(), yVelocity, damage, BOMB_IMAGE);
	}

}
