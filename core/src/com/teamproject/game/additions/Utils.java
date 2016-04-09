package com.teamproject.game.additions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Roman_Mashenkin on 01.04.2016.
 *
 * This structure set all needful methods.
 */
public class Utils {

    /* This method sets background color through GL-methods */
    public static void setBackgroundColor(float r, float g, float b, float a) {

        Gdx.gl.glClearColor(r, g, b, a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /* This method sets background color through pixmap */
    public static Pixmap setPixmapColor(int x, int y, String color) {

        Pixmap pixmap = new Pixmap(x, y, Pixmap.Format.RGB565);
        pixmap.setColor(Color.valueOf(color));
        pixmap.fill();

        return pixmap;
    }

    /* This method checks file */
    public static boolean isEmpty() {

        FileHandle file = Gdx.files.local(Constants.PLAYER);

        return file.length() == 0;
    }

    /* This method creates buttons through images */
    public static ImageTextButton.ImageTextButtonStyle makeImageTextButton
            (String name,
             String upTextColor, String downTextColor,
             BitmapFont font) {

        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(
            new TextureRegionDrawable(new TextureRegion(new Texture("data/buttons/" + name + ".png"))),
            new TextureRegionDrawable(new TextureRegion(new Texture("data/buttons/" + name + "_pressed.png"))),
            new TextureRegionDrawable(new TextureRegion(new Texture("data/buttons/" + name + ".png"))),
            font);

        style.fontColor = Color.valueOf(upTextColor);
        style.downFontColor = Color.valueOf(downTextColor);

        return style;
    }

    /* This methods creates buttons through pixmap */
    public static ImageTextButton.ImageTextButtonStyle makeImageTextButton
            (int x, int y,
             String upButtonColor, String downButtonColor,
             String upTextColor, String downTextColor,
             BitmapFont font) {

        ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(
            new TextureRegionDrawable(new TextureRegion(new Texture(setPixmapColor(x, y, upButtonColor)))),
            new TextureRegionDrawable(new TextureRegion(new Texture(setPixmapColor(x, y, downButtonColor)))),
            new TextureRegionDrawable(new TextureRegion(new Texture(setPixmapColor(x, y, upButtonColor)))),
            font);

        style.fontColor = Color.valueOf(upTextColor);
        style.downFontColor = Color.valueOf(downTextColor);

        return style;
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
