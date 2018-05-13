package hci.univie.ac.at.graetzel;

import java.util.ArrayList;

public class Frage {
    private String textFrage;
    private ArrayList<Antwort> antworten;

    /*-----Constructor-----*/
    public Frage(String textFrage) {
        this.textFrage = textFrage;
        antworten = new ArrayList<>();
    }

    /*----------Getter------------*/
    public String getTextFrage() {
        return textFrage;
    }

    public ArrayList<Antwort> getAntworten() {
        return !antworten.isEmpty() ? antworten: null;
    }

    //Neue Antwort zur Frage hinzufügen
    public void addAntwort(String antwort) {
        Antwort ant = new Antwort(antwort);
        antworten.add(ant);
    }
}
