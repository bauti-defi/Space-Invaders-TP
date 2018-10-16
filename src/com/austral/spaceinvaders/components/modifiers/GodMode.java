package com.austral.spaceinvaders.components.modifiers;

import com.austral.spaceinvaders.components.GameEngine;

public class GodMode extends GameModifier {

	public GodMode() {
		super("God Mode");
	}

	@Override
	public void activate(final GameEngine gameEngine) {
		gameEngine.getPlayer().makeImmune();
	}

	@Override
	public void deactivate(final GameEngine gameEngine) {
		gameEngine.getPlayer().makeMortal();
	}

	@Override
	public int getPercentProbability() {
		return 20;
	}
}
