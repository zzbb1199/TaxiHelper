package com.example.taxihelper.mvp.entity;

/**
 * Created by Raven on 2017/9/6.
 */

public class CancelOrderReason {

  
    private int id;
    private String value;
    private boolean arrival;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isArrival() {
        return arrival;
    }

    public void setArrival(boolean arrival) {
        this.arrival = arrival;
    }
}
