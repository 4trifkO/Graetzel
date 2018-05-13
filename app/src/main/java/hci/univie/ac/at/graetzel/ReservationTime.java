package hci.univie.ac.at.graetzel;

import java.util.ArrayList;

public class ReservationTime {

    private String time;
    private int user_id = 1;
    private boolean isFree = true;

    /*-----Constructor-----*/
    public ReservationTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void makeReservation(int user_id){
        this.isFree = false;
        this.user_id = user_id;
    }

    public void cancelReservation(){
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
