package com.daviddev.projetgocaching_intgration1;

public class Geocache {

    String question;

    int answerNbr; //On ne récupére pas la réponse mais son indice dans le tableau de proposition.
    String[] proposals;
    String[] clues;

    Coordinates coordinates;
    String title;

    //Constructeur de la classe qu'il faut appeler lors de la création d'un objet géocache

    public Geocache(int idGeocache /*L'id donnée via la classe parcours*/) {
     /* setQuestion(idGeocache);
        setAnswerNbr(idGeocache) ;
        setProposals(idGeocache);
        setClues(idGeocache);*/
        setCoordinates(idGeocache);
        setTitle(idGeocache);
    }

    //Accésseurs (J'utilise seulement les accésseurs)

    public String getQuestion() {
        return question;
    }

    public int getAnswerNbr() {
        return answerNbr;
    }

    public String[] getProposals() {
        return proposals;
    }

    public String[] getClues() {
        return clues;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getTitle() {
        return title;
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
    public void setCoordinates(int idGeocache) {
        //Faire une modification pour avoir les coords du PROCHAIN géocache.
        this.coordinates = DataBaseManager.getCoordinates(idGeocache);
    }

    public void setTitle(int idGeocache) {
        this.title = DataBaseManager.getTitle(idGeocache);
    }
}
