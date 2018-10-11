package com.austral.spaceinvaders.models.gameobjects.sprites.aliens;

import com.austral.spaceinvaders.models.gameobjects.sprites.Character;
import com.austral.spaceinvaders.physics.Velocity;

public class Alien extends Character {

	private final Velocity bombVelocity;
	private final int rewardPoints;

	public Alien(final int x, final int y, final Velocity xVelocity, final Velocity yVelocity, final int health, final Velocity bombVelocity, final int rewardPoints, String alienImage) {
		super(x, y, xVelocity, yVelocity, alienImage, health);
		this.bombVelocity = bombVelocity;
		this.rewardPoints = rewardPoints;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public Bomb fire() {
		return new Bomb(getX() + (getWidth() / 2), getY(), 1, bombVelocity);
	}
}
