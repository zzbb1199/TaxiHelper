package com.example.taxihelper.mvp.ui.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.example.taxihelper.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Raven on 2017/8/11.
 */

public class LocationChooseAdapter extends RecyclerArrayAdapter<Tip> {
    public LocationChooseAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChooseViewHolder(parent, R.layout.view_location_search_tv);
    }

    class ChooseViewHolder extends BaseViewHolder<Tip>{
        TextView tv;
        TextView tvDetail;
        public ChooseViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tv = $(R.id.location_choose_tv);
            tvDetail = $(R.id.location_choose_detail_tv);
        }

        @Override
        public void setData(Tip data) {
            super.setData(data);
            tv.setText(data.getName());
            tvDetail.setText(data.getDistrict()+data.getName());
        }
    }
}
