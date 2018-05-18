package com.example.taxihelper.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class TaxiPriceData implements Parcelable,Comparable<TaxiPriceData>{
    
    /**
     * 公司
     */
    public String company;
    /**
     * 车辆类型
     */
    public String type;

    /**
     * 价钱
     */
    public double price;

    /**
     * groupId
     */
    public int groupId;
    public TaxiPriceData(String company, String type, double price,int groupId) {
        this.company = company;
        this.type = type;
        this.price = price;
        this.groupId = groupId;
    }

    @Override
    public int compareTo(@NonNull TaxiPriceData taxiPriceData) {
        if(this.price - taxiPriceData.price > 0){
            return 1;
        }
        return -1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.company);
        dest.writeString(this.type);
        dest.writeDouble(this.price);
        dest.writeDouble(this.groupId);
    }

    protected TaxiPriceData(Parcel in) {
        this.company = in.readString();
        this.type = in.readString();
        this.price = in.readDouble();
        this.groupId = in.readInt();
    }

    public static final Creator<TaxiPriceData> CREATOR = new Creator<TaxiPriceData>() {
        @Override
        public TaxiPriceData createFromParcel(Parcel source) {
            return new TaxiPriceData(source);
        }

        @Override
        public TaxiPriceData[] newArray(int size) {
            return new TaxiPriceData[size];
        }
    };
}
