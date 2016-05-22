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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.teamproject.game.STGame;
import com.teamproject.game.models.Parameters;
import com.teamproject.game.models.Student;

import static com.badlogic.gdx.utils.TimeUtils.millis;

/**
 * Created by Roman_Mashenkin on 01.04.2016.
 *
 * This class sets all needful functions.
 */
public class Utils {

    /* This class help to save pointer on texture and pixmap for disposing */
    public static class TextureData {

        public TextureRegionDrawable texture1;
        public TextureRegionDrawable texture2;
        public Pixmap pixmap1;
        public Pixmap pixmap2;
        public ImageTextButton.ImageTextButtonStyle style;
    }

    /* This class help to manage information about time */
    public static class TimeData {

        public int seconds;
        public int minutes;
        public int hours;
        public int days;

        public TimeData(int seconds, int minutes, int hours, int days) {

            this.seconds = seconds;
            this.minutes = minutes;
            this.hours = hours;
            this.days = days;
        }
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
    public static boolean isEmpty(String path) {

        FileHandle file = Gdx.files.local(path);

        return file.length() == 0;
    }

    /* This methods creates buttons through pixmap */
    public static TextureData getImageTextButton
            (int width, int height,
             String upButtonColor, String downButtonColor,
             String upTextColor, String downTextColor,
             BitmapFont font) {

        TextureData temp = new TextureData();

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
    public static Animation getAnimation(Array<Texture> textures, int count, int velocity) {

        TextureRegion textureFrames[] = new TextureRegion[count];

        int index = 0;
        for (int i = 0; i < count; i++) {
                textureFrames[index++] = new TextureRegion(textures.get(i));
        }

        return new Animation(1f / velocity, textureFrames);
    }

    /* This method solves problem with smooth of fonts */
    public static void setLinearFilter(BitmapFont font) {

        for (int i = 0; i < font.getRegions().size; i++)
            font.getRegion(i).getTexture().setFilter(Texture.TextureFilter.Linear,
                    Texture.TextureFilter.Linear);
    }

    /* This method gets time in format HH:MM:SS / D */
    public static TimeData getTimeData(long time) {

        int seconds = (int) (time / 1E3);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;

        return new TimeData(seconds % 60, minutes % 60, hours % 24, days);
    }

    /* This method updates time on label in format HH:MM:SS / D */
    public static void updateTimeOnLabel(TimeData time, Label label) {

        label.setText(Constants.DF_TIME.format(time.hours) + ":" +
                Constants.DF_TIME.format(time.minutes) + ":" +
                Constants.DF_TIME.format(time.seconds) + "\n" +
                time.days);
    }

    /* This method returns "TRUE" if day is included in session period */
    public static boolean isSession(int length, int day) {

        return ((length >= 2) && ((((day + "").charAt(length - 2) == '9') &&
                (((day + "").charAt(length - 1) >= '1') &&
                        ((day + "").charAt(length - 1) <= '9')))
                || (((day + "").charAt(length - 2) == '0') &&
                ((day + "").charAt(length - 1) == '0'))));
    }

    /* This method returns "TRUE" if day is *30th/*60th/*90th */
    public static boolean isGrantDay(int length, int day) {

        return ((length >= 2) &&
                ((((day + "").charAt(length - 2) == '3') ||
                        ((day + "").charAt(length - 2) == '6') ||
                        ((day + "").charAt(length - 2) == '9')) &&
                        ((day + "").charAt(length - 1) == '0')));
    }

    /* This method updates value of energy */
    public static void updateEnergy(Student player, boolean[] isActiveAction) {

        if (isActiveAction[0] || isActiveAction[1]) {
            if (player.getEnergy() -
                    3 * Constants.INCREASE_TIME / 1E3f / Constants.DECREASE_ENERGY > 0f)
                player.setEnergy(player.getEnergy() -
                        3 * Constants.INCREASE_TIME / 1E3f / Constants.DECREASE_ENERGY);
            else player.setEnergy(0f);
        } else {
            if (player.getEnergy() -
                    Gdx.graphics.getDeltaTime() * 1.09f / Constants.DECREASE_ENERGY > 0f)
                player.setEnergy(player.getEnergy() -
                        Gdx.graphics.getDeltaTime() * 1.09f / Constants.DECREASE_ENERGY);
            else player.setEnergy(0f);
        }
    }

    /* This methods updates values of time structure (no for updating labels) */
    public static void updateTime(STGame game, boolean[] isActiveAction) {

        Student player = game.getPlayerData();
        int timer;

        TimeData time = Utils.getTimeData(millis() - player.getTime()[0]);

        if (isActiveAction[0] && (time.days == game.getParameters().getDayOfAction())) {
            timer = 14 - time.hours;

            if (timer > 0) {
                player.getTime()[0] -= Constants.INCREASE_TIME;
                time = Utils.getTimeData(millis() - player.getTime()[0]);
            } else {
                if (timer == 0) {
                    time = Utils.getTimeData(millis() - player.getTime()[0]);
                    isActiveAction[0] = false;
                } else {
                    isActiveAction[0] = false;
                    game.getParameters().setDayOfAction(time.days);
                }
            }
        } else isActiveAction[0] = false;

        if (isActiveAction[1] && (time.days == game.getParameters().getDayOfAction())) {
            timer = 18 - time.hours;

            if (timer > 0) {
                player.getTime()[0] -= Constants.INCREASE_TIME;
                time = Utils.getTimeData(millis() - player.getTime()[0]);
            } else {
                if (timer == 0) {
                    time = Utils.getTimeData(millis() - player.getTime()[0]);
                    isActiveAction[1] = false;
                } else {
                    isActiveAction[1] = false;
                    game.getParameters().setDayOfAction(time.days);
                }
            }
        } else isActiveAction[1] = false;

        if (!isActiveAction[0] && !isActiveAction[1])
            game.getParameters().setDayOfAction(time.days);
    }

    /* This method updates value of semester */
    public static void updateSemester(Student player, TimeData time, int[] indexOfSubject) {

        if (player.isFlag() && !Utils.isSession((time.days + "").length(), time.days) &&
                (time.days <= Constants.COUNT_AVAILABLE_SEMESTERS * 100 + 1))
            if (player.getSemester() != time.days / 100) {
                setGrantForResultOfSession(player, indexOfSubject);
                player.setSemester(time.days / 100);
            }
    }

    /* This methods updates value of cash */
    public static void updateCash(STGame game, TimeData time) {

        if (isGrantDay((time.days + "").length(), time.days)) {
            if ((game.getParameters().getDayOfGrant() < time.days) &&
                    (!game.getParameters().isGrant()[0])) {
                game.getPlayerData().setCash(
                        game.getPlayerData().getCash() + game.getPlayerData().getGrant()
                );
                game.getParameters().setGrant(new boolean[]{true, game.getParameters().isGrant()[1]});
                game.getParameters().setDayOfGrant(time.days);
            }
        } else {
            game.getParameters().setGrant(new boolean[]{false, game.getParameters().isGrant()[1]});
            game.getParameters().setDayOfGrant(time.days);
        }
    }

    /* This methods update information about energy of player */
    public static void setEnergy(Student player) {

        player.setEnergy(player.getEnergy() -
                (millis() - player.getTime()[1]) / 1E3f / Constants.DECREASE_ENERGY);
    }

    /* This methods update information about grant of player */
    public static void setGrant(STGame game) {

        Student player = game.getPlayerData();
        Parameters parameters = game.getParameters();

        Utils.TimeData timeNow = Utils.getTimeData(millis() - player.getTime()[0]);
        Utils.TimeData timeLast = Utils.getTimeData(player.getTime()[1] - player.getTime()[0]);

        int count = 0;
        for (int i = timeLast.days / 10 + 1; i < timeNow.days / 10; i++)
            if (i > 0) {
                if (((i + "").charAt((i + "").length() - 1) == '3') ||
                        ((i + "").charAt((i + "").length() - 1) == '6') ||
                        ((i + "").charAt((i + "").length() - 1) == '9'))
                    count++;
            }

        if (!parameters.isGrant()[0])
            if ((((timeNow.days / 10 + "").charAt((timeNow.days / 10 + "").length() - 1) == '3') ||
                    ((timeNow.days / 10 + "").charAt((timeNow.days / 10 + "").length() - 1) == '6') ||
                    ((timeNow.days / 10 + "").charAt((timeNow.days / 10 + "").length() - 1) == '9')) &&
                    (timeNow.days % 10 != '0')) {
                if ((parameters.getDayOfGrant() < timeNow.days) &&
                        (!parameters.isGrant()[1])) {
                    parameters.setGrant(new boolean[]{parameters.isGrant()[0],
                            true});
                    parameters.setDayOfGrant(timeNow.days);
                    count++;
                }
            } else {
                parameters.setGrant(new boolean[]{parameters.isGrant()[0],
                        false});
                parameters.setDayOfGrant(timeNow.days);
            }

        if (player.getSemester() == (timeNow.days - 1) / 100)
            player.setCash(player.getCash() + count * player.getGrant());
    }

    /* This methods sets grant through result of session */
    public static void setGrantForResultOfSession(Student player, int[] indexOfSubject) {

        boolean[] markOfExams = new boolean[]{false, false, false, false};

        for (int anIndexOfSubject : indexOfSubject) {

            String path = Constants.STATISTICS +
                    player.getSemester() + "_";

            switch (anIndexOfSubject) {
                case 0:
                    path += Constants.SOCIOLOGY;
                    break;
                case 1:
                    path += Constants.HISTORY_RU;
                    break;
                case 2:
                    path += Constants.HISTORY_WORLD;
                    break;
                case 3:
                    path += Constants.RUSSIAN;
                    break;
                case 4:
                    path += Constants.ENGLISH;
                    break;
                case 5:
                    path += Constants.ECONOMICS;
                    break;
                case 6:
                    path += Constants.CULTURAL_STUDIES;
                    break;
                case 7:
                    path += Constants.BIOLOGY;
                    break;
                case 8:
                    path += Constants.ANATOMY;
                    break;
                case 9:
                    path += Constants.LATIN;
                    break;
                case 10:
                    path += Constants.LITERATURE;
                    break;
                case 11:
                    path += Constants.PHILOSOPHY;
                    break;
                case 12:
                    path += Constants.JURISPRUDENCE;
                    break;
                case 13:
                    path += Constants.COMPUTER_SCIENCE;
                    break;
                case 14:
                    path += Constants.PHYSICS;
                    break;
            }

            FileHandle file = Gdx.files.local(path);
            String stringStream = file.readString();
            float mark = Float.parseFloat(stringStream);

            if ((mark > 0.8f) && (mark <= 1f)) markOfExams[0] = true;
            else if ((mark > 0.6f) && (mark <= 0.8)) markOfExams[1] = true;
            else if ((mark > 0.4f) && (mark <= 0.6f)) markOfExams[2] = true;
            else markOfExams[3] = true;
        }

        if (markOfExams[3]) player.setGrant(0);
        else if (markOfExams[2]) player.setGrant(0);
        else if (markOfExams[1] && !markOfExams[0]) player.setGrant(1500);
        else if (markOfExams[1] && markOfExams[0]) player.setGrant(2000);
        else if (markOfExams[0]) player.setGrant(2500);
    }
}
