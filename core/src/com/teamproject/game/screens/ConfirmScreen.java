package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;

import java.util.ArrayList;

/**
 * Created by gosha on 19.04.2016.
 *
 * This class makes screen for confirming some actions of user
 */
public class ConfirmScreen implements Screen {

    private STGame game;
    private Stage stage;
    private BitmapFont font;
    private Table table;
    private ImageTextButton buttonRed;
    private ImageTextButton buttonGreen;

    private ArrayList<Utils.TextureData> textureData = new ArrayList<Utils.TextureData>();
    private ArrayList<ImageTextButton.ImageTextButtonStyle> style =
            new ArrayList<ImageTextButton.ImageTextButtonStyle>();

    public ConfirmScreen(final STGame game, int index) {

        this.game = game;

        //Initialization stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Adding elements
        createConfirmScreen(index);
    }

    private void createConfirmScreen(int index) {

        //Getting font for labels
        font = Utils.getFont("BebasNeue.otf", 58);

        //Adding icon
        Image imageIconPlayer = addIcon(index);

        //Setting labels
        Label labelWarningText = new Label(getMessageText(index),
                new Label.LabelStyle(font, Color.valueOf("#445565")));
        labelWarningText.setAlignment(Align.center);

        //Setting button of red color: textureData, index == 0
        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() / 4f),
                (int) (stage.getHeight() / 9f),
                "#CC4B4B", "#666666",
                "#F2F2F2", "#F2F2F2",
                font)
        );

        style.add(textureData.get(0).style);

        buttonRed = new ImageTextButton("Да", style.get(0));

        //Setting button of green color: textureData, index == 1
        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() / 4f),
                (int) (stage.getHeight() / 9f),
                "#76A790", "#666666",
                "#F2F2F2", "#F2F2F2",
                font)
        );

        style.add(textureData.get(1).style);

        buttonGreen = new ImageTextButton("Нет", style.get(1));

        //Adding listeners for buttons
        addListeners(index);

        //Setting table for entering and choosing
        table = new Table();
        table.setFillParent(true);

        //Adding elements on table
        table.add(imageIconPlayer).width(3 / 16f * stage.getWidth()).
                height(3 / 16f * stage.getWidth()).colspan(2).center().expandX();
        table.row();
        table.add(labelWarningText).pad(20).colspan(2).center().expandX();
        table.row();
        table.add(buttonRed).width(stage.getWidth() / 3f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.add(buttonGreen).width(stage.getWidth() / 3f).height(stage.getHeight() / 9f).
                pad(20).expandX();
    }

    private Image addIcon(int index) {

        //Getting icon
        // TODO: 24.04.2016 Add variants of icons
        switch (index) {
            case 0:
                return new Image(game.getManager().get(Constants.ICON_CAT, Texture.class));
            case 1:
                return new Image(game.getManager().get(Constants.ICON_LOGO, Texture.class));
            default:
                return new Image();
        }
    }

    private String getMessageText(int index) {
        
        //Getting message
        // TODO: 24.04.2016 Add variants of messages
        switch (index) {
            case 0:
                return "Вы уверены, что хотите удалить профиль\n" +
                        "и начать новую игру?";
            case 1:
                return "Проверка ConfirmScreen на корректность работы...";
            default:
                return "";
        }
    }

    private void addListeners(final int index) {

        //Adding listener for buttons
        // TODO: 24.04.2016 Add variants of listeners 
        buttonRed.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switch (index) {
                    case 0:
                        FileHandle file = Gdx.files.local(Constants.PLAYER);
                        file.delete();
                        dispose();
                        game.setScreen(new LoginScreen(game));
                        break;
                    case 1:
                        dispose();
                        game.setScreen(new MainMenuScreen(game));
                        break;
                }
            }
        });

        buttonGreen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switch (index) {
                    case 0:
                        dispose();
                        game.setScreen(new SettingScreen(game));
                        break;
                    case 1:
                        dispose();
                        game.setScreen(new SettingScreen(game));
                        break;
                }
            }
        });
    }

    @Override
    public void show() {

        //Adding Actors on stage
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {

        //Setting background color #445565
        Utils.setBackgroundColor(242/255f, 242/255f, 242/255f, 1);

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
