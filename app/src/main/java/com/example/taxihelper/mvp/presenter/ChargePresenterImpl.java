package com.example.taxihelper.mvp.presenter;

import com.example.taxihelper.mvp.contract.ChargeContract;
import com.example.taxihelper.mvp.model.ChargeModelImpl;
import com.example.taxihelper.mvp.presenter.base.BasePresenterImpl;
import com.example.taxihelper.net.FilterSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class ChargePresenterImpl extends BasePresenterImpl<ChargeContract.View> implements ChargeContract.Presenter {

    @Inject
    ChargeModelImpl model;

    @Inject
    public ChargePresenterImpl() {
    }

    @Override
    public void chargeAmount(Integer amount, String phoneNum) {
        mView.showProgress();
        model.chargeAmount(amount,phoneNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<String>() {
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
                    public void onNext(String str) {
                        mView.hideProgress();
                        mView.showMsg(str);
                        mView.success();
                    }
                });
    }
 

  
}
