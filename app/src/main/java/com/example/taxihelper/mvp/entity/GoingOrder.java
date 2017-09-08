package com.example.taxihelper.mvp.entity;

/**
 * Created by 张兴锐 on 2017/9/8.
 */

public class GoingOrder {

    @Override
    public String toString() {
        return "GoingOrder{" +
                "elat=" + elat +
                ", elng=" + elng +
                ", orderId='" + orderId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", slat=" + slat +
                ", slng=" + slng +
                '}';
    }

    /**
     * elat : 39.135483
     * elng : 117.209206
     * orderId : 6254394997847949342
     * orderNo : 27277261140626
     * orderStatus : dispatched
     * slat : 39.15992
     * slng : 117.39242
     */

    private double elat;
    private double elng;
    private String orderId;
    private String orderNo;
    private String orderStatus;
    private double slat;
    private double slng;

    public double getElat() {
        return elat;
    }

    public void setElat(double elat) {
        this.elat = elat;
    }

    public double getElng() {
        return elng;
    }

    public void setElng(double elng) {
        this.elng = elng;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getSlat() {
        return slat;
    }

    public void setSlat(double slat) {
        this.slat = slat;
    }

    public double getSlng() {
        return slng;
    }

    public void setSlng(double slng) {
        this.slng = slng;
    }
}
