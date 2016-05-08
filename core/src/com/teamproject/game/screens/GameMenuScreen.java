package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.teamproject.game.STGame;
import com.teamproject.game.additions.Constants;
import com.teamproject.game.additions.Utils;
import com.teamproject.game.models.Student;

import static com.badlogic.gdx.utils.TimeUtils.millis;

/**
 * Created by Roman_Mashenkin on 28.03.2016.
 */
public class GameMenuScreen implements Screen {
    // TODO: 11.04.2016 to make new realization GameMenuScreen using new design

    private STGame game;
    private Stage stage;
    private BitmapFont font;
    private BitmapFont fontTime;
    private Label labelTime;
    private Table resourcesTable;
    private Pixmap pixmapTableBackground;
    private TextureRegionDrawable textureTableBackground;

    public GameMenuScreen(final STGame game) {

        this.game = game;

        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH,
                Constants.WORLD_WIDTH * Constants.RATIO));
        Gdx.input.setInputProcessor(stage);

        //Adding elements
        createGameMenuScreen();
    }

    public void createGameMenuScreen() {

        //Getting player data
        Student player = game.getPlayerData();

        //Getting fonts for labels
        font = Utils.getFont("BebasNeue.otf", 54);
        Utils.setLinearFilter(font);
        fontTime = Utils.getFont("BebasNeue.otf", 36);
        Utils.setLinearFilter(fontTime);

        //Adding icon of CASH and ENERGY
        Image iconCoin = new Image(game.getManager().get(Constants.ICON_COIN, Texture.class));
        Image iconEnergy = new Image(game.getManager().get(Constants.ICON_ENERGY, Texture.class));
        Image iconTime = new Image(game.getManager().get(Constants.ICON_TIME, Texture.class));

        //Adding labels for show information about cash and energy
        Label labelCoin = new Label(player.getCash() + "",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        Label labelEnergy = new Label(player.getEnergy() + "%",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        labelTime = new Label("",
                new Label.LabelStyle(fontTime, Color.valueOf("#F2F2F2")));

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

        resourcesTable.add(iconCoin).width(stage.getWidth() * 1 / 18f).
                height(stage.getWidth() * 1 / 18f).expand();
        resourcesTable.add(labelCoin).expand();
        resourcesTable.add(iconEnergy).width(stage.getWidth() * 1 / 12f).
                height(stage.getWidth() * 1 / 22f).expand();
        resourcesTable.add(labelEnergy).expand();
        resourcesTable.add(iconTime).width(1 / 18f * stage.getWidth()).
                height(1 / 18f * stage.getWidth()).expand();
        resourcesTable.add(labelTime).width(1 / 18f * stage.getWidth()).expand();
    }

    @Override
    public void show() {

        //Adding actor (table) on stage
        stage.addActor(resourcesTable);

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
        font.dispose();
        fontTime.dispose();
        pixmapTableBackground.dispose();
        textureTableBackground.getRegion().getTexture().dispose();
    }
}
