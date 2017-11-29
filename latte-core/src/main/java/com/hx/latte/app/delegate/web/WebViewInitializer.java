package com.hx.latte.app.delegate.web;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by hexiao on 2017/11/29.
 */

public class WebViewInitializer {

    public WebView createWebView(WebView webView){
        webView.setWebContentsDebuggingEnabled(true);
        //禁止横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //禁止竖向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        //初始化webSettings
        WebSettings webSettings=webView.getSettings();
        String agents=webSettings.getUserAgentString();
        webSettings.setUserAgentString(agents+"latte");
        //隐藏缩放
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        //禁止缩放
        webSettings.setSupportZoom(false);
        //文件权限
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        //缓存
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        return webView;
    }
}
