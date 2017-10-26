package com.hx.latte.app.net.interceptors;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by hx on 2017/9/12 0012.
 * email:362970502@qq.com
 * des:拦截器
 */

public abstract class BaseInterceptor implements Interceptor {


    /**
     * get请求网络请求的队列
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getUrlParameter(Chain chain){
        HttpUrl url=chain.request().url();
        int size=url.querySize();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        for (int i=0;i<size;i++){
            params.put(url.queryParameterValue(i),url.queryParameterValue(i));
        }
        return params;
    }

    /**
     * get请求拦截器方法的重载
     * @param chain
     * @param key
     * @return
     */
    protected String getUrlParameter(Chain chain,String key){
        return chain.request().url().queryParameter(key);
    }

    /**
     * post请求的
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
       final FormBody body= (FormBody) chain.request().body();
        int size=body.size();
        final LinkedHashMap<String,String> params=new LinkedHashMap<>();
        for (int i=0;i<size;i++){
            params.put(body.name(i),body.value(i));
        }
        return params;
    }

    /**
     * post请求拦截器方法的重载
     * @param chain
     * @param key
     * @return
     */
    protected String getBodyParameters(Chain chain,String key){
        return getUrlParameter(chain).get(key);
    }
}
