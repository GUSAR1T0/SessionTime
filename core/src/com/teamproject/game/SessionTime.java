package com.teamproject.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamproject.game.screens.GameScreenManager;
import com.teamproject.game.screens.MenuScreen;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * Main class of game "Session Time".
 */
public class SessionTime extends ApplicationAdapter {

	SpriteBatch batch;
	GameScreenManager gsm;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		gsm = new GameScreenManager();

		Gdx.gl.glClearColor(0, 0, 0.5f, 1);
		gsm.push(new MenuScreen(gsm));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}
