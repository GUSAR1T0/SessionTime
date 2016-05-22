package com.teamproject.game.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.teamproject.game.additions.Constants;

/**
 * Created by Anna on 09.05.2016.
 *
 * Class of questions for ExamScreen.
 *
 * Index of Subjects:
 *  0 - sociology;
 *  1 - history of Russia;
 *  2 - foreign history;
 *  3 - Russian;
 *  4 - English;
 *  5 - economics;
 *  6 - cultural studies;
 *  7 - biology;
 *  8 - anatomy;
 *  9 - Latin;
 *  10 - literature;
 *  11 - philosophy;
 *  12 - jurisprudence;
 *  13 - computer science;
 *  14 - physics.
 */
public class Session {

    public class Question {

        private String Q;
        private String[] A;
        private int right_A;

        public Question(String Q, String[] A, int right_A) {

            setQ(Q);
            setA(A);
            setRight_a(right_A);
        }

        public String getQ() {
            return Q;
        }

        public void setQ(String Q) {
            this.Q = Q;
        }

        public String[] getA() {
            return A;
        }

        public void setA(String[] A) {
            this.A = A;
        }

        public int getRight_a() {
            return right_A;
        }

        public void setRight_a(int right_A) {
            this.right_A = right_A;
        }
    }

    private Array<Question> jointListOfQuestions;

    private int count;

    public Session(int indexOfSubject) {

        switch (indexOfSubject) {
            case 0: {
                setJointListOfQuestions(readQuestions(Constants.SOCIOLOGY));
                break;
            }
            case 1: {
                setJointListOfQuestions(readQuestions(Constants.HISTORY_RU));
                break;
            }
            case 2: {
                setJointListOfQuestions(readQuestions(Constants.HISTORY_WORLD));
                break;
            }
            case 3: {
                setJointListOfQuestions(readQuestions(Constants.RUSSIAN));
                break;
            }
            case 4: {
                setJointListOfQuestions(readQuestions(Constants.ENGLISH));
                break;
            }
            case 5: {
                setJointListOfQuestions(readQuestions(Constants.ECONOMICS));
                break;
            }
            case 6: {
                setJointListOfQuestions(readQuestions(Constants.CULTURAL_STUDIES));
                break;
            }
            case 7: {
                setJointListOfQuestions(readQuestions(Constants.BIOLOGY));
                break;
            }
            case 8: {
                setJointListOfQuestions(readQuestions(Constants.ANATOMY));
                break;
            }
            case 9: {
                setJointListOfQuestions(readQuestions(Constants.LATIN));
                break;
            }
            case 10: {
                setJointListOfQuestions(readQuestions(Constants.LITERATURE));
                break;
            }
            case 11: {
                setJointListOfQuestions(readQuestions(Constants.PHILOSOPHY));
                break;
            }
            case 12: {
                setJointListOfQuestions(readQuestions(Constants.JURISPRUDENCE));
                break;
            }
            case 13: {
                setJointListOfQuestions(readQuestions(Constants.COMPUTER_SCIENCE));
                break;
            }
            case 14: {
                setJointListOfQuestions(readQuestions(Constants.PHYSICS));
                break;
            }
        }
    }

    public Array<Question> getJointListOfQuestions() {
        return jointListOfQuestions;
    }

    public void setJointListOfQuestions(Array<Question> jointListOfQuestions) {
        this.jointListOfQuestions = jointListOfQuestions;
    }

    /* Reading questions and answers for exams */
    public Array<Question> readQuestions(String nameSubject) {

        Array<Question> question = new Array<Question>();

        FileHandle file_Q = Gdx.files.internal("data/textfiles/questions/" + nameSubject);
        FileHandle file_A = Gdx.files.internal("data/textfiles/answers/" + nameSubject);

        String stringStream_Q = file_Q.readString();
        String stringStream_A = file_A.readString();

        int i = 0;
        count = 0;

        while (true) {

            String Q = readLine(stringStream_Q);
            count += 2;

            String[] A = new String[4];

            A[0] = readLine(stringStream_Q);
            count += 2;
            A[1] = readLine(stringStream_Q);
            count += 2;
            A[2] = readLine(stringStream_Q);
            count += 2;
            A[3] = readLine(stringStream_Q);
            count += 2;

            question.add(new Question(Q, A, stringStream_A.charAt(i++) - 48));

            if (stringStream_Q.length() <= count) break;
        }

        return question;
    }

    /* Reading line of string variable */
    private String readLine(String stringStream) {

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

    public Array<Question> getRandomListOfQuestions(Array<Question> jointListOfQuestions) {

        Array<Question> randomList = new Array<Question>();
        int[] numberOfQuestions = new int[10];

        for (int i = 0; i < 10; i++) {
            numberOfQuestions[i] = MathUtils.random(0, jointListOfQuestions.size - 1);

            while (true) {
                boolean flag = false;

                for (int j = 0; j < i; j++) {
                    if (numberOfQuestions[j] == numberOfQuestions[i]) {
                        numberOfQuestions[i] = MathUtils.random(0, jointListOfQuestions.size - 1);
                        flag = true;
                    }
                }

                if (!flag) break;
            }

            randomList.add(jointListOfQuestions.get(numberOfQuestions[i]));
        }

        return randomList;
    }
}
