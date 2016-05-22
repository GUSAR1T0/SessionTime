package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.teamproject.game.models.Student;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.utils.TimeUtils.millis;

/**
 * Created by Roman_Mashenkin on 20.05.2016.
 *
 * This class makes base of menu screens of game.
 */
abstract class MenuScreen implements Screen {

    public STGame game;
    public Stage stage;
    public BitmapFont fontData;
    public BitmapFont fontTime;
    public BitmapFont fontButton;
    public Label labelCash;
    public Label labelTime;
    public Label labelEnergy;
    public Label labelSemester;
    public Table resourcesTable;
    public Table buttonTable;
    public ScrollPane buttonScrollPane;
    public Pixmap pixmapTableBackground;
    public TextureRegionDrawable textureTableBackground;
    public Array<ImageTextButton> buttons;

    public Array<Utils.TextureData> textureData = new Array<Utils.TextureData>();
    public Utils.TimeData time;
    public Student player;

    public boolean[] isActiveAction;
    public int timer;
    public int salary = 1000;

    public MenuScreen(final STGame game) {

        this.game = game;

        //Initialization of stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Getting data from files
        isActiveAction = this.game.getParameters().getIsActiveAction();
        player = this.game.getPlayerData();

        //Adding elements
        setButtonStyles();
        createDataTable();
        createButtonTable();

        //Adding actors
        addActors();
    }

    public void createDataTable() {

        //Getting fonts for labels
        fontData = Utils.getFont("BebasNeue.otf", 54);
        Utils.setLinearFilter(fontData);
        fontTime = Utils.getFont("BebasNeue.otf", 36);
        Utils.setLinearFilter(fontTime);

        //Adding icon of CASH and ENERGY
        Image iconCoin = new Image(game.getManager().get(Constants.ICON_COIN, Texture.class));
        Image iconEnergy = new Image(game.getManager().get(Constants.ICON_ENERGY, Texture.class));
        Image iconSemester = new Image(game.getManager().get(Constants.LIGHTGRAY_STAR, Texture.class));
        Image iconTime = new Image(game.getManager().get(Constants.ICON_TIME, Texture.class));

        //Adding labels for show information about cash and energy
        labelSemester = new Label("", new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
        labelSemester.setAlignment(Align.center);

        labelCash = new Label(player.getCash() + "",
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
        labelCash.setAlignment(Align.center);

        labelEnergy = new Label("", new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
        labelEnergy.setAlignment(Align.center);

        labelTime = new Label("", new Label.LabelStyle(fontTime, Color.valueOf("#F2F2F2")));
        labelTime.setAlignment(Align.center);

        //Setting background of information tables
        pixmapTableBackground = Utils.setPixmapColor(1, 1, "#CC4B4B");
        textureTableBackground = new TextureRegionDrawable(new TextureRegion(
                new Texture(pixmapTableBackground)));

        //Setting table for resources data of PLAYER
        resourcesTable = new Table();
        resourcesTable.setWidth(stage.getWidth());
        resourcesTable.setHeight(stage.getHeight() * 1 / 8f);
        resourcesTable.setPosition(0, stage.getHeight() * 7 / 8f);
        resourcesTable.setBackground(textureTableBackground);

        resourcesTable.add(iconSemester).width(stage.getWidth() * 6 / 150f).
                height(stage.getWidth() * 6 / 150f).expand();
        resourcesTable.add(labelSemester).width(stage.getWidth() / 8f).expand();
        resourcesTable.add(iconCoin).width(stage.getWidth() / 18f).
                height(stage.getWidth() / 18f).expand();
        resourcesTable.add(labelCash).width(stage.getWidth() / 8f).expand();
        resourcesTable.add(iconEnergy).width(stage.getWidth() / 12f).
                height(stage.getWidth() / 22f).expand();
        resourcesTable.add(labelEnergy).width(stage.getWidth() / 8f).expand();
        resourcesTable.add(iconTime).width(stage.getWidth() / 18f).
                height(stage.getWidth() / 18f).expand();
        resourcesTable.add(labelTime).width(stage.getWidth() / 8f).expand();
    }

    public abstract void createButtonTable();

    public void setButtonStyles() {

        //Getting font for text on buttons
        fontButton = Utils.getFont("BebasNeue.otf", 58);
        Utils.setLinearFilter(fontButton);

        //Creating and adding style for major ImageTextButton
        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() * 7 / 16f),
                (int) (stage.getHeight() * 3 / 4f),
                "#445565", "#F2F2F2",
                "#F2F2F2", "#666666",
                fontButton));

        //Creating and adding style for button to back
        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() * 5 / 8f),
                (int) (stage.getHeight() * 3 / 4f),
                "#445565", "#CC4B4B",
                "#F2F2F2", "#F2F2F2",
                fontButton));
    }

    public void setIndexesOfSubject() {

        switch (player.getSemester()) {
            case 0:
                game.indexOfSubject = new int[]{0, 1, 2, 3, 4};
                break;
            case 1:
                switch (player.getValueOfSpecialty()) {
                    case 0:
                        game.indexOfSubject = new int[]{7, 8, 9};
                        break;
                    case 1:
                        game.indexOfSubject = new int[]{3, 6, 10};
                        break;
                    case 2:
                        game.indexOfSubject = new int[]{4, 13, 14};
                        break;
                    case 3:
                        game.indexOfSubject = new int[]{5, 11, 12};
                        break;
                }
        }
    }

    public int[] getIndexOfSubject() {
        return game.indexOfSubject;
    }

    public void updateEnergy() {

        Utils.updateEnergy(player, isActiveAction);

        labelEnergy.setText(Constants.DF_PERCENT.format(player.getEnergy()));
    }

    public void updateTime() {

        //Updating time and viewing it on labels
        time = Utils.getTimeData(millis() - player.getTime()[0]);
        Utils.updateTimeOnLabel(time, labelTime);

        if (isActiveAction[0] && (time.days == game.getParameters().getDayOfAction())) {
            timer = 14 - time.hours;

            if (timer > 0) {
                player.getTime()[0] -= Constants.INCREASE_TIME;
                time = Utils.getTimeData(millis() - player.getTime()[0]);
                Utils.updateTimeOnLabel(time, labelTime);
                timer = 14 - time.hours;
            } else {
                if (timer == 0) {
                    time = Utils.getTimeData(millis() - player.getTime()[0]);
                    Utils.updateTimeOnLabel(time, labelTime);
                    isActiveAction[0] = false;
                } else {
                    isActiveAction[0] = false;
                    game.getParameters().setDayOfAction(time.days);
                }
            }
        } else isActiveAction[0] = false;

        if (isActiveAction[1] && (time.days == game.getParameters().getDayOfAction())) {
            timer = 18 - time.hours;

            if (timer > 0) {
                player.getTime()[0] -= Constants.INCREASE_TIME;
                time = Utils.getTimeData(millis() - player.getTime()[0]);
                Utils.updateTimeOnLabel(time, labelTime);
                timer = 18 - time.hours;
            } else {
                if (timer == 0) {
                    time = Utils.getTimeData(millis() - player.getTime()[0]);
                    Utils.updateTimeOnLabel(time, labelTime);
                    isActiveAction[1] = false;
                } else {
                    isActiveAction[1] = false;
                    game.getParameters().setDayOfAction(time.days);
                }
            }
        } else isActiveAction[1] = false;

        if (!isActiveAction[0] && !isActiveAction[1])
            game.getParameters().setDayOfAction(time.days);
    }

    public void updateSemester() {

        Utils.updateSemester(player, time, game.indexOfSubject);

        labelSemester.setText(player.getSemester() + "");
    }

    public void updateCash() {

        Utils.updateCash(game, time);

        labelCash.setText(player.getCash() + "");
    }

    public void checkResults(int[] indexOfSubject) {

        if (Utils.isSession((time.days + "").length(), time.days)) {
            boolean flag = true;
            int i = 1;

            for (int anIndexOfSubject : indexOfSubject) {

                String path = Constants.STATISTICS +
                        game.getPlayerData().getSemester() + "_";

                switch (anIndexOfSubject) {
                    case 0:
                        path += Constants.SOCIOLOGY;
                        break;
                    case 1:
                        path += Constants.HISTORY_RU;
                        break;
                    case 2:
                        path += Constants.HISTORY_WORLD;
                        break;
                    case 3:
                        path += Constants.RUSSIAN;
                        break;
                    case 4:
                        path += Constants.ENGLISH;
                        break;
                    case 5:
                        path += Constants.ECONOMICS;
                        break;
                    case 6:
                        path += Constants.CULTURAL_STUDIES;
                        break;
                    case 7:
                        path += Constants.BIOLOGY;
                        break;
                    case 8:
                        path += Constants.ANATOMY;
                        break;
                    case 9:
                        path += Constants.LATIN;
                        break;
                    case 10:
                        path += Constants.LITERATURE;
                        break;
                    case 11:
                        path += Constants.PHILOSOPHY;
                        break;
                    case 12:
                        path += Constants.JURISPRUDENCE;
                        break;
                    case 13:
                        path += Constants.COMPUTER_SCIENCE;
                        break;
                    case 14:
                        path += Constants.PHYSICS;
                        break;
                }

                FileHandle file = Gdx.files.local(path);

                if (Utils.isEmpty(path)) {
                    file.writeString(0f + "", false);
                    flag = false;
                } else {
                    String stringStream = file.readString();

                    float mark = Float.parseFloat(stringStream);

                    if (mark > 0.4f) {
                        setDisabled(i);
                    } else {
                        setIncluded(i);
                        flag = false;
                    }

                    i++;
                }
            }

            if (flag) player.setFlag(true);
            else player.setFlag(false);
        }
    }

    /* Method needed for SessionMenuScreen */
    public abstract void setDisabled(int i);

    /* Method needed for SessionMenuScreen */
    public abstract void setIncluded(int i);

    public void checkOnGameOver() {

        //Checking value:
        // 1) of energy - equal "0"
        // 2) of flag on session - equal "false" in non-session time
        if (player.getEnergy() == 0) {
            for (int i = 0; i < buttons.size; i++)
                buttons.get(i).setDisabled(true);

            stage.getRoot().getColor().a = 1;

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(fadeOut(2f));
            sequenceAction.addAction(run(new Runnable() {
                @Override
                public void run() {
                    dispose();
                    game.setScreen(new DialogScreen(game, 1));
                }
            }));

            stage.getRoot().addAction(sequenceAction);
        }

        if (!player.isFlag() && !Utils.isSession((time.days + "").length(), time.days)) {
            for (int i = 0; i < buttons.size; i++)
                buttons.get(i).setDisabled(true);

            stage.getRoot().getColor().a = 1;

            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(fadeOut(2f));
            sequenceAction.addAction(run(new Runnable() {
                @Override
                public void run() {
                    dispose();
                    game.setScreen(new DialogScreen(game, 2));
                }
            }));

            stage.getRoot().addAction(sequenceAction);
        }
    }

    public void addActors() {

        //Adding actors on stage
        stage.addActor(resourcesTable);
        stage.addActor(buttonScrollPane);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
