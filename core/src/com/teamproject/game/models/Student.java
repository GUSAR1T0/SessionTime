package com.teamproject.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.teamproject.game.additions.Constants;

import java.util.ArrayList;

/**
 * Created by Roman_Mashenkin on 10.04.2016.
 *
 * Class of student data.
 */
public class Student {

    private String name;
    private String specialty;
    private int semester;
    private int cash;
    private int energy;
    private int attendance;

    private static ArrayList<String> specList = setSpecList();

    public Student(String name, int specialty, int semester, int cash, int energy, int attendance) {

        setName(name);

        for (int i = 0; i < specList.size(); i++)
            if (specialty == i) setSpecialty(specList.get(i));

        setSemester(semester);
        setCash(cash);
        setEnergy(energy);
        setAttendance(attendance);
    }

    public static Student readStudentData() {

        //Opening file to read player data
        FileHandle file = Gdx.files.local(Constants.PLAYER);
        String stringStream = file.readString();

        //Reading name of player
        int i = 0;
        String name = "";

        while (true) {
            if (stringStream.charAt(i) != '\r') {
                name += stringStream.charAt(i++);
            } else break;
        }

        //Reading specialty of player
        i += 2;
        String tmp = "";

        while (true) {
            if (stringStream.charAt(i) != '\r') {
                tmp += stringStream.charAt(i++);
            } else break;
        }

        int specialty = Integer.parseInt(tmp);

        /** Next variable can be read with considering of application version:
        *
        * 1.0 - don't have cash, energy and attendance;
        * 1.0.1 - added cash, energy and attendance.
        */

        //Reading semester of player
        i += 2;
        tmp = "";

        try {
            while (true) {
                if (stringStream.charAt(i) != '\r') {
                    tmp += stringStream.charAt(i++);
                } else break;
            }
        } catch (Exception e) {
            while (true) {
                if (stringStream.length() != i) {
                    tmp += stringStream.charAt(i++);
                } else break;
            }
        }

        int semester = Integer.parseInt(tmp);

        //Reading cash of player
        i += 2;
        tmp = "";

        try {
            while (true) {
                if (stringStream.charAt(i) != '\r') {
                    tmp += stringStream.charAt(i++);
                } else break;
            }
        } catch (Exception e) {
            tmp = "1000";
        }

        int cash = Integer.parseInt(tmp);

        //Reading energy of player
        i += 2;
        tmp = "";

        try {
            while (true) {
                if (stringStream.charAt(i) != '\r') {
                    tmp += stringStream.charAt(i++);
                } else break;
            }
        } catch (Exception e) {
            tmp = "100";
        }

        int energy = Integer.parseInt(tmp);

        //Reading attendance of player
        i += 2;
        tmp = "";

        try {
            while (true) {
                if (stringStream.length() != i) {
                    tmp += stringStream.charAt(i++);
                } else break;
            }
        } catch (Exception e) {
            tmp = "0";
        }

        int attendance = Integer.parseInt(tmp);

        return new Student(name, specialty, semester, cash, energy, attendance);
    }

    public static void writeStudentData(String name, int specialty, int semester,
                                        int cash, int energy, int attendance) {

        //Writing entering and choosing data of fields in file
        FileHandle file = Gdx.files.local(Constants.PLAYER);
        file.writeString(name + "\r\n" +
                specialty + "\r\n" +
                semester + "\r\n" +
                cash + "\r\n" +
                energy + "\r\n" +
                attendance, false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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
}
