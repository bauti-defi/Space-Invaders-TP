package com.austral.spaceinvaders.components.modifiers;

import com.austral.spaceinvaders.components.GameEngine;

public class DoubleShot extends GameModifier {

    public DoubleShot() {
        super("Double Shot");
    }

    @Override
    public void activate(GameEngine gameEngine) {
        gameEngine.getPlayer().activateSecondaryFire();
    }

    @Override
    public void deactivate(GameEngine gameEngine) {
        gameEngine.getPlayer().deactivateSecondaryFire();
    }

    @Override
    public int getPercentProbability() {
        return 70;
    }
}
