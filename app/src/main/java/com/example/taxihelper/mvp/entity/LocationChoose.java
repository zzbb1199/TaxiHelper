package com.example.taxihelper.mvp.entity;

/**
 * Created by 张兴锐 on 2017/8/11.
 */

public class LocationChoose {
    private String locatoin;
    public String type;
    private String formatLocation;

    public LocationChoose(String locatoin, String type, String formatLocation) {
        this.locatoin = locatoin;
        this.type = type;
        this.formatLocation = formatLocation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormatLocation() {
        return formatLocation;
    }

    public void setFormatLocation(String formatLocation) {
        this.formatLocation = formatLocation;
    }
    

    public LocationChoose(String locatoin) {
        this.locatoin = locatoin;
    }

    public String getLocatoin() {
        return locatoin;
    }

    public void setLocatoin(String locatoin) {
        this.locatoin = locatoin;
    }
}
