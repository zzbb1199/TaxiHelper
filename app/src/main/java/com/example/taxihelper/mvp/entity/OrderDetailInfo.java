package com.example.taxihelper.mvp.entity;

import java.util.List;

/**
 * Created by 张兴锐 on 2017/8/26.
 */

public class OrderDetailInfo {


    /**
     * driver : {"driverLat":0,"driverLng":0,"driverName":"李师傅","virtualPhone4Purchaser":"17081234041","virtualPhone4Passenger":"17081234041","driverScore":"5.0","vehicleModel":"别克GL8","vehicleNo":"津**1456"}
     * order : {"autoPayTime":1470711936,"carGroupId":3,"cityId":2,"createTime":1470711184,"customData":{"dOrderId":"35456983384","dCouponId":"5462395218","dCouponAmount":20},"departureTime":1470711245,"dispatchedTime":1470711200,"elat":39.135483,"elng":117.209202,"endAddress":"新纬路1号","endName":"天津站","employeeNumber":"070804532","finishedTime":1470711276,"id":"6316656437168963587","isDisagree":false,"isFixedPrice":false,"isDispatchedImmediately":true,"number":"27315150415317","orderRemark":"","orderScore":0,"passengerName":"","passengerMobile":"13167378647","paymentStatus":"unpaid","realElat":39.159349,"realElng":117.392584,"realEndAddress":"天津市东丽区保税区空港国际物流经济区漾美家居天津生活美学馆空港商务园东区","realEndName":"天津市东丽区环河北路84号","realSlat":39.159349,"realSlng":117.392584,"realStartAddress":"天津市东丽区保税区空港国际物流经济区漾美家居天津生活美学馆空港商务园东区","realStartName":"天津市东丽区环河北路84号","serviceId":14,"slat":39.159014,"slng":117.393367,"startAddress":"保税区空港国际物流经济区商务园东区e13号","startName":"奥凯航空有限公司(东二道)","status":"feeSubmitted"}
     * price : {"arrearsPrice":"0","couponDeductibleAmount":0,"couponId":"","detail":[{"amount":"52","name":"起租价"},{"amount":"118.92","name":"里程费(19.82公里)"},{"amount":"12","name":"时长费(1分钟)"},{"amount":"158.76","name":"远途费"},{"amount":"-0.68","name":"抹零"}],"distance":19820,"duration":1,"equivalentPrice":283,"totalPrice":"341"}
     */

    private DriverBean driver;
    private OrderBean order;
    private PriceBean price;

    public DriverBean getDriver() {
        return driver;
    }

    public void setDriver(DriverBean driver) {
        this.driver = driver;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public PriceBean getPrice() {
        return price;
    }

    public void setPrice(PriceBean price) {
        this.price = price;
    }

    public static class DriverBean {
        /**
         * driverLat : 0
         * driverLng : 0
         * driverName : 李师傅
         * virtualPhone4Purchaser : 17081234041
         * virtualPhone4Passenger : 17081234041
         * driverScore : 5.0
         * vehicleModel : 别克GL8
         * vehicleNo : 津**1456
         */

        private double driverLat;
        private double driverLng;
        private String driverName;
        private String virtualPhone4Purchaser;
        private String virtualPhone4Passenger;
        private String driverScore;
        private String vehicleModel;
        private String vehicleNo;

        public double getDriverLat() {
            return driverLat;
        }

        public void setDriverLat(float driverLat) {
            this.driverLat = driverLat;
        }

        public double getDriverLng() {
            return driverLng;
        }

        public void setDriverLng(float driverLng) {
            this.driverLng = driverLng;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getVirtualPhone4Purchaser() {
            return virtualPhone4Purchaser;
        }

        public void setVirtualPhone4Purchaser(String virtualPhone4Purchaser) {
            this.virtualPhone4Purchaser = virtualPhone4Purchaser;
        }

        public String getVirtualPhone4Passenger() {
            return virtualPhone4Passenger;
        }

        public void setVirtualPhone4Passenger(String virtualPhone4Passenger) {
            this.virtualPhone4Passenger = virtualPhone4Passenger;
        }

        public String getDriverScore() {
            return driverScore;
        }

        public void setDriverScore(String driverScore) {
            this.driverScore = driverScore;
        }

        public String getVehicleModel() {
            return vehicleModel;
        }

        public void setVehicleModel(String vehicleModel) {
            this.vehicleModel = vehicleModel;
        }

        public String getVehicleNo() {
            return vehicleNo;
        }

        public void setVehicleNo(String vehicleNo) {
            this.vehicleNo = vehicleNo;
        }
    }

    public static class OrderBean {
        private boolean canDisagree;
        private long companyId;
        private int dispatchingCountdown;
        private int rechargingCountdown;
        private int autoPayTime;
        private int carGroupId;
        private int cityId;
        private long createTime;
        private String customData;
        private long departureTime;
        private long dispatchedTime;
        private double elat;
        private double elng;
        private String endAddress;
        private String endName;
        private String employeeNumber;
        private long finishedTime;
        private String id;
        private boolean isDisagree;
        private boolean isFixedPrice;
        private boolean isDispatchedImmediately;
        private String number;
        private String orderRemark;
        private int orderScore;
        private String passengerName;
        private String passengerMobile;
        private String paymentStatus;
        private double realElat;
        private double realElng;
        private String realEndAddress;
        private String realEndName;
        private double realSlat;
        private double realSlng;
        private String realStartAddress;
        private String realStartName;
        private int serviceId;
        private double slat;
        private double slng;
        private String startAddress;
        private String startName;
        private String status;

        public int getAutoPayTime() {
            return autoPayTime;
        }

        public void setAutoPayTime(int autoPayTime) {
            this.autoPayTime = autoPayTime;
        }

        public int getCarGroupId() {
            return carGroupId;
        }

        public void setCarGroupId(int carGroupId) {
            this.carGroupId = carGroupId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCustomData() {
            return customData;
        }

        public void setCustomData(String customData) {
            this.customData = customData;
        }

        public long getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(int departureTime) {
            this.departureTime = departureTime;
        }

        public long getDispatchedTime() {
            return dispatchedTime;
        }

        public void setDispatchedTime(int dispatchedTime) {
            this.dispatchedTime = dispatchedTime;
        }

        public double getElat() {
            return elat;
        }

        public void setElat(double elat) {
            this.elat = elat;
        }

        public double getElng() {
            return elng;
        }

        public void setElng(double elng) {
            this.elng = elng;
        }

        public String getEndAddress() {
            return endAddress;
        }

        public void setEndAddress(String endAddress) {
            this.endAddress = endAddress;
        }

        public String getEndName() {
            return endName;
        }

        public void setEndName(String endName) {
            this.endName = endName;
        }

        public String getEmployeeNumber() {
            return employeeNumber;
        }

        public void setEmployeeNumber(String employeeNumber) {
            this.employeeNumber = employeeNumber;
        }

        public long getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(int finishedTime) {
            this.finishedTime = finishedTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsDisagree() {
            return isDisagree;
        }

        public void setIsDisagree(boolean isDisagree) {
            this.isDisagree = isDisagree;
        }

        public boolean isIsFixedPrice() {
            return isFixedPrice;
        }

        public void setIsFixedPrice(boolean isFixedPrice) {
            this.isFixedPrice = isFixedPrice;
        }

        public boolean isIsDispatchedImmediately() {
            return isDispatchedImmediately;
        }

        public void setIsDispatchedImmediately(boolean isDispatchedImmediately) {
            this.isDispatchedImmediately = isDispatchedImmediately;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getOrderRemark() {
            return orderRemark;
        }

        public void setOrderRemark(String orderRemark) {
            this.orderRemark = orderRemark;
        }

        public int getOrderScore() {
            return orderScore;
        }

        public void setOrderScore(int orderScore) {
            this.orderScore = orderScore;
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

        public double getRealElat() {
            return realElat;
        }

        public void setRealElat(double realElat) {
            this.realElat = realElat;
        }

        public double getRealElng() {
            return realElng;
        }

        public void setRealElng(double realElng) {
            this.realElng = realElng;
        }

        public String getRealEndAddress() {
            return realEndAddress;
        }

        public void setRealEndAddress(String realEndAddress) {
            this.realEndAddress = realEndAddress;
        }

        public String getRealEndName() {
            return realEndName;
        }

        public void setRealEndName(String realEndName) {
            this.realEndName = realEndName;
        }

        public double getRealSlat() {
            return realSlat;
        }

        public void setRealSlat(double realSlat) {
            this.realSlat = realSlat;
        }

        public double getRealSlng() {
            return realSlng;
        }

        public void setRealSlng(double realSlng) {
            this.realSlng = realSlng;
        }

        public String getRealStartAddress() {
            return realStartAddress;
        }

        public void setRealStartAddress(String realStartAddress) {
            this.realStartAddress = realStartAddress;
        }

        public String getRealStartName() {
            return realStartName;
        }

        public void setRealStartName(String realStartName) {
            this.realStartName = realStartName;
        }

        public int getServiceId() {
            return serviceId;
        }

        public void setServiceId(int serviceId) {
            this.serviceId = serviceId;
        }

        public double getSlat() {
            return slat;
        }

        public void setSlat(double slat) {
            this.slat = slat;
        }

        public double getSlng() {
            return slng;
        }

        public void setSlng(double slng) {
            this.slng = slng;
        }

        public String getStartAddress() {
            return startAddress;
        }

        public void setStartAddress(String startAddress) {
            this.startAddress = startAddress;
        }

        public String getStartName() {
            return startName;
        }

        public void setStartName(String startName) {
            this.startName = startName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class CustomDataBean {
            /**
             * dOrderId : 35456983384
             * dCouponId : 5462395218
             * dCouponAmount : 20
             */

            private String dOrderId;
            private String dCouponId;
            private int dCouponAmount;

            public String getDOrderId() {
                return dOrderId;
            }

            public void setDOrderId(String dOrderId) {
                this.dOrderId = dOrderId;
            }

            public String getDCouponId() {
                return dCouponId;
            }

            public void setDCouponId(String dCouponId) {
                this.dCouponId = dCouponId;
            }

            public int getDCouponAmount() {
                return dCouponAmount;
            }

            public void setDCouponAmount(int dCouponAmount) {
                this.dCouponAmount = dCouponAmount;
            }
        }
    }

    public static class PriceBean {

        private String arrearsPrice;
        private int couponDeductibleAmount;
        private String couponId;
        private float distance;
        private float duration;
        private int equivalentPrice;
        private String totalPrice;
        private List<DetailBean> detail;

        public String getArrearsPrice() {
            return arrearsPrice;
        }

        public void setArrearsPrice(String arrearsPrice) {
            this.arrearsPrice = arrearsPrice;
        }

        public int getCouponDeductibleAmount() {
            return couponDeductibleAmount;
        }

        public void setCouponDeductibleAmount(int couponDeductibleAmount) {
            this.couponDeductibleAmount = couponDeductibleAmount;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getEquivalentPrice() {
            return equivalentPrice;
        }

        public void setEquivalentPrice(int equivalentPrice) {
            this.equivalentPrice = equivalentPrice;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * amount : 52
             * name : 起租价
             */

            private String amount;
            private String name;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
            
        }
    }
}
