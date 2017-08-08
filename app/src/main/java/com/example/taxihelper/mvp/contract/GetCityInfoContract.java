package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.entity.CityInfo;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/8/8.
 */

public interface GetCityInfoContract {
    interface Model {
        Observable<CityInfo> getCityInfo(String accessToken,double slat,double slot);
    }

    interface View extends BaseView{
        void getCityInfo(CityInfo cityInfo);
    }

    interface Presenter {
        void getCityInfo(String accessToken,double slat,double slot);
    }
}
