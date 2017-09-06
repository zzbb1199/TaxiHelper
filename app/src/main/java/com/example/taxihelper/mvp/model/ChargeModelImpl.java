package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.ChargeContract;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class ChargeModelImpl extends BaseModelImpl implements ChargeContract.Model {
    @Inject
    public ChargeModelImpl() {
    }

    @Override
    public Observable<String> chargeAmount(Integer amount, String phoneNum) {
        return filterStatus(getApi().chargeAccount(accessToken,amount,phoneNum));
    }
}
