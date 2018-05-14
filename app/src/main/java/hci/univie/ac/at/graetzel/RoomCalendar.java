package hci.univie.ac.at.graetzel;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RoomCalendar extends FragenActivity {
    Toolbar toolbar;
    ListView listView;
    List<ReservationTime> timeList;

    ReservationListAdapter adapter;
    String room_kind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_calendar);

        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        room_kind = intent.getStringExtra("room_kind");
        toolbar.setTitle("Raumbelegung " + room_kind);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        timeList = new ArrayList<>();
        Integer i;
        //adding new rooms to our list every time the activity starts to
        // provide a simple prototype of the reservation
        for(i=8;i<20;i++) {
            timeList.add(new ReservationTime(i + " :00 - " + (i+1) + " :00 Uhr"));
        }


        // initialising the list view and display it with the help of the adapter
        listView = (ListView) findViewById(R.id.listview);

        adapter = new ReservationListAdapter(this, R.layout.listview_reservationtimes_layout,timeList );

        listView.setAdapter(adapter);

    }
}
