package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.teamproject.game.models.Student;

import java.util.ArrayList;

/**
 * Created by Roman_Mashenkin on 11.04.2016.
 *
 * This class updates files from "assets" folder and runs screens.
 */
public class LoadingScreen implements Screen{

    private STGame game;
    private Stage stage;
    private Texture textureLogo;
    private Image imageLogo;
    private Animation animationLoading;

    private ArrayList<Texture> textureLoading;

    private float stateTime = 0;

    public LoadingScreen(final STGame game) {

        this.game = game;

        //Initialization stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Adding elements
        createLoadingScreen();

        //Initialization asset manager
        game.setAssetManager();
    }

    public void createLoadingScreen() {

        //Setting logo of game
        textureLogo = new Texture(Gdx.files.internal(Constants.ICON_LOGO));
        imageLogo = new Image(textureLogo);

        imageLogo.setSize(stage.getWidth() / 5f, stage.getWidth() / 5f);
        imageLogo.setPosition(stage.getWidth() / 2f - imageLogo.getWidth() / 2f,
                stage.getHeight() / 2f - imageLogo.getHeight() / 2f);

        //Setting frames of loading in ArrayList
        textureLoading = new ArrayList<Texture>();
        for (int i = 0; i < Constants.COUNT_LOADING_TEXTURES; i++) {
            Texture texture = new Texture(Gdx.files.internal(
                    "data/images/loading/loading-" + i + ".png"));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            textureLoading.add(texture);
        }

        //Creating animation
        animationLoading = Utils.getAnimation(textureLoading, Constants.COUNT_LOADING_TEXTURES, 10);
    }

    public void runLoading(float delta) {

        //Incrementing stateTime
        stateTime += delta;

        //Updating loading textures on stage
        Image imageLoading = new Image(animationLoading.getKeyFrame(stateTime, true));
        imageLoading.setSize(stage.getWidth() / 3f, stage.getWidth() / 3f);
        imageLoading.setPosition(stage.getWidth() / 2 - imageLoading.getWidth() / 2,
                stage.getHeight() / 2 - imageLoading.getHeight() / 2);

        //Adding Actor on stage
        stage.addActor(imageLoading);
    }

    private void setLinearFilter() {

        game.getSkin().getRegion("background").getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        game.getSkin().getRegion("scroll_knob").getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        game.getSkin().getRegion("cursor").getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        game.getSkin().getRegion("selection").getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        game.getSkin().getRegion("bar_background").getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        game.getSkin().getRegion("bar_knob").getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);

        game.getManager().get(Constants.ICON_CAT, Texture.class).
                setFilter(Texture.TextureFilter.Linear,
                        Texture.TextureFilter.Linear);
        game.getManager().get(Constants.ICON_LOGO, Texture.class).
                setFilter(Texture.TextureFilter.Linear,
                        Texture.TextureFilter.Linear);
        game.getManager().get(Constants.ICON_CASH, Texture.class).
                setFilter(Texture.TextureFilter.Linear,
                        Texture.TextureFilter.Linear);
        game.getManager().get(Constants.ICON_ENERGY, Texture.class).
                setFilter(Texture.TextureFilter.Linear,
                        Texture.TextureFilter.Linear);
        game.getManager().get(Constants.LIGHTGRAY_STAR, Texture.class).
                setFilter(Texture.TextureFilter.Linear,
                        Texture.TextureFilter.Linear);
        game.getManager().get(Constants.DARKGRAY_STAR, Texture.class).
                setFilter(Texture.TextureFilter.Linear,
                        Texture.TextureFilter.Linear);
    }

    @Override
    public void show() {

        //Adding Actor on stage
        stage.addActor(imageLogo);
    }

    @Override
    public void render(final float delta) {

        //Setting background color #445565
        Utils.setBackgroundColor(242/255f, 242/255f, 242/255f, 1);

        //Drawing actors
        stage.draw();

        //Updating of graphic elements
        stage.act(delta);

        //Adding and updating loading textures on stage

        //WARNING: here it should be "stateTime < 3"!
        if (stateTime == 0) runLoading(delta);
        else
            if (game.getManager().update()) {

                dispose();

                //Getting from asset manager skin and background music
                game.setSkin(game.getManager().get(Constants.SKIN, Skin.class));
                game.setGameMusic(game.getManager().get(Constants.BACKGROUND_MUSIC, Music.class));
                game.getManager().finishLoading();

                //Smoothing font and textures
                setLinearFilter();

                //Start of playing background music
                game.playMusic();

                //Choosing screen
                if (Utils.isEmpty()) game.setScreen(new LoginScreen(game));
                else {
                    game.setPlayerData(Student.readPlayerData());
                    game.setScreen(new MainMenuScreen(game));
                }
            }
            else
                runLoading(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();
        textureLogo.dispose();

        for (int i = 0; i < Constants.COUNT_LOADING_TEXTURES; i++) {
            animationLoading.getKeyFrame(i).getTexture().dispose();
            textureLoading.get(i).dispose();
        }
    }
}
