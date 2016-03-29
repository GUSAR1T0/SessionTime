package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constant;

/**
 * Created by Roman_Mashenkin on 29.03.2016.
 */
public class LoginScreen implements Screen {

    private Texture background;
    private Stage stage;
    private STGame game;

    public LoginScreen(STGame game) {

        this.game = game;

        background = new Texture(Gdx.files.internal(Constant.MENU_BACKGROUND_2));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        writePersonData("1234", 40);
    }

    private void writePersonData(String name, int specialty) {
        FileHandle file = Gdx.files.local("person.log");
        file.writeString(name + "\n" + specialty, false);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
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
