package com.example.taxihelper.mvp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.widget.StickyHeaderDecoration;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by 张兴锐 on 2017/8/13.
 */

public class CityChooseHeaderAdapter implements StickyHeaderDecoration.IStickyHeaderAdapter<CityChooseHeaderAdapter.HeaderHolder> {
    private Context mContext;
    private CityChooseContentAdapter adapter;

    public CityChooseHeaderAdapter(Context mContext, CityChooseContentAdapter adapter) {
        this.mContext = mContext;
        this.adapter = adapter;
    }
    @Override
    public long getHeaderId(int position) {
        if (position == 0){
            return position;
        }
        char nowLetter = adapter.getAllData().get(position).getCitySpell().toCharArray()[0];
        char lastLetter = adapter.getAllData().get(position - 1).getCitySpell().toCharArray()[0];
        if (nowLetter != lastLetter) {
            //更新字母
            return position;
        }
        //如果还是上一个字母
        return position - 1;
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.header_item, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        //设置
        viewholder.header.setText(adapter.getItem((int) getHeaderId(position)).getCitySpell().toCharArray()[0] + "");
    }

    class HeaderHolder extends BaseViewHolder {
        TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            this.header = (TextView) itemView;
        }
    }

}
