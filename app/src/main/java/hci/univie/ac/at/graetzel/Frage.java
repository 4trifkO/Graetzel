package hci.univie.ac.at.graetzel;

import java.util.ArrayList;

/**
 * Created by Trifko on 10.05.2018.
 */

public class Frage {
    private String textFrage;
    private ArrayList<Antwort> antworten;

    public Frage(String textFrage) {
        this.textFrage = textFrage;
    }


    public String getTextFrage() {
        return textFrage;
    }

    public ArrayList<Antwort> getAntworten() {
        return antworten;
    }

    public void addAntwort(Antwort antwort) {
        antworten.add(antwort);
    }
}
