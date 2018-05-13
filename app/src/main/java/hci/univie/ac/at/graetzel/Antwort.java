package hci.univie.ac.at.graetzel;

//Data-Klasse für die App-Funktion "Fragen & Antworten"
public class Antwort {

    private String antwortText;

    /*-----Constructor-----*/
    public Antwort(String antwortText) {
        this.antwortText = antwortText;
    }

    /*----------Getter------------*/
    public String getAntwortText() {
        return antwortText;
    }
}
