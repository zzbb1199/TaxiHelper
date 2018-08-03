package com.example.taxihelper.mvp.entity;

/**
 * Created by Raven on 2017/8/22.
 */

public class CreateOrder {

    /**
     * createStatus : createSuccess
     * orderId : 6438718716671623171
     * orderNumber : 30656340644037
     * isDispatchedImmediately : true
     * orderType : 1
     */

    private String createStatus;
    private String orderId;
    private String orderNumber;
    private boolean isDispatchedImmediately;
    private int orderType;

    public String getCreateStatus() {
        return createStatus;
    }

    public void setCreateStatus(String createStatus) {
        this.createStatus = createStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean isIsDispatchedImmediately() {
        return isDispatchedImmediately;
    }

    public void setIsDispatchedImmediately(boolean isDispatchedImmediately) {
        this.isDispatchedImmediately = isDispatchedImmediately;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }
}
