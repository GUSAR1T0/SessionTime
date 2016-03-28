package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constant;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class is view of game menu:
 * - setting parameters of orthographic camera;
 * - drawing background and images for buttons.
 */
public class MenuScreen implements Screen {

    private Texture background;
    private Sprite logo;
    private Stage stage;
    private final STGame game;

    float textureScale;

    public MenuScreen(STGame game) {

        this.game = game;

        stage = new Stage(new StretchViewport(Constant.WORLD_WIDTH,
                Constant.WORLD_HEIGHT * Constant.RATIO));
        Gdx.input.setInputProcessor(stage);

        createMenuScreen();
    }

    private void createMenuScreen() {
        //Setting background texture
        background = new Texture(Gdx.files.internal(Constant.MENU_BACKGROUND));

        //Setting logo texture
        logo = new Sprite(new Texture(Gdx.files.internal("logo.png")));
        logo.setSize(logo.getWidth() * 2 * Constant.SCALING_FACTOR,
                logo.getHeight() * 2 * Constant.SCALING_FACTOR);
        logo.setPosition(Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2,
                Gdx.graphics.getHeight() * 6 / 7 - logo.getHeight() / 2);
    }

    public float getTextureScale() { return stage.getWidth() / Gdx.graphics.getWidth(); }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        logo.draw(stage.getBatch());
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
        background.dispose();
        stage.dispose();
    }
}
