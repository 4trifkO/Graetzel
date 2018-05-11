package hci.univie.ac.at.graetzel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;

public class SucheBiete extends AppCompatActivity {//BaseAdapter{
    private Toolbar toolbar;
    GridView grid;
    RadioButton rbSuche, rbBiete,rbErstelle;

    SucheBieteData data=new SucheBieteData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suche_biete);

        get_widgets();
        makeToolbar();
        select_biete();
        load_grid();
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
    private void load_grid()    {
        SucheBieteGrid adapter = new SucheBieteGrid(this, data);//,is_suche, header, text, imageId);

        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("onItemClick="+position + " " +view.toString());
                openSucheBieteSingleView(position);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        RadioButton rb=null;
        boolean checked = ((RadioButton) view).isChecked();

        //System.out.println(view.getId() + " " +checked );

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
                    openSucheBieteErstelle();
                }
                break;
        }
        load_grid();
    }

    public void openSucheBieteSingleView(int position) {
        Intent intent = new Intent(this, SucheBieteSingleView.class);
        Bundle b = new Bundle();
        b.putBoolean("Is_suche",data.getIs_suche(position));
        b.putString("Header",data.getHeader(position));
        b.putString("Text",data.getText(position));
        b.putString("KontaktInfo",data.getKontaktInfo(position));
        b.putInt("ImageId",data.getImageId(position));
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
    public void openSucheBieteErstelle() {
        Intent intent = new Intent(this, SucheBieteErstelle.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent ret_data) {
        //super.onActivityResult(requestCode, resultCode, data);
        //System.out.println(data.size());
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
        load_grid();
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