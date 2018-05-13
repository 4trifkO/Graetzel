package hci.univie.ac.at.graetzel;

import android.content.Context;

import android.content.DialogInterface;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/*Der Adapter kümmert sich um den View & die Funktion jedes einzelnen Items der ListView*/
public class FragenListAdapter extends ArrayAdapter<Frage> {

    private Context context;
    private int layoutResource;
    private List<Frage> fragenListe;

    /*--------Constructor--------*/
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

        //Verbindet Backend mit Frontend Widgets
        Frage frage = fragenListe.get(position);
        final TextView textFrage = (TextView) view.findViewById(R.id.textFrage);
        TextView textAntworten = (TextView) view.findViewById(R.id.textAntworten);

        Button buttonAntworten = (Button) view.findViewById(R.id.buttonAntworten);
        final Switch switchAntworten = (Switch) view.findViewById(R.id.switchAntworten);
        final ConstraintLayout layout = view.findViewById(R.id.layoutAntworten);

        //Setzen der Frontend-Texte
        textFrage.setText(frage.getTextFrage());
        textAntworten.setText(arrayListToTextView(frage.getAntworten()));

        /*Die Antworten-Liste wird abhängig vom Switch angezeigt oder versteckt*/
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

        //Antworten Button onclicklistener
        buttonAntworten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    /*Öffnet beim Click des Antworten-Buttons ein Dialog Fenster wo man eine neue Antwort
                      verfassen kann*/
                    openDialog(position, textFrage.getText().toString());

                }catch(Exception e){
                    Log.e("Antwortfehler:", e.getMessage());
                }
            }
        });

        return view;
    }
    //Wandelt komplette ArrayList von Objekten zu einem String und liefert den String zurück
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


    private void openDialog(final int position, String textFrage){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.antwortdialog_layout, null);

        TextView dialogTextView = (TextView) subView.findViewById(R.id.dialogTextView);
        dialogTextView.setText("Eingabe:");

        final EditText editAntwort = (EditText) subView.findViewById(R.id.editAntwort);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
        builder.setTitle(textFrage);
        builder.setMessage("Neue Antwort");
        builder.setView(subView);

        AlertDialog dialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(editAntwort.getText().toString().equals("")){
                    Toast.makeText(context,"Sie müssen eine Antwort eingeben", Toast.LENGTH_SHORT).show();
                }else {
                    fragenListe.get(position).addAntwort(editAntwort.getText().toString());
                    notifyDataSetChanged();
                    Toast.makeText(context,"Antwort hinzugefügt", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //This will close the Dialog
            }
        });



        builder.show();
    }


}