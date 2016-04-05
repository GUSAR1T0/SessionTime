package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Methods;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class is view of game menu.
 * The main operations:
 * - setting stage;
 * - adding logo of game;
 * - creating table and adding buttons;
 * - drawing background and actors.
 */
public class MainMenuScreen implements Screen {

    private STGame game;
    private Stage stage;
    private Sprite logo;
    private Table table;

    public MainMenuScreen(STGame game) {

        this.game = game;

        //Initialization stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Adding elements
        createMenuScreen();
    }

    private void createMenuScreen() {
        //Setting background texture
        game.background = new Texture(Gdx.files.internal(Constants.MENU_BACKGROUND));

        //Setting logo texture
        logo = new Sprite(new Texture(Gdx.files.internal(Constants.LOGO)));
        logo.setSize(logo.getWidth() * 2 * Constants.SCALING_FACTOR,
                logo.getHeight() * 2 * Constants.SCALING_FACTOR);
        logo.setPosition(Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2,
                Gdx.graphics.getHeight() * 6 / 7 - logo.getHeight() / 2);

        //Setting menu buttons
        ImageButton playButton = Methods.makeButton("play_button");
        playButton.setSize(playButton.getWidth() * 2 * Constants.SCALING_FACTOR,
                playButton.getHeight() * 2 * Constants.SCALING_FACTOR);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FileHandle file = Gdx.files.local(Constants.PERSON);

                //If file of PERSON DATA isn't empty then ... else ...
                if (file.length() > 0) game.setScreen(new GameMenuScreen(game));
                else if (file.length() == 0) game.setScreen(new LoginScreen(game));
            }
        });

        ImageButton settingButton = Methods.makeButton("setting_button");
        settingButton.setSize(settingButton.getWidth() * 2 * Constants.SCALING_FACTOR,
                settingButton.getHeight() * 2 * Constants.SCALING_FACTOR);
        settingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SettingScreen(game));
            }
        });

        ImageButton menuButton = Methods.makeButton("menu_button");
        menuButton.setSize(menuButton.getWidth() * 2 * Constants.SCALING_FACTOR,
                menuButton.getHeight() * 2 * Constants.SCALING_FACTOR);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                /* TODO: 02.04.2016, make useful item of MainMenuScreen
                 * This item of menu deletes local file of personal data
                 */
                FileHandle file = Gdx.files.local(Constants.PERSON);
                file.delete();
            }
        });

        //Setting table for menu buttons
        table = new Table();
        table.setFillParent(true);
        table.align(Align.center | Align.bottom);
        table.padBottom(stage.getHeight() / 20);

        //Adding elements on table
        table.add(playButton).height(playButton.getHeight()).width(playButton.getWidth()).expandX();
        table.add(settingButton).height(settingButton.getHeight()).width(settingButton.getWidth()).expandX();
        table.add(menuButton).height(menuButton.getHeight()).width(menuButton.getWidth()).expandX();
    }

    @Override
    public void show() {
        //Adding actor (table) on stage
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        //Clear screen and color blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing all graphic elements (without actors)
        stage.getBatch().begin();
        stage.getBatch().draw(game.background, 0, 0);
        logo.draw(stage.getBatch());
        stage.getBatch().end();

        //Drawing actors
        stage.draw();
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
        logo.getTexture().dispose();
    }
}
