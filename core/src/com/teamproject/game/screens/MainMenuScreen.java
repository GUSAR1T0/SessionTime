package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class is view of game menu.
 */
public class MainMenuScreen implements Screen {

    private class PlayerData {

        String name;
        int specialty;

        PlayerData(String name, int specialty) {

            this.name = name;
            this.specialty = specialty;
        }
    }

    private STGame game;
    private Stage stage;
    private Table playerTable;
    private Table buttonTable;

    public MainMenuScreen(STGame game) {

        this.game = game;

        //Initialization stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Adding elements
        createDataScreen();
        createButtonScreen();
    }

    private void createDataScreen() {

        //Reading data of player from local file
        MainMenuScreen.PlayerData player = readPlayerData();

        //Getting font for labels
        BitmapFont font = Utils.getFont("Bebas_Neue.otf", 46);

        //Label shows name of player
        Label labelPlayerName = new Label(player.name,
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));

        //Adding icon of player
        Image icon = new Image(new Texture(Gdx.files.internal(Constants.ICON_CAT)));

        //Setting table for data of PLAYER
        playerTable = new Table();

        playerTable.setWidth(stage.getWidth() * 3/8f);
        playerTable.setHeight(stage.getHeight());

        playerTable.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture(Utils.setPixmapColor(1, 1, "#CC4B4B")))));

        playerTable.add(icon).width(3/16f * stage.getWidth()).
                height(3/16f * stage.getWidth());
        playerTable.row();
        playerTable.add(labelPlayerName).padTop(stage.getHeight() / 10f);
    }

    private void createButtonScreen() {

        //Getting font for text on buttons
        BitmapFont font = Utils.getFont("Bebas_Neue.otf", 58);

        //Creating style for ImageTextButton
        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(
                Utils.makeImageTextButton((int) (stage.getWidth() / 2),
                        (int) (stage.getHeight() * 7 / 48),
                        "#F2F2F2", "#666666",
                        "#445565", "#F2F2F2",
                        font)
        );

        //Creating ImageTextButtons
        ImageTextButton playButton = new ImageTextButton("Играть", style);

        ImageTextButton statisticsButton = new ImageTextButton("Статистика", style);

        ImageTextButton settingsButton = new ImageTextButton("Настройки", style);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FileHandle file = Gdx.files.local(Constants.PLAYER);
                file.delete();
                game.setScreen(new LoginScreen(game));
            }
        });

        //Setting table for button of MENU
        buttonTable = new Table();

        buttonTable.setPosition(stage.getWidth() * 3/8f, 0);
        buttonTable.setWidth(stage.getWidth() * 5/8f);
        buttonTable.setHeight(stage.getHeight());

        buttonTable.add(playButton).expand();
        buttonTable.row();
        buttonTable.add(statisticsButton).expand();
        buttonTable.row();
        buttonTable.add(settingsButton).expand();
    }

    public PlayerData readPlayerData() {

        //Opening file to read player data
        FileHandle file = Gdx.files.local(Constants.PLAYER);
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

        return new PlayerData(name, specialty);
    }

    @Override
    public void show() {

        //Adding actor (table) on stage
        stage.addActor(playerTable);
        stage.addActor(buttonTable);
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
    }
}
