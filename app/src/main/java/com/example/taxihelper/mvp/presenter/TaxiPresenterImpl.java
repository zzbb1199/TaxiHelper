package com.example.taxihelper.mvp.presenter;

import com.example.taxihelper.mvp.contract.TaxiContract;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.entity.CreateOrder;
import com.example.taxihelper.mvp.entity.GoingOrder;
import com.example.taxihelper.mvp.entity.NearbyCarInfo;
import com.example.taxihelper.mvp.entity.TaxiPriceInfo;
import com.example.taxihelper.mvp.entity.UserInfo;
import com.example.taxihelper.mvp.model.TaxiModelImpl;
import com.example.taxihelper.mvp.presenter.base.BasePresenterImpl;
import com.example.taxihelper.net.FilterSubscriber;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Raven on 2017/8/22.
 */

public class TaxiPresenterImpl extends BasePresenterImpl<TaxiContract.View> implements TaxiContract.Presenter {

    TaxiModelImpl model;

    @Inject
    public TaxiPresenterImpl() {
        model = new TaxiModelImpl();
    }

    @Override
    public void getNearbyCarInfo(double slat, double slot) {
        mView.showProgress();
        model.getNearbyCarInfo(slat, slot)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<NearbyCarInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideProgress();
                        mView.showMsg(error);
                    }

                    @Override
                    public void onNext(NearbyCarInfo nearbyCarInfo) {
                        mView.hideProgress();
                        mView.showNearbyCarInfo(nearbyCarInfo);
                    }
                });
    }

    @Override
    public void getCityInfo(double slat, double slot) {
        mView.showProgress();
        model.getCityInfo(slat, slot)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<CityInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideProgress();
                        mView.showMsg(error);
                    }

                    @Override
                    public void onNext(CityInfo cityInfo) {
                        mView.hideProgress();
                        mView.getCityInfo(cityInfo);
                    }
                });
    }

    @Override
    public void getTaxiPrice(double slat, double slng, double elat, double elng, Integer serviceId, Integer cityId, Long departureTime, String flt, Long flightDate, Integer flightDelayTime, String airCode) {
        mView.showProgress();
        model.getTaxiPrice(slat, slng, elat, elng, serviceId, cityId, departureTime, flt, flightDate,
                flightDelayTime, airCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<TaxiPriceInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.showMsg(error);
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(TaxiPriceInfo taxiPriceInfo) {
                        mView.hideProgress();
                        mView.showPriceResult(taxiPriceInfo);
                    }
                });
    }

    @Override
    public void createOrder(Integer serviceId, Integer carGroupId, String passengerMobil, String passengerName, double slat, double slot, String startName, String startAddress, String endName, String endAddress,
                            double elat, double elot, String estimateId) {
        mView.showProgress();
        model.createOrder(serviceId, carGroupId, passengerMobil, passengerName, slat, slot, startName, startAddress,
                endName, endAddress,
                elat, elot, estimateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<CreateOrder>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideProgress();
                        mView.showMsg(error);
                    }

                    @Override
                    public void onNext(CreateOrder createOrder) {
                        mView.hideProgress();
                        mView.showCreateOrderResult(createOrder);
                    }
                });
    }

    @Override
    public void getUserInfo() {
        mView.showProgress();
        model.getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideProgress();
                        mView.showMsg(error);
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        mView.hideProgress();
                        mView.showUserInfo(userInfo);
                    }
                });
    }

    @Override
    public void checkGoingOrder() {
        model.checkGoingOrder()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<List<GoingOrder>>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.showMsg(error);
                    }

                    @Override
                    public void onNext(List<GoingOrder> goingOrder) {
                        mView.showGoingOrderResult(goingOrder);
                    }
                });
    }


}
