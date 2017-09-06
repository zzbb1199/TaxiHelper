package com.example.taxihelper.mvp.presenter;

import com.example.taxihelper.mvp.contract.AcceptOrderContract;
import com.example.taxihelper.mvp.entity.OrderStatus;
import com.example.taxihelper.mvp.model.AcceptOrderModelImpl;
import com.example.taxihelper.mvp.presenter.base.BasePresenterImpl;
import com.example.taxihelper.net.FilterSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class AcceptOrderPresenterImpl extends BasePresenterImpl<AcceptOrderContract.View> implements AcceptOrderContract.Presenter {
    @Inject
    AcceptOrderModelImpl model;
    @Inject
    public AcceptOrderPresenterImpl() {
    }

    @Override
    public void changOrderStatus(String orderId, long driverId, String status) {
        mView.showProgress();
        model.changeOrderStatus(orderId,driverId,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<OrderStatus>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideProgress();
                        mView.showMsg(error);
                        mView.onError();
                    }

                    @Override
                    public void onNext(OrderStatus orderStatus) {
                        mView.showOrderStatus(orderStatus);
                        mView.hideProgress();
                    }
                });
    }
}
