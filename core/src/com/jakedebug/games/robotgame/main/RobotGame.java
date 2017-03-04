package com.jakedebug.games.robotgame.main;

import com.badlogic.gdx.Game;

public class RobotGame extends Game {

	private static final String TAG = RobotGame.class.getName();

	@Override
	public void create () {
		//At game creation, set screen
		setScreen(new com.jakedebug.games.robotgame.screens.RobotGameScreen());
	}
}
