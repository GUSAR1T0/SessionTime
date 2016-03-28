package com.teamproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.teamproject.game.screens.MenuScreen;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * Main class of game "Session Time".
 */
public class STGame extends Game {

	@Override
	public void create() {
		Gdx.gl.glClearColor(0, 0, 0.5f, 1);
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
