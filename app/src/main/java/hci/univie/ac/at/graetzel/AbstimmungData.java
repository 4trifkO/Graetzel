package hci.univie.ac.at.graetzel;

import java.util.ArrayList;
import java.util.Random;

//Data-Klasse für die App-Funktion "Abstimmungen"
public class AbstimmungData {
    private String thema;
    private int optionsNr;
    private ArrayList<String> options;
    private int[] optionData;
    private boolean abgestimmt;

    /*-----Constructor-----*/
    public AbstimmungData(String thema, ArrayList<String> options) {
        this.thema = thema;
        this.options = options;
        this.optionsNr = options.size();
        optionData = new int[optionsNr];
        this.abgestimmt=false;
    }

    /*----------Getter------------*/
    public String getThema() {
        return thema;
    }

    public int getOptionsNr() {
        return optionsNr;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public String getTheOneOption(int position) {
        return options.get(position);
    }

    public int[] getOptionData() {
        return optionData;
    }

    public float getTheOneOptionData(int position) {
        return optionData[position];
    }

    public boolean getAbgestimmt(){
        return abgestimmt;
    }

    //Increase data value by one
    public void increaseValue(int position){
        optionData[position]++;
        abgestimmt=true;
    }

    //Für FakeData
    //Array wird mit Randomwerte befüllt
    public void randomData(){
        Random rd = new Random();
        for (int i = 0; i < optionsNr; i++){
            optionData[i]= rd.nextInt(20)+1;
        }
    }
}
