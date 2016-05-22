package com.teamproject.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class is main menu view of game.
 */
public class MainMenuScreen extends MenuScreen implements Screen {

    private Array<Table> playerTable;
    private ScrollPane scrollPlayerTable;

    public MainMenuScreen(final STGame game) {
        super(game);
    }

    @Override
    public void createDataTable() {

        //Getting font for labels
        fontData = Utils.getFont("BebasNeue.otf", 46);
        Utils.setLinearFilter(fontData);

        //Label shows data of PLAYER
        Label labelName = new Label(player.getName(),
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
        Label labelSpec = new Label(player.getNameOfSpecialty(),
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));

        //Adding icons of light-gray and dark-gray stars
        Array<Image> iconStar = new Array<Image>();
        for (int i = 0; i < player.getSemester(); i++)
            iconStar.add(new Image(game.getManager().get(Constants.LIGHTGRAY_STAR, Texture.class)));

        for (int i = player.getSemester(); i < Constants.COUNT_AVAILABLE_SEMESTERS; i++)
            iconStar.add(new Image(game.getManager().get(Constants.BLUE_STAR, Texture.class)));

        for (int i = Constants.COUNT_AVAILABLE_SEMESTERS; i < Constants.COUNT_STARS_B; i++)
            iconStar.add(new Image(game.getManager().get(Constants.DARKGRAY_STAR, Texture.class)));

        //Adding icon of PLAYER
        Image iconPlayer = new Image(game.getManager().get(Constants.ICON_CAT, Texture.class));

        playerTable = new Array<Table>();

        //Setting table for general data of PLAYER
        Table infoTable = new Table();
        infoTable.setWidth(stage.getWidth() * 3/8f);
        infoTable.setHeight(stage.getHeight());

        for (int i = 0; i < Constants.COUNT_STARS_B; i++) {
            infoTable.add(iconStar.get(i)).width(6 / 150f * stage.getWidth()).
                    height(6 / 150f * stage.getWidth()).expand();
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
        labelCash = new Label(player.getCash() + "",
                new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
        labelEnergy = new Label("", new Label.LabelStyle(fontData, Color.valueOf("#F2F2F2")));
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
    }

    @Override
    public void createButtonTable() {

        //Creating ImageTextButtons
        ImageTextButton.ImageTextButtonStyle style = textureData.get(0).style;

        buttons = new Array<ImageTextButton>();
        buttons.add(new ImageTextButton("Играть", style));
        buttons.add(new ImageTextButton("Настройки", style));

        buttons.get(0).addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                game.setScreen(new GameMenuScreen(game));
            }
        });

        buttons.get(1).addListener(new ChangeListener() {
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

        buttonTable.add(buttons.get(0)).expand();
        buttonTable.row();
        buttonTable.add(buttons.get(1)).expand();
    }

    @Override
    public void setButtonStyles() {

        //Getting font for text on buttons
        fontButton = Utils.getFont("BebasNeue.otf", 58);
        Utils.setLinearFilter(fontButton);

        //Creating style for ImageTextButton
        textureData.add(Utils.getImageTextButton((int) (stage.getWidth() / 2),
                (int) (stage.getHeight() * 7 / 40),
                "#F2F2F2", "#666666",
                "#445565", "#F2F2F2",
                fontButton));
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
    public void updateSemester() {

        if (player.isFlag() && !Utils.isSession((time.days + "").length(), time.days) &&
                (time.days <= Constants.COUNT_AVAILABLE_SEMESTERS * 100 + 1))
            player.setSemester(time.days / 100);
    }

    @Override
    public void setDisabled(int i) {}

    @Override
    public void setIncluded(int i) {}

    @Override
    public void addActors() {

        //Adding actor (table) on stage
        stage.addActor(scrollPlayerTable);
        stage.addActor(buttonTable);
    }

    @Override
    public void show() {

        //Debugging all position of stage elements
//        stage.setDebugAll(true);
    }

    @Override
    public void render(float delta) {

        //Setting background color #445565
        Utils.setBackgroundColor(68/255f, 85/255f, 101/255f, 1);

        //Offset control of table with scroll
        addScrollAction();

        //Updating information about energy, time, semester
        updateTime();
        updateEnergy();
        updateCash();
        updateSemester();

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
        fontButton.dispose();
        pixmapTableBackground.dispose();
        textureTableBackground.getRegion().getTexture().dispose();
        textureData.get(0).texture1.getRegion().getTexture().dispose();
        textureData.get(0).texture2.getRegion().getTexture().dispose();
        textureData.get(0).pixmap1.dispose();
        textureData.get(0).pixmap2.dispose();
    }
}
