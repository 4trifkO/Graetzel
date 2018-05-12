package hci.univie.ac.at.graetzel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AbstimmungenActivity extends FragenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstimmungen);

        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Abstimmungen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
