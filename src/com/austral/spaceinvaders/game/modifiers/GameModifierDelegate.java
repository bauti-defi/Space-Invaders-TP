package com.austral.spaceinvaders.game.modifiers;

import com.austral.spaceinvaders.game.core.GameEngine;

public class GameModifierDelegate {

	private final GameModifier gameModifier;
	private final long runTimeDuration;
	private long startTime;
	private boolean active;

	public GameModifierDelegate(final GameModifier gameModifier, final long runTimeDuration) {
		this.gameModifier = gameModifier;
		this.runTimeDuration = runTimeDuration * 1000;
	}

	public void start(GameEngine gameEngine) {
		this.active = true;
		this.gameModifier.activate(gameEngine);
		this.startTime = System.currentTimeMillis();
	}

	public boolean isActive() {
		return active;
	}

	public boolean isDone(long currentTime) {
		return currentTime >= startTime + runTimeDuration;
	}

	public void stop(GameEngine gameEngine) {
		this.active = false;
		this.gameModifier.deactivate(gameEngine);
	}

	public String getModifierName() {
		return gameModifier.getName();
	}

}
