package com.austral.spaceinvaders.physics;

public class Velocity {

	private double magnitude;
	private Direction direction;

	public Velocity(final double magnitude, final Direction direction) {
		this.magnitude = magnitude;
		this.direction = direction;
	}

	public Velocity() {
		this(0, Direction.SOUTH);
	}

	public double getMagnitude() {
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

	public double getVector() {
		return (direction == Direction.NORTH || direction == Direction.WEST) ? magnitude * -1 : magnitude;
	}
}
