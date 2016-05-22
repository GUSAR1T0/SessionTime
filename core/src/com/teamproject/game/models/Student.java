package com.teamproject.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.teamproject.game.additions.Constants;

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
    private boolean flag;
    private int cash;
    private int grant;
    private float energy;
    private int[] attendance;
    private long[] time;

    private static Array<String> specList = setSpecList();

    public Student(String name, int specialty, int semester, boolean flag, int cash,
                   int grant, float energy, int[] attendance, long[] time) {

        setName(name);

        specialtyI = specialty;
        for (int i = 0; i < specList.size; i++)
            if (specialty == i) setSpecialty(specList.get(i));

        setSemester(semester);
        setFlag(flag);
        setCash(cash);
        setGrant(grant);
        setEnergy(energy);
        setAttendance(attendance);
        setTime(time);
    }

    public static Student readStudentData() {

        //Opening file to read player data
        FileHandle file = Gdx.files.local(Constants.PLAYER);
        String stringStream = file.readString();

        //Reading data of player
        String tmp[] = new String[11];
        count = 0;

        for (int i = 0; i < 11; i++) {
            tmp[i] = readLine(stringStream);
            count += 2;
        }

        return new Student(tmp[0],
                Integer.parseInt(tmp[1]),
                Integer.parseInt(tmp[2]),
                Boolean.parseBoolean(tmp[3]),
                Integer.parseInt(tmp[4]),
                Integer.parseInt(tmp[5]),
                Float.parseFloat(tmp[6]),
                new int[]{Integer.parseInt(tmp[7]), Integer.parseInt(tmp[8])},
                new long[]{Long.parseLong(tmp[9]), Long.parseLong(tmp[10])}
        );
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
    public static void writeStudentData(String name, int specialty, int semester, boolean flag,
                                        int cash, int grant, float energy, int[] attendance,
                                        long[] time) {

        FileHandle file = Gdx.files.local(Constants.PLAYER);
        file.writeString(name + "\r\n" +
                specialty + "\r\n" +
                semester + "\r\n" +
                flag + "\r\n" +
                cash + "\r\n" +
                grant + "\r\n" +
                energy + "\r\n" +
                attendance[0] + "\r\n" +
                attendance[1] + "\r\n" +
                time[0] + "\r\n" +
                time[1], false
        );
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

    public static Array<String> getSpecList() {
        return specList;
    }

    public static Array<String> setSpecList() {

        //Opening file to read specialty list
        FileHandle file = Gdx.files.internal(Constants.LIST_SPECIALTY);
        String stringStream = file.readString();

        specList = new Array<String>();
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getGrant() {
        return grant;
    }

    public void setGrant(int grant) {
        this.grant = grant;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public int[] getAttendance() {
        return attendance;
    }

    public void setAttendance(int[] attendance) {
        this.attendance = attendance;
    }

    public void setTime(long[] time) {
        this.time = time;
    }

    public long[] getTime() {
        return time;
    }
}
