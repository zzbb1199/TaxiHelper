package com.example.taxihelper.mvp.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.taxihelper.R;
import com.example.taxihelper.mvp.contract.HistoryOrderContract;
import com.example.taxihelper.mvp.entity.HistoryOrder;
import com.example.taxihelper.mvp.presenter.HistoryOrderPresenterImpl;
import com.example.taxihelper.mvp.ui.activities.base.BaseActivity;
import com.example.taxihelper.mvp.ui.adapters.HistoryOrderAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class HistoryOrderActivity extends BaseActivity implements HistoryOrderContract.View{
    @InjectView(R.id.history_recycler)
    EasyRecyclerView historyRecycler;

    private HistoryOrderAdapter adapter;
    @Inject
    HistoryOrderPresenterImpl presenter;
    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
        presenter.injectView(this);
    }

    @Override
    public void initViews() {
        adapter = new HistoryOrderAdapter(this);
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        historyRecycler.setAdapter(adapter);
        presenter.getHistroyOrder(20,null);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_histroy;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void showHistroyOrder(HistoryOrder historyOrder) {
        adapter.addAll(historyOrder.getList());
    }
}
