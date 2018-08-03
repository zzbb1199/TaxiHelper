package com.example.taxihelper.mvp.entity;

import java.util.List;

/**
 * Created by Raven on 2017/8/8.
 */

public class Test {


    /**
     * busiCode : BASE000
     * code : 1
     * content : []
     * handler : CLIENT
     * msg : 成功
     * status : SUCCESS
     * uid : 51fb89fb-e716-45ce-a4af-17cb6f377864
     * version : 001
     */

    private String busiCode;
    private int code;
    private String handler;
    private String msg;
    private String status;
    private String uid;
    private String version;
    private List<?> content;

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}
