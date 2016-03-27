package com.teamproject.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Roman_Mashenkin on 26.03.2016.
 *
 * This class is view of game menu:
 * - setting parameters of orthographic camera;
 * - drawing background and icons of buttons.
 */
public class MenuState extends State {

    private static final float SCALING_FACTOR = (float) Gdx.graphics.getHeight() / 2880 ;
    private static final String MENU_BACKGROUND = "menu_background_2560x1600.png";

    private Texture background;
    private Sprite button1, button2, button3;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        background = new Texture(Gdx.files.internal(MENU_BACKGROUND));

        button1 = new Sprite(new Texture(Gdx.files.internal("icon_cat.png")));
        button1.setPosition(camera.position.x - button1.getWidth() / 2 -
                1.5f * SCALING_FACTOR * button1.getWidth(),
                camera.position.y - button1.getWidth() / 2);
        button1.setScale(SCALING_FACTOR);

        button2 = new Sprite(new Texture(Gdx.files.internal("icon_cat.png")));
        button2.setPosition(camera.position.x - button2.getWidth() / 2,
                camera.position.y - button2.getHeight() / 2);
        button2.setScale(SCALING_FACTOR);

        button3 = new Sprite(new Texture(Gdx.files.internal("icon_cat.png")));
        button3.setPosition(camera.position.x - button3.getWidth() / 2 +
                1.5f * SCALING_FACTOR * button3.getWidth(),
                camera.position.y - button3.getHeight() / 2);
        button3.setScale(SCALING_FACTOR);
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
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        button1.draw(sb);
        button2.draw(sb);
        button3.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
