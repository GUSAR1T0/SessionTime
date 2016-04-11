package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.teamproject.game.models.Student;

import java.util.ArrayList;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class is view of game menu.
 */
public class MainMenuScreen implements Screen {

    private STGame game;
    private Stage stage;
    private BitmapFont fontData;
    private BitmapFont fontButton;
    private Pixmap pixmapTableBackground;
    private TextureRegionDrawable textureTableBackground;
    private Texture lightgrayStar;
    private Texture darkgrayStar;
    private Texture iconPlayer;
    private Table playerTable;
    private Table buttonTable;

    private Utils.PointerData pointerData;

    private int stateTime = 0;

    public MainMenuScreen(STGame game) {

        this.game = game;

        //Initialization stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Adding elements
        createDataScreen();
        createButtonScreen();
    }

    private void createDataScreen() {

        //Reading data of PLAYER from local file
        Student player = Student.readStudentData();

        //Getting font for labels
        fontData = Utils.getFont("Bebas_Neue.otf", 46);

        //Label shows data of PLAYER
        Label labelName = new Label(player.getName(),
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
        Label labelSpec = new Label(player.getSpecialty(),
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));

        //Adding icons of light-gray and dark-gray stars
        lightgrayStar = new Texture(Gdx.files.internal(Constants.LIGHTGRAY_STAR));
        darkgrayStar = new Texture(Gdx.files.internal(Constants.DARKGRAY_STAR));

        ArrayList<Image> iconStar = new ArrayList<Image>();
        for (int i = 0; i < player.getSemester(); i++) {
            iconStar.add(new Image(lightgrayStar));
        }
        for (int i = 0; i < Constants.COUNT_STARS_B - player.getSemester(); i++) {
            iconStar.add(new Image(darkgrayStar));
        }

        //Adding icon of PLAYER
        iconPlayer = new Texture(Gdx.files.internal(Constants.ICON_CAT));
        Image imageIconPlayer = new Image(iconPlayer);

        //Setting table for data of PLAYER
        playerTable = new Table();

        playerTable.setWidth(stage.getWidth() * 3/8f);
        playerTable.setHeight(stage.getHeight());

        pixmapTableBackground = Utils.setPixmapColor(1, 1, "#CC4B4B");
        textureTableBackground = new TextureRegionDrawable(new TextureRegion(
                new Texture(pixmapTableBackground)));

        playerTable.setBackground(textureTableBackground);

        for (int i = 0; i < Constants.COUNT_STARS_B; i++) {
            playerTable.add(iconStar.get(i)).width(6 / 150f * stage.getWidth()).
                    height(6 / 150f * stage.getWidth()).padBottom(stage.getHeight() / 15f).
                    padLeft(2).padRight(2);
        }
        playerTable.row();
        playerTable.add(imageIconPlayer).width(3/16f * stage.getWidth()).
                height(3/16f * stage.getWidth()).colspan(Constants.COUNT_STARS_B);
        playerTable.row();
        playerTable.add(labelName).padTop(stage.getHeight() / 10f).colspan(Constants.COUNT_STARS_B);
        playerTable.row();
        playerTable.add(labelSpec).padTop(stage.getHeight() / 10f).colspan(Constants.COUNT_STARS_B);
    }

    private void createButtonScreen() {

        //Getting font for text on buttons
        fontButton = Utils.getFont("Bebas_Neue.otf", 58);

        //Creating style for ImageTextButton
        pointerData = Utils.getImageTextButton((int) (stage.getWidth() / 2),
                (int) (stage.getHeight() * 7 / 40),
                "#F2F2F2", "#666666",
                "#445565", "#F2F2F2",
                fontButton);

        //Creating ImageTextButtons
        ImageTextButton.ImageTextButtonStyle style = pointerData.style;

        ImageTextButton playButton = new ImageTextButton("Играть", style);
        // TODO: 11.04.2016 to add listener for playButton

        ImageTextButton statisticsButton = new ImageTextButton("Статистика", style);
        // TODO: 11.04.2016 to add listener for statisticsButton

        ImageTextButton helpButton = new ImageTextButton("Помощь", style);
        // TODO: 11.04.2016 to add listener for helpButton

        ImageTextButton settingsButton = new ImageTextButton("Настройки", style);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                FileHandle file = Gdx.files.local(Constants.PLAYER);
                file.delete();
                dispose();
                game.setScreen(new LoginScreen(game));
            }
        });

        //Setting table for button of MENU
        buttonTable = new Table();

        buttonTable.setPosition(stage.getWidth() * 3/8f, 0);
        buttonTable.setWidth(stage.getWidth() * 5/8f);
        buttonTable.setHeight(stage.getHeight());

        buttonTable.add(playButton).expand();
        buttonTable.row();
        buttonTable.add(statisticsButton).expand();
        buttonTable.row();
        buttonTable.add(helpButton).expand();
        buttonTable.row();
        buttonTable.add(settingsButton).expand();
    }

    @Override
    public void show() {

        //Adding actor (table) on stage
        stage.addActor(playerTable);
        stage.addActor(buttonTable);
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
        fontData.dispose();
        fontButton.dispose();
        pixmapTableBackground.dispose();
        textureTableBackground.getRegion().getTexture().dispose();
        pointerData.texture1.getRegion().getTexture().dispose();
        pointerData.texture2.getRegion().getTexture().dispose();
        pointerData.pixmap1.dispose();
        pointerData.pixmap2.dispose();
        lightgrayStar.dispose();
        darkgrayStar.dispose();
        iconPlayer.dispose();
    }
}
