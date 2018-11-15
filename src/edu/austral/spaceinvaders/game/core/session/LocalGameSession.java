package edu.austral.spaceinvaders.game.core.session;

import edu.austral.spaceinvaders.input.LocalJoystickAdapter;
import edu.austral.spaceinvaders.models.LocalPlayer;

public class LocalGameSession extends GameSession {

	public LocalGameSession(final LocalPlayer localPlayer) {
		super(localPlayer);
		this.gameFrame.addKeyListener(new LocalJoystickAdapter(this));
	}
}
