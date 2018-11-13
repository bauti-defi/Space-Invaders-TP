package com.austral.spaceinvaders.game.modifiers;

import com.austral.spaceinvaders.game.core.GameEngine;
import com.austral.spaceinvaders.models.gameobjects.sprites.Character;
import com.austral.spaceinvaders.models.gameobjects.sprites.aliens.Alien;

public class Freeze extends GameModifier {

    public Freeze() {
        super("Freeze");
    }

    @Override
    public void activate(GameEngine gameEngine) {
	    gameEngine.getAliens().forEach(Alien::freeze);
    }

    @Override
    public void deactivate(GameEngine gameEngine) {
	    gameEngine.getAliens().forEach(Character::unFreeze);
    }

    @Override
    public int getPercentProbability() {
        return 10;
    }
}
