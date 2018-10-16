package com.austral.spaceinvaders.models.gameobjects;

public class Shield extends GameObject {

	private static final String SHIELD_IMAGE_100 = "src/images/shield.png";
	private static final String SHIELD_IMAGE_80 = "src/images/shield.png";
	private static final String SHIELD_IMAGE_60 = "src/images/shield.png";
	private static final String SHIELD_IMAGE_40 = "src/images/shield.png";
	private static final String SHIELD_IMAGE_20 = "src/images/shield.png";
	private int health;

	public Shield(final int x, final int y, final int health) {
		super(x, y, SHIELD_IMAGE_100, true);
		this.health = health;
	}

	public boolean isDestroyed() {
		return health <= 0;
	}

	public int getHealth() {
		return health;
	}

	public void takeDamage(int damage) {
		this.health -= damage;
		//TODO: change icon
	}
}
