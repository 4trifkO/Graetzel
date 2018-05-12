package hci.univie.ac.at.graetzel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.RadioButton;

/**
 * Created by uni on 11.05.18.
 */

public class SucheBieteBase extends FragenActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Suche & Biete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
