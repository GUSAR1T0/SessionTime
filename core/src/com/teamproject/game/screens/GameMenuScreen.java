package com.teamproject.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;

import static com.badlogic.gdx.utils.TimeUtils.millis;

/**
 * Created by Roman_Mashenkin on 28.03.2016.
 *
 * This class views game menu: buttons for doing game process and state of player.
 */
public class GameMenuScreen extends MenuScreen implements Screen {

    public GameMenuScreen(final STGame game) {
        super(game);
    }

    @Override
    public void createButtonTable() {

        //Creating buttons and adding listeners its
        buttons = new Array<ImageTextButton>();

        buttons.add(new ImageTextButton("Учёба/Сессия", textureData.get(0).style));
        buttons.add(new ImageTextButton("Работа", textureData.get(0).style));
        buttons.add(new ImageTextButton("Отдых", textureData.get(0).style));
        buttons.add(new ImageTextButton("Перекусить", textureData.get(0).style));
        buttons.add(new ImageTextButton("Развлечения", textureData.get(0).style));
        buttons.add(new ImageTextButton("Вернуться в главное меню", textureData.get(1).style));

        buttons.get(0).addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Utils.isSession((time.days + "").length(), time.days)) {
                    dispose();
                    game.setScreen(new SessionMenuScreen(game));
                } else if (!isActiveAction[0] && !isActiveAction[1]) {
                    int[] attendance = new int[2];
                    attendance[0] = player.getAttendance()[0] + 1;
                    attendance[1] = player.getAttendance()[1];
                    player.setAttendance(attendance);

                    isActiveAction[0] = true;
                    timer = 14 - time.hours;
                }
            }
        });

        buttons.get(1).addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (!isActiveAction[1] && !isActiveAction[0]) {
                    int[] attendance = new int[2];
                    attendance[0] = player.getAttendance()[0];
                    attendance[1] = player.getAttendance()[1] + 1;
                    player.setAttendance(attendance);

                    isActiveAction[1] = true;
                    timer = 18 - time.hours;

                    if (player.getAttendance()[1] % 15 == 0) {
                        player.setCash(player.getCash() + salary);
                        labelCash.setText(player.getCash() + "");
                    }
                }
            }
        });

        buttons.get(3).addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (player.getCash() > 0) {
                    player.setCash(player.getCash() - 100);
                    labelCash.setText(player.getCash() + "");

                    if (player.getEnergy() < 0.9f)
                        player.setEnergy(player.getEnergy() + 0.1f);
                    else if (player.getEnergy() < 1f)
                        player.setEnergy(1f);

                    labelEnergy.setText(player.getEnergy() + "");
                }
            }
        });

        buttons.get(4).addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (player.getCash() > 0) {
                    player.setCash(player.getCash() - 300);
                    labelCash.setText(player.getCash() + "");

                    if (player.getEnergy() < 0.75f)
                        player.setEnergy(player.getEnergy() + 0.25f);
                    else if (player.getEnergy() < 1f)
                        player.setEnergy(1f);

                    labelEnergy.setText(player.getEnergy() + "");
                }
            }
        });

        buttons.get(5).addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        //Setting table for button of MENU
        buttonTable = new Table();
        buttonTable.setWidth(stage.getWidth());
        buttonTable.setHeight(stage.getHeight() * 7 / 8f);

        buttonTable.add(buttons.get(0)).padLeft(25).padRight(25).expand();
        buttonTable.add(buttons.get(1)).padLeft(25).padRight(25).expand();
        buttonTable.add(buttons.get(2)).padLeft(25).padRight(25).expand();
        buttonTable.add(buttons.get(3)).padLeft(25).padRight(25).expand();
        buttonTable.add(buttons.get(4)).padLeft(25).padRight(25).expand();
        buttonTable.add(buttons.get(5)).padLeft(25).padRight(25).expand();

        buttonScrollPane = new ScrollPane(buttonTable);
        buttonScrollPane.setWidth(stage.getWidth());
        buttonScrollPane.setHeight(stage.getHeight() * 7 / 8f);
    }

    @Override
    public void updateEnergy() {
        super.updateEnergy();

        if (buttons.get(2).isPressed()) {
            if (player.getEnergy() +
                    Constants.INCREASE_TIME / 2E3f * Constants.DECREASE_ENERGY < 1f)
                player.setEnergy(player.getEnergy() +
                        Constants.INCREASE_TIME / 2E3f * Constants.DECREASE_ENERGY);
            else player.setEnergy(1f);
        }

        labelEnergy.setText(Constants.DF_PERCENT.format(player.getEnergy()));
    }

    @Override
    public void updateTime() {
        super.updateTime();

        if (buttons.get(2).isPressed() && !isActiveAction[0] && !isActiveAction[1]) {
            player.getTime()[0] -= Constants.INCREASE_TIME;
            time = Utils.getTimeData(millis() - player.getTime()[0]);
            Utils.updateTimeOnLabel(time, labelTime);
        }
    }

    @Override
    public void setDisabled(int i) {}

    @Override
    public void setIncluded(int i) {}

    public void manageButtons() {

        //Managing button "Учёба/Сессия"
        if ((player.getSemester() < Constants.COUNT_AVAILABLE_SEMESTERS) &&
                ((time.hours >= 8) && (time.hours < 14) && !isActiveAction[0])) {
            buttons.get(0).getColor().a = 1f;
            buttons.get(0).setDisabled(false);
        } else {
            buttons.get(0).getColor().a = 0.5f;
            buttons.get(0).setDisabled(true);
        }

        //Managing button "Работа"
        if ((time.hours >= 10) && (time.hours < 18) && !isActiveAction[1]) {
            buttons.get(1).getColor().a = 1f;
            buttons.get(1).setDisabled(false);
        } else {
            buttons.get(1).getColor().a = 0.5f;
            buttons.get(1).setDisabled(true);
        }

        if (player.getCash() - 100 > 0) {
            buttons.get(3).getColor().a = 1f;
            buttons.get(3).setDisabled(false);
        } else {
            buttons.get(3).getColor().a = 0.5f;
            buttons.get(3).setDisabled(true);
        }

        if (player.getCash() - 300 > 0) {
            buttons.get(4).getColor().a = 1f;
            buttons.get(4).setDisabled(false);
        } else {
            buttons.get(4).getColor().a = 0.5f;
            buttons.get(4).setDisabled(true);
        }
    }

    public void setTextOfEducation() {

        if (time.days < Constants.COUNT_AVAILABLE_SEMESTERS * 100 + 1) {
            if (Utils.isSession((time.days + "").length(), time.days))
                buttons.get(0).setText("Сессия");
            else
                buttons.get(0).setText("Учёба");
        } else
            buttons.get(0).setText("  Следующие этапы игры на стадии разработки  ");
    }

    @Override
    public void show() {

        //Debugging all position of stage elements
//        stage.setDebugAll(true);
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

        //Setting of subject sets and check result of session
        setIndexesOfSubject();
        checkResults(game.indexOfSubject);

        //Checking value of energy
        checkOnGameOver();

        //Controlling buttons for click
        manageButtons();

        //Setting text on button "Учёба/Сессия"
        setTextOfEducation();

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
