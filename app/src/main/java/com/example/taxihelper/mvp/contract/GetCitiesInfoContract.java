package com.example.taxihelper.mvp.contract;

import com.example.taxihelper.mvp.BaseView;
import com.example.taxihelper.mvp.entity.CitiesInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/8/13.
 */

public interface GetCitiesInfoContract {
    interface Model {
        Observable<List<CitiesInfo>> getCitiesInfo(String accessToken, String type, Integer serviceId);
    }

    interface View extends BaseView{
        void getDatas(List<CitiesInfo> list);
    }

    interface Presenter {
        void getCitiesInfo(String accessToken,String type,Integer serviceId);
    }
}
