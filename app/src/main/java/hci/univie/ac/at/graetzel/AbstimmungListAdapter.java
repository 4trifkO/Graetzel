package hci.univie.ac.at.graetzel;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/*Der Adapter kümmert sich um den View & die Funktion jedes einzelnen Items der ListView*/
class AbstimmungListAdapter  extends ArrayAdapter<AbstimmungData> {

    private Context context;
    private int layoutResource;
    private List<AbstimmungData> abstimmungDataList;

    /*--------Constructor--------*/
    public AbstimmungListAdapter(Context context, int resource, List<AbstimmungData> liste) {
        super(context, resource, liste);
        this.context = context;
        this.layoutResource = resource;
        this.abstimmungDataList = liste;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutResource, null);

        //Verbindet Backend mit Frontend Widgets
        final AbstimmungData abstimmung = abstimmungDataList.get(position);

        TextView textAbstimmung = (TextView) view.findViewById(R.id.textAbstimmung);
        Button buttonDiagramm = (Button) view.findViewById(R.id.buttonAbstimmen);
        final Switch switchAbstimmen = (Switch) view.findViewById(R.id.switchAbstimmen);
        final LinearLayout layout = view.findViewById(R.id.layoutAbstimmen);

        //Setzen der Frontend-Texte
        textAbstimmung.setText(abstimmung.getThema());

        switchAbstimmen.setVisibility(abstimmung.getAbgestimmt()!=true?View.VISIBLE:View.GONE);

        /*Die Abstimmungs-Optionen-Liste wird abhängig vom Switch angezeigt oder versteckt*/
        switchAbstimmen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    layout.setVisibility(View.VISIBLE);

                }else{
                    layout.setVisibility(View.GONE);
                }
            }
        });

        //Diagramm Button onclicklistener
        buttonDiagramm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Öffnet beim Click des Diagramm-Buttons einen Dialog mit dem Diagram*/
                openDiagrammDialog(position, abstimmung.getThema());
            }
        });

        Drawable itembutton = ContextCompat.getDrawable(context,R.drawable.itembutton);

        //Erstellen von N Buttons zur Wahl
        for (int i = 0; i < abstimmung.getOptionsNr(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,5);
            Button btn = new Button(context);

            btn.setBackground(itembutton);
            btn.setId(i);
            final int id_ = btn.getId();
            btn.setText(abstimmung.getOptions().get(id_));
            btn.setTextColor(Color.WHITE);
            layout.addView(btn, params);

            final Button btn1 = (Button) view.findViewById(id_);
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    openStimmeAbgebenDialog(position,id_);
                }
            });
        }
        return view;
    }

    private void openDiagrammDialog(int position, String text) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View subView = inflater.inflate(R.layout.diagram_layout, null);

        PieChart pieChart = (PieChart) subView.findViewById(R.id.diagramm);

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);

        pieChart.setHoleColor(-1);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> array = new ArrayList<>();

        for (int i=0; i<abstimmungDataList.get(position).getOptionsNr();i++){
            array.add(new PieEntry(abstimmungDataList.get(position).getTheOneOptionData(i), abstimmungDataList.get(position).getTheOneOption(i)));
        }

        PieDataSet dataSet = new PieDataSet(array, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
        builder.setTitle(text);
        builder.setMessage("Kuchendiagramm");
        builder.setView(subView);

        AlertDialog dialog = builder.create();

        builder.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //This will close the Dialog
            }
        });

        builder.show();
    }

    //SicherheitsDialog ob man wirklich eine Stimme abgeben möchte
    private void openStimmeAbgebenDialog(final int position, final int id_) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View subView = inflater.inflate(R.layout.stimmeabgeben_layout, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
        builder.setTitle("Diese Auswahl? Sind Sie sicher?");
        builder.setMessage("Eine Stimme kann man nur einmal abgeben");
        builder.setView(subView);

        AlertDialog dialog = builder.create();

        builder.setPositiveButton("JA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                abstimmungDataList.get(position).increaseValue(id_);
                notifyDataSetChanged();
                Toast.makeText(getContext(),
                        "Danke für die Stimme", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        builder.setNegativeButton("NEIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //This will close the Dialog
            }
        });

        builder.show();
    }
}
