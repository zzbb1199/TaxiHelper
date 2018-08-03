package com.example.taxihelper.mvp.presenter;

import com.example.taxihelper.mvp.contract.GetCitiesInfoContract;
import com.example.taxihelper.mvp.entity.CitiesInfo;
import com.example.taxihelper.mvp.model.GetCitiesInfoModelImpl;
import com.example.taxihelper.mvp.presenter.base.BasePresenterImpl;
import com.example.taxihelper.net.FilterSubscriber;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Raven on 2017/8/13.
 */

public class GetCitiesInfoPresenterImpl extends BasePresenterImpl<GetCitiesInfoContract.View> implements GetCitiesInfoContract.Presenter {
    
    @Inject
    GetCitiesInfoModelImpl model;
    
    @Inject
    public GetCitiesInfoPresenterImpl() {
    }

    @Override
    public void getCitiesInfo(String accessToken, String type, Integer serviceId) {
        mView.showProgress();
        model.getCitiesInfo(accessToken,type,serviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<List<CitiesInfo>>() {
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
                    public void onNext(List<CitiesInfo> list) {
                        mView.hideProgress();
                        mView.getDatas(list);
                    }
                    
                });
    }
}
