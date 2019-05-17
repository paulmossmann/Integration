package com.daviddev.projetgocaching_intgration1;


public class Geocache {

    private static String question;
    private static String answer;
    private static String choice_1;
    private static String choice_2;
    private static String choice_3;
    private static String clue_1;
    private static String clue_2;
    private static String url;

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

    public static String getUrl(){
        return  DataBaseManager.getURL(Course.getNextGeocacheId());
    }

    public static double getNextLongitude() {

        return Double.parseDouble(DataBaseManager.getLongitude(Course.getNextGeocacheId()));

    }

    public static double getNextLatitude() {

        return Double.parseDouble(DataBaseManager.getLatitude(Course.getNextGeocacheId()));

    }

    public static String getNextTitle() {
        return DataBaseManager.getTitle(Course.getNextGeocacheId());
    }

    public static void setupGeocache() {
        question = DataBaseManager.getQuestion(Course.getCurrentGeocacheId());
        answer = DataBaseManager.getAnswer(Course.getCurrentGeocacheId());
        choice_1 = DataBaseManager.getChoice1(Course.getCurrentGeocacheId());
        choice_2 = DataBaseManager.getChoice2(Course.getCurrentGeocacheId());
        choice_3 = DataBaseManager.getChoice3(Course.getCurrentGeocacheId());
        clue_1 = DataBaseManager.getClue1(Course.getCurrentGeocacheId());
        clue_2 = DataBaseManager.getClue2(Course.getCurrentGeocacheId());
        url = DataBaseManager.getURL(Course.getCurrentGeocacheId());
    }
}