package com.example.taxihelper.mvp.ui.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.example.taxihelper.R;
import com.example.taxihelper.mvp.ui.activities.base.BaseActivity;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class AboutUsActivity extends BaseActivity {
    @Override
    public void initInjector() {
        
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initViews() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_about_author;
    }
}
