package com.austral.spaceinvaders;

import java.io.File;

public interface GlobalConfiguration {

	static int frameWidth = 400;
	static int frameHeight = 600;
	static long gameTickDuration = 30;
	static int groundY = 550;
	static int playerStartX = 200;
	static int playerStartY = 540;
	static int shieldHealth = 50;
	static int consecutiveHitsForModifier = 4;
	static int minimumUFOSpawnDelay = 45;
	static int maxUFOSPawnDelay = 60;
	static String leadboardFilePath = System.getProperty("user.home") + File.separator + "/Desktop" + File.separator + "space-invaders-leaderboard.txt";
}
