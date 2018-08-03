package com.example.taxihelper.mvp.presenter;

import com.example.taxihelper.mvp.contract.HistoryOrderContract;
import com.example.taxihelper.mvp.entity.HistoryOrder;
import com.example.taxihelper.mvp.model.HistoryOrderModelImpl;
import com.example.taxihelper.mvp.presenter.base.BasePresenterImpl;
import com.example.taxihelper.net.FilterSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Raven on 2017/9/6.
 */

public class HistoryOrderPresenterImpl extends BasePresenterImpl<HistoryOrderContract.View> implements HistoryOrderContract.Presenter {
    @Inject
    public HistoryOrderPresenterImpl() {
    }

    @Inject
    HistoryOrderModelImpl model;

    @Override
    public void getHistroyOrder(Integer limit, String orderStatus) {
        mView.showProgress();
        model.getHistroyOrder(limit, orderStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<HistoryOrder>() {
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
                    public void onNext(HistoryOrder historyOrder) {
                        mView.hideProgress();
                        mView.showHistroyOrder(historyOrder);
                    }
                });

    }
}
