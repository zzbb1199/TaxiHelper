package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.entity.CancelOrder;
import com.example.taxihelper.mvp.entity.CancelOrderReason;
import com.example.taxihelper.mvp.entity.OrderStatus;
import com.example.taxihelper.mvp.presenter.base.BasePresenter;

import java.util.List;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public interface AcceptOrderContract {
    interface Model {
        Observable<OrderStatus> changeOrderStatus(String orderId,long driverId,String status);
        Observable<CancelOrder> cancelOrder(String orderId,boolean force,Integer reasonId);
        Observable<List<CancelOrderReason>> cancelOrderReason();
    }

    interface View extends BaseView{
        void showOrderStatus(OrderStatus orderStatus);
        void onError();
        void cancelOrder(CancelOrder cancelOrder);
        void cancelReson(List<CancelOrderReason> cancelOrderReasons);
    }

    interface Presenter extends BasePresenter{
        void changOrderStatus(String orderId,long driverId,String status);
        void cancelOrder(String orderId,boolean force,Integer reasonId);
        void cancelOrderReason();
    }
}
