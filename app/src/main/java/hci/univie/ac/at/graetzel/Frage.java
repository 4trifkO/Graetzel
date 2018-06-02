package hci.univie.ac.at.graetzel;

import java.util.ArrayList;

//Data-Klasse für die App-Funktion "Fragen & Antworten"
public class Frage {
    private String textFrage;
    private ArrayList<Antwort> antworten;
    private boolean switchState;

    /*-----Constructor-----*/
    public Frage(String textFrage) {
        this.textFrage = textFrage;
        antworten = new ArrayList<>();
        this.switchState = false;
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

    public boolean isSwitchState() {
        return switchState;
    }

    public void setSwitchState(boolean switchState) {
        this.switchState = switchState;
    }
}
