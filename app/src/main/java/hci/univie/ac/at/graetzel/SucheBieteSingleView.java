package hci.univie.ac.at.graetzel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class SucheBieteSingleView extends AppCompatActivity {
    private Toolbar toolbar;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        switch (id){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
