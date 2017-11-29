package com.hx.latte.app.delegate.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hx.latte.app.delegate.web.WebDelegate;
import com.hx.latte.app.delegate.web.route.Router;

/**
 * Created by hexiao on 2017/11/29.
 *WebViewClient的实现类，进行拦截
 */

public class WebViewClientImpl extends WebViewClient{
    private WebDelegate webDelegate;

    public WebViewClientImpl(WebDelegate webDelegate) {
        this.webDelegate = webDelegate;
    }

    /**
     * 路由的拦截 会拦截进行跳转
     * @param view
     * @param url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleUrl(webDelegate,url);
    }
}
