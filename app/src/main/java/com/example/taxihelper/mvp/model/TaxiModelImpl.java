package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.TaxiContract;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.entity.CreateOrder;
import com.example.taxihelper.mvp.entity.GoingOrder;
import com.example.taxihelper.mvp.entity.NearbyCarInfo;
import com.example.taxihelper.mvp.entity.TaxiPriceInfo;
import com.example.taxihelper.mvp.entity.UserInfo;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/8/22.
 */

public class TaxiModelImpl extends BaseModelImpl implements TaxiContract.Model {
    

    @Inject
    public TaxiModelImpl() {
    }

    @Override
    public Observable<NearbyCarInfo> getNearbyCarInfo(double slat, double slot) {
        return filterStatus(getApi().getNearbyCarInfo(accessToken,slat,slot));
    }

    @Override
    public Observable<CityInfo> getCityInfo(double slat, double slot) {
        return filterStatus(getApi().getCityInfo(accessToken,slat,slot));
    }

    @Override
    public Observable<TaxiPriceInfo> getTaxiPrice(double slat, double slng, double elat, double elng, Integer serviceId, 
                                                  Integer cityId, Long departureTime, String flt, Long flightDate, Integer flightDelayTime, String airCode) {
        return filterStatus(getApi().getTaxiPriceInfo(accessToken,slat,slng,elat,elng,serviceId));
//        ,cityId,
//                departureTime,flt,flightDate,
//                flightDelayTime,airCode));
    }

    @Override
    public Observable<CreateOrder> createOrder(Integer serviceId, Integer carGroupId, String passengerMobil, String passengerName,
                                               double slat, double slot, String startName,String startAddress, 
                                               String endName,String endAddress,
                                               double elat, double elot, String estimateId) {
        return filterStatus(getApi().createOrder(accessToken,serviceId,carGroupId,passengerMobil,passengerName,slat,slot,startName,startAddress,endName,endAddress,elat,elot,estimateId));
    }

    @Override
    public Observable<UserInfo> getUserInfo() {
        return filterStatus(getApi().getUserInfo(accessToken));
    }

    @Override
    public Observable<List<GoingOrder>> checkGoingOrder() {
        return filterStatus(getApi().checkGoingOrder(accessToken));
    }


}
