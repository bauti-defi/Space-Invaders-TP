package com.austral.spaceinvaders.physics;

import com.austral.spaceinvaders.models.sprites.Sprite;

public class CollisionFlag<T1 extends Sprite, T2 extends Sprite> {

	private final T1 alphaCollider;
	private final T2 betaCollider;

	public CollisionFlag(final T1 alphaCollider, final T2 betaCollider) {
		this.alphaCollider = alphaCollider;
		this.betaCollider = betaCollider;
	}

	public T1 getAlphaCollider() {
		return alphaCollider;
	}

	public T2 getBetaCollider() {
		return betaCollider;
	}
}
