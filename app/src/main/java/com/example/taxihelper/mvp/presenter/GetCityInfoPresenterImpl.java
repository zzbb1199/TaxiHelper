package com.example.taxihelper.mvp.presenter;

import com.example.taxihelper.mvp.contract.GetCityInfoContract;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.model.GetCityInfoModelImpl;
import com.example.taxihelper.mvp.presenter.base.BasePresenterImpl;
import com.example.taxihelper.net.FilterSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class GetCityInfoPresenterImpl extends BasePresenterImpl<GetCityInfoContract.View> implements GetCityInfoContract.Presenter {

    @Inject
    GetCityInfoModelImpl model;
    
    @Inject
    public GetCityInfoPresenterImpl() {
    }

    @Override
    public void getCityInfo(String accessToken, double slat, double slot) {
        mView.showProgress();
        model.getCityInfo(accessToken,slat,slot)
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
}
