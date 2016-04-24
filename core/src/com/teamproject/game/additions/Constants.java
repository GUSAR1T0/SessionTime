package com.teamproject.game.additions;

import com.badlogic.gdx.Gdx;

/**
 * Created by Roman_Mashenkin on 27.03.2016.
 *
 * This structure set all needful constants.
 */
public class Constants {

    //Size of Desktop application
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    //Width and height of World
    public static final int WORLD_WIDTH = 1250;
    public static final int WORLD_HEIGHT = 1250;

    //Title of game
    public static final String TITLE = "Session Time";

    //The ratio of height and width
    public static final float RATIO = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();

    //The count of stars in Main Menu
    public static final int COUNT_STARS_B = 8;

    //The count of loading textures
    public static final int COUNT_LOADING_TEXTURES = 16;

    //Setting of sequence of English and Russian letters, numerals and special chars
    public static final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "abcdefghijklmnopqrstuvwxyz" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    //Path to textures
    public static final String ICON_CAT = "data/images/icon_cat.png";
    public static final String ICON_LOGO = "data/images/icon_logo.png";
    public static final String LIGHTGRAY_STAR = "data/images/stars/lightgray_star.png";
    public static final String DARKGRAY_STAR = "data/images/stars/darkgray_star.png";

    //Path to file of player data
    public static final String PLAYER = "player";

    //Path to file of style
    public static final String SKIN = "data/styles/uiskin.json";

    //Path to file of text list
    public static final String LIST_SPECIALTY = "data/textfiles/specialty.txt";

    //Path to file of music
    public static final String BACKGROUND_MUSIC = "data/music/nuan.mp3";
}
