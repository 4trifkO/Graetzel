package hci.univie.ac.at.graetzel;

import java.util.ArrayList;

public class ReservationTime {

    private String time;
    private int user_id = 1;
    private boolean isFree = true;


    public ReservationTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void makeReservation(int user_id){   //reserve a room (set it !free and reserved by user_id)
        this.isFree = false;
        this.user_id = user_id;
    }

    public void cancelReservation(){  //cancel a reservation (set it free and the user_id to default)
        this.isFree = true;
        this.user_id = 1;
    }

    public int getUser_id() {
        return user_id;
    }

    public boolean roomIsFree(){
        return this.isFree;
    }

}
