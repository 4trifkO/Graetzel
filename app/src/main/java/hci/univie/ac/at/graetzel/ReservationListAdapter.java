package hci.univie.ac.at.graetzel;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

//adapter is taking care of the list view as in the 'FragenActivity'
public class ReservationListAdapter extends ArrayAdapter<ReservationTime>{
    private Context context;
    private int layoutResource;
    private List<ReservationTime> reservationTimeList;


    public ReservationListAdapter(Context context, int resource, List<ReservationTime> reservationTimeList) {
        super(context, resource, reservationTimeList);
        this.context = context;
        this.layoutResource = resource;
        this.reservationTimeList = reservationTimeList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutResource, null);

        final TextView textreservationTime = (TextView) view.findViewById(R.id.textReservation);
        final ReservationTime reservationTime = reservationTimeList.get(position);
        textreservationTime.setText(reservationTime.getTime());


        Button buttonReservation = (Button) view.findViewById(R.id.btnReservation);
        Button btncancel = (Button) view.findViewById(R.id.btnCancelReservation);

        // if there are no reservation for the actual position then show the reservation
        // button and if, in our simple case, the room was not reserved from the
        // other default user 1, then show the cancel button instead
        if(reservationTimeList.get(position).roomIsFree()) {
            btncancel.setVisibility(View.INVISIBLE);
        }else{
            buttonReservation.setVisibility(View.INVISIBLE);
            if(reservationTimeList.get(position).getUser_id() != 1) {
                btncancel.setVisibility(View.VISIBLE);
            }
        }

        buttonReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDialog(position,reservationTime.getTime());
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openCancelDialog(position,reservationTime.getTime());
            }
        });


        return view;
    }



    // these two methods show a dialo to confirm reservation/cancelation
    private void openCancelDialog(final int position, String textreservationTime){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.confirmreservation_layout, null);
        TextView dialogTextView = (TextView) subView.findViewById(R.id.dialogTextView);
        dialogTextView.setText("Die Reservierung des Raumes für  " + '\n' +  textreservationTime + " wirklich stornieren?");


        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
        builder.setTitle(textreservationTime);
        builder.setMessage("Reservierung stornieren?");
        builder.setView(subView);

        AlertDialog dialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                reservationTimeList.get(position).cancelReservation();
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //This will close the Dialog
            }
        });



        builder.show();
    }


    private void openDialog(final int position, String textreservationTime){
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.confirmreservation_layout, null);
        TextView dialogTextView = (TextView) subView.findViewById(R.id.dialogTextView);
        dialogTextView.setText("Der Raum wurde für 1 Stunde reserviert ab " + '\n' + textreservationTime);


        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogStyle);
        builder.setTitle(textreservationTime);
        builder.setMessage("Reservierung bestätigen");
        builder.setView(subView);

        AlertDialog dialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reservationTimeList.get(position).makeReservation(2);
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //This will close the Dialog
            }
        });



        builder.show();
    }



}
