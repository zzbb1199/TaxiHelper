package com.example.taxihelper.mvp.entity;

/**
 * Created by 张兴锐 on 2017/8/7.
 */

public class GainAccessToken {

    /**
     * access_token : 0f541eec-67f9-4831-8af6-8e5330290bc5
     * token_type : bearer
     * refresh_token : 3e86c0e7-ff8f-4abc-a4f8-0624365f3aa9
     * expires_in : 599
     * scope : read
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
