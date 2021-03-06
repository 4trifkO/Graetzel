package hci.univie.ac.at.graetzel;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Klasse für die Activity und App-Funktion "Abstimmungen"
public class AbstimmungenActivity extends FragenActivity {
    Toolbar toolbar;
    ListView listView;
    List<AbstimmungData> abstimmungDataList;
    FloatingActionButton floatAbstimmung;
    AbstimmungListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstimmungen);

        //Setzen der Actiontoolbar
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Abstimmungen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //FakeData
        abstimmungDataList = new ArrayList<>();

        AbstimmungData fake = new AbstimmungData("SPÖ oder FPÖ?",
                new ArrayList<String>(Arrays.asList("SPÖ","FPÖ") ) );
        AbstimmungData fake2 = new AbstimmungData("Bier, Wein oder Alk ist Alk :)",
                new ArrayList<String>(Arrays.asList("Bier","Wein","Alk ist Alk") ) );
        AbstimmungData fake3 = new AbstimmungData("Lieblings-Programmiermodul",
                new ArrayList<String>(Arrays.asList("ADS","SWE","SWA","HCI","DSE") ) );

        fake.randomData();
        fake2.randomData();
        fake3.randomData();

        abstimmungDataList.add(fake);
        abstimmungDataList.add(fake2);
        abstimmungDataList.add(fake3);

        listView = (ListView) findViewById(R.id.listviewAbstimmen);

        adapter = new AbstimmungListAdapter(this, R.layout.listview_abstimmung_layout, abstimmungDataList);

        listView.setAdapter(adapter);

        //Clicklistener vom Plus-Button rechts unten am Bildschirm
        floatAbstimmung = (FloatingActionButton) findViewById(R.id.floatAbstimmen);
        floatAbstimmung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Öffnet den Dialog wo man eine neue Abstimmung erstellen kann
                openNeueAbstimmungDialog();
            }
        });
    }

    private void openNeueAbstimmungDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.floatdialog_abstimmung_layout, null);

        final EditText editNeueAbstimmung = (EditText) subView.findViewById(R.id.editNeueAbstimmung);
        final EditText editNeueOptionen = (EditText) subView.findViewById(R.id.editAbstimmOptionen);
        final TextView alleOptionen = (TextView) subView.findViewById(R.id.optionenAlle);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.floatDialogStyle);
        builder.setTitle("Neue Abstimmung");
        builder.setMessage("Sie müssen mindestens 2 Optionen hinzufügen!");
        builder.setView(subView);

        builder.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //This will close the Dialog
            }
        });

        final AlertDialog dialog = builder.create();

        ImageButton buttonAddOption = (ImageButton) subView.findViewById(R.id.addOption);
        buttonAddOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context ctx =getApplicationContext();
                if(editNeueOptionen.getText().toString().equals("")){
                    Toast.makeText(ctx,"Sie müssen zuerst eine Option eingeben!", Toast.LENGTH_SHORT).show();
                    editNeueOptionen.setHint("Hier Optionen eingeben");
                }else{
                    if(!editNeueOptionen.getText().toString().replaceAll("\\s+","").equals("")){
                        if(alleOptionen.getText().toString().equals("0")){
                            alleOptionen.setText(editNeueOptionen.getText().toString());
                        }else{
                            alleOptionen.setText(alleOptionen.getText().toString() + ";" +
                                    editNeueOptionen.getText().toString());
                        }
                        editNeueOptionen.setText("");
                        editNeueOptionen.setHint("Neue Option eingeben");
                    }
                }
            }
        });

        Button buttonPosten = (Button) subView.findViewById(R.id.buttonAbstimmPosten);
        buttonPosten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context ctx =getApplicationContext();
                if(editNeueAbstimmung.getText().toString().equals("")){
                    Toast.makeText(ctx,"Sie müssen zuerst ein Abstimmungsthema eingeben!", Toast.LENGTH_SHORT).show();
                    editNeueAbstimmung.setHint("Hier Thema eingeben");

                }else if(alleOptionen.getText().toString().equals("0")){
                    Toast.makeText(ctx,"Sie müssen mindestens zwei Optionen hinzufügen!", Toast.LENGTH_SHORT).show();
                    editNeueOptionen.setHint("Hier Option eingeben");

                }else{
                    String[] tempArray=alleOptionen.getText().toString().split(";");
                    ArrayList<String> optionenArray = new ArrayList<>();

                    for(int i=0; i<tempArray.length; i++){
                        if(!tempArray[i].replaceAll("\\s+","").equals("")){
                            optionenArray.add(tempArray[i]);
                        }
                    }

                    if(optionenArray.size()<=1){
                        Toast.makeText(ctx,"Sie brauchen mehr als eine Option", Toast.LENGTH_SHORT).show();
                    }else{
                        AbstimmungData data = new AbstimmungData(editNeueAbstimmung.getText().toString(),
                                optionenArray);
                        abstimmungDataList.add(data);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ctx,"Neue Abstimmung gepostet", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();
    }
}
