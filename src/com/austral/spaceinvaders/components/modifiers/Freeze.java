package com.austral.spaceinvaders.components.modifiers;

import com.austral.spaceinvaders.components.GameEngine;
import com.austral.spaceinvaders.models.gameobjects.sprites.Character;

public class Freeze extends GameModifier {

    public Freeze() {
        super("Freeze");
    }

    @Override
    public void activate(GameEngine gameEngine) {
        gameEngine.getAliens().forEach(Character::Freeze);
    }

    @Override
    public void deactivate(GameEngine gameEngine) {
        gameEngine.getAliens().forEach(Character::UnFreeze);
    }

    @Override
    public int getPercentProbability() {
        return 10;
    }
}
