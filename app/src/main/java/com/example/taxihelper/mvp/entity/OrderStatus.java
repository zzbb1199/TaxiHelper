package com.example.taxihelper.mvp.entity;

/**
 * Created by Raven on 2017/9/6.
 */

public class OrderStatus {

    /**
     * code : 1
     * message : 订单状态修改成功
     */

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
