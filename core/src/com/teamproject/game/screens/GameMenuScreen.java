package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Utils;

/**
 * Created by Roman_Mashenkin on 28.03.2016.
 */
public class GameMenuScreen implements Screen {
    // TODO: 11.04.2016 to make new realization GameMenuScreen using new design

    private OrthographicCamera camera;
    private Stage stage;
    private STGame game;

    public GameMenuScreen(STGame game) {

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Setting background color #445565
        Utils.setBackgroundColor(68/255f, 85/255f, 101/255f, 1);
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
