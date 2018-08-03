package com.example.taxihelper.mvp.entity;

/**
 * Created by Raven on 2017/8/11.
 */

public class CityChoose {
    private String city;
    private int cityId;

    public CityChoose(String city, int cityId) {
        this.city = city;
        this.cityId = cityId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
