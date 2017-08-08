package com.example.taxihelper.mvp.ui.activities;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.taxihelper.R;
import com.example.taxihelper.constant.Constant;
import com.example.taxihelper.mvp.ui.activities.base.BaseActivity;
import com.example.taxihelper.utils.system.RxBus;
import com.example.taxihelper.utils.system.SpUtil;

import butterknife.InjectView;

/**
 * Created by 张兴锐 on 2017/8/7.
 */

public class ShenZhouAuthActivity extends BaseActivity {
    @InjectView(R.id.web_view)
    WebView mWebView;

    String url = Constant.SHENZHOU_TEST_AUTH + "/oauth/authorize?client_id=" + Constant.SHENZHOU_CLIENT_ID +
            "&redirect_uri=" + Constant.SHENZHOUE_REDIRECT_URL + "&response_type=code&scope=read&mobile=15086943358";

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {

        WebSettings webSettings = mWebView.getSettings();
        //支持JS
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            int count = 0;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                count += 1;
                Log.i(TAG, count + "");
                
                if (url.equals("client_id=A73371C10000030A&redirect_uri=http://nmid.cqupt.edu.cn/&response_type=code&scope=read&mobile=15086943358")){
                    //如果得到了如上所示的url，
                    String accessToken = SpUtil.getString(ShenZhouAuthActivity.this,Constant.ACCESS_TOKEN,null);
                    if (accessToken != null){
                        RxBus.getDefault().post(accessToken);
                        finish();
                        return;
                    }
                    //如果过期了这个返回值是什么？？？，暂时未知
                }
                
                if (count == 3){//已经在授权期限内
                    String redirectUrl = url.split("[?]")[0];
                    if (redirectUrl.equals(Constant.SHENZHOUE_REDIRECT_URL)){
                        //判定是否跳转到最后
                        String code_url = url.split("[?]")[1];
                        String code = code_url.substring(code_url.indexOf("=")+1, code_url.length());
                        RxBus.getDefault().post(code);
                        finish();
                        return;
                    }
                }
                
                if (count == 4) {//为重新获取了code
                    //4次以后
                    String code_url = url.split("[?]")[1];
                    String code = code_url.substring(code_url.indexOf("=")+1, code_url.length());
                    RxBus.getDefault().post(code);
                    finish();
                }
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_shenzhou_auth;
    }

}
