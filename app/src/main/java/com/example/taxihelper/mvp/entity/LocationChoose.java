package com.example.taxihelper.mvp.entity;

/**
 * Created by 张兴锐 on 2017/8/11.
 */

public class LocationChoose {
    private String locatoin;
    public String type;
    private String formatLocation;
    private int cityId;

    public LocationChoose(String locatoin, String type, String formatLocation, int cityId) {
        this.locatoin = locatoin;
        this.type = type;
        this.formatLocation = formatLocation;
        this.cityId = cityId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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
