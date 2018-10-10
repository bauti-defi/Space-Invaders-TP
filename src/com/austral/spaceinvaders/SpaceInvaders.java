package com.austral.spaceinvaders;

public class SpaceInvaders {

	public static final void main(String... args) {
		GameController controller = new GameController();
		controller.createNewLocalGameSession("Bautista");
		controller.startGameSessions();
	}
}
