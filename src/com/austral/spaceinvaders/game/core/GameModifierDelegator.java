package com.austral.spaceinvaders.game.core;

import com.austral.spaceinvaders.GlobalConfiguration;
import com.austral.spaceinvaders.game.modifiers.*;
import com.austral.spaceinvaders.models.gameobjects.sprites.Shot;
import com.austral.spaceinvaders.util.DistributionList;
import com.austral.spaceinvaders.util.RandomGenerator;

import java.util.stream.Stream;

public class GameModifierDelegator implements GlobalConfiguration {

	private final GameEngine gameEngine;
	private final DistributionList<GameModifier> gameModifierDistributionList = new DistributionList<>(new GodMode(), new DoubleShot(), new Freeze());

	private int currentConsecutiveHitCount;
	private int previousId = -1;
	private GameModifierDelegate delegate;
	private static final String DEFAULT_MESSAGE = "HITS TILL MODIFIER: ";

	public GameModifierDelegator(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	public void analyzeFrame(final Stream<Shot> hits) {
		hits.map(Shot::getId).sorted().forEachOrdered(id -> {
			if (previousId == (id - 1)) {//consecutive hit
				if (delegate == null || !delegate.isActive()) {
					if (++this.currentConsecutiveHitCount >= this.consecutiveHitsForModifier) {
						this.delegate = new GameModifierDelegate(this.gameModifierDistributionList.get(), RandomGenerator.getRandomIntBetween(2, 4));
						this.delegate.start(gameEngine);
						this.currentConsecutiveHitCount = 0;
					}
				} else if (delegate.isActive() && delegate.isDone(System.currentTimeMillis())) {
					delegate.stop(gameEngine);
				}
			} else {
				this.currentConsecutiveHitCount = 1;
			}
			this.previousId = id;
		});

		//remove game modifier
		if (delegate != null && delegate.isDone(System.currentTimeMillis()) && delegate.isActive()) {
			delegate.stop(gameEngine);
		}
	}

	public void forceStop() {
		if (delegate != null && delegate.isActive()) {
			this.currentConsecutiveHitCount = 0;
			delegate.stop(gameEngine);
		}
	}

	public String getCurrentGameModifier() {
		if (delegate == null) {
			return DEFAULT_MESSAGE + (consecutiveHitsForModifier - currentConsecutiveHitCount);
		}
		return delegate.isActive() ? delegate.getModifierName() : DEFAULT_MESSAGE + (consecutiveHitsForModifier - currentConsecutiveHitCount);
	}

}
