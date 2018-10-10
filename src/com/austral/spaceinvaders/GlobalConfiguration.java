package com.austral.spaceinvaders;

import java.io.File;

public interface GlobalConfiguration {

	static int frameWidth = 400;
	static int frameHeight = 600;
	static long gameTickDuration = 30;
	static int groundY = 550;
	static int playerStartX = 200;
	static int playerStartY = 540;
	String leadboardFilePath = System.getProperty("user.home") + File.separator + "Space Invaders" + File.separator + "leaderboard.txt";

}
