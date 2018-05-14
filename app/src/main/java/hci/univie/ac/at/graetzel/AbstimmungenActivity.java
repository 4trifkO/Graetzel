package hci.univie.ac.at.graetzel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

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

        fake.randomData();
        fake2.randomData();

        abstimmungDataList.add(fake);
        abstimmungDataList.add(fake2);

        listView = (ListView) findViewById(R.id.listviewAbstimmen);

        adapter = new AbstimmungListAdapter(this, R.layout.listview_abstimmung_layout, abstimmungDataList);

        listView.setAdapter(adapter);
    }
}
