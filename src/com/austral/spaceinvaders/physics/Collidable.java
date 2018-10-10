package com.austral.spaceinvaders.physics;

import com.austral.spaceinvaders.models.sprites.Sprite;

import java.awt.*;

public interface Collidable {

	Rectangle getCollisionBox();

	boolean collided(Sprite sprite);

}
