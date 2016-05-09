package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.teamproject.game.models.Student;

import static com.badlogic.gdx.utils.TimeUtils.millis;

/**
 * Created by Roman_Mashenkin on 29.03.2016.
 *
 * This class shows general fields for filling information about player.
 */
public class LoginScreen implements Screen {

    private STGame game;
    private Stage stage;
    private BitmapFont font;
    private Table table;

    private Utils.TextureData textureData;

    public LoginScreen(final STGame game) {

        this.game = game;

        //Initialization stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Checking keyboard focus on Actors
        stage.getRoot().addCaptureListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (!(event.getTarget() instanceof TextField)) {
                    stage.setKeyboardFocus(null);
                    Gdx.input.setOnscreenKeyboardVisible(false);
                }
                return false;
            }
        });

        //Adding elements
        createLoginScreen();
    }

    private void createLoginScreen() {

        //Getting skin for UIs
        Skin skin = game.getSkin();

        //Getting font for labels
        font = Utils.getFont("BebasNeue.otf", 58);
        Utils.setLinearFilter(font);

        //Setting labels
        Label labelWelcome = new Label("Привет, студент!\n" +
                "Создай новый профиль для входа в игру",
                new Label.LabelStyle(font,  Color.valueOf("#F2F2F2")));
        labelWelcome.setAlignment(Align.center);

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

        String[] specList = new String[Student.getSpecList().size()];

        for (int i = 0; i < Student.getSpecList().size(); i++)
            specList[i] = " " + Student.getSpecList().get(i);

        selectBox.setItems(specList);

        //Setting button (for going to MainMenuScreen class)
        textureData = Utils.getImageTextButton((int) (stage.getWidth() / 4f),
                (int) (stage.getHeight() / 9f),
                "#76A790", "#666666",
                "#F2F2F2", "#F2F2F2",
                font);

        ImageTextButton.ImageTextButtonStyle style = textureData.style;

        ImageTextButton buttonOK = new ImageTextButton("OK", style);
        buttonOK.setPosition(stage.getWidth() - buttonOK.getWidth() - 30,
                stage.getHeight() / 5f - buttonOK.getHeight() - 10);
        buttonOK.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //If text field isn't empty then ...
                if (textField.getText().length() > 0) {
                    game.setPlayerData(new Student(textField.getText(), selectBox.getSelectedIndex(),
                            0, 1000, 100, 0, millis()));
                    dispose();
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        });

        //Setting table for entering and choosing
        table = new Table();

        table.setFillParent(true);

        //Adding elements on table
        table.add(labelWelcome).colspan(2).center().expand();
        table.row();
        table.add(labelName).width(stage.getWidth() / 3f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.add(textField).width(stage.getWidth() / 2f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.row();
        table.add(labelSpecialty).width(stage.getWidth() / 3f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.add(selectBox).width(stage.getWidth() / 2f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.row();
        table.add(buttonOK).colspan(2).center().expand();
    }

    @Override
    public void show() {

        //Adding Actors on stage
        stage.addActor(table);

        //Debugging position of table
//        table.setDebug(true);
    }

    @Override
    public void render(float delta) {

        //Setting background color #445565
        Utils.setBackgroundColor(68/255f, 85/255f, 101/255f, 1);

        //Updating of graphic elements
        stage.act(delta);

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
        font.dispose();
        textureData.texture1.getRegion().getTexture().dispose();
        textureData.texture2.getRegion().getTexture().dispose();
        textureData.pixmap1.dispose();
        textureData.pixmap2.dispose();
    }
}
