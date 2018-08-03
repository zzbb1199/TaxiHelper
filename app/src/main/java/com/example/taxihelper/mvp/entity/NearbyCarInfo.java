package com.example.taxihelper.mvp.entity;

import java.util.List;

/**
 * Created by Raven on 2017/8/22.
 */

public class NearbyCarInfo {

    /**
     * carList : [{"lat":"39.159693","lng":"117.393305"},{"lat":"39.159971","lng":"117.392022"}]
     * number : 2
     * shortestTimeOfArrival : 4
     */

    private int number;
    private int shortestTimeOfArrival;
    private List<CarListBean> carList;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getShortestTimeOfArrival() {
        return shortestTimeOfArrival;
    }

    public void setShortestTimeOfArrival(int shortestTimeOfArrival) {
        this.shortestTimeOfArrival = shortestTimeOfArrival;
    }

    public List<CarListBean> getCarList() {
        return carList;
    }

    public void setCarList(List<CarListBean> carList) {
        this.carList = carList;
    }

    public static class CarListBean {
        /**
         * lat : 39.159693
         * lng : 117.393305
         */

        private String lat;
        private String lng;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }
    }
}
