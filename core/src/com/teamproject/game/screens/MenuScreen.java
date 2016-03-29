package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constant;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class is view of game menu.
 * The main operations:
 * - setting stage;
 * - creating table and adding buttons;
 * - drawing background, images for logo and buttons.
 */
public class MenuScreen implements Screen {

    private Texture background;
    private Sprite logo;
    private Stage stage;
    private ImageButton playButton, settingButton, menuButton;
    private Table table;
    private final STGame game;

    public MenuScreen(STGame game) {

        this.game = game;

        stage = new Stage();
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

        //Setting menu buttons
        playButton = makeButton("play_button");
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FileHandle file = Gdx.files.internal("person.log");

                if (file.length() > 0) game.setScreen(new GameMenuScreen(game));
                else if (file.length() == 0) game.setScreen(new LoginScreen(game));
            }
        });

        settingButton = makeButton("setting_button");
        settingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SettingScreen(game));
            }
        });

        menuButton = makeButton("menu_button");
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameMenuScreen(game));
            }
        });

        //Setting table for menu buttons
        table = new Table();
        table.setFillParent(true);
        table.align(Align.center | Align.bottom);
        table.padBottom(stage.getHeight() / 20);
    }

    private ImageButton makeButton(String name) {
        //Creating button for menu
        TextureRegionDrawable buttonUp =
                new TextureRegionDrawable(new TextureRegion(new Texture(name + ".png")));
        TextureRegionDrawable buttonDown =
                new TextureRegionDrawable(new TextureRegion(new Texture(name + "_pressed.png")));

        ImageButton button = new ImageButton(buttonUp, buttonDown);
        button.setSize(button.getWidth() * 2 * Constant.SCALING_FACTOR,
                button.getHeight() * 2 * Constant.SCALING_FACTOR);

        return button;
    }

    @Override
    public void show() {
        //Adding elements on table
        table.add(playButton).height(playButton.getHeight()).expandX();
        table.add(settingButton).height(settingButton.getHeight()).expandX();
        table.add(menuButton).height(menuButton.getHeight()).expandX();

        //Adding Actor on Stage
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        //Clear screen and color blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing all graphic elements
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0);
        logo.draw(stage.getBatch());
        stage.getBatch().end();

        stage.draw();               //Drawing Actors
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
