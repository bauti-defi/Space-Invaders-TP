package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.components.modifiers.DoubleShot;
import com.austral.spaceinvaders.components.modifiers.Freeze;
import com.austral.spaceinvaders.components.modifiers.GameModifier;
import com.austral.spaceinvaders.components.modifiers.GodMode;
import com.austral.spaceinvaders.util.DistributionList;
import com.austral.spaceinvaders.util.RandomGenerator;

public class GameModifierService {

	private final GameEngine gameEngine;
	private final DistributionList<GameModifier> gameModifierDistributionList = new DistributionList<>(new GodMode(), new DoubleShot(), new Freeze());

	private GameModifier currentGameModifier;
	private int currentModifierDuration;
	private long activationTime;

	public GameModifierService(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	public boolean isGameModifierActive() {
		return currentModifierDuration > 0;
	}

	public String getActiveGameModifier() {
		if (isGameModifierActive() && currentGameModifier != null) {
			return currentGameModifier.getName();
		}
		return "";
	}

	private GameModifier getNewGameModifier() {
		return gameModifierDistributionList.get();
	}

	public void activateModifier() {
		currentGameModifier = getNewGameModifier();
		currentGameModifier.activate(gameEngine);
		currentModifierDuration = RandomGenerator.getRandomIntBetween(3000, 5000);
		activationTime = System.currentTimeMillis();
	}

	public void forceDeactivateModifier() {
		currentModifierDuration = 0;
		if (currentGameModifier != null) {
			currentGameModifier.deactivate(gameEngine);
		}
	}

	public void ping() {
		if (currentModifierDuration > 0) {
			long elapsedTime = System.currentTimeMillis() - activationTime;
			if (currentModifierDuration <= elapsedTime) {
				currentGameModifier.deactivate(gameEngine);
				currentModifierDuration = 0;
			}
		}
	}

}
