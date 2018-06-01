package hci.univie.ac.at.graetzel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout1;
    private TableLayout tableLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout1 = (TableLayout) findViewById(R.id.tableForButtons1);
        tableLayout2 = (TableLayout) findViewById(R.id.tableForButtons2);

        addButtonsToTable(1);
        addButtonsToTable(2);

    }

    private void addButtonsToTable(final int option){
        for(int zeile = 0; zeile < 3; zeile++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            if(option==1){
                tableLayout1.addView(tableRow);
            }else if(option==2){
                tableLayout2.addView(tableRow);
            }

            final float scale = getResources().getDisplayMetrics().density;
            int padding_in_px = (int) (16 * scale + 0.5f);
            tableRow.setPadding(0,0,0,padding_in_px);

            ImageButton btn = new ImageButton(this);
            btn.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f));

            btn.setBackgroundResource(R.drawable.mainbuttonrectselector);
            if(option==2 && zeile== 2){
                btn.setBackgroundResource(R.drawable.mainbuttonroundselector);
            }

            final int finalZeile = zeile;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openActivity(option==1 ? finalZeile +1 : finalZeile +4);
                }
            });



            tableRow.addView(btn);
        }
    }

    private void openActivity(int option){
        Intent intent=null;
        switch (option){
            case 1:
                intent = new Intent(this, FragenActivity.class);
                break;
            case 4:
                intent = new Intent(this, SucheBiete.class);
                break;
            case 2:
                intent = new Intent(this, GemeinsamActivity.class);
                break;
            case 5:
                intent = new Intent(this, AbstimmungenActivity.class);
                break;
            case 3:
                intent = new Intent(this, RoomReservation.class);
                break;
            case 6:
                intent = new Intent(this,Avatar1.class);
                break;

        }
            startActivity(intent);
    }

}
