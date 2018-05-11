package hci.univie.ac.at.graetzel;

import android.widget.RadioButton;

import java.util.ArrayList;


public class SucheBieteData {
    boolean is_suche_option=false;
    ArrayList<Boolean> is_suche =  new ArrayList<Boolean>();
    ArrayList<String> header =  new ArrayList<String>();
    ArrayList<String> text =  new ArrayList<String>();
    ArrayList<String> KontaktInfo = new ArrayList<String>();
    ArrayList<Integer> imageID =  new ArrayList<Integer>();

    SucheBieteData()    {
        fill_data();
    }


    public int size()   {
        System.out.println("size()");
        return is_suche.size();
    }

    public void add(boolean is_suche,String header, String text, String KontaktInfo, int imageID )   {
        this.is_suche.add(0,is_suche);
        this.header.add(0,header);
        this.text.add(0,text);
        this.KontaktInfo.add(0,KontaktInfo);
        this.imageID.add(0,imageID);

        System.out.println("add:"+is_suche+","+header+","+ text+","+KontaktInfo+","+imageID );
    }

    public int getSelectedCount()    {
        int c=0;
        for(int i=0;i<is_suche.size();i++)  if (is_suche.get(i)==is_suche_option) c++;
        //System.out.println("getSelectedCount()="+c);
        return c;
    }
    private int getSelectedPos(int pos)    {
        //System.out.println("getSelectedPos "+pos);
        //System.out.println("is_suche_option="+is_suche_option);
        int c=0,i=0;
        for(;c<=pos;i++)  {
            if (is_suche.get(i)==is_suche_option)  c++;
        }
        --i;
        //System.out.println("getSelectedPos="+i);
        return i;
    }

    public boolean getIs_suche(int pos) {
        return is_suche.get(getSelectedPos(pos));
    }
    public void setIs_suche(int pos, boolean val) {
        this.is_suche.set(pos,val);
    }
    public void setIsSucheOption(boolean val)   {
        is_suche_option=val;
    }
    public String getHeader(int pos) {
        return header.get(getSelectedPos(pos));
    }
    public void setHeader(int pos, String header) {
        this.header.set(pos,header);
    }
    public String getText(int pos) {
        return text.get(getSelectedPos(pos));
    }
    public void setText(int pos, String text) {
        this.text.set(pos,text);
    }

    public String getKontaktInfo(int pos) {
        return KontaktInfo.get(getSelectedPos(pos));
    }

    public int getImageId(int pos) {
        int id=imageID.get(getSelectedPos(pos));
        if(id==0) id=R.drawable.noimage;
        return id;
    }
    public void setImageId(int pos, int imageId) {
        this.imageID.set(pos,imageId);
    }

    private void fill_data()    {
        add(false,"Fahrradcodierung","online","",R.drawable.bild_bikefinder);
        add(false,"English Coaching","British without accent","",0);
        add(false,"Computerreparatur","auch Abends und am Wochenende!","",R.drawable.bild_computerreparatur);
        add(false,"PC-Service","auch am Wochenende + Feiertag","",R.drawable.bild_pc);
        add(false,"PC-Service","und Laptop Reparatur","",0);
        add(false,"Kürkleider","für Eiskunstlauf, Rollschuhlauf, Eistanz Marken JM.in verschiedenen Design Farben, Größen","",R.drawable.bild_kuerkleider);
        add(false,"Designe Top","Wunderschönen für Mädchen und Damen für Eiskunstlauf und verschiedenen Sportarten","",R.drawable.bild_wunderschoene_jacke);
        add(false,"Kombi Set Top und Rock","Wunderschöne champagnerfarbige","",R.drawable.bild_elegante);
        add(false,"Damen-Rauhleder-Jacke","Moosgrüne","",R.drawable.bild_moosgruene);
        add(true,"Jacke","Jean - blau","",R.drawable.bild_jean);
        add(true,"Jacke","Jean - schwarz","",R.drawable.bild_jean_schwarz);

    }
}
