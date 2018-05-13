package hci.univie.ac.at.graetzel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonFragen;
    private Button buttonSucheBiete;
    private Button buttonGemeinsam;
    private Button buttonAbstimmungen;
    private Button buttonRoomReservation;
    private Button buttonProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Verbindung zwischen Frontend Buttons und Backend und ihre OnClickListener,
        //eine neue Seite bzw. Activity öffnet
        buttonFragen  = findViewById(R.id.buttonFragen);
        buttonFragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragenActivity();
            }
        });

        buttonSucheBiete = findViewById(R.id.buttonSucheBiete);
        buttonSucheBiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSucheBieteActivity();
            }
        });

        buttonGemeinsam  = findViewById(R.id.buttonGemeinsam);
        buttonGemeinsam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGemeinsamActivity();
            }
        });

        buttonAbstimmungen  = findViewById(R.id.buttonAbstimmungen);
        buttonAbstimmungen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbstimmungenActivity();
            }
        });

        buttonRoomReservation = findViewById(R.id.buttonRoom);
        buttonRoomReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRoomActivity();
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
    private void openGemeinsamActivity() {
        Intent intent = new Intent(this, GemeinsamActivity.class);

        startActivity(intent);
    }
    private void openAbstimmungenActivity() {
        Intent intent = new Intent(this, AbstimmungenActivity.class);

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
