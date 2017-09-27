package com.example.taxihelper.mvp.ui.activities;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.taxihelper.R;
import com.example.taxihelper.mvp.ui.activities.base.BaseActivity;
import com.example.taxihelper.utils.image.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;

/**
 * Created by 张兴锐 on 2017/9/6.
 */

public class AboutTeamActivity extends BaseActivity {

    @InjectView(R.id.about_team_web_view)
    WebView mWebView;
    private final int TIME_OUT = 1;
    private Timer timer;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == TIME_OUT) {
                ToastUtil.shortToast("连接超时啦o(╥﹏╥)o");
//                finish();
            }
        }
    };

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {

        WebSettings webSettings = mWebView.getSettings();
        //支持JS
        webSettings.setJavaScriptEnabled(true);
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                timer = new Timer();
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        Log.i(TAG, "超时");
                        timer.cancel();
                        timer.purge();
                        handler.sendEmptyMessage(1);
                    }
                };
                timer.schedule(tt, 3000, 1);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                timer.cancel();
                timer.purge();
            }
        });
        mWebView.loadUrl("https://nmid.cqupt.edu.cn/");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_abount_team;
    }
}
