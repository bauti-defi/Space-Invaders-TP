package com.austral.spaceinvaders.models;

import com.austral.spaceinvaders.physics.Direction;
import com.austral.spaceinvaders.physics.Velocity;

public enum Level {

	FIRST(4, 3, 5, new Velocity(0, Direction.EAST), new Velocity(15, Direction.SOUTH), new Velocity(30, Direction.SOUTH)),
	SECOND(3, 3, 5, new Velocity(0, Direction.EAST), new Velocity(15, Direction.SOUTH), new Velocity(30, Direction.SOUTH));

	private int initialShieldCount;
	private int initialLiveCount;
	private int alienCount;
	private Velocity alienXVelocity;
	private Velocity alienYVelocity;
	private Velocity alienBombVelocity;

	Level(final int initialShieldCount, final int initialLiveCount, final int alienCount, final Velocity alienXVelocity, final Velocity alienYVelocity, final Velocity alienBombVelocity) {
		this.initialShieldCount = initialShieldCount;
		this.initialLiveCount = initialLiveCount;
		this.alienCount = alienCount;
		this.alienXVelocity = alienXVelocity;
		this.alienYVelocity = alienYVelocity;
		this.alienBombVelocity = alienBombVelocity;
	}

	public int getAlienCount() {
		return alienCount;
	}

	public int getInitialShieldCount() {
		return initialShieldCount;
	}

	public int getInitialLiveCount() {
		return initialLiveCount;
	}

	public Velocity getAlienXVelocity() {
		return alienXVelocity;
	}

	public Velocity getAlienYVelocity() {
		return alienYVelocity;
	}

	public Velocity getAlienBombVelocity() {
		return alienBombVelocity;
	}
}
