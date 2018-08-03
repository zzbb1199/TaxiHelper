package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.entity.OrderDetailInfo;

import rx.Observable;

/**
 * Created by Raven on 2017/8/26.
 */

public interface OrderDetailContract {
    interface Model {
        Observable<OrderDetailInfo> getOrderDetailInfo(String orderId);
    }

    interface View extends BaseView{
        void showOrderDetial(OrderDetailInfo orderDetailInfo);
    }

    interface Presenter {
        void getOrderDetialInfo(String orderId);
    }
}
