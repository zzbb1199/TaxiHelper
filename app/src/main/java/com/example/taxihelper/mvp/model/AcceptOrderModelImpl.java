package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.AcceptOrderContract;
import com.example.taxihelper.mvp.entity.OrderStatus;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class AcceptOrderModelImpl extends BaseModelImpl implements AcceptOrderContract.Model {
    @Inject
    public AcceptOrderModelImpl() {
    }

    @Override
    public Observable<OrderStatus> changeOrderStatus(String orderId, long driverId, String status) {
        return filterStatus(getApi().changOrderStatus(accessToken,orderId,driverId,status));
    }
}
