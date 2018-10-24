package com.austral.spaceinvaders;

public class SpaceInvaders {

	//Run for game

	public static final void main(String... args) {
		GameController controller = new GameController();
		controller.createNewLocalGameSession("Diego");
		controller.startGameSessions();
	}
}
