package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;

/**
 * Created by Roman_Mashenkin on 29.03.2016.
 *
 * This class is authorization view.
 */
public class LoginScreen implements Screen {

    private STGame game;
    private Stage stage;
    private Table table;
    private ImageTextButton okButton;

    public LoginScreen(STGame game) {

        this.game = game;

        //Initialization stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Checking keyboard focus on Actors
        stage.getRoot().addCaptureListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!(event.getTarget() instanceof TextField))
                    stage.setKeyboardFocus(null);
                return false;
            }
        });

        //Adding elements
        createLoginScreen();
    }

    private void writePersonData(String name, int specialty) {

        //Writing entering and choosing data of fields in file
        FileHandle file = Gdx.files.local(Constants.PLAYER);
        file.writeString(name + "\n" + specialty, false);
    }

    private void createLoginScreen() {

        Skin skin = game.getSkin();

        //Getting font for labels
        BitmapFont font = Utils.getFont("Bebas_Neue.otf", 58);

        //Setting labels
        Label labelEnterData = new Label("Пожалуйста, для начала игры заполните все поля",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        Label labelName = new Label("Имя:", new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        Label labelSpecialty = new Label("Специальность:",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));

        //Setting text field
        final TextField textField = new TextField("", skin, "default");
        textField.setMaxLength(20);
        textField.setAlignment(Align.center);
        textField.setMessageText("Введите Ваше имя");

        //Setting select box
        final SelectBox<String> selectBox = new SelectBox<String>(skin, "default");
        selectBox.setItems(" Техническая", " Гуманитарная", " Медицинская", " Педагогическая", " Агропромышленная");

        //Setting table for entering and choosing
        table = new Table();
        table.setFillParent(true);

        //Adding elements on table
        table.add(labelEnterData).colspan(2).center().padBottom(20);
        table.row();
        table.add(labelName).center();
        table.add(textField).pad(30).width(4 * stage.getWidth() / 9);
        table.row();
        table.add(labelSpecialty).center();
        table.add(selectBox).pad(30).width(4 * stage.getWidth() / 9);

        //Setting button (for going to MainMenuScreen class)
        ImageTextButton.ImageTextButtonStyle style = Utils.makeImageTextButton(
                "OK_button",
                "#F2F2F2", "#F2F2F2",
                font);
        okButton = new ImageTextButton("OK", style);
        okButton.setPosition(stage.getWidth() - okButton.getWidth() - 30,
                stage.getHeight() / 5 - okButton.getHeight() - 10);
        okButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //If text field isn't empty then ...
                if (textField.getText().length() > 0) {
                    writePersonData(textField.getText(), selectBox.getSelectedIndex());
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        });
    }

    @Override
    public void show() {
        //Adding Actors on stage
        stage.addActor(table);
        stage.addActor(okButton);

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
    }
}
