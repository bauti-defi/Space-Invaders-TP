package com.austral.spaceinvaders.models.gameobjects;

public class Shield extends GameObject {

	private static final String SHIELD_IMAGE_50 = "src/images/shield.png";
	private static final String SHIELD_IMAGE_40 = "src/images/shield_40.png";
	private static final String SHIELD_IMAGE_30 = "src/images/shield_30.png";
	private static final String SHIELD_IMAGE_20 = "src/images/shield_20.png";
	private static final String SHIELD_IMAGE_10 = "src/images/shield_10.png";
	private int health;

	public Shield(final int x, final int y, final int health) {
		super(x, y, SHIELD_IMAGE_50, true);
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
		if (health <= 10) {
			updateImage(SHIELD_IMAGE_10);
		} else if (health <= 20) {
			updateImage(SHIELD_IMAGE_20);
		} else if (health <= 30) {
			updateImage(SHIELD_IMAGE_30);
		} else if (health <= 40) {
			updateImage(SHIELD_IMAGE_40);
		}
	}
}
