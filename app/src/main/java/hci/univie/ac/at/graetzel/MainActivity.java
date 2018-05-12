package hci.univie.ac.at.graetzel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonFragen;
    private Button buttonRoomReservation;
    private Button buttonSucheBiete;
    private Button buttonProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFragen  = findViewById(R.id.buttonFragen);
        buttonFragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragenActivity();
            }
        });

        buttonRoomReservation = findViewById(R.id.room);
        buttonRoomReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRoomActivity();
            }
        });

        buttonSucheBiete = findViewById(R.id.buttonSucheBiete);
        buttonSucheBiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSucheBieteActivity();
            }
        });
        buttonProfil = findViewById(R.id.buttonProfil);
        buttonProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });



    }

    public void openFragenActivity() {
        Intent intent = new Intent(this, FragenActivity.class);

        startActivity(intent);
    }
    public void openSucheBieteActivity() {
        Intent intent = new Intent(this, SucheBiete.class);

        startActivity(intent);
    }
    public void openRoomActivity() {
        Intent intent = new Intent(this, RoomReservation.class);

        startActivity(intent);
    }
    public void openProfile(){
        Intent intent = new Intent(this,Avatar1.class);
        startActivity(intent);

    }
}
