package com.austral.spaceinvaders.physics;

import com.austral.spaceinvaders.models.gameobjects.GameObject;

public class CollisionFlag<T1 extends GameObject, T2 extends GameObject> {

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
