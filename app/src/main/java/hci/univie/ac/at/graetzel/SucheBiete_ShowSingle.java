package hci.univie.ac.at.graetzel;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class SucheBiete_ShowSingle extends FragenActivity {
    private Toolbar toolbar;
    private TextView header, text, KontaktInfo;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suche_biete_single_view);

        Bundle b = getIntent().getExtras();
        get_widgets();
        makeToolbar();

        header.setText(b.getString("Header"));
        text.setText(b.getString("Text"));
        KontaktInfo.setText(b.getString("KontaktInfo"));
        image.setImageResource(b.getInt("ImageId"));
    }
    private void get_widgets()  {
        toolbar = findViewById(R.id.appbar);
        header=findViewById(R.id.tvSucheBieteSingleView_header);
        text=findViewById(R.id.tvSucheBieteSingleView_text);
        KontaktInfo=findViewById(R.id.tvSucheBieteSingleView_kontakt);
        image=findViewById(R.id.imgSucheBieteSingleView);
    }

    private void makeToolbar()  {
        setSupportActionBar(toolbar);
        toolbar.setTitle("Suche & Biete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
