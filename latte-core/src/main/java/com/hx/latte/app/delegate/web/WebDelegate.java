package com.hx.latte.app.delegate.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.delegate.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by hexiao on 2017/11/28.
 * 加载Web的核心
 * 显示webView
 */

public abstract class WebDelegate extends LatteDelegate {

    private WebView mWebView = null;
    //弱引用来存放webView避免内存溢出
    private final ReferenceQueue<WebView> webQueue=new ReferenceQueue<>();
    private String mUrl=null;//请求地址
    private boolean isViewAvailable=false;
    public WebDelegate(){

    }

    public abstract IWebViewInitalizer initalizer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if (bundle!=null){
            mUrl=bundle.getString(RouteKeys.URL.name());
        }
        initWebView();
    }

    /**
     * 初始化WebView
     */
    @SuppressLint("JavascriptInterface")
    private void initWebView(){
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }else {
            final IWebViewInitalizer initalizer=initalizer();
            if (initalizer!=null){
                final WeakReference<WebView> webViewWeakReference=new
                        WeakReference<>(new WebView(getContext()),webQueue);
                mWebView=webViewWeakReference.get();
                mWebView=initalizer.initWebView(mWebView);
                mWebView.setWebViewClient(initalizer.initWebViewClient());
                mWebView.setWebChromeClient(initalizer.initWebChromeClient());
                mWebView.addJavascriptInterface(LatteWebInterface.creator(this),"latte");
                isViewAvailable=true;
            }else {
                throw  new NullPointerException("未初始化");
            }
        }
    }

    /**
     * 获取webView视图
     * @return
     */
    public WebView getWebView(){
        if (mWebView==null){
            throw new NullPointerException("webView is null");
        }
        return isViewAvailable?mWebView:null;
    }

    /**
     * 获取到的Url
     * @return
     */
    public String getUrl(){
        if (mUrl==null){
            throw new NullPointerException("Url is null");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView!=null){
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView!=null){
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mWebView!=null){
            isViewAvailable=false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView=null;
        }
    }
}
