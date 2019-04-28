package com.daviddev.projetgocaching_intgration1;

import android.util.Log;
import android.widget.Toast;

public class Geocache {

    public static String question;
    public static String answer;
    public static String choice_1;
    public static String choice_2;
    public static String choice_3;
    public static String clue_1;
    public static String clue_2;
    public static String longitude;
    public static String latitude;
    public static String title;

    int answerNbr; //On ne récupére pas la réponse mais son indice dans le tableau de proposition.
    public String[] proposals;
    public String[] clues;
    public static String[] AllTable;

    public static Coordinates coordinates;



    //Accésseurs (J'utilise seulement les accésseurs)

    public static String getQuestion() {
        return question;
    }

    public static String getAnswer() {
        return answer;
    }

    public static String getChoice_1() {
        return choice_1;
    }

    public static String getChoice_2() {
        return choice_2;
    }

    public static String getChoice_3() {
        return choice_3;
    }

    public static String getClue_1() {
        return clue_1;
    }

    public static String getClue_2() {
        return clue_2;
    }

    public static double getLongitude() {
        return Double.parseDouble(longitude);
    }

    public static double getLatitude() {
        return Double.parseDouble(latitude);
    }


    public static String getTitle() {
        return title;
    }



    public static void setQuestion() {
        question = AllTable[0];
    }

    public static void setAnswer() {
        answer = AllTable[1];
    }

    public static void setChoice_1() {
        choice_1 = AllTable[2];
    }

    public static void setChoice_2() {
        choice_2 = AllTable[3];
    }

    public static void setChoice_3() {
        choice_3 = AllTable[4];
    }

    public static void setClue_1() {
        clue_1 = AllTable[5];
    }

    public static void setClue_2() {
        clue_2 = AllTable[6];
    }

    public static void setLongitude() {
        longitude = AllTable[7];
    }
    public static void setLatitude() {
        latitude = AllTable[8];
    }

    public static void setTitle() {
        title = AllTable[9];
    }

    public static void setCoordinates(int idGeocache) {
        //Faire une modification pour avoir les coords du PROCHAIN géocache.
        coordinates = DataBaseManager.getCoordinates(idGeocache);
    }

    public static void setAllTable(int idGeocache) {
        AllTable = DataBaseManager.getAllTable(idGeocache);
        setQuestion();
        setAnswer();
        setChoice_1();
        setChoice_2();
        setChoice_3();
        setClue_1();
        setClue_2();
        setLongitude();
        setLatitude();
        setTitle();

    }
}