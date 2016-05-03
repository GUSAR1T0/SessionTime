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

/**
 * Created by Roman_Mashenkin on 28.03.2016.
 */
public class GameMenuScreen implements Screen {
    // TODO: 11.04.2016 to make new realization GameMenuScreen using new design

    private STGame game;
    private Stage stage;
    private BitmapFont font;
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
        final Student player = game.getPlayerData();

        //Getting font for labels
        font = Utils.getFont("BebasNeue.otf", 54);
        Utils.setLinearFilter(font);

        //Adding icon of CASH and ENERGY
        Image iconCoin = new Image(game.getManager().get(Constants.ICON_COIN, Texture.class));
        Image iconEnergy = new Image(game.getManager().get(Constants.ICON_ENERGY, Texture.class));

        //Adding labels for show information about cash and energy
        Label labelCoin = new Label(player.getCash() + "",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));
        Label labelEnergy = new Label(player.getEnergy() + " %",
                new Label.LabelStyle(font, Color.valueOf("#F2F2F2")));

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

        resourcesTable.add(iconCoin).width(stage.getWidth() * 1 / 20f).
                height(stage.getWidth() * 1 / 20f).expand();
        resourcesTable.add(labelCoin).expand();
        resourcesTable.add(iconEnergy).width(stage.getWidth() * 1 / 12f).
                height(stage.getWidth() * 1 / 22f).expand();
        resourcesTable.add(labelEnergy).expand();
    }

    @Override
    public void show() {

        //Adding actor (table) on stage
        stage.addActor(resourcesTable);
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
        pixmapTableBackground.dispose();
        textureTableBackground.getRegion().getTexture().dispose();
    }
}
