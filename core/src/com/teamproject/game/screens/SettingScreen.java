package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;

/**
 * Created by Roman_Mashenkin on 28.03.2016.
 */
public class SettingScreen implements Screen {
    // TODO: 11.04.2016 to make screen of settings with different parameters using new design

    private Stage stage;
    private STGame game;

    public SettingScreen(STGame game) {

        this.game = game;

        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_HEIGHT * Constants.RATIO));
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
