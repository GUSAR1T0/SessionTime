package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.teamproject.game.STGame;

/**
 * Created by Roman_Mashenkin on 28.03.2016.
 */
public class GameMenuScreen implements Screen {
    // TODO: 02.04.2016, make realization main idea of game or menu of mini-games

    private OrthographicCamera camera;
    private Stage stage;
    private Texture background;
    private STGame game;

    public GameMenuScreen(STGame game) {

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

//        background = new Texture(Gdx.files.internal(Constants.MENU_BACKGROUND));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
//        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();
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
