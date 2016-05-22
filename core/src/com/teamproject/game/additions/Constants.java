package com.teamproject.game.additions;

import com.badlogic.gdx.Gdx;

import java.text.DecimalFormat;

/**
 * Created by Roman_Mashenkin on 27.03.2016.
 *
 * This class sets all needful constants.
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

    //The main coefficients and numbers for game
    public static final int INCREASE_TIME = 1000000;
    public static final float DECREASE_ENERGY = (float) 1 / 604800;

    //The counts of stars in Main Menu
    public static final int COUNT_STARS_B = 8;
    public static final int COUNT_AVAILABLE_SEMESTERS = 2;

    //The count of loading textures
    public static final int COUNT_LOADING_TEXTURES = 16;

    //Format of numbers
    public static final DecimalFormat DF_TIME = new DecimalFormat("00");
    public static final DecimalFormat DF_PERCENT = new DecimalFormat("##0.00%");

    //Setting of sequence of English and Russian letters, numerals and special chars
    public static final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "abcdefghijklmnopqrstuvwxyz" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    //Path to textures
    public static final String ICON_CAT = "data/images/icons/icon_cat.png";
    public static final String ICON_LOGO = "data/images/icons/icon_logo.png";
    public static final String ICON_CASH = "data/images/icons/icon_cash.png";
    public static final String ICON_COIN = "data/images/icons/icon_coin.png";
    public static final String ICON_ENERGY = "data/images/icons/icon_energy.png";
    public static final String ICON_TIME = "data/images/icons/icon_time.png";
    public static final String LIGHTGRAY_STAR = "data/images/stars/lightgray_star.png";
    public static final String DARKGRAY_STAR = "data/images/stars/darkgray_star.png";
    public static final String BLUE_STAR = "data/images/stars/blue_star.png";

    //Paths to file of game data
    public static final String PLAYER = "configs/player";
    public static final String PARAMETERS = "configs/parameters";
    public static final String STATISTICS = "configs/statistics/";

    //Path to file of style
    public static final String SKIN = "data/styles/uiskin.json";

    //Path to file of text list of specialty
    public static final String LIST_SPECIALTY = "data/textfiles/specialty.txt";

    //Path to file of music
    public static final String BACKGROUND_MUSIC = "data/music/The xx - Intro (Long Version).mp3";

    //Name files of questions and answer
    public static final String SOCIOLOGY = "sociology.txt";
    public static final String HISTORY_RU = "history_of_Russia.txt";
    public static final String HISTORY_WORLD = "foreign_history.txt";
    public static final String RUSSIAN = "Russian.txt";
    public static final String ENGLISH = "English.txt";
    public static final String ECONOMICS = "economics.txt";
    public static final String CULTURAL_STUDIES = "cultural_studies.txt";
    public static final String BIOLOGY = "biology.txt";
    public static final String ANATOMY = "anatomy.txt";
    public static final String LATIN = "Latin.txt";
    public static final String LITERATURE = "literature.txt";
    public static final String PHILOSOPHY = "philosophy.txt";
    public static final String JURISPRUDENCE = "jurisprudence.txt";
    public static final String COMPUTER_SCIENCE = "computer_science.txt";
    public static final String PHYSICS = "physics.txt";
}
