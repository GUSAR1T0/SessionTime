package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.teamproject.game.models.Student;

import static com.badlogic.gdx.utils.TimeUtils.millis;

/**
 * Created by gosha on 19.04.2016.
 *
 * This class makes screen for confirming some actions of user
 */
public class DialogScreen implements Screen {

    private STGame game;
    private Stage stage;
    private BitmapFont font;
    private Table table;
    private Label labelText;
    private ImageTextButton buttonRed;
    private ImageTextButton buttonGreen;

    private Array<Utils.TextureData> textureData = new Array<Utils.TextureData>();
    private Array<ImageTextButton.ImageTextButtonStyle> style =
            new Array<ImageTextButton.ImageTextButtonStyle>();

    private boolean flagOnExit = false;

    public DialogScreen(final STGame game, int index) {

        this.game = game;

        //Initialization of stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Adding elements
        createConfirmScreen(index);
    }

    private void createConfirmScreen(int index) {

        //Getting font for labels
        font = Utils.getFont("BebasNeue.otf", 58);
        Utils.setLinearFilter(font);

        //Adding icon
        Image imageIconPlayer = addIcon(index);

        //Setting text on buttons
        String[] buttonMessage = getButtonMessage(index);

        //Setting labels
        labelText = new Label(getMessageText(index),
                new Label.LabelStyle(font, Color.valueOf("#445565")));
        labelText.setAlignment(Align.center);

        //Setting button of red color: textureData, index == 0
        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() / 4f),
                (int) (stage.getHeight() / 9f),
                "#CC4B4B", "#666666",
                "#F2F2F2", "#F2F2F2",
                font)
        );

        style.add(textureData.get(0).style);

        buttonRed = new ImageTextButton(buttonMessage[0], style.get(0));

        //Setting button of green color: textureData, index == 1
        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() / 4f),
                (int) (stage.getHeight() / 9f),
                "#76A790", "#666666",
                "#F2F2F2", "#F2F2F2",
                font)
        );

        style.add(textureData.get(1).style);

        buttonGreen = new ImageTextButton(buttonMessage[1], style.get(1));

        //Adding listeners for buttons
        addListeners(index);

        //Setting table for entering and choosing
        table = new Table();
        table.setFillParent(true);

        //Adding elements on table
        table.add(imageIconPlayer).width(3 / 16f * stage.getWidth()).
                height(3 / 16f * stage.getWidth()).colspan(2).center().expandX();
        table.row();
        table.add(labelText).pad(20).colspan(2).center().expandX();
        table.row();
        table.add(buttonRed).width(stage.getWidth() / 3f).height(stage.getHeight() / 9f).
                pad(20).expandX();
        table.add(buttonGreen).width(stage.getWidth() / 3f).height(stage.getHeight() / 9f).
                pad(20).expandX();
    }

    private Image addIcon(int index) {

        //Getting icon
        switch (index) {
            case 0:
                return new Image(game.getManager().get(Constants.ICON_CAT, Texture.class));
            case 1:
                return new Image(game.getManager().get(Constants.ICON_CAT, Texture.class));
            case 2:
                return new Image(game.getManager().get(Constants.ICON_CAT, Texture.class));
            default:
                return new Image();
        }
    }

    private String[] getButtonMessage(int index) {

        //Getting current message on buttons
        switch (index) {
            case 0:
                return new String[]{"Да, определённо", "Нет, пожалуй"};
            case 1:
                return new String[]{"Да, хорошо", "Нет, не сейчас"};
            case 2:
                return new String[]{"Да, хорошо", "Нет, не сейчас"};
            default:
                return new String[]{"", ""};
        }
    }

    private String getMessageText(int index) {
        
        //Getting message
        switch (index) {
            case 0:
                return "Вы уверены, что хотите удалить профиль\n" +
                        "и начать новую игру?";
            case 1:
                return "К сожалению, студент, твоя энергия на нуле...\n" +
                        "Может быть, начнём всё сначала?";
            case 2:
                return "К сожалению, студент, ты не закрыл сессию...\n" +
                        "Может быть, начнём всё сначала?";
            default:
                return "";
        }
    }

    private void addListeners(final int index) {

        //Adding listener for buttons
        buttonRed.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switch (index) {
                    case 0: {
                        FileHandle fileStudentData = Gdx.files.local(Constants.PLAYER);
                        fileStudentData.delete();
                        FileHandle fileParameters = Gdx.files.local(Constants.PARAMETERS);
                        fileParameters.delete();
                        FileHandle fileStatistics = Gdx.files.local(Constants.STATISTICS);
                        fileStatistics.deleteDirectory();

                        dispose();
                        game.setScreen(new LoginScreen(game));
                        break;
                    }
                    case 1: {
                        FileHandle fileStudentData = Gdx.files.local(Constants.PLAYER);
                        fileStudentData.delete();
                        FileHandle fileParameters = Gdx.files.local(Constants.PARAMETERS);
                        fileParameters.delete();
                        FileHandle fileStatistics = Gdx.files.local(Constants.STATISTICS);
                        fileStatistics.deleteDirectory();

                        dispose();
                        game.setScreen(new LoginScreen(game));
                        break;
                    }
                    case 2: {
                        FileHandle fileStudentData = Gdx.files.local(Constants.PLAYER);
                        fileStudentData.delete();
                        FileHandle fileParameters = Gdx.files.local(Constants.PARAMETERS);
                        fileParameters.delete();
                        FileHandle fileStatistics = Gdx.files.local(Constants.STATISTICS);
                        fileStatistics.deleteDirectory();

                        dispose();
                        game.setScreen(new LoginScreen(game));
                        break;
                    }
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
                    case 1: {
                        labelText.setText("Ну что ж, до свидания, студент!\n" +
                                "Ждём тебя снова!");

                        buttonRed.setVisible(false);
                        buttonGreen.setVisible(false);

                        flagOnExit = true;
                    }
                    case 2: {
                        labelText.setText("Ну что ж, до свидания, студент!\n" +
                                "Ждём тебя снова!");

                        buttonRed.setVisible(false);
                        buttonGreen.setVisible(false);

                        flagOnExit = true;
                    }
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

        //Setting background color #F2F2F2
        Utils.setBackgroundColor(242/255f, 242/255f, 242/255f, 1);

        //Updating information about time, energy, cash and semester
        Utils.updateTime(game, game.getParameters().getIsActiveAction());
        Utils.updateEnergy(game.getPlayerData(), game.getParameters().getIsActiveAction());
        Utils.updateCash(game, Utils.getTimeData(millis() - game.getPlayerData().getTime()[0]));
        Utils.updateSemester(game.getPlayerData(),
                Utils.getTimeData(millis() - game.getPlayerData().getTime()[0]), game.indexOfSubject);

        //Check on exit
        if (flagOnExit)
           if (Gdx.input.isTouched()) Gdx.app.exit();

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
        game.saveData(game.getParameters(), game.getPlayerData());
    }

    @Override
    public void resume() {

        game.setPlayerData(Student.readStudentData());
        Utils.setEnergy(game.getPlayerData());
        Utils.setGrant(game);
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
