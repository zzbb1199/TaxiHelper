package com.example.taxihelper.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.example.taxihelper.mvp.BaseView;


/**
 * Created by 猿人 on 2017/4/9.
 */

public interface BasePresenter {

    void injectView(@NonNull BaseView view);

}
