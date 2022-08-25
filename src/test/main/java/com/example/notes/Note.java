package com.example.notes;
import io.realm.RealmObject;

public class Note extends RealmObject {
    // class instance variables
    String title;
    String detail;
    long time;

    public String getTitle() {
        return title;
    } //returns title

    public void setTitle(String title) {
        this.title = title;
    } // sets title

    public String getDetail() {
        return detail;
    } // returns detail

    public void setDetail(String detail) {
        this.detail = detail;
    }  //sets detail

    public void setTime(long time) {
        this.time = time;
    }  //sets time

    public long getTime() {
        return time;
    } //returns time


}