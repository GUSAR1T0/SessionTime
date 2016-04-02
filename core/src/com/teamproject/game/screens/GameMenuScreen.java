package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constant;

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

        background = new Texture(Gdx.files.internal(Constant.MENU_BACKGROUND));

        readPersonData();
    }

    public void readPersonData() {

        FileHandle file = Gdx.files.local(Constant.PERSON);
        String tmpString = file.readString();

        String name = "";
        int specialty, i = 0;

        while (true) {
            if (tmpString.charAt(i) != '\n') {
                name += tmpString.charAt(i++);
            } else break;
        }

        i++;
        String tmp = "";

        while (true) {
            if (tmpString.length() != i) {
                tmp += tmpString.charAt(i++);
            } else break;
        }

        specialty = Integer.parseInt(tmp);

        //Debugging values of name & specialty
        //P.S. Truly value of length of "name" equals name.length() - 1
//        Gdx.app.log("NAME", name);
//        Gdx.app.log("SPECIALTY", specialty + "");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
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

    }
}