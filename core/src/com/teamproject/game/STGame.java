package com.teamproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.screens.LoadingScreen;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * Main class of game "Session Time".
 * This class initializes asset manager for game and run LoadingScreen.
 */
public class STGame extends Game {

	private Skin mSkin;
	private AssetManager mAssetManager;

	public Skin getSkin() {
		return mSkin;
	}

	public void setSkin(Skin skin) {
		mSkin = skin;
	}

	public AssetManager getManager() {
		return mAssetManager;
	}

	public void setAssetManager(AssetManager manager) {
		this.mAssetManager = manager;
	}

	public void setAssetManager() {

		//Setting manager and loading general assets
		mAssetManager.load(Constants.SKIN, Skin.class);
		mAssetManager.load(Constants.ICON_CAT, Texture.class);
		mAssetManager.load(Constants.ICON_LOGO, Texture.class);
		mAssetManager.load(Constants.LIGHTGRAY_STAR, Texture.class);
		mAssetManager.load(Constants.DARKGRAY_STAR, Texture.class);
	}

	@Override
	public void create() {

		setAssetManager(new AssetManager());
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		mSkin.dispose();
		mAssetManager.dispose();
	}
}
