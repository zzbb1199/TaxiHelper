package com.example.taxihelper.mvp.ui.activities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.taxihelper.App;
import com.example.taxihelper.dagger.component.ActivityComponent;
import com.example.taxihelper.dagger.component.DaggerActivityComponent;
import com.example.taxihelper.dagger.module.ActivityModule;
import com.example.taxihelper.utils.system.ActivityStack;

import butterknife.ButterKnife;

/**
 * Created by 猿人 on 2017/4/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent mActivityComponent;
    protected String TAG;

    /**
     * 初始化注入信息
     */
    public abstract void initInjector();

    /**
     * 初始化view，相关view配置
     */
    public abstract void initViews();

    /**
     * 得到资源layout id
     */
    public abstract int getLayout();


    //===============================================
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        ActivityStack.getScreenManager().pushActivity(this);
        //初始化组件 注入器
        initActivityComponent();

        setContentView(getLayout());
        ButterKnife.inject(this);

        initInjector();
        initViews();


    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getmApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getScreenManager().popActivity(this);
    }
}
