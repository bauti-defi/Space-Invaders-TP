package com.austral.spaceinvaders.components.modifiers;

import com.austral.spaceinvaders.components.GameEnvironment;

public class GodMode implements GameModifier {

	@Override
	public String name() {
		return "God Mode";
	}

	@Override
	public int probability() {
		return 100;
	}

	@Override
	public void activate(final GameEnvironment gameEnvironment) {
		gameEnvironment.getPlayer().makeImmune();
	}

	@Override
	public void deactivate(final GameEnvironment gameEnvironment) {
		gameEnvironment.getPlayer().makeMortal();
	}
}
