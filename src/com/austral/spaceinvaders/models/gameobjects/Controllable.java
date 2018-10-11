package com.austral.spaceinvaders.models.gameobjects;

import com.austral.spaceinvaders.models.gameobjects.sprites.Projectile;

public interface Controllable {

	<T extends Projectile> T fire();

	void moveLeft();

	void moveRight();

	void stop();

}
