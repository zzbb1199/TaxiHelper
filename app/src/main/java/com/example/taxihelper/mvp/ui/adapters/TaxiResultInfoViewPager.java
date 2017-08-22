package com.example.taxihelper.mvp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.taxihelper.mvp.ui.adapters.PagerAdapter.base.BaseViewPagerAdapter;

import java.util.List;

/**
 * Created by 张兴锐 on 2017/8/22.
 */

public class TaxiResultInfoViewPager extends BaseViewPagerAdapter {
    
    List<Fragment> list ;


    public TaxiResultInfoViewPager(FragmentManager fm) {
        super(fm);
    }

    public TaxiResultInfoViewPager(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }
}
