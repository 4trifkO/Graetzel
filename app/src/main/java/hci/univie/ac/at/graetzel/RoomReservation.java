package hci.univie.ac.at.graetzel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RoomReservation extends FragenActivity {
    Toolbar toolbar;
    private ImageButton btn_kitchen, btn_wash, btn_learn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation2);
        // initialising the image buttons and adding click listeners to it.
        btn_kitchen = findViewById(R.id.btn_kitchen);
        btn_learn = findViewById(R.id.btn_learn);
        btn_wash = findViewById(R.id.btn_wash);
        btn_kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRoomCalendarActivity("Küche");
            }
        });
        btn_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRoomCalendarActivity("Lernstube");
            }
        });
        btn_wash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRoomCalendarActivity("Waschraum");
            }
        });
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Raumreservierungen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // depending on the clicked button, there will show up the corresponding
    // room reservation calendar (in prototyp simply illustrated with a different
    // navigation header in the appbar
    public void openRoomCalendarActivity(String room_kind) {
        Intent intent = new Intent(this, RoomCalendar.class);
        intent.putExtra("room_kind",room_kind);
        startActivity(intent);
    }
}
