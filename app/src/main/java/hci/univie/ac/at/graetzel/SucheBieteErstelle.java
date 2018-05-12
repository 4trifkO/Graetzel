package hci.univie.ac.at.graetzel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SucheBieteErstelle extends AppCompatActivity {
    private Toolbar toolbar;
    EditText header;
    EditText text;
    EditText kontakt;
    RadioButton rbSuche,rbBiete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suche_biete_erstelle);
        header = (EditText) findViewById(R.id.editText_header);
        text = (EditText) findViewById(R.id.editText_text);
        kontakt = (EditText) findViewById(R.id.editText_kontaktdaten);
        rbSuche = (RadioButton) findViewById(R.id.rbSuche);
        rbBiete = (RadioButton) findViewById(R.id.rbBiete);
        rbBiete.setChecked(true);
        makeToolbar();
    }

    public void onRadioButtonBiete(View view) {
        rbSuche.setChecked(false);
    }
    public void onRadioButtonSuche(View view) {
        rbBiete.setChecked(false);
    }

    public void onButtonOK(View view) {
        String h=header.getText().toString();
        if(h.isEmpty()) {
            Toast.makeText(this, "Bitte Überschrift angeben.", Toast.LENGTH_LONG).show();
        } else {
            String k=kontakt.getText().toString();
            if(k.isEmpty()) {
                Toast.makeText(this, "Bitte Kontaktdaten angeben.", Toast.LENGTH_LONG).show();
            } else {
                Intent i = new Intent();
                i.putExtra("suche", rbSuche.isChecked());
                i.putExtra("header", h);
                i.putExtra("text", text.getText().toString());
                i.putExtra("kontackt", k);
                setResult(Activity.RESULT_OK, i);
                go_back();
            }
        }

    }
    public void onButtonChancel(View view) {
        setResult(Activity.RESULT_CANCELED);
        go_back();
    }
    private void go_back()  {
        finish();
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
