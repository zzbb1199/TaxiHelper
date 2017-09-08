package com.example.taxihelper.mvp.presenter;

import com.example.taxihelper.mvp.contract.CommentContract;
import com.example.taxihelper.mvp.model.CommentModelImpl;
import com.example.taxihelper.mvp.presenter.base.BasePresenterImpl;
import com.example.taxihelper.net.FilterSubscriber;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 张兴锐 on 2017/9/8.
 */

public class CommentPresenterImpl extends BasePresenterImpl implements CommentContract.Presenter {
    @Inject
    CommentModelImpl model;

    @Inject
    public CommentPresenterImpl() {
    }

    @Override
    public void commentOrder(String orderId, int score, String remark) {
        mView.showProgress();
        model.commentOrder(orderId,score,remark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<String>() {
                    @Override
                    public void onCompleted() {
                        
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.hideProgress();
                        mView.showMsg(error);
                    }

                    @Override
                    public void onNext(String s) {
                        mView.hideProgress();
                        mView.showMsg(s);
                    }
                });
    }
}
