package com.teamproject.game.additions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Roman_Mashenkin on 01.04.2016.
 *
 * This structure set all needful methods.
 */
public class Methods {

    /* This method creates buttons */
    public static ImageButton makeButton(String name) {

        TextureRegionDrawable buttonUp =
                new TextureRegionDrawable(new TextureRegion(new Texture("data/buttons/" +
                        name + ".png")));

        TextureRegionDrawable buttonDown =
                new TextureRegionDrawable(new TextureRegion(new Texture("data/buttons/" +
                        name + "_pressed.png")));

        return new ImageButton(buttonUp, buttonDown);
    }

    /* This method generates font */
    public static BitmapFont getFont(String name, int size) {

        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/" + name));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = Constants.FONT_CHARS;
        parameter.size = size;

        BitmapFont font = generator.generateFont(parameter);

        generator.dispose();

        return font;
    }
}
