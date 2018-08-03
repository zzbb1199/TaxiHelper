package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.AcceptOrderContract;
import com.example.taxihelper.mvp.entity.CancelOrder;
import com.example.taxihelper.mvp.entity.CancelOrderReason;
import com.example.taxihelper.mvp.entity.OrderStatus;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Raven on 2017/9/6.
 */

public class AcceptOrderModelImpl extends BaseModelImpl implements AcceptOrderContract.Model {
    @Inject
    public AcceptOrderModelImpl() {
    }

    @Override
    public Observable<OrderStatus> changeOrderStatus(String orderId, long driverId, String status) {
        return filterStatus(getApi().changOrderStatus(accessToken,orderId,driverId,status));
    }

    @Override
    public Observable<CancelOrder> cancelOrder(String orderId, boolean force, Integer reasonId) {
        return filterStatus(getApi().cancelOrder(accessToken,orderId,force,reasonId));
    }

    @Override
    public Observable<List<CancelOrderReason>> cancelOrderReason() {
        return filterStatus(getApi().cancelOrderReason(accessToken));
    }
}
