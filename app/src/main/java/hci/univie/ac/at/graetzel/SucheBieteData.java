package hci.univie.ac.at.graetzel;

import java.util.ArrayList;


public class SucheBieteData {   // nimmt die Daten zu SucheBiete auf
    private boolean is_suche_option=false;
    private ArrayList<Boolean> is_suche =  new ArrayList<Boolean>();
    private ArrayList<String> header =  new ArrayList<String>();
    private ArrayList<String> text =  new ArrayList<String>();
    private ArrayList<String> KontaktInfo = new ArrayList<String>();
    private ArrayList<Integer> imageID =  new ArrayList<Integer>();

    SucheBieteData()    {
        fill_dummy_data();
    }

    /*public int size()   {
        System.out.println("size()");
        return is_suche.size();
    }*/

    public void add(boolean is_suche,String header, String text, String KontaktInfo, int imageID )   {
        this.is_suche.add(0,is_suche);
        this.header.add(0,header);
        this.text.add(0,text);
        this.KontaktInfo.add(0,KontaktInfo);
        this.imageID.add(0,imageID);
        //System.out.println("add:"+is_suche+","+header+","+ text+","+KontaktInfo+","+imageID );
    }

    public int getSelectedCount()    {  // abhängig ob suche oder biete selektiert
        int c=0;
        for(int i=0;i<is_suche.size();i++)  if (is_suche.get(i)==is_suche_option) c++;
        return c;
    }
    private int getSelectedPos(int pos)    {  // abhängig ob suche oder biete selektiert
        int c=0,i=0;
        for(;c<=pos;i++)  {
            if (is_suche.get(i)==is_suche_option)  c++;
        }
        --i;
        return i;
    }

    public boolean getIs_suche(int pos) {  // abhängig ob suche oder biete selektiert
        return is_suche.get(getSelectedPos(pos));
    }
    /*public void setIs_suche(int pos, boolean val) {
        this.is_suche.set(pos,val);
    }*/
    public void setIsSucheOption(boolean val)   {   // augewählt vom nuter über optionbutton
        is_suche_option=val;
    }
    public String getHeader(int pos) {  // abhängig ob suche oder biete selektiert
        return header.get(getSelectedPos(pos));
    }
    /*public void setHeader(int pos, String header) {
        this.header.set(pos,header);
    }*/
    public String getText(int pos) {  // abhängig ob suche oder biete selektiert
        return text.get(getSelectedPos(pos));
    }
    /*public void setText(int pos, String text) {
        this.text.set(pos,text);
    }*/

    public String getKontaktInfo(int pos) {  // abhängig ob suche oder biete selektiert
        return KontaktInfo.get(getSelectedPos(pos));
    }

    public int getImageId(int pos) {  // abhängig ob suche oder biete selektiert
        int id=imageID.get(getSelectedPos(pos));
        if(id==0) id=R.drawable.noimage;    // kein bild vorhanden
        return id;
    }
    /*public void setImageId(int pos, int imageId) {
        this.imageID.set(pos,imageId);
    }*/

    private void fill_dummy_data()    {
        add(false,"Fahrradcodierung","online","Tel: 0676-123212222",R.drawable.bild_bikefinder);
        add(false,"English Coaching","British without accent","Tel: 0676-223212222",0);
        add(false,"Computerreparatur","auch Abends und am Wochenende!","Tel: 0676-323212222",R.drawable.bild_computerreparatur);
        add(false,"PC-Service","auch am Wochenende + Feiertag","Tel: 0676-423212222",R.drawable.bild_pc);
        add(false,"PC-Service","und Laptop Reparatur","Tel: 0676-523212222",0);
        add(false,"Kürkleider","für Eiskunstlauf, Rollschuhlauf, Eistanz Marken JM.in verschiedenen Design Farben, Größen","Tel: 0676-623212222",R.drawable.bild_kuerkleider);
        add(false,"Designe Top","Wunderschönen für Mädchen und Damen für Eiskunstlauf und verschiedenen Sportarten","Tel: 0676-723212222",R.drawable.bild_wunderschoene_jacke);
        add(false,"Kombi Set Top und Rock","Wunderschöne champagnerfarbige","Tel: 0676-823212222",R.drawable.bild_elegante);
        add(false,"Damen-Rauhleder-Jacke","Moosgrüne","Tel: 0676-923212222",R.drawable.bild_moosgruene);
        add(true,"Jacke","Jean - blau","Tel: 0676-1023212222",R.drawable.bild_jean);
        add(true,"Jacke","Jean - schwarz","Tel: 0676-1123212222",R.drawable.bild_jean_schwarz);

    }
}
