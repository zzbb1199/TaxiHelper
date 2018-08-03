package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.presenter.base.BasePresenter;

import rx.Observable;

/**
 * Created by Raven on 2017/9/8.
 */

public interface CommentContract {
    interface Model {
        Observable<String> commentOrder(String orderId,int score,String remark);
    }

    interface View extends BaseView{
    }

    interface Presenter extends BasePresenter {
        void commentOrder(String orderId,int score,String remark);
    }
}
