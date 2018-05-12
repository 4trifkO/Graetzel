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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragenListe = new ArrayList<>();

        fragenListe.add(new Frage("Wo gibt es eine Party heute?"));
        fragenListe.add(new Frage("Wer spielt wieder so laut Musik?"));

        listView = (ListView) findViewById(R.id.listview);

        adapter = new FragenListAdapter(this, R.layout.listview_layout, fragenListe);

        listView.setAdapter(adapter);

        floatFragen = (FloatingActionButton) findViewById(R.id.floatFragen);
        floatFragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


    }

    private void openDialog(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.floatdialog_layout, null);

        final EditText editNeueFrage = (EditText) subView.findViewById(R.id.editNeueFrage);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.floatDialogStyle);
        builder.setTitle("Neue Frage\n\n");
        builder.setView(subView);

        AlertDialog dialog = builder.create();

        builder.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //This will close the Dialog
            }
        });

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
                        editNeueFrage.setHint("Eingabe");
                        editNeueFrage.setText("");
                    }catch (Exception e){
                        Log.e("Neue Frage", "Fehler beim adden einer neuen Frage");
                    }finally {
                        Toast.makeText(getApplicationContext(),"Neue Frage gepostet", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
