package com.example.taxihelper.mvp.entity;

/**
 * Created by Raven on 2017/8/13.
 */

public class CitiesInfo {
    private int cityId;
    private String cityName;
    private String citySpell;
    private boolean isHot;
    private double lat;
    private double lng;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCitySpell() {
        return citySpell;
    }

    public void setCitySpell(String citySpell) {
        this.citySpell = citySpell;
    }

    public boolean isIsHot() {
        return isHot;
    }

    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
