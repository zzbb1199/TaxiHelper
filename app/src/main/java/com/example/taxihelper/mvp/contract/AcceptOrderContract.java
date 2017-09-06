package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.entity.OrderStatus;
import com.example.taxihelper.mvp.presenter.base.BasePresenter;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public interface AcceptOrderContract {
    interface Model {
        Observable<OrderStatus> changeOrderStatus(String orderId,long driverId,String status);
    }

    interface View extends BaseView{
        void showOrderStatus(OrderStatus orderStatus);
        void onError();
    }

    interface Presenter extends BasePresenter{
        void changOrderStatus(String orderId,long driverId,String status);
    }
}
