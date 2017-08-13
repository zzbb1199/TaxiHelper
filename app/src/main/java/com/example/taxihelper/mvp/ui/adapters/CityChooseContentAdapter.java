package com.example.taxihelper.mvp.ui.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.mvp.entity.CitiesInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by 张兴锐 on 2017/8/13.
 */

public class CityChooseContentAdapter extends RecyclerArrayAdapter<CitiesInfo> {
    
    private List<CitiesInfo> citiesInfos ;
    
    public CityChooseContentAdapter(Context context) {
        super(context);
    }
    

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(parent, R.layout.view_city_item);
    }

    class MyViewHolder extends BaseViewHolder<CitiesInfo>{
        private TextView tv;
        public MyViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tv = $(R.id.city_name);
        }

        @Override
        public void setData(CitiesInfo data) {
            super.setData(data);
            tv.setText(data.getCityName());
        }
    }
}
