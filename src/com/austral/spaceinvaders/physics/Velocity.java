package com.austral.spaceinvaders.physics;

public class Velocity {

	private int magnitude;
	private Direction direction;

	public Velocity(final int magnitude, final Direction direction) {
		this.magnitude = magnitude;
		this.direction = direction;
	}

	public Velocity() {
		this(0, Direction.SOUTH);
	}

	public int getMagnitude() {
		return magnitude;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setMagnitude(final int magnitude) {
		this.magnitude = magnitude;
	}

	public void setDirection(final Direction direction) {
		this.direction = direction;
	}

	public boolean isSignificant() {
		return magnitude != 0;
	}

	public int getVector() {
		return (direction == Direction.NORTH || direction == Direction.WEST) ? magnitude * -1 : magnitude;
	}
}
