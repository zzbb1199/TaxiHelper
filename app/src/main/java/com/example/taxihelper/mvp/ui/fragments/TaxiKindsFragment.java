package com.example.taxihelper.mvp.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.entity.GroupId;
import com.example.taxihelper.mvp.entity.TaxiPriceData;
import com.example.taxihelper.mvp.ui.fragments.base.BaseFragment;
import com.example.taxihelper.utils.system.DensityUtil;
import com.example.taxihelper.utils.system.RxBus;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by Raven on 2017/8/22.
 */

public class TaxiKindsFragment extends BaseFragment implements View.OnClickListener {

    List<TaxiPriceData> priceDataList;
    @InjectView(R.id.linear_layout)
    LinearLayout linearLayout;

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
        int screenWidth = DensityUtil.getDeviceInfo(getActivity())[0] - DensityUtil.dip2px(getActivity(),32);
        //获取数据
        priceDataList = getArguments().getParcelableArrayList(Constant.TAXI_PRICE);
        //根据数目添加
        for (TaxiPriceData price: priceDataList) {
            LinearLayout item = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.viewpager_item_single_price,null);
            item.setLayoutParams(new LinearLayout.LayoutParams(screenWidth/3, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((TextView)item.findViewById(R.id.company)).setText(price.company);
            ((TextView)item.findViewById(R.id.price)).setText(String.format("%.2f",price.price)+"元");
            ((TextView)item.findViewById(R.id.type)).setText(price.type);
            item.setTag(price.groupId);
            item.setOnClickListener(this);
            linearLayout.addView(item);
        }
    }
        
    

    @Override
    public int getLayoutId() {
        return R.layout.fragment_taxi_kinds;
    }
    

    @Override
    public void onClick(View view) {
        int id = (int) view.getTag();
        if(id == -1){
            new AlertDialog.Builder(getActivity())
                    .setTitle("提示")
                    .setMessage("非常抱歉，当前版本暂不支持滴滴打车")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        }else{
            RxBus.getDefault().post(new GroupId(id));
        }
    }
}
