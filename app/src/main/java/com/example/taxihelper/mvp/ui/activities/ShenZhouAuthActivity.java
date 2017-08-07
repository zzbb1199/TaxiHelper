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
                if (count == 3) {
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
