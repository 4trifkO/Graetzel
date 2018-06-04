package hci.univie.ac.at.graetzel;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

//Data-Klasse für die App-Funktion "Gemeinsame Aktivitäten"
public class SocialAktivitaet {
    private String aktivitaet;
    private String ort;
    private Date datum;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
    private ArrayList<User> teilnehmerListe;
    private boolean switchState;
    private boolean teilnahmeState;
    private String[] fakeNamen = {"Jelena","Stefan","Klaus","Benni","Oliver","Michael","Stefi","Anna","Emili","Stormtrooper"};
    private Random rn;

    /*--------Constructor--------*/
    public SocialAktivitaet(String aktivitaet, String ort, String datum) throws Exception{
        this.aktivitaet = aktivitaet;
        this.ort = ort;
        teilnehmerListe = new ArrayList<>();
        rn = new Random();

        for(int i=rn.nextInt(6); i > 0; i--){
            addTeilnehmer();
        }

        try {
            this.datum = new Date();
            this.datum = sdf.parse(datum);
        } catch (Exception e) {
            Log.e("DateParser",e.getMessage());
            throw e;
        }
        this.switchState = false;
        this.teilnahmeState = false;
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
    public void addTeilnehmer(){

        int randomName = rn.nextInt(10);
        int randomZahl = rn.nextInt(1000);

        User teilnehmer = new User(fakeNamen[randomName]+randomZahl);
        teilnehmerListe.add(teilnehmer);
    }

    public boolean isSwitchState() {
        return switchState;
    }

    public void setSwitchState(boolean switchState) {
        this.switchState = switchState;
    }

    public boolean isTeilnahmeState() {
        return teilnahmeState;
    }

    public void setTeilnahmeState(boolean teilnahmeState) {
        this.teilnahmeState = teilnahmeState;
    }

    public void deleteTeilnehmer(){
        teilnehmerListe.remove(teilnehmerListe.size()-1);
    }

}
