package hci.univie.ac.at.graetzel;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//Klasse für die Activity und App-Funktion "Fragen & Antworten"
public class FragenActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView listView;
    List<Frage> fragenListe;
    FloatingActionButton floatFragen;
    FragenListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragen);

        //Setzen der Actiontoolbar
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //FakeData
        fragenListe = new ArrayList<>();
        fragenListe.add(new Frage("Wo gibt es eine Party heute?"));
        fragenListe.add(new Frage("Wer spielt wieder so laut Musik?"));
        fragenListe.add(new Frage("Wo gibt es gute Pizza?"));


        listView = (ListView) findViewById(R.id.listview);

        adapter = new FragenListAdapter(this, R.layout.listview_fragen_layout, fragenListe);

        listView.setAdapter(adapter);

        //Clicklistener vom Plus-Button rechts unten am Bildschirm
        floatFragen = (FloatingActionButton) findViewById(R.id.floatFragen);
        floatFragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Öffnet den Dialog wo man eine neue Frage erstellen kann
                openDialog();
            }
        });
    }

    private void openDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.floatdialog_fragen_layout, null);

        final EditText editNeueFrage = (EditText) subView.findViewById(R.id.editNeueFrage);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.floatDialogStyle);
        builder.setTitle("Neue Frage\n\n");
        builder.setView(subView);

        builder.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //This will close the Dialog
            }
        });

        final AlertDialog dialog = builder.create();

        Button button = (Button) subView.findViewById(R.id.buttonFragePosten);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNeueFrage.getText().toString().equals("")){
                    editNeueFrage.setHint("Hier eine Frage eingeben!");
                    Toast.makeText(getApplicationContext(),"Sie müssen zuerst eine Frage eingeben!", Toast.LENGTH_SHORT).show();
                }else {
                    try{
                        fragenListe.add(new Frage(editNeueFrage.getText().toString()));
                        adapter.notifyDataSetChanged();

                    }catch (Exception e){
                        Log.e("Neue Frage", "Fehler beim adden einer neuen Frage");
                    }finally {
                        Toast.makeText(getApplicationContext(),"Neue Frage gepostet", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }

                }

            }
        });

        dialog.show();
    }

    //Adden eines Menüs in der Action-Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Definition was bei welchem Menü-Punkt gemacht wird
    //FLAG_ACTIVITY_REORDER_TO_FRONT = Wenn eine Activity bereits im hintergrund aktiv ist dann wird
    //                                  keine neue erzeugt sondern die alte erscheint
    //FLAG_ACTIVITY_CLEAR_TOP = bringt die gewünschte neue Activity zum vorschein und löscht alle
    //                          anderen die im Hintergrund sind
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.action_sneakyway_1:
                intent = new Intent(this,FragenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            case R.id.action_sneakyway_2:
                intent = new Intent(this,SucheBiete.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            case R.id.action_sneakyway_3:
                intent = new Intent(this,GemeinsamActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            case R.id.action_sneakyway_4:
                intent = new Intent(this,AbstimmungenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            case R.id.action_sneakyway_5:
                intent = new Intent(this,RoomReservation.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            case R.id.homeicon:
                intent = new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
