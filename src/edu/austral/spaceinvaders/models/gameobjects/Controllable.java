package edu.austral.spaceinvaders.models.gameobjects;

import edu.austral.spaceinvaders.models.gameobjects.sprites.Projectile;

public interface Controllable {

	<T extends Projectile> T fire();

	void moveLeft();

	void moveRight();

	void stop();

}
