package com.teamproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.teamproject.game.screens.LoginScreen;
import com.teamproject.game.screens.MainMenuScreen;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * Main class of game "Session Time".
 * This class initializes skin parameters of game (needed for authorization view) and
 * background texture which uses in some screens.
 */
public class STGame extends Game {

	private Skin mSkin;

	public void setSkin(Skin skin) {
		mSkin = skin;
	}

	public Skin getSkin() {
		return mSkin;
	}

	@Override
	public void create() {
		this.setSkin(new Skin(Gdx.files.internal(Constants.SKIN)));

		if (Utils.isEmpty()) setScreen(new LoginScreen(this));
		else setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		mSkin.dispose();
	}

}
