package com.austral.spaceinvaders.components.modifiers;

import com.austral.spaceinvaders.components.GameEnvironment;

public class GodMode extends GameModifier {

	public GodMode() {
		super("God Mode");
	}

	@Override
	public void activate(final GameEnvironment gameEnvironment) {
		gameEnvironment.getPlayer().makeImmune();
	}

	@Override
	public void deactivate(final GameEnvironment gameEnvironment) {
		gameEnvironment.getPlayer().makeMortal();
	}

	@Override
	public int getPercentProbability() {
		return 20;
	}
}
