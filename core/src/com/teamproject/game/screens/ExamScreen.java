package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.teamproject.game.models.Session;
import com.teamproject.game.models.Student;

import static com.badlogic.gdx.utils.TimeUtils.millis;

/**
 * Created by Roman_Mashenkin on 20.05.2016.
 *
 * This class realizes process passing exam.
 */
public class ExamScreen implements Screen {

    private STGame game;
    private Stage stage;
    private BitmapFont fontText;
    private BitmapFont fontButton;
    private Label textQuestion;
    private Array<ImageTextButton> buttonAnswers;
    private Table examTable;

    private Array<Utils.TextureData> textureData = new Array<Utils.TextureData>();
    private Array<Session.Question> randomList;
    private Student player;

    private boolean[] isActiveAction;
    private boolean isFrozen = false;
    private int[] answers;
    private int indexOfSubject;
    private int currentQuestions = 0;

    public ExamScreen(final STGame game, int indexOfSubject) {

        this.game = game;
        this.indexOfSubject = indexOfSubject;

        //Initialization of stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Getting data from files
        isActiveAction = this.game.getParameters().getIsActiveAction();
        player = this.game.getPlayerData();

        //Initialization of questions list
        Session session = new Session(indexOfSubject);
        randomList = session.getRandomListOfQuestions(session.getJointListOfQuestions());
        answers = new int[10];

        //Creating elements
        setButtonStyles();
        createExamScreen();

        //Adding elements on stage
        addActors();
    }

    public void setButtonStyles() {

        //Getting font for buttons
        fontButton = Utils.getFont("BebasNeue.otf", 40);
        Utils.setLinearFilter(fontButton);

        //Creating and adding style for ImageTextButton
        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() * 15 / 16f),
                (int) (stage.getHeight() * 1 / 8f),
                "#445565", "#F2F2F2",
                "#F2F2F2", "#666666",
                fontButton));

        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() * 15 / 16f),
                (int) (stage.getHeight() * 1 / 8f),
                "#76A790", "#76A790",
                "#F2F2F2", "#F2F2F2",
                fontButton));

        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() * 15 / 16f),
                (int) (stage.getHeight() * 1 / 8f),
                "#CC4B4B", "#CC4B4B",
                "#F2F2F2", "#F2F2F2",
                fontButton));
    }

    private void createExamScreen() {

        //Getting font for text
        fontText = Utils.getFont("BebasNeue.otf", 50);
        Utils.setLinearFilter(fontText);

        //Label of question text
        textQuestion = new Label("", new Label.LabelStyle(fontText, Color.valueOf("#445565")));
        textQuestion.setAlignment(Align.center);
        textQuestion.setWrap(true);

        ScrollPane scrollPaneQuestions = new ScrollPane(textQuestion);

        //Buttons of answers
        buttonAnswers = new Array<ImageTextButton>();
        buttonAnswers.add(new ImageTextButton("", textureData.get(0).style));
        buttonAnswers.add(new ImageTextButton("", textureData.get(0).style));
        buttonAnswers.add(new ImageTextButton("", textureData.get(0).style));
        buttonAnswers.add(new ImageTextButton("", textureData.get(0).style));

        Array<ScrollPane> scrollPaneAnswers = new Array<ScrollPane>();
        scrollPaneAnswers.add(new ScrollPane(buttonAnswers.get(0)));
        scrollPaneAnswers.add(new ScrollPane(buttonAnswers.get(1)));
        scrollPaneAnswers.add(new ScrollPane(buttonAnswers.get(2)));
        scrollPaneAnswers.add(new ScrollPane(buttonAnswers.get(3)));

        //Creating table for screen
        examTable = new Table();
        examTable.setWidth(stage.getWidth() * 3 / 4f);
        examTable.setHeight(stage.getHeight());
        examTable.setPosition(stage.getWidth() / 2f, stage.getHeight() / 2f, Align.center);

        examTable.add(scrollPaneQuestions).height(stage.getHeight() * 1 / 3f).
                width(stage.getWidth() * 3 / 4f).expand();
        examTable.row();
        examTable.add(scrollPaneAnswers.get(0)).width(stage.getWidth() * 15 / 16f).pad(10).expand();
        examTable.row();
        examTable.add(scrollPaneAnswers.get(1)).width(stage.getWidth() * 15 / 16f).pad(10).expand();
        examTable.row();
        examTable.add(scrollPaneAnswers.get(2)).width(stage.getWidth() * 15 / 16f).pad(10).expand();
        examTable.row();
        examTable.add(scrollPaneAnswers.get(3)).width(stage.getWidth() * 15 / 16f).pad(10).expand();
    }

    private void addActors() {
        stage.addActor(examTable);
    }

    private void setQuestions() {

        //Setting text on actors
        if (currentQuestions < 10) {
            textQuestion.setText(randomList.get(currentQuestions).getQ());
            buttonAnswers.get(0).setText("  "  + randomList.get(currentQuestions).getA()[0] + "  ");
            buttonAnswers.get(1).setText("  "  + randomList.get(currentQuestions).getA()[1] + "  ");
            buttonAnswers.get(2).setText("  "  + randomList.get(currentQuestions).getA()[2] + "  ");
            buttonAnswers.get(3).setText("  "  + randomList.get(currentQuestions).getA()[3] + "  ");
        }
    }

    private void manageAnswers() {

        //Freezing buttons for view of result of click
        if (!isFrozen)
            for (int i = 0; i < 4; i++)
                if (buttonAnswers.get(i).isChecked() && (currentQuestions < 10)) {
                    answers[currentQuestions] = i + 1;

                    if (answers[currentQuestions] == randomList.get(currentQuestions).getRight_a())
                        buttonAnswers.get(i).setStyle(textureData.get(1).style);
                    else
                        buttonAnswers.get(i).setStyle(textureData.get(2).style);

                    for (int j = 0; j < 4; j++)
                        buttonAnswers.get(j).setDisabled(true);

                    isFrozen = true;
                }
    }

    private void checkOnFreezing() {

        //Check on freeze of elements
        if (isFrozen)
            if (Gdx.input.isTouched() &&
                    !buttonAnswers.get(0).isPressed() &&
                    !buttonAnswers.get(1).isPressed() &&
                    !buttonAnswers.get(2).isPressed() &&
                    !buttonAnswers.get(3).isPressed())

                for (int i = 0; i < 4; i++)
                    if (buttonAnswers.get(i).isChecked()) {

                        buttonAnswers.get(i).setStyle(textureData.get(0).style);
                        buttonAnswers.get(i).setChecked(false);

                        for (int j = 0; j < 4; j++)
                            buttonAnswers.get(j).setDisabled(false);

                        isFrozen = false;
                        currentQuestions++;
                    }
    }

    private void checkOnFinish() {

        //Check on finish exam for go to SessionMenuScreen
        //Result of exam saving into one's of statistics file
        if (!isFrozen && (currentQuestions == 10)) {
            String path = Constants.STATISTICS +
                    game.getPlayerData().getSemester() + "_";

            switch (indexOfSubject) {
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

            int countOfRightAnswers = 0;
            for (int i = 0; i < 10; i++)
                if (answers[i] == randomList.get(i).getRight_a())
                    countOfRightAnswers++;

            float mark = countOfRightAnswers / 10f;

            FileHandle file = Gdx.files.local(path);
            file.writeString(mark + "", false);

            dispose();
            game.setScreen(new SessionMenuScreen(game));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Setting background color #F2F2F2
        Utils.setBackgroundColor(242/255f, 242/255f, 242/255f, 1);

        //Updating information about time, energy, cash and semester
        Utils.updateTime(game, isActiveAction);
        Utils.updateEnergy(player, isActiveAction);
        Utils.updateCash(game, Utils.getTimeData(millis() - game.getPlayerData().getTime()[0]));
        Utils.updateSemester(player, Utils.getTimeData(millis() - player.getTime()[0]),
                game.indexOfSubject);

        //Setting text on stage and managing of buttons
        setQuestions();
        manageAnswers();

        //Some check
        checkOnFreezing();
        checkOnFinish();

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
        fontText.dispose();
        fontButton.dispose();

        for (int i = 0; i < 3; i++) {
            textureData.get(i).texture1.getRegion().getTexture().dispose();
            textureData.get(i).texture2.getRegion().getTexture().dispose();
            textureData.get(i).pixmap1.dispose();
            textureData.get(i).pixmap2.dispose();
        }
    }
}
