package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.GetCityInfoContract;
import com.example.taxihelper.mvp.entity.CityInfo;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public class GetCityInfoModelImpl extends BaseModelImpl implements GetCityInfoContract.Model {
    @Inject
    public GetCityInfoModelImpl() {
    }

    @Override
    public Observable<CityInfo> getCityInfo(String accessToken, double slat, double slot) {
        return filterStatus(getApi().getCityInfo(accessToken, slat, slot));
    }
}
