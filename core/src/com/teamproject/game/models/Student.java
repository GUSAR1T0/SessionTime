package com.teamproject.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.teamproject.game.additions.Constants;

import static com.badlogic.gdx.utils.TimeUtils.millis;

import java.util.ArrayList;

/**
 * Created by Roman_Mashenkin on 10.04.2016.
 *
 * Class of student data.
 */
public class Student {

    private static int count;
    private String name;
    private String specialtyS;
    private int specialtyI;
    private int semester;
    private int cash;
    private int energy;
    private int attendance;
    private long time;

    private static ArrayList<String> specList = setSpecList();

    public Student(String name, int specialty, int semester, int cash,
                   int energy, int attendance, long time) {

        setName(name);

        specialtyI = specialty;
        for (int i = 0; i < specList.size(); i++)
            if (specialty == i) setSpecialty(specList.get(i));

        setSemester(semester);
        setCash(cash);
        setEnergy(energy);
        setAttendance(attendance);
        setTime(time);
    }

    public static Student readPlayerData() {

        //Opening file to read player data
        FileHandle file = Gdx.files.local(Constants.PLAYER);
        String stringStream = file.readString();

        //Reading name of player
        count = 0;
        String name = readLine(stringStream);

        //Reading specialty of player
        count += 2;
        String tmp = readLine(stringStream);

        int specialty = Integer.parseInt(tmp);

        /** Next variable can be read with considering of application version:
         *
         * 1.0 - added name, specialty and semester;
         * 1.0.1 - added cash, energy and attendance;
         * 1.0.2 - added time of the first launching.
         */

        //Reading semester of player
        count += 2;
        tmp = readLine(stringStream);

        int semester = Integer.parseInt(tmp);

        //Reading cash of player
        count += 2;

        try {
            tmp = readLine(stringStream);
        } catch (Exception e) {
            tmp = "1000";
        }

        int cash = Integer.parseInt(tmp);

        //Reading energy of player
        count += 2;

        try {
            tmp = readLine(stringStream);
        } catch (Exception e) {
            tmp = "100";
        }

        int energy = Integer.parseInt(tmp);

        //Reading attendance of player
        count += 2;

        try {
            tmp = readLine(stringStream);
        } catch (Exception e) {
            tmp = "0";
        }

        int attendance = Integer.parseInt(tmp);

        //Reading time of the first launching
        count += 2;

        try {
            tmp = readLine(stringStream);
        } catch (Exception e) {
            tmp = millis() + "";
        }

        long time = Long.parseLong(tmp);

        return new Student(name, specialty, semester, cash, energy, attendance, time);
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

    /* Writing entered and chosen data of fields to file */
    public static void writeStudentData(String name, int specialty, int semester, int cash,
                                        int energy, int attendance, long time) {

        FileHandle file = Gdx.files.local(Constants.PLAYER);
        file.writeString(name + "\r\n" +
                specialty + "\r\n" +
                semester + "\r\n" +
                cash + "\r\n" +
                energy + "\r\n" +
                attendance + "\r\n" +
                time, false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfSpecialty() {
        return specialtyS;
    }

    public int getValueOfSpecialty() {
        return specialtyI;
    }

    public void setSpecialty(String specialty) {
        this.specialtyS = specialty;
    }

    public static ArrayList<String> getSpecList() {
        return specList;
    }

    public static ArrayList<String> setSpecList() {

        //Opening file to read specialty list
        FileHandle file = Gdx.files.internal(Constants.LIST_SPECIALTY);
        String stringStream = file.readString();

        specList = new ArrayList<String>();
        String tmpString = ""; int i = 0;

        //Getting the list of specialty
        while (true) {
            while (true) {
                if ((stringStream.charAt(i) != '\r') && (stringStream.charAt(i) != '.')) {
                    tmpString += stringStream.charAt(i++);
                } else break;
            }

            specList.add(tmpString);
            tmpString = "";

            if (stringStream.charAt(i) == '.') break;
            else i += 2;
        }

        return specList;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
