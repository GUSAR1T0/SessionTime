package com.teamproject.game.screens;

/**
 * Created by Anna on 09.05.2016.
 */
public class SessionScreen implements Screen {

    private STGame game;
    private Stage stage;

    SessionScreen(STGame game) {

        this.game = game;
        stage = new Stage(new StretchViewport(Constants.WORLD_WIDTH, Constants.WORLD_WIDTH * Constants.RATIO));
    }
}