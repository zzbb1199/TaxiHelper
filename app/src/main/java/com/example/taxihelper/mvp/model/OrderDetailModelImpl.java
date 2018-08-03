package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.OrderDetailContract;
import com.example.taxihelper.mvp.entity.OrderDetailInfo;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Raven on 2017/8/26.
 */

public class OrderDetailModelImpl extends BaseModelImpl implements OrderDetailContract.Model {

    @Inject
    public OrderDetailModelImpl() {
    }

    @Override
    public Observable<OrderDetailInfo> getOrderDetailInfo(String orderId) {
        return filterStatus(getApi().getOrderDetail(accessToken,orderId));
    }
}
