package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;


/**
 * Created by Roman_Mashenkin on 28.03.2016.
 */
public class SettingScreen implements Screen {
    // TODO: 11.04.2016 to make screen of settings with different parameters using new design

    private STGame game;
    private Stage stage;
    private BitmapFont font;
    private Table table;
    private ImageTextButton okButton;

    private Utils.PointerData pointerData;

    public SettingScreen(final STGame game) {

        this.game = game;
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_HEIGHT * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        createSettingScreen();
    }

    private void createSettingScreen() {
        font = Utils.getFont("BebasNeue.otf", 58);

        //Setting labels
        Label labelEnterData = new Label("Настройки игры",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        Label labelNewGame = new Label("Удалить игрока, начать новую игру! - ",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        Label labelVolum = new Label("Громкость музыки:",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));

        pointerData = Utils.getImageTextButton((int) (stage.getWidth() / 6),
                (int) (stage.getHeight() * 7 / 55),
                "#445565", "#445565",
                "#f2f2f2", "#F2F2F2",
                font);
        ImageTextButton.ImageTextButtonStyle styleDel = pointerData.style;
        ImageTextButton DelPlayer = new ImageTextButton("ДА!", styleDel);
        DelPlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new ConfirmDeletion(game));

            }
        });

        //Slider
        Slider slider = new Slider(1, 100, 1, false, game.getSkin(), "default");
        slider.setAnimateDuration(0.3f);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });

        table = new Table();
        table.setFillParent(true);
        table.setPosition(stage.getWidth() / 3f - labelEnterData.getWidth(),
                stage.getHeight() / 5f - labelEnterData.getHeight());
        //adding element on table
        table.add(labelEnterData).center();
        table.row();
        table.add(labelNewGame).colspan(2).pad(60,0,0,0);
        table.add(DelPlayer).pad(60,0,0,0);
        table.row();
        table.add(labelVolum).pad(40,0,20,0);
        table.row();
        table.add(slider).width(stage.getWidth()/2).pad(20,0,20,0);


        //Setting button (for going to MainMenuScreen class)
        pointerData = Utils.getImageTextButton(
                "green", "#F2F2F2", "#F2F2F2",
                font);
        ImageTextButton.ImageTextButtonStyle style = pointerData.style;
        okButton = new ImageTextButton("OK", style);
        okButton.setPosition(stage.getWidth() - okButton.getWidth() - 30,
                stage.getHeight() / 5f - okButton.getHeight() - 10);
        okButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }



    @Override
    public void show() {

        stage.addActor(table);
        stage.addActor(okButton);

    }

    @Override
    public void render(float delta) {

        //Setting background color #445565
        Utils.setBackgroundColor(68/255f, 85/255f, 101/255f, 1);

        //Drawing actors
        stage.draw();

        //Updating of graphic elements
        stage.act(delta);
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
        font.dispose();
    }
}
