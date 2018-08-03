package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.presenter.base.BasePresenter;

import rx.Observable;

/**
 * Created by Raven on 2017/9/6.
 */

public interface ChargeContract {
    interface Model {
        Observable<String> chargeAmount(Integer amount, String phoneNum);
    }

    interface View extends BaseView{
        void success();
    }

    interface Presenter extends BasePresenter{
        void chargeAmount(Integer amount,String phoneNum);
    }
}
