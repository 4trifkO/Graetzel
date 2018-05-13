package hci.univie.ac.at.graetzel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SucheBieteGridData extends BaseAdapter{    // für die anzeige der einzelnen angebote im hauptfenster
    private SucheBiete mContext;
    private SucheBieteData data;
    private ImageView imageView;
    private TextView vheader;
    private TextView vtext;
    private View grid;

    public SucheBieteGridData(SucheBiete c, SucheBieteData data){
        mContext = c;
        this.data = data;
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
        //System.out.println("getItemId()");
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        get_widgets();
        if (convertView == null) {
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