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

    private static ArrayList<String> specList = setSpecList();

    public Student(String name, int specialty, int semester) {

        setName(name);

        for (int i = 0; i < specList.size(); i++)
            if (specialty == i) setSpecialty(specList.get(i));

        setSemester(semester);
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

        //Reading semester of player
        i += 2;
        tmp = "";

        while (true) {
            if (stringStream.length() != i) {
                tmp += stringStream.charAt(i++);
            } else break;
        }

        int semester = Integer.parseInt(tmp);

        return new Student(name, specialty, semester);
    }

    public static void writeStudentData(String name, int specialty, int semester) {

        //Writing entering and choosing data of fields in file
        FileHandle file = Gdx.files.local(Constants.PLAYER);
        file.writeString(name + "\r\n" + specialty + "\r\n" + semester, false);
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
}
