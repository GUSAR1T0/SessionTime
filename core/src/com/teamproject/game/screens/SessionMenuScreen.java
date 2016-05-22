package com.teamproject.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Utils;

/**
 * Created by Anna on 09.05.2016.
 *
 * This class views game menu of session.
 */
public class SessionMenuScreen extends MenuScreen implements Screen {

    public SessionMenuScreen(final STGame game) {
        super(game);
    }

    @Override
    public void createButtonTable() {

        addButtons();

        //Setting table for button of MENU
        buttonTable = new Table();
        buttonTable.setWidth(6 * stage.getWidth());
        buttonTable.setHeight(stage.getHeight() * 7 / 8f);

        for (int i = 1; i < buttons.size; i++)
            buttonTable.add(buttons.get(i)).padLeft(25).padRight(25).expand();

        buttonTable.add(buttons.get(0)).padLeft(25).padRight(25).expand();

        //Setting scroll menu of choosing
        buttonScrollPane = new ScrollPane(buttonTable);
        buttonScrollPane.setWidth(stage.getWidth());
        buttonScrollPane.setHeight(stage.getHeight() * 7 / 8f);
    }

    private void addButtons() {

        //Creating buttons and adding listeners its
        buttons = new Array<ImageTextButton>();

        buttons.add(new ImageTextButton("Вернуться назад", textureData.get(1).style));
        buttons.get(0).addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new GameMenuScreen(game));
            }
        });

        switch (player.getSemester()) {
            case 0: {
                buttons.add(new ImageTextButton("Социология", textureData.get(0).style));
                buttons.add(new ImageTextButton("История России", textureData.get(0).style));
                buttons.add(new ImageTextButton("Зарубежная история", textureData.get(0).style));
                buttons.add(new ImageTextButton("Русский язык", textureData.get(0).style));
                buttons.add(new ImageTextButton("Английский язык", textureData.get(0).style));

                break;
            }
            case 1: {
                switch (player.getValueOfSpecialty()) {
                    case 0: {
                        buttons.add(new ImageTextButton("Биология", textureData.get(0).style));
                        buttons.add(new ImageTextButton("Анатомия", textureData.get(0).style));
                        buttons.add(new ImageTextButton("Латынь", textureData.get(0).style));

                        break;
                    }
                    case 1: {
                        buttons.add(new ImageTextButton("Русский язык", textureData.get(0).style));
                        buttons.add(new ImageTextButton("Культурология", textureData.get(0).style));
                        buttons.add(new ImageTextButton("Литература", textureData.get(0).style));

                        break;
                    }
                    case 2: {
                        buttons.add(new ImageTextButton("Английский язык", textureData.get(0).style));
                        buttons.add(new ImageTextButton("Информатика", textureData.get(0).style));
                        buttons.add(new ImageTextButton("Физика", textureData.get(0).style));

                        break;
                    }
                    case 3: {
                        buttons.add(new ImageTextButton("Экономика", textureData.get(0).style));
                        buttons.add(new ImageTextButton("Философия", textureData.get(0).style));
                        buttons.add(new ImageTextButton("Право", textureData.get(0).style));

                        break;
                    }
                }
            }
        }

        for (int i = 1; i < buttons.size; i++) {
            final int finalI = i;

            buttons.get(i).addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    dispose();
                    game.setScreen(new ExamScreen(game, getIndexOfSubject()[finalI - 1]));
                }
            });
        }
    }

    @Override
    public void setDisabled(int i) {

        buttons.get(i).getColor().a = 0.5f;
        buttons.get(i).setDisabled(true);
    }

    @Override
    public void setIncluded(int i) {

        buttons.get(i).getColor().a = 1f;
        buttons.get(i).setDisabled(false);
    }

    @Override
    public void addActors() {

        //Adding actors on stage
        stage.addActor(resourcesTable);
        stage.addActor(buttonScrollPane);
    }

    @Override
    public void show() {

        //Debugging position of stage
//        stage.setDebug(true);
    }

    @Override
    public void render(float delta) {

        //Setting background color #F2F2F2
        Utils.setBackgroundColor(242/255f, 242/255f, 242/255f, 1);

        //Updating information about time, energy, cash and semester
        updateTime();
        updateEnergy();
        updateCash();
        updateSemester();

        setIndexesOfSubject();
        checkResults(game.indexOfSubject);

        //Checking value of energy
        checkOnGameOver();

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
        game.saveData(game.getParameters(), player);
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
        fontData.dispose();
        fontTime.dispose();
        fontButton.dispose();
        pixmapTableBackground.dispose();
        textureTableBackground.getRegion().getTexture().dispose();

        for (int i = 0; i < 2; i++) {
            textureData.get(i).texture1.getRegion().getTexture().dispose();
            textureData.get(i).texture2.getRegion().getTexture().dispose();
            textureData.get(i).pixmap1.dispose();
            textureData.get(i).pixmap2.dispose();
        }
    }
}