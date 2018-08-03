package com.example.taxihelper.mvp.entity;

import java.util.List;

/**
 * Created by Raven on 2017/8/22.
 */

public class TaxiPriceInfo {

    /**
     * distance : 22983
     * duration : 45
     * estimateId : 09135ac099faf7bbff46adf755b30a48
     * prices : [{"carGroupId":2,"companyDiscountAmount":0,"floatFactor":2,"kilometrePrice":114.9,"longDistancePrice":83.92,"longDistancePriceLimit":2,"name":"公务轿车","outCityPrice":0,"perKilometrePrice":5,"perLongDistancePrice":4,"perTimePrice":3,"price":353,"priceType":1,"productKilometre":0,"productTime":0,"startPrice":20,"timePrice":135},{"carGroupId":1,"companyDiscountAmount":0,"floatFactor":2,"kilometrePrice":45.96,"longDistancePrice":45.96,"longDistancePriceLimit":0,"name":"优驾舒享","outCityPrice":0,"perKilometrePrice":2,"perLongDistancePrice":2,"perTimePrice":4.2,"price":300,"priceType":1,"productKilometre":0,"productTime":0,"startPrice":20,"timePrice":189},{"carGroupId":3,"companyDiscountAmount":0,"floatFactor":2,"kilometrePrice":128.69,"longDistancePrice":75.02,"longDistancePriceLimit":10,"name":"商务7座","outCityPrice":0,"perKilometrePrice":5.6,"perLongDistancePrice":5.78,"perTimePrice":3,"price":340,"priceType":1,"productKilometre":0,"productTime":0,"startPrice":2,"timePrice":135},{"carGroupId":4,"companyDiscountAmount":0,"floatFactor":2,"kilometrePrice":22.98,"longDistancePrice":83.92,"longDistancePriceLimit":2,"name":"豪华轿车","outCityPrice":0,"perKilometrePrice":1,"perLongDistancePrice":4,"perTimePrice":2,"price":216,"priceType":1,"productKilometre":0,"productTime":0,"startPrice":20,"timePrice":90}]
     */

    private float distance;
    private float duration;
    private String estimateId;
    private List<PricesBean> prices;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(String estimateId) {
        this.estimateId = estimateId;
    }

    public List<PricesBean> getPrices() {
        return prices;
    }

    public void setPrices(List<PricesBean> prices) {
        this.prices = prices;
    }

    public static class PricesBean {
        /**
         * carGroupId : 2
         * companyDiscountAmount : 0
         * floatFactor : 2
         * kilometrePrice : 114.9
         * longDistancePrice : 83.92
         * longDistancePriceLimit : 2
         * name : 公务轿车
         * outCityPrice : 0
         * perKilometrePrice : 5
         * perLongDistancePrice : 4
         * perTimePrice : 3
         * price : 353
         * priceType : 1
         * productKilometre : 0
         * productTime : 0
         * startPrice : 20
         * timePrice : 135
         */

        private int carGroupId;
        private float companyDiscountAmount;
        private float floatFactor;
        private float kilometrePrice;
        private float longDistancePrice;
        private float longDistancePriceLimit;
        private String name;
        private float outCityPrice;
        private float perKilometrePrice;
        private float perLongDistancePrice;
        private float perTimePrice;
        private float price;
        private int priceType;
        private float productKilometre;
        private float productTime;
        private float startPrice;
        private float timePrice;

        public int getCarGroupId() {
            return carGroupId;
        }

        public void setCarGroupId(int carGroupId) {
            this.carGroupId = carGroupId;
        }

        public float getCompanyDiscountAmount() {
            return companyDiscountAmount;
        }

        public void setCompanyDiscountAmount(float companyDiscountAmount) {
            this.companyDiscountAmount = companyDiscountAmount;
        }

        public float getFloatFactor() {
            return floatFactor;
        }

        public void setFloatFactor(float floatFactor) {
            this.floatFactor = floatFactor;
        }

        public float getKilometrePrice() {
            return kilometrePrice;
        }

        public void setKilometrePrice(float kilometrePrice) {
            this.kilometrePrice = kilometrePrice;
        }

        public float getLongDistancePrice() {
            return longDistancePrice;
        }

        public void setLongDistancePrice(float longDistancePrice) {
            this.longDistancePrice = longDistancePrice;
        }

        public float getLongDistancePriceLimit() {
            return longDistancePriceLimit;
        }

        public void setLongDistancePriceLimit(float longDistancePriceLimit) {
            this.longDistancePriceLimit = longDistancePriceLimit;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getOutCityPrice() {
            return outCityPrice;
        }

        public void setOutCityPrice(float outCityPrice) {
            this.outCityPrice = outCityPrice;
        }

        public float getPerKilometrePrice() {
            return perKilometrePrice;
        }

        public void setPerKilometrePrice(float perKilometrePrice) {
            this.perKilometrePrice = perKilometrePrice;
        }

        public float getPerLongDistancePrice() {
            return perLongDistancePrice;
        }

        public void setPerLongDistancePrice(float perLongDistancePrice) {
            this.perLongDistancePrice = perLongDistancePrice;
        }

        public float getPerTimePrice() {
            return perTimePrice;
        }

        public void setPerTimePrice(float perTimePrice) {
            this.perTimePrice = perTimePrice;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public float getPriceType() {
            return priceType;
        }

        public void setPriceType(int priceType) {
            this.priceType = priceType;
        }

        public float getProductKilometre() {
            return productKilometre;
        }

        public void setProductKilometre(float productKilometre) {
            this.productKilometre = productKilometre;
        }

        public float getProductTime() {
            return productTime;
        }

        public void setProductTime(float productTime) {
            this.productTime = productTime;
        }

        public float getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(float startPrice) {
            this.startPrice = startPrice;
        }

        public float getTimePrice() {
            return timePrice;
        }

        public void setTimePrice(float timePrice) {
            this.timePrice = timePrice;
        }
    }

    @Override
    public String toString() {
        return "ContentBean{" +
                "distance=" + distance +
                ", duration=" + duration +
                ", estimateId='" + estimateId + '\'' +
                ", prices=" + prices +
                '}';
    }   
}
