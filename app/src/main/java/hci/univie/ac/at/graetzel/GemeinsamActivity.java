 package hci.univie.ac.at.graetzel;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

 public class GemeinsamActivity extends FragenActivity {
     ListView listView;
     List<SocialAktivitaet> aktivitaetenList;
     FloatingActionButton floatFragen;
     GemeinsamListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gemeinsam);

        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Gemeinsame Aktivitäten");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aktivitaetenList = new ArrayList<>();

        //aktivitaetenList.add(new SocialAktivitaet("Laufen", "U1 Donauinsel", "12:00", "20.05.2018"));
        //aktivitaetenList.add(new SocialAktivitaet("Kaffee trinken", "Zimmer 21", "15:00", "16.05.2018"));

        listView = (ListView) findViewById(R.id.listviewGemeinsam);

        //adapter = new FragenListAdapter(this, R.layout.listview_gemeinsam_layout, aktivitaetenList);

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

     }
}
