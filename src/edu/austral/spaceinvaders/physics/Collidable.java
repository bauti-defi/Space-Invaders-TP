package edu.austral.spaceinvaders.physics;

import edu.austral.spaceinvaders.models.gameobjects.GameObject;

import java.awt.*;

public interface Collidable {

	Rectangle getCollisionBox();

	boolean collided(GameObject gameObject);

}
