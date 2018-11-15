package edu.austral.spaceinvaders.game.modifiers;

import edu.austral.spaceinvaders.game.core.GameEngine;
import edu.austral.spaceinvaders.util.Distributable;

public abstract class GameModifier implements Distributable {

	private final String name;

	public GameModifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract void activate(GameEngine gameEngine);

	public abstract void deactivate(GameEngine gameEngine);

}
