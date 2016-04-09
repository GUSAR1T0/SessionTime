package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;

/**
 * Created by Roman_Mashenkin on 28.03.2016.
 */
public class SettingScreen implements Screen {
    // TODO: 02.04.2016, make screen of settings with different parameters

    private Stage stage;
    private Texture background;
    private STGame game;

    public SettingScreen(STGame game) {

        this.game = game;

//        background = new Texture(Gdx.files.internal(Constants.MENU_BACKGROUND));

        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_HEIGHT * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    }
}
