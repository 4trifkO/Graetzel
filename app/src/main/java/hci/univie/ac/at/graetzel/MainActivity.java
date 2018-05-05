package hci.univie.ac.at.graetzel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonFragen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonFragen  = findViewById(R.id.buttonFragen);
        buttonFragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragenActivity();
            }
        });
    }

    public void openFragenActivity() {
        Intent intent = new Intent(this, FragenActivity.class);

        startActivity(intent);
    }
}
