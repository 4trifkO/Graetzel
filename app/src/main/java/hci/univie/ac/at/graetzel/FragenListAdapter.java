package hci.univie.ac.at.graetzel;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Trifko on 10.05.2018.
 */

public class FragenListAdapter extends ArrayAdapter<Frage> {

    private Context context;
    private int layoutResource;
    private List<Frage> fragenListe;


    public FragenListAdapter(Context context, int resource, List<Frage> fragenListe) {
        super(context, resource, fragenListe);
        this.context = context;
        this.layoutResource = resource;
        this.fragenListe = fragenListe;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutResource, null);

        TextView textFrage = (TextView) view.findViewById(R.id.textFrage);
        Frage frage = fragenListe.get(position);

        textFrage.setText(frage.getTextFrage());

        Switch switchAntworten = (Switch) view.findViewById(R.id.switchAntworten);
        final ConstraintLayout layout = view.findViewById(R.id.layoutAntworten);

        switchAntworten.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    layout.setVisibility(View.VISIBLE);

                }else{
                    layout.setVisibility(View.GONE);
                }
            }
        });

        return view;
    }

}
