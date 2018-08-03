package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.entity.HistoryOrder;

import rx.Observable;

/**
 * Created by Raven on 2017/9/6.
 */

public interface HistoryOrderContract {
    interface Model {
        Observable<HistoryOrder> getHistroyOrder(Integer limit,String orderStatus);
    }

    interface View extends BaseView {
        void showHistroyOrder(HistoryOrder historyOrder);
    }

    interface Presenter {
        void getHistroyOrder(Integer limit,String orderStatus);
    }
}
