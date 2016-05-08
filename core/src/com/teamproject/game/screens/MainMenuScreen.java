package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.teamproject.game.models.Student;

import static com.badlogic.gdx.utils.TimeUtils.millis;

import java.util.ArrayList;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class is main menu view of game.
 */
public class MainMenuScreen implements Screen {

    private STGame game;
    private Stage stage;
    private BitmapFont fontData;
    private BitmapFont fontButton;
    private Label labelTime;
    private Pixmap pixmapTableBackground;
    private TextureRegionDrawable textureTableBackground;
    private ArrayList<Table> playerTable = new ArrayList<Table>();
    private ScrollPane scrollPlayerTable;
    private Table buttonTable;

    private Utils.TextureData textureData;

    public MainMenuScreen(final STGame game) {

        this.game = game;

        //Initialization stage
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Creating and adding elements
        createDataScreen();
        createButtonScreen();
    }

    private void createDataScreen() {

        //Getting player data
        Student player = game.getPlayerData();

        //Getting font for labels
        fontData = Utils.getFont("BebasNeue.otf", 46);
        Utils.setLinearFilter(fontData);

        //Label shows data of PLAYER
        Label labelName = new Label(player.getName(),
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
        Label labelSpec = new Label(player.getNameOfSpecialty(),
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));

        //Adding icons of light-gray and dark-gray stars
        ArrayList<Image> iconStar = new ArrayList<Image>();
        for (int i = 0; i < player.getSemester(); i++) {
            iconStar.add(new Image(game.getManager().get(Constants.LIGHTGRAY_STAR, Texture.class)));
        }
        for (int i = 0; i < Constants.COUNT_STARS_B - player.getSemester(); i++) {
            iconStar.add(new Image(game.getManager().get(Constants.DARKGRAY_STAR, Texture.class)));
        }

        //Adding icon of PLAYER
        Image iconPlayer = new Image(game.getManager().get(Constants.ICON_CAT, Texture.class));

        //Setting table for general data of PLAYER
        Table infoTable = new Table();
        infoTable.setWidth(stage.getWidth() * 3/8f);
        infoTable.setHeight(stage.getHeight());

        for (int i = 0; i < Constants.COUNT_STARS_B; i++) {
            infoTable.add(iconStar.get(i)).width(6 / 150f * stage.getWidth()).
                    height(6 / 150f * stage.getWidth()).
                    padLeft(2).padRight(2).expand();
        }
        infoTable.row();
        infoTable.add(iconPlayer).width(3 / 16f * stage.getWidth()).
                height(3 / 16f * stage.getWidth()).colspan(Constants.COUNT_STARS_B).expand();
        infoTable.row();
        infoTable.add(labelName).colspan(Constants.COUNT_STARS_B).expand();
        infoTable.row();
        infoTable.add(labelSpec).colspan(Constants.COUNT_STARS_B).expand();

        //Adding infoTable in array for scrolling
        playerTable.add(infoTable);

        //Adding icon of CASH and ENERGY
        Image iconCash = new Image(game.getManager().get(Constants.ICON_CASH, Texture.class));
        Image iconEnergy = new Image(game.getManager().get(Constants.ICON_ENERGY, Texture.class));
        Image iconTime = new Image(game.getManager().get(Constants.ICON_TIME, Texture.class));

        //Adding labels for show information about cash and energy
        Label labelCash = new Label(player.getCash() + "",
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
        Label labelEnergy = new Label(player.getEnergy() + "%",
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
        labelTime = new Label("", new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));

        //Setting table for resources data of PLAYER
        Table resourcesTable = new Table();
        resourcesTable.setWidth(stage.getWidth() * 3 / 8f);
        resourcesTable.setHeight(stage.getHeight());

        resourcesTable.add(iconCash).width(1 / 10f * stage.getWidth()).
                height(1 / 10f * stage.getWidth()).expand();
        resourcesTable.add(labelCash).width(1 / 10f * stage.getWidth()).expand();
        resourcesTable.row();
        resourcesTable.add(iconEnergy).width(1 / 10f * stage.getWidth()).
                height(1 / 18f * stage.getWidth()).expand();
        resourcesTable.add(labelEnergy).width(1 / 10f * stage.getWidth()).expand();
        resourcesTable.row();
        resourcesTable.add(iconTime).width(1 / 10f * stage.getWidth()).
                height(1 / 10f * stage.getWidth()).expand();
        resourcesTable.add(labelTime).width(1 / 10f * stage.getWidth()).expand();

        //Adding resourcesTable in array for scrolling
        playerTable.add(resourcesTable);

        //Setting of scrolling
        showSectionPlayerData();
    }

    private void showSectionPlayerData() {

        //Setting background of information tables
        pixmapTableBackground = Utils.setPixmapColor(1, 1, "#CC4B4B");
        textureTableBackground = new TextureRegionDrawable(new TextureRegion(
                new Texture(pixmapTableBackground)));

        //Integration information tables in one table (division on sections)
        Table sections = new Table();
        sections.setBackground(textureTableBackground);

        sections.add(playerTable.get(0)).width(stage.getWidth() * 3 / 8f).height(stage.getHeight());
        sections.add(playerTable.get(1)).width(stage.getWidth() * 3 / 8f).height(stage.getHeight());

        //Adding scrolling
        scrollPlayerTable = new ScrollPane(sections);
        scrollPlayerTable.setWidth(stage.getWidth() * 3 / 8f);
        scrollPlayerTable.setHeight(stage.getHeight());

        //Setting parameters of scrolling
        scrollPlayerTable.setOverscroll(false, false);
        scrollPlayerTable.setupFadeScrollBars(0, 0);
    }

    private void createButtonScreen() {

        //Getting font for text on buttons
        fontButton = Utils.getFont("BebasNeue.otf", 58);
        Utils.setLinearFilter(fontButton);

        //Creating style for ImageTextButton
        textureData = Utils.getImageTextButton((int) (stage.getWidth() / 2),
                (int) (stage.getHeight() * 7 / 40),
                "#F2F2F2", "#666666",
                "#445565", "#F2F2F2",
                fontButton);

        //Creating ImageTextButtons
        ImageTextButton.ImageTextButtonStyle style = textureData.style;

        ImageTextButton playButton = new ImageTextButton("Играть", style);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new GameMenuScreen(game));
            }
        });

        ImageTextButton statisticsButton = new ImageTextButton("Статистика", style);
        // TODO: 11.04.2016 to add listener for statisticsButton

        ImageTextButton helpButton = new ImageTextButton("Помощь", style);
        // TODO: 11.04.2016 to add listener for helpButton

        ImageTextButton settingsButton = new ImageTextButton("Настройки", style);
        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new SettingScreen(game));
            }
        });

        //Setting table for button of MENU
        buttonTable = new Table();

        buttonTable.setPosition(stage.getWidth() * 3 / 8f, 0);
        buttonTable.setWidth(stage.getWidth() * 5 / 8f);
        buttonTable.setHeight(stage.getHeight());

        buttonTable.add(playButton).expand();
        buttonTable.row();
        buttonTable.add(statisticsButton).expand();
        buttonTable.row();
        buttonTable.add(helpButton).expand();
        buttonTable.row();
        buttonTable.add(settingsButton).expand();
    }

    private void addScrollAction() {

        //Managing position of scroll view
        if (!scrollPlayerTable.isPanning()) {
            float position = scrollPlayerTable.getScrollX() / (stage.getWidth() * 3 / 8f);

            //If it's the first section...
            if ((position > 0) && (position <= 0.5f)) {
                if (scrollPlayerTable.getScrollX() >= 10)
                    scrollPlayerTable.setScrollX(scrollPlayerTable.getScrollX() - 10);
                else scrollPlayerTable.setScrollX(0);
            }

            //If it's the second section...
            if ((position > 0.5f) && (position <= 1)) {
                if (scrollPlayerTable.getScrollX() <= stage.getWidth() * 3 / 8 - 10)
                    scrollPlayerTable.setScrollX(scrollPlayerTable.getScrollX() + 10);
                else scrollPlayerTable.setScrollX(stage.getWidth() * 3 / 8f);
            }
        }
    }

    @Override
    public void show() {

        //Adding actor (table) on stage
        stage.addActor(scrollPlayerTable);
        stage.addActor(buttonTable);

        //Debugging all position of stage elements
//        stage.setDebugAll(true);
    }

    @Override
    public void render(float delta) {

        //Setting background color #445565
        Utils.setBackgroundColor(68/255f, 85/255f, 101/255f, 1);

        //Updating of graphic elements
        stage.act(delta);

        //Drawing actors
        stage.draw();

        //Offset control of table with scroll
        addScrollAction();

        //Updating time
        Utils.updateTimeOnLabel(millis() - game.getPlayerData().getTime(), labelTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        game.saveData(game.getPlayerData());
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
        textureData.texture1.getRegion().getTexture().dispose();
        textureData.texture2.getRegion().getTexture().dispose();
        textureData.pixmap1.dispose();
        textureData.pixmap2.dispose();
    }
}
