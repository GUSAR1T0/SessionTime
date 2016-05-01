package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;

import java.util.ArrayList;

/**
 * Created by Roman_Mashenkin on 28.03.2016.
 *
 * This class shows general parameters of settings for game
 */
public class SettingScreen implements Screen {

    private STGame game;
    private Stage stage;
    private Music music;
    private BitmapFont font;
    private Table table;

    private ArrayList<Utils.TextureData> textureData = new ArrayList<Utils.TextureData>();

    public SettingScreen(final STGame game) {

        this.game = game;

        //Initialization stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_HEIGHT * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Getting pointer on background music
        music = game.getGameMusic();

        //Adding elements
        createSettingScreen();
    }

    private void createSettingScreen() {

        //Getting font for labels
        font = Utils.getFont("BebasNeue.otf", 58);

        //Setting labels
        Label labelSetting = new Label("Настройки игры",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        Label labelVolume = new Label("Громкость",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        Label labelNewGame = new Label("Начать новую игру?",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        // TODO: 24.04.2016 Change data of object - labelNew
        Label labelNew = new Label("Ещё один label",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));

        //Setting slider for manage volume of background music
        final Slider slider = new Slider(0, 1, 0.01f, false, game.getSkin(), "default");
        slider.setValue(music.getVolume());
        slider.setAnimateDuration(0.1f);
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                music.setVolume(slider.getValue());
            }
        });

        //Setting objects of ImageTextButton: textureData, index == 0
        textureData.add(Utils.getImageTextButton(1, 1,
                "#F2F2F2", "#666666",
                "#445565", "#F2F2F2",
                font)
        );

        ImageTextButton.ImageTextButtonStyle styleDel = textureData.get(0).style;

        ImageTextButton DelPlayer = new ImageTextButton("ДА!", styleDel);
        DelPlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new ConfirmScreen(game, 0));
            }
        });

        // TODO: 24.04.2016 Change data of object - imageTextButton
        ImageTextButton imageTextButton = new ImageTextButton("НЕТ!", styleDel);
        imageTextButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new ConfirmScreen(game, 1));
            }
        });

        //Setting button (for going to MainMenuScreen class): textureData, index == 1
        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() / 4f),
                (int) (stage.getHeight() / 9f),
                "#76A790", "#666666",
                "#F2F2F2", "#F2F2F2",
                font)
        );

        ImageTextButton.ImageTextButtonStyle style = textureData.get(1).style;

        ImageTextButton buttonOK = new ImageTextButton("OK", style);
        buttonOK.setPosition(stage.getWidth() - buttonOK.getWidth() - 30,
                stage.getHeight() / 5f - buttonOK.getHeight() - 10);
        buttonOK.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        //Setting table
        table = new Table();

        table.setFillParent(true);

        //Adding elements on table
        table.add(labelSetting).colspan(2).center().expand();
        table.row();
        table.add(labelVolume).width(stage.getWidth() / 3f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.add(slider).width(stage.getWidth() / 2f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.row();
        table.add(labelNewGame).width(stage.getWidth() / 3f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.add(DelPlayer).width(stage.getWidth() / 2f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.row();
        table.add(labelNew).width(stage.getWidth() / 3f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.add(imageTextButton).width(stage.getWidth() / 2f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.row();
        table.add(buttonOK).colspan(2).center().expand();
    }

    @Override
    public void show() {

        stage.addActor(table);

        //Debugging position of table
//        table.setDebug(true);
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

        for (int i = 0; i < 2; i++) {
            textureData.get(i).texture1.getRegion().getTexture().dispose();
            textureData.get(i).texture2.getRegion().getTexture().dispose();
            textureData.get(i).pixmap1.dispose();
            textureData.get(i).pixmap2.dispose();
        }
    }
}
