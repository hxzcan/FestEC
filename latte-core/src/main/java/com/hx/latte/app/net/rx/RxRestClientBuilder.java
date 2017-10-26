package com.hx.latte.app.net.rx;

import android.content.Context;

import com.hx.latte.app.net.RestCreator;
import com.hx.latte.app.ui.loader.LoaderStyles;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by hx on 2017/9/4 0004.
 * email:362970502@qq.com
 * des:建造者模式 对应着RestClient
 */

public class RxRestClientBuilder {
    private  String mUrl=null;//请求地址
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();//请求参数
    private  RequestBody mBody=null;//okHttp请求体
    private File mFile=null;
    private LoaderStyles mLoaderStyls=null;//进度条的样式
    private Context mContext=null;//上下文
    RxRestClientBuilder(){

    }

    /**
     * 请求地址
     * @param mUrl
     * @return
     */
    public final RxRestClientBuilder url(String mUrl){
        this.mUrl=mUrl;
        return this;
    }

    /**
     * 请求参数方法
     * @param mParams 请求参数
     * @return
     */
    public final RxRestClientBuilder params(WeakHashMap<String,Object> mParams){
        this.PARAMS.putAll(mParams);
        return this;
    }

    /**
     * 请求参数方法，以键值对形式传入
     * @param key 键
     * @param value 值
     * @return
     */
    public final RxRestClientBuilder params(String key, Object value){
        this.PARAMS.put(key,value);
        return  this;
    }

    /**
     * 传入的是原始数据，转化为json格式
     * @param string
     * @return
     */
    public  final RxRestClientBuilder raw(String string){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),string);
        return this;
    }


    public final RxRestClientBuilder file(File file){
        this.mFile=file;
        return this;
    }

    public final RxRestClientBuilder file(String filePath){
        this.mFile=new File(filePath);
        return this;
    }

    public final RxRestClientBuilder loader(LoaderStyles loaderStyles, Context context){
        this.mLoaderStyls=loaderStyles;
        this.mContext=context;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoaderStyls=LoaderStyles.BallClipRotateIndicator;
        return this;
    }

    public final RxRestClient build(){
        return  new RxRestClient(mUrl,PARAMS,mBody,mFile,mLoaderStyls,mContext);
    }
}
