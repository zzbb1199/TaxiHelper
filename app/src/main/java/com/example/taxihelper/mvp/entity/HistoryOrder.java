package com.example.taxihelper.mvp.entity;

import java.util.List;

/**
 * Created by Raven on 2017/9/6.
 */

public class HistoryOrder {

    /**
     * limit : 2
     * list : [{"endCity":{"displayName":"天津站","lat":39.135483,"lng":117.209206},"id":"6254394997847949342","number":"27277261140625","orderStatus":"canceled","serviceId":"14","passengerName":"","passengerMobile":"13245678976","paymentStatus":"","price":"45","startCity":{"displayName":"创新创业中心","lat":39.15992,"lng":117.39242},"createTime":1456214813,"departureTime":1467095760,"finishedTime":1467091884}]
     * offset : 0
     */

    private int limit;
    private int offset;
    private List<ListBean> list;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * endCity : {"displayName":"天津站","lat":39.135483,"lng":117.209206}
         * id : 6254394997847949342
         * number : 27277261140625
         * orderStatus : canceled
         * serviceId : 14
         * passengerName : 
         * passengerMobile : 13245678976
         * paymentStatus : 
         * price : 45
         * startCity : {"displayName":"创新创业中心","lat":39.15992,"lng":117.39242}
         * createTime : 1456214813
         * departureTime : 1467095760
         * finishedTime : 1467091884
         */

        private EndCityBean endCity;
        private String id;
        private String number;
        private String orderStatus;
        private String serviceId;
        private String passengerName;
        private String passengerMobile;
        private String paymentStatus;
        private String price;
        private StartCityBean startCity;
        private int createTime;
        private int departureTime;
        private int finishedTime;

        public EndCityBean getEndCity() {
            return endCity;
        }

        public void setEndCity(EndCityBean endCity) {
            this.endCity = endCity;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getPassengerName() {
            return passengerName;
        }

        public void setPassengerName(String passengerName) {
            this.passengerName = passengerName;
        }

        public String getPassengerMobile() {
            return passengerMobile;
        }

        public void setPassengerMobile(String passengerMobile) {
            this.passengerMobile = passengerMobile;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public StartCityBean getStartCity() {
            return startCity;
        }

        public void setStartCity(StartCityBean startCity) {
            this.startCity = startCity;
        }

        public int getCreateTime() {
            return createTime;
        }

        public void setCreateTime(int createTime) {
            this.createTime = createTime;
        }

        public int getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(int departureTime) {
            this.departureTime = departureTime;
        }

        public int getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(int finishedTime) {
            this.finishedTime = finishedTime;
        }

        public static class EndCityBean {
            /**
             * displayName : 天津站
             * lat : 39.135483
             * lng : 117.209206
             */

            private String displayName;
            private double lat;
            private double lng;

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
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

        public static class StartCityBean {
            /**
             * displayName : 创新创业中心
             * lat : 39.15992
             * lng : 117.39242
             */

            private String displayName;
            private double lat;
            private double lng;

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
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
    }
}
