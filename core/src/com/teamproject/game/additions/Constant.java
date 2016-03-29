package com.teamproject.game.additions;

import com.badlogic.gdx.Gdx;

/**
 * Created by Roman_Mashenkin on 27.03.2016.
 *
 * This structure set all needful constants.
 */
public class Constant {

    //Size of Desktop application
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    //Width and height of World
    public static final int WORLD_WIDTH = 1000;
    public static final int WORLD_HEIGHT = 1000;

    //Title of game
    public static final String TITLE = "Session Time";

    //Scaling factor for Android devices
    public static final float SCALING_FACTOR = (float) Gdx.graphics.getHeight() / 2880 ;

    //The ratio of height and width
    public static final float RATIO = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();

    //Setting of sequence of English and Russian letters, numerals and special chars
    public static final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "abcdefghijklmnopqrstuvwxyz" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    //Path to backgrounds texture
    public static final String MENU_BACKGROUND = "menu_background_2560x1600.png";
    public static final String MENU_BACKGROUND_2 = "menu_background_2560x1600_2.png";
}
