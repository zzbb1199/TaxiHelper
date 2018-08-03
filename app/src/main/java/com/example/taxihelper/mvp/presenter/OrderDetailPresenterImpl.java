package com.example.taxihelper.mvp.presenter;

import com.example.taxihelper.mvp.contract.OrderDetailContract;
import com.example.taxihelper.mvp.entity.OrderDetailInfo;
import com.example.taxihelper.mvp.model.OrderDetailModelImpl;
import com.example.taxihelper.mvp.presenter.base.BasePresenterImpl;
import com.example.taxihelper.net.FilterSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Raven on 2017/8/26.
 */

public class OrderDetailPresenterImpl extends BasePresenterImpl<OrderDetailContract.View> implements OrderDetailContract.Presenter {

 
    OrderDetailModelImpl model;

    @Inject
    public OrderDetailPresenterImpl() {
        model = new OrderDetailModelImpl();
    }

    @Override
    public void getOrderDetialInfo(String orderId) {
        mView.showProgress();
        model.getOrderDetailInfo(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<OrderDetailInfo>() {
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
                    public void onNext(OrderDetailInfo orderDetailInfo) {
                        mView.hideProgress();
                        mView.showOrderDetial(orderDetailInfo);
                    }
                });
    }
}
