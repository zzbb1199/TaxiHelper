package com.example.taxihelper.mvp.entity;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class CancelOrder {

    /**
     * needPay : true
     * cost : 12.3
     * isCanceled : false
     */

    private boolean needPay;
    private String cost;
    private boolean isCanceled;

    public boolean isNeedPay() {
        return needPay;
    }

    public void setNeedPay(boolean needPay) {
        this.needPay = needPay;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }
}
