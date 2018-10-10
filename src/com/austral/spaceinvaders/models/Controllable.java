package com.austral.spaceinvaders.models;

import com.austral.spaceinvaders.models.sprites.Projectile;

public interface Controllable {

	<T extends Projectile> T fire();

	void moveLeft();

	void moveRight();

	void stop();

}
