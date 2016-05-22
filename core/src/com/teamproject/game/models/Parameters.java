package com.teamproject.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.teamproject.game.additions.Constants;

/**
 * Created by Roman_Mashenkin on 13.05.2016.
 *
 * Class of game parameters.
 */
public class Parameters {

    private float volume;
    private boolean[] isActiveAction;
    private int dayOfAction;
    private boolean[] grant;
    private int dayOfGrant;

    private static int count;

    public Parameters(float volume, boolean[] isActiveAction, int dayOfAction,
                      boolean[] grant, int dayOfGrant) {

        setVolume(volume);
        setIsActiveAction(isActiveAction);
        setDayOfAction(dayOfAction);
        setGrant(grant);
        setDayOfGrant(dayOfGrant);
    }

    /* Reading data of parameters from file */
    public static Parameters readParameters() {

        //Opening file to read parameters
        FileHandle file = Gdx.files.local(Constants.PARAMETERS);
        String stringStream = file.readString();

        String tmp[] = new String[7];
        count = 0;

        for (int i = 0; i < 7; i++) {
            tmp[i] = readLine(stringStream);
            count += 2;
        }

        return new Parameters(Float.parseFloat(tmp[0]),
                new boolean[]{Boolean.parseBoolean(tmp[1]), Boolean.parseBoolean(tmp[2])},
                Integer.parseInt(tmp[3]),
                new boolean[]{Boolean.parseBoolean(tmp[4]), Boolean.parseBoolean(tmp[5])},
                Integer.parseInt(tmp[6]));
    }

    /* Reading line of string variable */
    private static String readLine(String stringStream) {

        String tmp = "";

        while (true) {
            if (stringStream.length() != count) {
                if (stringStream.charAt(count) != '\r') {
                    tmp += stringStream.charAt(count++);
                } else break;
            } else break;
        }

        return tmp;
    }

    /* Writing data of parameters into file */
    public static void writeParameters(float volume, boolean[] isActiveAction, int dayOfAction,
                                       boolean[] grant, int dayOfGrant) {

        FileHandle file = Gdx.files.local(Constants.PARAMETERS);
        file.writeString(volume + "\r\n" +
                isActiveAction[0] + "\r\n" +
                isActiveAction[1] + "\r\n" +
                dayOfAction + "\r\n" +
                grant[0] + "\r\n" +
                grant[1] + "\r\n" +
                dayOfGrant, false);
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public boolean[] getIsActiveAction() {
        return isActiveAction;
    }

    public void setIsActiveAction(boolean[] isActiveAction) {
        this.isActiveAction = isActiveAction;
    }

    public int getDayOfAction() {
        return dayOfAction;
    }

    public void setDayOfAction(int dayOfAction) {
        this.dayOfAction = dayOfAction;
    }

    public boolean[] isGrant() {
        return grant;
    }

    public void setGrant(boolean[] grant) {
        this.grant = grant;
    }

    public int getDayOfGrant() {
        return dayOfGrant;
    }

    public void setDayOfGrant(int dayOfGrant) {
        this.dayOfGrant = dayOfGrant;
    }
}
