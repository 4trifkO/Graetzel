package hci.univie.ac.at.graetzel;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Data-Klasse für die App-Funktion "Gemeinsame Aktivitäten"
public class SocialAktivitaet {
    private String aktivitaet;
    private String ort;
    private Date datum;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
    private ArrayList<User> teilnehmerListe;
    private boolean switchState;

    /*--------Constructor--------*/
    public SocialAktivitaet(String aktivitaet, String ort, String datum) throws Exception{
        this.aktivitaet = aktivitaet;
        this.ort = ort;
        teilnehmerListe = new ArrayList<>();
        try {
            this.datum = new Date();
            this.datum = sdf.parse(datum);
        } catch (Exception e) {
            Log.e("DateParser",e.getMessage());
            throw e;
        }
        this.switchState = false;
    }

    /*----------Getter------------*/
    public String getAktivitaet() {
        return aktivitaet;
    }

    public String getOrt() {
        return ort;
    }

    public String getDatum() {
        return sdf.format(datum)+"";
    }

    public ArrayList<User> getTeilnehmerListe() {
        return !teilnehmerListe.isEmpty() ? teilnehmerListe: null;
    }

    //Neuen Teilnehmer zur Aktivität hinzufügen
    public void addTeilnehmer(String name){
        User teilnehmer = new User(name);
        teilnehmerListe.add(teilnehmer);
    }

    public boolean isSwitchState() {
        return switchState;
    }

    public void setSwitchState(boolean switchState) {
        this.switchState = switchState;
    }
}
