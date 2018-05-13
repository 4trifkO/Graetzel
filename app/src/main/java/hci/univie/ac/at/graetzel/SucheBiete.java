package hci.univie.ac.at.graetzel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;

public class SucheBiete extends FragenActivity {    // die Hauptklasse von SucheBiete
    private Toolbar toolbar;
    private GridView grid;
    private RadioButton rbSuche, rbBiete,rbErstelle;
    private SucheBieteData data=new SucheBieteData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suche_biete);

        get_widgets();
        makeToolbar();
        load_data();
        select_biete();
        setOnItemClick();
    }

    private void get_widgets()  {
        grid = (GridView) findViewById(R.id.grid);
        rbSuche=(RadioButton) findViewById(R.id.radioButton_suche);
        rbBiete=(RadioButton) findViewById(R.id.radioButton_biete);
        rbErstelle=(RadioButton) findViewById(R.id.radioButton_erstelle);
    }
    private void select_suche() {
        data.setIsSucheOption(true);
        rbSuche.setChecked(true);
        rbBiete.setChecked(false);
        rbErstelle.setChecked(false);
    }
    private void select_biete() {
        data.setIsSucheOption(false);
        rbSuche.setChecked(false);
        rbBiete.setChecked(true);
        rbErstelle.setChecked(false);
    }
    private void select_erstelle() {
        data.setIsSucheOption(false);
        rbSuche.setChecked(false);
        rbBiete.setChecked(false);
        rbErstelle.setChecked(true);
    }
    private void load_data()    {
        SucheBieteGridData adapter = new SucheBieteGridData(this, data);
        grid.setAdapter(adapter);
    }
    private void setOnItemClick()    {
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openSucheBiete_SingleView(position);
            }
        });
    }

    public void onSucheBiete_RadioButtonClicked(View view) {
        RadioButton rb=null;
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButton_suche:
                if (checked)    select_suche();
                break;
            case R.id.radioButton_biete:
                if (checked) select_biete();
                break;
            case R.id.radioButton_erstelle:
                if (checked) {
                    select_erstelle();
                    openSucheBiete_Erstelle();
                }
                break;
        }
        load_data();
    }

    public void openSucheBiete_SingleView(int position) {
        Intent intent = new Intent(this, SucheBiete_ShowSingle.class);
        Bundle b = new Bundle();
        b.putBoolean("Is_suche",data.getIs_suche(position));
        b.putString("Header",data.getHeader(position));
        b.putString("Text",data.getText(position));
        b.putString("KontaktInfo",data.getKontaktInfo(position));
        b.putInt("ImageId",data.getImageId(position));
        intent.putExtras(b);
        startActivity(intent);
    }
    public void openSucheBiete_Erstelle() {
        Intent intent = new Intent(this, SucheBiete_AnfrageErstellung.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent ret_data) {
        Boolean sel_suche=true;
        if(resultCode== Activity.RESULT_OK) {
            this.data.add(
                ret_data.getBooleanExtra("suche",true),
                ret_data.getStringExtra("header"),
                ret_data.getStringExtra("text"),
                ret_data.getStringExtra("kontackt"),0);
            sel_suche =ret_data.getBooleanExtra("suche",true);
        }
        if(sel_suche)  // suche anfrage
                select_suche();
        else    select_biete();
        load_data();
    }

    private void makeToolbar()  {
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Suche & Biete");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}