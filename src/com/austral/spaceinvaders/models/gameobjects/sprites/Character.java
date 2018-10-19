package com.austral.spaceinvaders.models.gameobjects.sprites;

import com.austral.spaceinvaders.physics.Velocity;

public abstract class Character extends Sprite {

	private int health;
	private boolean immune, doubleshot, freeze;

	public Character(final int x, final int y, final int health, final String imagePath) {
		super(x, y, imagePath);
		this.health = health;
	}

	public Character(final int x, final int y, final Velocity xVelocity, final Velocity yVelocity, final String imagePath, final int health) {
		super(x, y, xVelocity, yVelocity, true, imagePath);
		this.health = health;
	}

	public void makeImmune() {
		this.immune = true;
	}

	public void makeMortal() {
		this.immune = false;
	}

	public boolean isImmune() {
		return immune;
	}

	public void Doubleshot(){
		this.doubleshot = true;
	}

	public void NoDoubleShot(){
		this.doubleshot = false;
	}

	public boolean isDoubleshotActive(){
		return doubleshot;
	}

	public void Freeze(){
		this.freeze = true;
	}

	public void UnFreeze(){
		this.freeze = false;
	}

	public boolean isFreeze(){
		return freeze;
	}

	public int getHealth() {
		return health;
	}

	public void takeDamage(final int damage) {
		if (!immune) {
			this.health -= damage;
		}
	}

	public boolean isDead() {
		return health <= 0;
	}
}
