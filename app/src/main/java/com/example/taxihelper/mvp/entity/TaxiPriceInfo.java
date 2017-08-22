package com.example.taxihelper.mvp.entity;

import java.util.List;

/**
 * Created by 张兴锐 on 2017/8/22.
 */

public class TaxiPriceInfo {
    private int distance;
    private int duration;
    private String estimateid;
    private List<Prices> prices;

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setEstimateid(String estimateid) {
        this.estimateid = estimateid;
    }

    public String getEstimateid() {
        return estimateid;
    }

    public void setPrices(List<Prices> prices) {
        this.prices = prices;
    }

    public List<Prices> getPrices() {
        return prices;
    }

    public static class Prices {


        private int cargroupid;

        private int companydiscountamount;

        private int floatfactor;

        private double kilometreprice;

        private int longdistanceprice;

        private int longdistancepricelimit;
        private String name;

        private int outcityprice;

        private double perkilometreprice;

        private int perlongdistanceprice;

        private double pertimeprice;
        private int price;

        private int pricetype;

        private int productkilometre;

        private int producttime;

        private int startprice;
     
        private double timeprice;

        public void setCargroupid(int cargroupid) {
            this.cargroupid = cargroupid;
        }

        public int getCargroupid() {
            return cargroupid;
        }

        public void setCompanydiscountamount(int companydiscountamount) {
            this.companydiscountamount = companydiscountamount;
        }

        public int getCompanydiscountamount() {
            return companydiscountamount;
        }

        public void setFloatfactor(int floatfactor) {
            this.floatfactor = floatfactor;
        }

        public int getFloatfactor() {
            return floatfactor;
        }

        public void setKilometreprice(double kilometreprice) {
            this.kilometreprice = kilometreprice;
        }

        public double getKilometreprice() {
            return kilometreprice;
        }

        public void setLongdistanceprice(int longdistanceprice) {
            this.longdistanceprice = longdistanceprice;
        }

        public int getLongdistanceprice() {
            return longdistanceprice;
        }

        public void setLongdistancepricelimit(int longdistancepricelimit) {
            this.longdistancepricelimit = longdistancepricelimit;
        }

        public int getLongdistancepricelimit() {
            return longdistancepricelimit;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setOutcityprice(int outcityprice) {
            this.outcityprice = outcityprice;
        }

        public int getOutcityprice() {
            return outcityprice;
        }

        public void setPerkilometreprice(double perkilometreprice) {
            this.perkilometreprice = perkilometreprice;
        }

        public double getPerkilometreprice() {
            return perkilometreprice;
        }

        public void setPerlongdistanceprice(int perlongdistanceprice) {
            this.perlongdistanceprice = perlongdistanceprice;
        }

        public int getPerlongdistanceprice() {
            return perlongdistanceprice;
        }

        public void setPertimeprice(double pertimeprice) {
            this.pertimeprice = pertimeprice;
        }

        public double getPertimeprice() {
            return pertimeprice;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }

        public void setPricetype(int pricetype) {
            this.pricetype = pricetype;
        }

        public int getPricetype() {
            return pricetype;
        }

        public void setProductkilometre(int productkilometre) {
            this.productkilometre = productkilometre;
        }

        public int getProductkilometre() {
            return productkilometre;
        }

        public void setProducttime(int producttime) {
            this.producttime = producttime;
        }

        public int getProducttime() {
            return producttime;
        }

        public void setStartprice(int startprice) {
            this.startprice = startprice;
        }

        public int getStartprice() {
            return startprice;
        }

        public void setTimeprice(double timeprice) {
            this.timeprice = timeprice;
        }

        public double getTimeprice() {
            return timeprice;
        }

    }
}
