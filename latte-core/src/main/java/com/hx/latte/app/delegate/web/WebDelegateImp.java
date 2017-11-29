package com.hx.latte.app.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hx.latte.app.delegate.web.client.WebChromeClientImpl;
import com.hx.latte.app.delegate.web.client.WebViewClientImpl;
import com.hx.latte.app.delegate.web.route.RouteKeys;
import com.hx.latte.app.delegate.web.route.Router;

/**
 * Created by hexiao on 2017/11/29.
 * WebDelegate的实现类
 */

public class WebDelegateImp extends WebDelegate implements IWebViewInitalizer{

    public static WebDelegateImp creator(String url){
        Bundle bundle=new Bundle();
        bundle.putString(RouteKeys.URL.name(),url);
        WebDelegateImp webDelegateImp=new WebDelegateImp();
        webDelegateImp.setArguments(bundle);
        return webDelegateImp;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl()!=null){
            //用原生的方式模式web跳转
            Router.getInstance().loadPage(this,getUrl());
        }
    }

    @Override
    public IWebViewInitalizer initalizer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        WebViewClientImpl webViewClient=new WebViewClientImpl(this);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
