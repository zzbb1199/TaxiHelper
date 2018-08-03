package com.example.taxihelper.mvp.entity;

/**
 * Created by Raven on 2017/9/8.
 */

public class CurrentOrderStatus {
    private String currentOrderStauts;

    public CurrentOrderStatus(String currentOrderStauts) {
        this.currentOrderStauts = currentOrderStauts;
    }

    public void setCurrentOrderStauts(String currentOrderStauts) {
        this.currentOrderStauts = currentOrderStauts;
    }

    public String getCurrentOrderStauts() {
        return currentOrderStauts;
    }
}
