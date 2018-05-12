package hci.univie.ac.at.graetzel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class SucheBieteSingleView extends FragenActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suche_biete_single_view);

        Bundle b = getIntent().getExtras();

        TextView header=findViewById(R.id.tvSucheBieteSingleView_header);
        TextView text=findViewById(R.id.tvSucheBieteSingleView_text);
        TextView KontaktInfo=findViewById(R.id.tvSucheBieteSingleView_kontakt);
        ImageView image=findViewById(R.id.imgSucheBieteSingleView);

        makeToolbar();

        header.setText(b.getString("Header"));
        text.setText(b.getString("Text"));
        KontaktInfo.setText(b.getString("KontaktInfo"));
        image.setImageResource(b.getInt("ImageId"));
    }

    private void makeToolbar()  {
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Suche & Biete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
