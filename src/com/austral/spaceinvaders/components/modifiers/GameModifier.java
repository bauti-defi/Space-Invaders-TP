package com.austral.spaceinvaders.components.modifiers;

import com.austral.spaceinvaders.components.GameEnvironment;

public interface GameModifier {

	String name();

	int probability();

	void activate(GameEnvironment gameEnvironment);

	void deactivate(GameEnvironment gameEnvironment);
}
