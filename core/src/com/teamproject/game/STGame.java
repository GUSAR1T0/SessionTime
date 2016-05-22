package com.teamproject.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.teamproject.game.models.Parameters;
import com.teamproject.game.models.Student;
import com.teamproject.game.screens.LoadingScreen;

import static com.badlogic.gdx.utils.TimeUtils.millis;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * Main class of game "Session Time".
 * This class initializes asset manager for game, controls data of player and runs LoadingScreen.
 */
public class STGame extends Game {

	private Skin mSkin;
	private Music gameMusic;
	private AssetManager mAssetManager;

	private Parameters parameters;
	private Student player;

	public int[] indexOfSubject = new int[]{};

	public Skin getSkin() {
		return mSkin;
	}

	public void setSkin(Skin skin) {
		mSkin = skin;
	}

	public Music getGameMusic() {
		return gameMusic;
	}

	public void setGameMusic(Music music) {
		gameMusic = music;
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
		mAssetManager.load(Constants.BACKGROUND_MUSIC, Music.class);
		mAssetManager.load(Constants.ICON_CAT, Texture.class);
		mAssetManager.load(Constants.ICON_LOGO, Texture.class);
		mAssetManager.load(Constants.ICON_CASH, Texture.class);
		mAssetManager.load(Constants.ICON_COIN, Texture.class);
		mAssetManager.load(Constants.ICON_ENERGY, Texture.class);
		mAssetManager.load(Constants.ICON_TIME, Texture.class);
		mAssetManager.load(Constants.LIGHTGRAY_STAR, Texture.class);
		mAssetManager.load(Constants.DARKGRAY_STAR, Texture.class);
		mAssetManager.load(Constants.BLUE_STAR, Texture.class);
	}

	public void playMusic(float volume) {

		//Start to playing background music
		gameMusic = mAssetManager.get(Constants.BACKGROUND_MUSIC, Music.class);
		gameMusic.setLooping(true);
		gameMusic.play();
		gameMusic.setPosition(1.7f);
		gameMusic.setVolume(volume);
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public Student getPlayerData() {
		return player;
	}

	public void setPlayerData(Student player) {
		this.player = player;
	}

	public void saveData(Parameters parameters, Student player) {

		Parameters.writeParameters(parameters.getVolume(), parameters.getIsActiveAction(),
				parameters.getDayOfAction(), parameters.isGrant(), parameters.getDayOfGrant());

		Student.writeStudentData(player.getName(),
				player.getValueOfSpecialty(), player.getSemester(), player.isFlag(),
				player.getCash(), player.getGrant(), player.getEnergy(), player.getAttendance(),
				new long[]{player.getTime()[0], millis()});
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

		if (!Utils.isEmpty(Constants.PLAYER)) saveData(parameters, player);
	}
}
