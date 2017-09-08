package com.example.taxihelper.mvp.ui.activities;

import android.view.View;
import android.widget.EditText;

import com.example.taxihelper.App;
import com.example.taxihelper.R;
import com.example.taxihelper.mvp.contract.ChargeContract;
import com.example.taxihelper.mvp.presenter.ChargePresenterImpl;
import com.example.taxihelper.mvp.ui.activities.base.BaseActivity;
import com.example.taxihelper.utils.image.ToastUtil;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 张兴锐 on 2017/8/26.
 */

public class ChargeActivity extends BaseActivity implements ChargeContract.View{
   

    @Inject
    ChargePresenterImpl presenter;


    @InjectView(R.id.account_input)
    EditText accountInput;
    
    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
        presenter.injectView(this);
    }

    @Override
    public void initViews() {
        String phoneNum = App.getDaoSession().getUserInfoDao().loadAll().get(0).getPhone();
        accountInput.setText(phoneNum+"");
        //设置光标位置
        accountInput.setSelection(phoneNum.length());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_charge;
    }



    @OnClick({R.id.ten_yuan, R.id.twenty_yuan, R.id.thirty_yuan, R.id.fifty_yuan, R.id.one_thousand_yuan, R.id.two_thousand_yuan})
    public void onViewClicked(View view) {
        String account = accountInput.getText().toString();
        if (account.equals(""))
        {
            ToastUtil.shortToast("电话不能为空，请重新输入");
            return;
        }
        if (account.length() != 11){
            ToastUtil.shortToast("当前输入电话位数部位11位，请重新输入");
        }
        int amount = 0;
        switch (view.getId()) {
            case R.id.ten_yuan:
                amount = 10;
                break;
            case R.id.twenty_yuan:
                amount = 20;
                break;
            case R.id.thirty_yuan:
                amount = 30; 
                break;
            case R.id.fifty_yuan:
                amount = 50;
                break;
            case R.id.one_thousand_yuan:
                amount = 100;
                break;
            case R.id.two_thousand_yuan:
                amount = 200;
                break;
        }
        presenter.chargeAmount(amount,account);
    }

    @Override
    public void showProgress() {
//        DialogProgressUtils.ShowDialogProgressWithMsg(this,"正在为您充值，请稍后...");
    }

    @Override
    public void hideProgress() {
//        DialogProgressUtils.hideDialogProgress();
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.shortToast(msg);
    }

    @Override
    public void success() {
        finish();
    }
}
