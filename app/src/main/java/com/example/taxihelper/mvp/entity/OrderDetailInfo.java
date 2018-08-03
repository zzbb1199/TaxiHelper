package com.example.taxihelper.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Raven on 2017/8/26.
 */

public class OrderDetailInfo implements Parcelable {


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

    public static class DriverBean implements Parcelable {
        
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.driverLat);
            dest.writeDouble(this.driverLng);
            dest.writeString(this.driverName);
            dest.writeString(this.virtualPhone4Purchaser);
            dest.writeString(this.virtualPhone4Passenger);
            dest.writeString(this.driverScore);
            dest.writeString(this.vehicleModel);
            dest.writeString(this.vehicleNo);
        }

        public DriverBean() {
        }

        protected DriverBean(Parcel in) {
            this.driverLat = in.readDouble();
            this.driverLng = in.readDouble();
            this.driverName = in.readString();
            this.virtualPhone4Purchaser = in.readString();
            this.virtualPhone4Passenger = in.readString();
            this.driverScore = in.readString();
            this.vehicleModel = in.readString();
            this.vehicleNo = in.readString();
        }

        public static final Creator<DriverBean> CREATOR = new Creator<DriverBean>() {
            @Override
            public DriverBean createFromParcel(Parcel source) {
                return new DriverBean(source);
            }

            @Override
            public DriverBean[] newArray(int size) {
                return new DriverBean[size];
            }
        };
    }

    public static class OrderBean implements Parcelable {
        
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

        public static class CustomDataBean implements Parcelable {
            
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.dOrderId);
                dest.writeString(this.dCouponId);
                dest.writeInt(this.dCouponAmount);
            }

            public CustomDataBean() {
            }

            protected CustomDataBean(Parcel in) {
                this.dOrderId = in.readString();
                this.dCouponId = in.readString();
                this.dCouponAmount = in.readInt();
            }

            public static final Creator<CustomDataBean> CREATOR = new Creator<CustomDataBean>() {
                @Override
                public CustomDataBean createFromParcel(Parcel source) {
                    return new CustomDataBean(source);
                }

                @Override
                public CustomDataBean[] newArray(int size) {
                    return new CustomDataBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.canDisagree ? (byte) 1 : (byte) 0);
            dest.writeLong(this.companyId);
            dest.writeInt(this.dispatchingCountdown);
            dest.writeInt(this.rechargingCountdown);
            dest.writeInt(this.autoPayTime);
            dest.writeInt(this.carGroupId);
            dest.writeInt(this.cityId);
            dest.writeLong(this.createTime);
            dest.writeString(this.customData);
            dest.writeLong(this.departureTime);
            dest.writeLong(this.dispatchedTime);
            dest.writeDouble(this.elat);
            dest.writeDouble(this.elng);
            dest.writeString(this.endAddress);
            dest.writeString(this.endName);
            dest.writeString(this.employeeNumber);
            dest.writeLong(this.finishedTime);
            dest.writeString(this.id);
            dest.writeByte(this.isDisagree ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isFixedPrice ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isDispatchedImmediately ? (byte) 1 : (byte) 0);
            dest.writeString(this.number);
            dest.writeString(this.orderRemark);
            dest.writeInt(this.orderScore);
            dest.writeString(this.passengerName);
            dest.writeString(this.passengerMobile);
            dest.writeString(this.paymentStatus);
            dest.writeDouble(this.realElat);
            dest.writeDouble(this.realElng);
            dest.writeString(this.realEndAddress);
            dest.writeString(this.realEndName);
            dest.writeDouble(this.realSlat);
            dest.writeDouble(this.realSlng);
            dest.writeString(this.realStartAddress);
            dest.writeString(this.realStartName);
            dest.writeInt(this.serviceId);
            dest.writeDouble(this.slat);
            dest.writeDouble(this.slng);
            dest.writeString(this.startAddress);
            dest.writeString(this.startName);
            dest.writeString(this.status);
        }

        public OrderBean() {
        }

        protected OrderBean(Parcel in) {
            this.canDisagree = in.readByte() != 0;
            this.companyId = in.readLong();
            this.dispatchingCountdown = in.readInt();
            this.rechargingCountdown = in.readInt();
            this.autoPayTime = in.readInt();
            this.carGroupId = in.readInt();
            this.cityId = in.readInt();
            this.createTime = in.readLong();
            this.customData = in.readString();
            this.departureTime = in.readLong();
            this.dispatchedTime = in.readLong();
            this.elat = in.readDouble();
            this.elng = in.readDouble();
            this.endAddress = in.readString();
            this.endName = in.readString();
            this.employeeNumber = in.readString();
            this.finishedTime = in.readLong();
            this.id = in.readString();
            this.isDisagree = in.readByte() != 0;
            this.isFixedPrice = in.readByte() != 0;
            this.isDispatchedImmediately = in.readByte() != 0;
            this.number = in.readString();
            this.orderRemark = in.readString();
            this.orderScore = in.readInt();
            this.passengerName = in.readString();
            this.passengerMobile = in.readString();
            this.paymentStatus = in.readString();
            this.realElat = in.readDouble();
            this.realElng = in.readDouble();
            this.realEndAddress = in.readString();
            this.realEndName = in.readString();
            this.realSlat = in.readDouble();
            this.realSlng = in.readDouble();
            this.realStartAddress = in.readString();
            this.realStartName = in.readString();
            this.serviceId = in.readInt();
            this.slat = in.readDouble();
            this.slng = in.readDouble();
            this.startAddress = in.readString();
            this.startName = in.readString();
            this.status = in.readString();
        }

        public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
            @Override
            public OrderBean createFromParcel(Parcel source) {
                return new OrderBean(source);
            }

            @Override
            public OrderBean[] newArray(int size) {
                return new OrderBean[size];
            }
        };
    }

    public static class PriceBean implements Parcelable {
        

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

        public static class DetailBean implements Parcelable {
            
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.amount);
                dest.writeString(this.name);
            }

            public DetailBean() {
            }

            protected DetailBean(Parcel in) {
                this.amount = in.readString();
                this.name = in.readString();
            }

            public static final Creator<DetailBean> CREATOR = new Creator<DetailBean>() {
                @Override
                public DetailBean createFromParcel(Parcel source) {
                    return new DetailBean(source);
                }

                @Override
                public DetailBean[] newArray(int size) {
                    return new DetailBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.arrearsPrice);
            dest.writeInt(this.couponDeductibleAmount);
            dest.writeString(this.couponId);
            dest.writeFloat(this.distance);
            dest.writeFloat(this.duration);
            dest.writeInt(this.equivalentPrice);
            dest.writeString(this.totalPrice);
            dest.writeTypedList(this.detail);
        }

        public PriceBean() {
        }

        protected PriceBean(Parcel in) {
            this.arrearsPrice = in.readString();
            this.couponDeductibleAmount = in.readInt();
            this.couponId = in.readString();
            this.distance = in.readFloat();
            this.duration = in.readFloat();
            this.equivalentPrice = in.readInt();
            this.totalPrice = in.readString();
            this.detail = in.createTypedArrayList(DetailBean.CREATOR);
        }

        public static final Creator<PriceBean> CREATOR = new Creator<PriceBean>() {
            @Override
            public PriceBean createFromParcel(Parcel source) {
                return new PriceBean(source);
            }

            @Override
            public PriceBean[] newArray(int size) {
                return new PriceBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.driver, flags);
        dest.writeParcelable(this.order, flags);
        dest.writeParcelable(this.price, flags);
    }

   

    protected OrderDetailInfo(Parcel in) {
        this.driver = in.readParcelable(DriverBean.class.getClassLoader());
        this.order = in.readParcelable(OrderBean.class.getClassLoader());
        this.price = in.readParcelable(PriceBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<OrderDetailInfo> CREATOR = new Parcelable.Creator<OrderDetailInfo>() {
        @Override
        public OrderDetailInfo createFromParcel(Parcel source) {
            return new OrderDetailInfo(source);
        }

        @Override
        public OrderDetailInfo[] newArray(int size) {
            return new OrderDetailInfo[size];
        }
    };
}
