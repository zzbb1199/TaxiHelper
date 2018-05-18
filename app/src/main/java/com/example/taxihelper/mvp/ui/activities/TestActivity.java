package com.example.taxihelper.mvp.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.taxihelper.R;
import com.example.taxihelper.utils.system.DensityUtil;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linearLayout;
    int screenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        //
        screenWidth = DensityUtil.getDeviceInfo(this)[0];
        
    }

    @Override
    public void onClick(View view) {
        //新增一个item
        LinearLayout item = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.viewpager_item_single_price,null);
        item.setLayoutParams(new LinearLayout.LayoutParams(screenWidth/3, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(item);
    }
}
