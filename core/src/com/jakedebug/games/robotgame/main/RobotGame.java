package com.jakedebug.games.robotgame.main;

import com.badlogic.gdx.Game;
import com.jakedebug.games.robotgame.screens.RobotGameScreen;

public class RobotGame extends Game {

	private static final String TAG = RobotGame.class.getName();

	@Override
	public void create () {
		//At game creation, set screen
		setScreen(new RobotGameScreen());
	}
}
