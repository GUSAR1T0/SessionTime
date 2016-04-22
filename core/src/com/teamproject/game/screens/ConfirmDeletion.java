package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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

/**
 * Created by gosha on 19.04.2016.
 */
public class ConfirmDeletion implements Screen {

    private STGame game;
    private Stage stage;
    private BitmapFont font;
    private Table table;
    private Utils.PointerData pointerData;
    private ImageTextButton delButton, noDelButton;

    public ConfirmDeletion(final STGame game){
        this.game = game;
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
        createScreen();
    }


    private void createScreen() {
        
        //Getting font for labels
        font = Utils.getFont("BebasNeue.otf", 58);

        //Setting labels
        Label labelData = new Label("Вы уверены что хотите удалить профиль \n и начать новую игру?",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));

        pointerData = Utils.getImageTextButton((int) (stage.getWidth() / 6),
                (int) (stage.getHeight() * 7 / 55),
                "#445565", "#445565",
                "#f2f2f2", "#F2F2F2",
                font);


//=============================================
        pointerData = Utils.getImageTextButton(
                "red", "#F2F2F2", "#F2F2F2",
                font);
        ImageTextButton.ImageTextButtonStyle style = pointerData.style;
        delButton = new ImageTextButton("Да", style);
        delButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FileHandle file = Gdx.files.local(Constants.PLAYER);
                file.delete();
                dispose();
                game.setScreen(new LoginScreen(game));
            }
        });
        pointerData = Utils.getImageTextButton(
                "green", "#F2F2F2", "#F2F2F2",
                font);
        ImageTextButton.ImageTextButtonStyle style1 = pointerData.style;
        noDelButton = new ImageTextButton("Нет", style1);
        noDelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new SettingScreen(game));
            }
        });

        //Setting table for entering and choosing
        table = new Table();
        table.setFillParent(true);

        //Adding elements on table
        table.add(labelData).colspan(2).center().padBottom(20);
        table.row();
        table.add(delButton).pad(20,0,0,20);
        table.add(noDelButton).pad(20,20,0,0);
    }



    @Override
    public void show() {
        stage.addActor(table);
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

    }
}
