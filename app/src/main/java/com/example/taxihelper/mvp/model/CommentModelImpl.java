package com.example.taxihelper.mvp.model;

import com.example.taxihelper.mvp.contract.CommentContract;
import com.example.taxihelper.mvp.model.base.BaseModelImpl;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by 张兴锐 on 2017/9/8.
 */

public class CommentModelImpl extends BaseModelImpl implements CommentContract.Model {
    @Inject
    public CommentModelImpl() {
    }

    @Override
    public Observable<String> commentOrder(String orderId, int score, String remark) {
        return filterStatus(getApi().commentOrder(orderId,score,remark));
    }
}
