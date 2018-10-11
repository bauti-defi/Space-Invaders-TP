package com.austral.spaceinvaders.components;

import com.austral.spaceinvaders.components.modifiers.GameModifier;

import java.util.ArrayList;
import java.util.Random;

public class GameModifierService {

	private final GameEnvironment gameEnvironment;
	private final ArrayList<GameModifier> gameModifiers = new ArrayList<>();

	private GameModifier currentGameModifier;
	private int currentPowerUpDuration;
	private long activationTime;

	public GameModifierService(GameEnvironment gameEnvironment) {
		this.gameEnvironment = gameEnvironment;
	}

	public void supplyGameModifier(GameModifier gameModifier) {
		this.gameModifiers.add(gameModifier);
	}

	public boolean isGameModifierActive() {
		return currentPowerUpDuration > 0;
	}

	private int generateNewDuration() {
		Random durationGenerator = new Random();
		return durationGenerator.nextInt(2000) + 3000;
	}

	public String getActiveGameModifier() {
		if (isGameModifierActive() && currentGameModifier != null) {
			return currentGameModifier.name();
		}
		return "";
	}

	private GameModifier getNewGameModifier() {
		final Random randomGenerator = new Random();
		int roll = randomGenerator.nextInt(99) + 1; //0-100
		return gameModifiers.stream().filter(gameModifier -> gameModifier.probability() >= roll)
				.sorted((gameModifier1, gameModifier2) -> {
					if (gameModifier1.probability() < gameModifier2.probability()) {
						return 1;
					} else if (gameModifier1.probability() > gameModifier2.probability()) {
						return -1;
					}
					return 0;
				})
				.findFirst().get();
	}

	public void activateModifier() {
		currentGameModifier = getNewGameModifier();
		currentGameModifier.activate(gameEnvironment);
		currentPowerUpDuration = generateNewDuration();
		activationTime = System.currentTimeMillis();
	}

	public void forceDeactivateModifier() {
		currentPowerUpDuration = 0;
		if (currentGameModifier != null) {
			currentGameModifier.deactivate(gameEnvironment);
		}
	}

	public void ping() {
		if (currentPowerUpDuration > 0) {
			long difference = System.currentTimeMillis() - activationTime;
			if ((currentPowerUpDuration -= difference) <= 0) {
				currentGameModifier.deactivate(gameEnvironment);
			}
		}
	}

}
