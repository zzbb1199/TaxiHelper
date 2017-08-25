package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.entity.CreateOrder;
import com.example.taxihelper.mvp.entity.NearbyCarInfo;
import com.example.taxihelper.mvp.entity.TaxiPriceInfo;
import com.example.taxihelper.mvp.entity.UserInfo;
import com.example.taxihelper.mvp.presenter.base.BasePresenter;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/8/22.
 */

public interface TaxiContract {
    interface Model {
        Observable<NearbyCarInfo> getNearbyCarInfo(double slat, double slot);

        Observable<CityInfo> getCityInfo(double slat, double slot);

        Observable<TaxiPriceInfo> getTaxiPrice(double slat, double slng, double elat, double elng,
                                               Integer serviceId, Integer cityId, Long departureTime,
                                               String flt, Long flightDate, Integer flightDelayTime, String airCode);

        /**
         */
        Observable<CreateOrder> createOrder(Integer serviceId,Integer carGroupId,String passengerMobil,
                                            String passengerName, double slat,double slot,String startName,
                                            String startAddress,String endName,String endAddress,
                                            double elat,double elot,String estimateId);
        
        Observable<UserInfo> getUserInfo();
    }

    interface View extends BaseView {
        void showNearbyCarInfo(NearbyCarInfo nearbyCarInfo);

        void showPriceResult(TaxiPriceInfo taxiPriceInfo);

        void getCityInfo(CityInfo cityInfo);
        
        void showCreateOrderResult(CreateOrder createOrder);
        
        void showUserInfo(UserInfo userInfo);
    }

    interface Presenter extends BasePresenter {
        void getNearbyCarInfo(double slat, double lot);

        void getCityInfo(double slat, double slot);

        void getTaxiPrice(double slat, double slng, double elat, double elng,
                          Integer serviceId, Integer cityId, Long departureTime,
                          String flt, Long flightDate, Integer flightDelayTime, String airCode);
        
        void  createOrder(Integer serviceId,Integer carGroupId,String passengerMobil,
                          String passengerName, double slat,double slot,String startName,String startAddress,
                          String endName,String endAddress,
                          double elat,double elot,String estimateId);
        
        void getUserInfo();
    }
}
