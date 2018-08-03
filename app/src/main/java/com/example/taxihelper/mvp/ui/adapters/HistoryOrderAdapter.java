package com.example.taxihelper.mvp.ui.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.mvp.entity.HistoryOrder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Raven on 2017/9/6.
 */

public class HistoryOrderAdapter extends RecyclerArrayAdapter<HistoryOrder.ListBean> {

    public HistoryOrderAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(parent, R.layout.history_order_item);
    }

    class MyViewHolder extends BaseViewHolder<HistoryOrder.ListBean>{

        TextView orderNum,date,upLocation,downLocation;
        public MyViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            orderNum = $(R.id.order_num);
            date = $(R.id.date);
            upLocation = $(R.id.up_location);
            downLocation = $(R.id.down_location);
        }
        
        @Override
        public void setData(HistoryOrder.ListBean data) {
            super.setData(data);
            orderNum.setText(data.getId());
            date.setText(TimeStamp2Date(data.getCreateTime()));
            upLocation.setText(data.getStartCity().getDisplayName());
            downLocation.setText(data.getEndCity().getDisplayName());
        }
    }
    
    public String TimeStamp2Date(long timestampString){
        Long timestamp = timestampString*1000;
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(timestamp));
        return date;
    }
}
