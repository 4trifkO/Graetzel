package hci.univie.ac.at.graetzel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Trifko on 10.05.2018.
 */

public class Frage {
    private String textFrage;
    private ArrayList<Antwort> antworten;

    public Frage(String textFrage) {
        this.textFrage = textFrage;
        antworten = new ArrayList<>();
    }


    public String getTextFrage() {
        return textFrage;
    }

    public ArrayList<Antwort> getAntworten() {
        return !antworten.isEmpty() ? antworten: null;
    }

    public void addAntwort(String antwort) {
        Antwort ant = new Antwort(antwort);
        antworten.add(ant);
    }
}
