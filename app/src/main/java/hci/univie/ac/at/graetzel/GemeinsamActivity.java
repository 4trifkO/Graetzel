package hci.univie.ac.at.graetzel;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//Klasse für die Activity und App-Funktion "Gemeinsame Aktivitäten"
public class GemeinsamActivity extends FragenActivity {
    ListView listView;
    List<SocialAktivitaet> aktivitaetenList;
    FloatingActionButton floatGemeinsam;
    GemeinsamListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gemeinsam);

        //Setzen der Actiontoolbar
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Gemeinsame Aktivitäten");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //FakeData
        aktivitaetenList = new ArrayList<>();
        try {
            aktivitaetenList.add(new SocialAktivitaet("Laufen", "U1 Donauinsel", "12:00 20/05/2018"));
            aktivitaetenList.add(new SocialAktivitaet("Kaffee trinken", "Zimmer 21", "15:00 16/05/2018"));
        }catch(Exception e){
            Log.e("Add FakeAktivity", "Failed");
        }

        listView = (ListView) findViewById(R.id.listviewGemeinsam);

        adapter = new GemeinsamListAdapter(this, R.layout.listview_gemeinsam_layout, aktivitaetenList);

        listView.setAdapter(adapter);

        //Clicklistener vom Plus-Button rechts unten am Bildschirm
        floatGemeinsam = (FloatingActionButton) findViewById(R.id.floatGemeinsam);
        floatGemeinsam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Öffnet den Dialog wo man eine neue Aktivität erstellen kann
                openDialog();
            }
        });

    }

    private void openDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.floatdialog_gemeinsam_layout, null);

        final EditText editNeueAktivitaet = (EditText) subView.findViewById(R.id.editNeueAktivitaet);
        final EditText editNeueOrt = (EditText) subView.findViewById(R.id.editOrt);
        final EditText editUhrzeit = (EditText) subView.findViewById(R.id.editUhrzeit);
        final EditText editDatum = (EditText) subView.findViewById(R.id.editDatum);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.floatDialogStyle);
        builder.setTitle("Neue Aktivität\n\n");
        builder.setView(subView);

        AlertDialog dialog = builder.create();

        builder.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //This will close the Dialog
            }
        });

        Button button = (Button) subView.findViewById(R.id.buttonAktivitaetPosten);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context ctx =getApplicationContext();
                SimpleDateFormat sdfUhrzeit = new SimpleDateFormat("hh:mm");
                SimpleDateFormat sdfDatum = new SimpleDateFormat("dd/MM/yyyy");

                if(editNeueAktivitaet.getText().toString().equals("")){
                    editNeueAktivitaet.setHint("Hier eine Aktivität eingeben!");
                    Toast.makeText(ctx,"Sie müssen zuerst eine Aktivitaet eingeben!", Toast.LENGTH_SHORT).show();

                }else if(editNeueOrt.getText().toString().equals("")){
                    editNeueOrt.setHint("Hier einen Ort eingeben!");
                    Toast.makeText(ctx,"Sie müssen zuerst einen Ort eingeben!", Toast.LENGTH_SHORT).show();

                }else if(editUhrzeit.getText().toString().equals("")){
                    Toast.makeText(ctx,"Sie müssen zuerst eine Uhrzeit eingeben!", Toast.LENGTH_SHORT).show();

                }else if(editDatum.getText().toString().equals("")){
                    Toast.makeText(ctx,"Sie müssen zuerst ein Datum eingeben!", Toast.LENGTH_SHORT).show();

                }else{
                    try{
                        aktivitaetenList.add(new SocialAktivitaet(editNeueAktivitaet.getText().toString(),
                                editNeueOrt.getText().toString(),
                                editUhrzeit.getText().toString()+" "+editDatum.getText().toString()));
                        adapter.notifyDataSetChanged();
                        editNeueAktivitaet.setText("");
                        editNeueOrt.setText("");
                        editUhrzeit.setText("");
                        editDatum.setText("");
                    }catch(Exception e){
                        Toast.makeText(ctx,"Sie müssen Uhrzeit & Datum im richtigen Format eingeben!", Toast.LENGTH_LONG).show();
                    }finally {
                        Toast.makeText(ctx,"Neue Aktivität gepostet", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        builder.show();
    }
}
