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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Trifko on 10.05.2018.
 */

public class FragenListAdapter extends ArrayAdapter<Frage> {

    private Context context;
    private int layoutResource;
    private List<Frage> fragenListe;


    public FragenListAdapter(Context context, int resource, List<Frage> fragenListe) {
        super(context, resource, fragenListe);
        this.context = context;
        this.layoutResource = resource;
        this.fragenListe = fragenListe;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutResource, null);

        TextView textFrage = (TextView) view.findViewById(R.id.textFrage);
        final Frage frage = fragenListe.get(position);

        textFrage.setText(frage.getTextFrage());

        final Switch switchAntworten = (Switch) view.findViewById(R.id.switchAntworten);
        final ConstraintLayout layout = view.findViewById(R.id.layoutAntworten);
        TextView textAntworten = (TextView) view.findViewById(R.id.textAntworten);
        textAntworten.setText(arrayListToTextView(frage.getAntworten()));

        Button buttonAntworten = (Button) view.findViewById(R.id.buttonAntworten);

        switchAntworten.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    layout.setVisibility(View.VISIBLE);

                }else{
                    layout.setVisibility(View.GONE);
                }
            }
        });

        buttonAntworten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    frage.addAntwort("yooooooo");
                    notifyDataSetChanged();
                }catch(Exception e){
                    Log.e("Antwortfehler:", e.getMessage());
                }
            }
        });


        return view;
    }

    private String arrayListToTextView(ArrayList<Antwort> array){
        String returnString = "";

        if(array == null){
            returnString += "Es existieren keine Antworten!!!";
        }else{
            ListIterator<Antwort> it =array.listIterator();

            for (Antwort a:array) {
                returnString += "Antwort: " + a.getAntwortText();
                if(!array.get(array.size() - 1).equals(a)){
                    returnString += "\n\n";
                }
            }
        }
        return returnString;
    }


}
