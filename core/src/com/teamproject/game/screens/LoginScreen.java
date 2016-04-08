package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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
 * The main operations:
 * - setting stage;
 * - creating table with labels, text field and select box;
 * - adding button "OK" on stage;
 * - drawing background and actors.
 */
public class LoginScreen implements Screen {

    private STGame game;
    private Stage stage;
    private Table table;
    private ImageButton okButton;

    public LoginScreen(STGame game) {

        this.game = game;

        //Clear screen and color red
        Gdx.gl.glClearColor(0.5f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        FileHandle file = Gdx.files.local(Constants.PERSON);
        file.writeString(name + "\n" + specialty, false);
    }

    private void createLoginScreen() {
        Skin skin = game.getSkin();

        //Getting font for labels
        BitmapFont font = Utils.getFont("Bebas_Neue.otf", 76);

        //Setting labels
        Label labelEnterData = new Label("Введите данные",
                new Label.LabelStyle(font, Color.valueOf("#FFF971")));
        Label labelName = new Label("Имя:", new Label.LabelStyle(font, Color.valueOf("#FFF971")));
        Label labelSpecialty = new Label("Специальность:",
                new Label.LabelStyle(font, Color.valueOf("#FFF971")));

        //Setting text field
        final TextField textField = new TextField("", skin, "default");
        textField.setMaxLength(14);
        textField.setAlignment(Align.center);
        textField.setMessageText("Введите Ваше имя");
        textField.setColor(skin.getColor("white"));

        //Setting select box
        final SelectBox<String> selectBox = new SelectBox<String>(skin, "default");
        selectBox.setItems(" Техническая", " Гуманитарная");

        //Setting table for entering and choosing
        table = new Table();
        table.setFillParent(true);

        //Adding elements on table
        table.add(labelEnterData).colspan(2).center().padBottom(30);
        table.row();
        table.add(labelName).center();
        table.add(textField).pad(30).width(4 * stage.getWidth() / 9);
        table.row();
        table.add(labelSpecialty).center();
        table.add(selectBox).pad(30).width(4 * stage.getWidth() / 9);

        //Setting button (for going to GameMenuScreen class)
        okButton = Utils.makeButton("OK_button");
        okButton.setSize(okButton.getWidth() / 2, okButton.getHeight() / 2);
        okButton.setPosition(stage.getWidth() - okButton.getWidth() - 30,
                stage.getHeight() / 5 - okButton.getHeight());
        okButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //If text field isn't empty then ...
                if (textField.getText().length() > 0) {
                    writePersonData(textField.getText(), selectBox.getSelectedIndex());
                    game.setScreen(new GameMenuScreen(game));
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
        //Drawing all graphic elements (without actors)
        stage.getBatch().begin();
        stage.getBatch().draw(game.getBackground(), 0, 0);
        stage.getBatch().end();

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
