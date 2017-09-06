package com.example.taxihelper.mvp.ui.activities;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.taxihelper.R;
import com.example.taxihelper.mvp.ui.activities.base.BaseActivity;

import butterknife.InjectView;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class AboutTeamActivity extends BaseActivity {
    
    @InjectView(R.id.about_team_web_view)
    WebView mWebView;
    @Override
    public void initInjector() {
        
    }

    @Override
    public void initViews() {

        WebSettings webSettings =  mWebView.getSettings();
        //支持JS
        webSettings.setJavaScriptEnabled(true);
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://nmid.cqupt.edu.cn/");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_abount_team;
    }
}
