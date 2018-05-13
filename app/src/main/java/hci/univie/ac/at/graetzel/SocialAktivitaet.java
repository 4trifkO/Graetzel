package hci.univie.ac.at.graetzel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class SocialAktivitaet {
    private String aktivitaet;
    private String ort;
    private Date uhrzeit;
    private Date datum;
    private ArrayList<User> teilnehmerListe;

    /*--------Constructor--------*/
    public SocialAktivitaet(String aktivitaet, String ort, Date uhrzeit, Date datum) {
        this.aktivitaet = aktivitaet;
        this.ort = ort;
        this.uhrzeit = uhrzeit;
        this.datum = datum;
    }

    /*----------Getter------------*/
    public String getAktivitaet() {
        return aktivitaet;
    }

    public String getOrt() {
        return ort;
    }

    public Date getUhrzeit() {
        return uhrzeit;
    }

    public Date getDatum() {
        return datum;
    }

    //Neuen Teilnehmer zur Aktivität hinzufügen
    public void addTeilnehmer(String name){
        User teilnehmer = new User(name);
        teilnehmerListe.add(teilnehmer);
    }
}
