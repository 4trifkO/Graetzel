package hci.univie.ac.at.graetzel;

import android.content.Context;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*Der Adapter kümmert sich um den View & die Funktion jedes einzelnen Items der ListView*/
public class GemeinsamListAdapter extends ArrayAdapter<SocialAktivitaet> {

    private Context context;
    private int layoutResource;
    private List<SocialAktivitaet> aktivitaetenList;

    /*--------Constructor--------*/
    public GemeinsamListAdapter(Context context, int resource, List<SocialAktivitaet> liste) {
        super(context, resource, liste);
        this.context = context;
        this.layoutResource = resource;
        this.aktivitaetenList = liste;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutResource, null);

        //Verbindet Backend mit Frontend Widgets
        final SocialAktivitaet aktivitaet = aktivitaetenList.get(position);
        TextView textAktivitaet = (TextView) view.findViewById(R.id.textAktivitaet);
        TextView textOrt = (TextView) view.findViewById(R.id.textOrt);
        TextView textDate = (TextView) view.findViewById(R.id.textDate);
        TextView textTeilnehmer = (TextView) view.findViewById(R.id.textTeilnehmer);

        Button buttonTeilnehmen = (Button) view.findViewById(R.id.buttonTeilnehmen);
        final Switch switchTeilnehmer = (Switch) view.findViewById(R.id.switchGemeinsam);
        final ConstraintLayout layout = view.findViewById(R.id.layoutGemeinsam);

        //Setzen der Frontend-Texte
        textAktivitaet.setText(aktivitaet.getAktivitaet());
        textOrt.setText(aktivitaet.getOrt());
        textDate.setText(aktivitaet.getDatum());
        textTeilnehmer.setText(arrayListToTextView(aktivitaet.getTeilnehmerListe()));

        if(aktivitaet.isSwitchState()){
            switchTeilnehmer.setChecked(true);
            layout.setVisibility(View.VISIBLE);
        }

        /*Die Teilnehmer-Liste wird abhängig vom Switch angezeigt oder versteckt*/
        switchTeilnehmer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    aktivitaet.setSwitchState(true);
                    layout.setVisibility(View.VISIBLE);

                }else{
                    aktivitaet.setSwitchState(false);
                    layout.setVisibility(View.GONE);
                }
            }
        });

        if(aktivitaet.isTeilnahmeState()){
            buttonTeilnehmen.setText("Absagen");
        }else{
            buttonTeilnehmen.setText("Teilnehmen");
        }

        /*Fügt beim Click des Teilnehmen-Buttons einen Random-Teilnehmer zur Aktivität hinzu*/
        buttonTeilnehmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    if(aktivitaet.isTeilnahmeState()){
                        deleteRandomTeilnehmer(position);
                        aktivitaet.setTeilnahmeState(false);
                    }else {
                        aktivitaet.setTeilnahmeState(true);
                        addRandomTeilnehmer(position);
                    }

                }catch(Exception e){
                    Log.e("AddTeilnehmerFehler:", e.getMessage());
                }
            }
        });

        return view;
    }

    //Wandelt komplette ArrayList von Objekten zu einem String und liefert den String zurück
    private String arrayListToTextView(ArrayList<User> array){
        String returnString = "";

        if(array == null){
            returnString += "Es existieren keine Teilnehmer!!!";
        }else{
            
            for (User a:array) {
                returnString += "Teilnehmer: " + a.getName();
                if(!array.get(array.size() - 1).equals(a)){
                    returnString += "\n\n";
                }
            }
        }
        return returnString;
    }

    //Generiert Randomzahlen
    //Abhängig von der Randomzahl wird ein Name aus dem FakeNamen-Array gezogen
    //Neuer Teilnehmer wird zur Aktivität hinzugefügt
    public void addRandomTeilnehmer(int position){
        try{
            aktivitaetenList.get(position).addTeilnehmer();
            notifyDataSetChanged();
        }catch(Exception e){
            Log.e("AddRandomTeilnehmer",e.getMessage());
        }finally {
            Toast.makeText(context,"Sie nehmen teil",Toast.LENGTH_LONG).show();
        }

    }

    private void deleteRandomTeilnehmer(int position) {
        aktivitaetenList.get(position).deleteTeilnehmer();
        notifyDataSetChanged();
        Toast.makeText(context,"Sie haben abgesagt",Toast.LENGTH_LONG).show();
    }

}
