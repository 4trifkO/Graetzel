package hci.univie.ac.at.graetzel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class SucheBieteGrid extends BaseAdapter{
    private SucheBiete mContext;
    boolean is_suche_option=true;
    //private final boolean[] is_suche;
    //private final String[] text_header;
    //private final String[] text;
    //private final int[] Imageid;
    SucheBieteData data;
    ImageView imageView;
    TextView vheader;
    TextView vtext;
    View grid;

    public SucheBieteGrid(SucheBiete c, SucheBieteData data){//boolean[] is_suche, String[] text_header, String[] text, int[] Imageid ) {
        mContext = c;
        this.data = data;
        //get_widgets()  ;
    }

    private void get_widgets()  {
        grid = new View(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        grid = inflater.inflate(R.layout.suche_biete_single, null);
        imageView = (ImageView)grid.findViewById(R.id.grid_image);
        vheader = (TextView) grid.findViewById(R.id.suche_biete_single_header_text);
        vtext = (TextView) grid.findViewById(R.id.suche_biete_single_text);
    }

    @Override
    public int getCount() {
        return data.getSelectedCount();
    }

    @Override
    public Object getItem(int position) {
        System.out.println("getItem()");
        return null;
    }

    @Override
    public long getItemId(int position) {
        System.out.println("getItemId()");
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        get_widgets();
        if (convertView == null) {
            //position=data.getSelectedPos(position);
            imageView.setImageResource(data.getImageId(position));
            vheader.setText(data.getHeader(position));
            vtext.setText(data.getText(position));
            grid.setVisibility(View.VISIBLE);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}