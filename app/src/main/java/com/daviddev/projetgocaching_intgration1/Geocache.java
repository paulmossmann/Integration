package com.daviddev.projetgocaching_intgration1;

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

    Coordinates coordinates;


    //Constructeur de la classe qu'il faut appeler lors de la création d'un objet géocache

    public Geocache(int idGeocache /*L'id donnée via la classe parcours*/) {
        /*setQuestion(idGeocache);
        setAnswer(idGeocache);
        setChoice_1(idGeocache);
        setChoice_2(idGeocache);
        setChoice_3(idGeocache);
        setClue_1(idGeocache);
        setClue_2(idGeocache);
        setLongitude(idGeocache);
        setLatitude(idGeocache);
        setTitle(idGeocache);*/

        setCoordinates(idGeocache);
        setAllTable(idGeocache);
    }

    //Accésseurs (J'utilise seulement les accésseurs)

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getChoice_1() {
        return choice_1;
    }

    public String getChoice_2() {
        return choice_2;
    }

    public String getChoice_3() {
        return choice_3;
    }

    public String getClue_1() {
        return clue_1;
    }

    public String getClue_2() {
        return clue_2;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAllTable() {
        return AllTable;
    }


    //Modifieurs (Tu dois coder tes méthodes de databaseManager pour récupérer les données)
    /*
     VVV Les méthode tout en bas sont fonctionelles, il faut repartir
     de leurs modéle pour coder le reste dans DataBaseManager
    */
/*
    public void setQuestion(int idGeocache) {
        //La base de donnée retourne un String avec la questions
        this.question = DataBaseManager.getQuestion(idGeocache);
    }

    public void setAnswerNbr(int idGeocache) {
        //La base de donnée retourne un int avec le numéro de la réposne dans le tableau de proposition
        this.answerNbr = DataBaseManager.getAnswerNbr(idGeocache);
    }

    public void setProposals(int idGeocache) {
        //La base de donnée retourne un tableau de String avec les propositions
        this.proposals = DataBaseManager.getProposuals(idGeocache);
    }

    public void setClues(int idGeocache) {
        this.clues = DataBaseManager.getClues(idGeocache);
    }
*/
    public void setQuestion() {
        this.question = AllTable[0];
    }

    public void setAnswer() {
        this.answer = AllTable[1];
    }

    public void setChoice_1() {
        this.choice_1 = AllTable[2];;
    }

    public void setChoice_2() {
        this.choice_2 = AllTable[3];;
    }

    public void setChoice_3() {
        this.choice_3 = AllTable[4];;
    }

    public void setClue_1() {
        this.clue_1 = AllTable[5];;
    }

    public void setClue_2() {
        this.clue_2 = AllTable[6];;
    }

    public void setLongitude() {
        this.longitude = AllTable[7];;
    }
    public void setLatitude() {
        this.latitude = AllTable[8];
    }

    public void setTitle() {
        this.title = AllTable[9];;
    }

    public void setCoordinates(int idGeocache) {
        //Faire une modification pour avoir les coords du PROCHAIN géocache.
        this.coordinates = DataBaseManager.getCoordinates(idGeocache);
    }

    public static void setAllTable(int idGeocache) {
        AllTable = DataBaseManager.getAllTable(idGeocache);
    }
}