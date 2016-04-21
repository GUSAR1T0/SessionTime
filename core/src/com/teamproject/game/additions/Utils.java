package com.teamproject.game.additions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

/**
 * Created by Roman_Mashenkin on 01.04.2016.
 *
 * This class set all needful functions.
 */
public class Utils {

    public static class PointerData {

        public TextureRegionDrawable texture1;
        public TextureRegionDrawable texture2;
        public Pixmap pixmap1;
        public Pixmap pixmap2;
        public ImageTextButton.ImageTextButtonStyle style;
        public Slider.SliderStyle styleSlide;
    }

    /* This method sets background color through GL-methods */
    public static void setBackgroundColor(float r, float g, float b, float a) {

        Gdx.gl.glClearColor(r, g, b, a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /* This method sets background color through pixmap */
    public static Pixmap setPixmapColor(int width, int height, String color) {

        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGB565);
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
    public static PointerData getImageTextButton
            (String buttonColor, String upTextColor, String downTextColor,
             BitmapFont font) {

        PointerData temp = new PointerData();

        TextureRegionDrawable texture1 = new TextureRegionDrawable(new TextureRegion(
                new Texture("data/images/buttons/" + buttonColor + "_button.png")));
        TextureRegionDrawable texture2 = new TextureRegionDrawable(new TextureRegion(
                new Texture("data/images/buttons/button_pressed.png")));

        temp.texture1 = texture1;
        temp.texture2 = texture2;

        temp.style = new ImageTextButton.ImageTextButtonStyle(
            texture1, texture2, texture1, font);

        temp.style.fontColor = Color.valueOf(upTextColor);
        temp.style.downFontColor = Color.valueOf(downTextColor);

        return temp;
    }

    /* This methods creates buttons through pixmap */
    public static PointerData getImageTextButton
            (int width, int height,
             String upButtonColor, String downButtonColor,
             String upTextColor, String downTextColor,
             BitmapFont font) {

        PointerData temp = new PointerData();

        temp.pixmap1 = setPixmapColor(width, height, upButtonColor);
        temp.pixmap2 = setPixmapColor(width, height, downButtonColor);

        TextureRegionDrawable texture1 = new TextureRegionDrawable(new TextureRegion(
                new Texture(temp.pixmap1)));
        TextureRegionDrawable texture2 = new TextureRegionDrawable(new TextureRegion(
                new Texture(temp.pixmap2)));

        temp.texture1 = texture1;
        temp.texture2 = texture2;

        temp.style = new ImageTextButton.ImageTextButtonStyle(
            texture1, texture2, texture1, font);

        temp.style.fontColor = Color.valueOf(upTextColor);
        temp.style.downFontColor = Color.valueOf(downTextColor);

        return temp;
    }

    /* This method generates font */
    public static BitmapFont getFont(String fontName, int size) {

        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("data/fonts/" + fontName));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = Constants.FONT_CHARS;
        parameter.size = size;

        BitmapFont font = generator.generateFont(parameter);

        generator.dispose();

        return font;
    }

    /* This method creates animation from textures ArrayList */
    public static Animation getAnimation(ArrayList<Texture> textures, int count, int velocity) {

        TextureRegion textureFrames[] = new TextureRegion[count];

        int index = 0;
        for (int i = 0; i < count; i++) {
                textureFrames[index++] = new TextureRegion(textures.get(i));
        }

        return new Animation(1f / velocity, textureFrames);
    }
}
