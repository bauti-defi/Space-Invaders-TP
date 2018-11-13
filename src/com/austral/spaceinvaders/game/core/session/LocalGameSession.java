package com.austral.spaceinvaders.game.core.session;

import com.austral.spaceinvaders.input.GameServerRemoteAdapter;
import com.austral.spaceinvaders.models.LocalPlayer;

public class LocalGameSession extends GameSession {

	public LocalGameSession(final LocalPlayer localPlayer) {
		super(localPlayer);
		this.gameFrame.addKeyListener(new GameServerRemoteAdapter(this));
	}
}
