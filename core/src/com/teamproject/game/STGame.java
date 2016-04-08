package com.teamproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.screens.MainMenuScreen;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * Main class of game "Session Time".
 * This class initializes skin parameters of game (needed for authorization view) and
 * background texture which uses in some screens.
 */
public class STGame extends Game {

	private Texture background;
	private Skin skin;

	public void setBackground(Texture background) {
		this.background = background;
	}

	public Texture getBackground() {
		return background;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public Skin getSkin() {
		return skin;
	}

	@Override
	public void create() {
		this.setSkin(new Skin(Gdx.files.internal(Constants.SKIN)));
		this.setBackground(new Texture(Gdx.files.internal(Constants.MENU_BACKGROUND)));

		Gdx.gl.glClearColor(0, 0, 0.5f, 1);
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		background.dispose();
		skin.dispose();
	}

}
