package com.example.taxihelper.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.example.taxihelper.mvp.BaseView;


/**
 * Created by 猿人 on 2017/4/9.
 */

/**
 * @param <T> view
 */
public class BasePresenterImpl<T extends BaseView> implements BasePresenter {

    //声明为protected 供子类使用
    protected T mView;


    @Override
    public void injectView(@NonNull BaseView view) {
        mView = (T) view;
    }
}


