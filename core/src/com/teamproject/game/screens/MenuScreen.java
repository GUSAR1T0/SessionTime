package com.teamproject.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.teamproject.game.additions.Constant;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class is view of game menu:
 * - setting parameters of orthographic camera;
 * - drawing background and icons of buttons.
 */
public class MenuScreen extends Screen {

    private Texture background;
    private Sprite button1, button2, button3, logo;

    public MenuScreen(GameScreenManager gsm) {
        super(gsm);
        camera.setToOrtho(false);

        background = new Texture(Gdx.files.internal(Constant.MENU_BACKGROUND));

        button1 = new Sprite(new Texture(Gdx.files.internal("icon_cat.png")));
        button1.setPosition(camera.position.x - button1.getWidth() / 2 -
                1.5f * Constant.SCALING_FACTOR * button1.getWidth(),
                camera.position.y - button1.getWidth() / 2);
        button1.setScale(Constant.SCALING_FACTOR);

        button2 = new Sprite(new Texture(Gdx.files.internal("icon_cat.png")));
        button2.setPosition(camera.position.x - button2.getWidth() / 2,
                camera.position.y - button2.getHeight() / 2);
        button2.setScale(Constant.SCALING_FACTOR);

        button3 = new Sprite(new Texture(Gdx.files.internal("icon_cat.png")));
        button3.setPosition(camera.position.x - button3.getWidth() / 2 +
                1.5f * Constant.SCALING_FACTOR * button3.getWidth(),
                camera.position.y - button3.getHeight() / 2);
        button3.setScale(Constant.SCALING_FACTOR);

        logo = new Sprite(new Texture(Gdx.files.internal("logo.png")));
        logo.setPosition(camera.position.x - logo.getWidth() / 2,
                Gdx.graphics.getHeight()* 3/4);
        logo.setScale(2 * Constant.SCALING_FACTOR);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //Drawing batch
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        button1.draw(sb);
        button2.draw(sb);
        button3.draw(sb);
        logo.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
