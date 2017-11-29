package com.hx.latte.app.delegate.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.delegate.web.WebDelegate;
import com.hx.latte.app.delegate.web.WebDelegateImp;

/**
 * Created by hexiao on 2017/11/29.
 * 路由者
 */

public class Router {
    private  Router() {

    }

    private static class Holder{
        private static final Router INSTANCE=new Router();
    }

    public static Router getInstance(){
        return Holder.INSTANCE;
    }

    /**
     * 对url的处理
     * @param delegate fragment
     * @param url 地址
     * @return 是否需要被拦截处理
     */
    public final boolean handleUrl(WebDelegate delegate,String url){
        //如果是电话协议
        if (url.contains("tel:")){
            callPhone(delegate.getContext(),url);
            return true;
        }
        //如果不是电话链接
        LatteDelegate parentDelegate=delegate.getParentDelegate();//如果父fragment有链接跳转，没有则不跳转
        WebDelegateImp webDelegateImp=WebDelegateImp.creator(url);
        if (parentDelegate==null){
            delegate.start(webDelegateImp);
        }else {
            delegate.start(parentDelegate);
        }
        return true;
    }

    private void loadWebPage(WebView webView,String url){
        if (webView!=null){
            webView.loadUrl(url);
        }else {
            throw new NullPointerException("webView is null");
        }
    }

    private void loadLocalPage(WebView webView,String url){
        loadWebPage(webView,"file:///android_assets/"+url);
    }

    public  void loadPage(WebView webView,String url){
        if (URLUtil.isNetworkUrl(url)||URLUtil.isAssetUrl(url)){
            loadWebPage(webView,url);
        }else {
            loadLocalPage(webView,url);
        }
    }

    public void loadPage(WebDelegate delegate,String url){
        loadPage(delegate.getWebView(),url);
    }

    private void callPhone(Context context,String uri){
        Intent intent=new Intent(Intent.ACTION_DIAL);
        final Uri data=Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context,intent,null);
    }
}
