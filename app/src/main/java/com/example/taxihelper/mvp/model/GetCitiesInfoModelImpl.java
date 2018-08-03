package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.GetCitiesInfoContract;
import com.example.taxihelper.mvp.entity.CitiesInfo;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Raven on 2017/8/13.
 */

public class GetCitiesInfoModelImpl extends BaseModelImpl implements GetCitiesInfoContract.Model {
    @Inject
    public GetCitiesInfoModelImpl() {
    }

    @Override
    public Observable<List<CitiesInfo>> getCitiesInfo(String accessToken, String type, Integer serviceId) {
        return filterStatus(getApi().getCitiesInfo(accessToken,type,serviceId));
    }
}
