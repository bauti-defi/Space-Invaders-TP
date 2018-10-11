package com.austral.spaceinvaders.physics;

import com.austral.spaceinvaders.models.gameobjects.GameObject;

import java.awt.*;

public interface Collidable {

	Rectangle getCollisionBox();

	boolean collided(GameObject gameObject);

}
