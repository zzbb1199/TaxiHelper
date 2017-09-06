package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.HistoryOrderContract;
import com.example.taxihelper.mvp.entity.HistoryOrder;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class HistoryOrderModelImpl extends BaseModelImpl implements HistoryOrderContract.Model {
    @Inject
    public HistoryOrderModelImpl() {
    }

    @Override
    public Observable<HistoryOrder> getHistroyOrder(Integer limit, String orderStatus) {
        return filterStatus(getApi().getHistroyOrder(accessToken,limit,orderStatus));
    }
}
