package com.hx.latte.app.net;

import com.hx.latte.app.net.callback.IError;
import com.hx.latte.app.net.callback.IFailure;
import com.hx.latte.app.net.callback.IRequest;
import com.hx.latte.app.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by hx on 2017/9/4 0004.
 * email:362970502@qq.com
 * des:建造者模式 对应着RestClient
 */

public class RestClientBuilder {
    private  String mUrl;//请求地址
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();//请求参数
    private  IRequest mIRequest;//请求
    private  ISuccess mISuccess;//请求成功
    private  IError mIError;//请求出错
    private  IFailure mIFailure;//请求失败
    private  RequestBody mBody;//okHttp请求体
    private MultipartBody.Part mPart;

    RestClientBuilder(){

    }

    /**
     * 请求地址
     * @param mUrl
     * @return
     */
    public final RestClientBuilder url(String mUrl){
        this.mUrl=mUrl;
        return this;
    }

    /**
     * 请求参数方法
     * @param mParams 请求参数
     * @return
     */
    public final RestClientBuilder params(WeakHashMap<String,Object> mParams){
        this.PARAMS.putAll(mParams);
        return this;
    }

    /**
     * 请求参数方法，以键值对形式传入
     * @param key 键
     * @param value 值
     * @return
     */
    public final RestClientBuilder params(String key,Object value){
        this.PARAMS.put(key,value);
        return  this;
    }

    /**
     * 传入的是原始数据，转化为json格式
     * @param string
     * @return
     */
    public  final RestClientBuilder raw(String string){
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),string);
        return this;
    }

    /**
     * 请求成功
     * @param mISuccess
     * @return
     */
    public final RestClientBuilder success(ISuccess mISuccess){
        this.mISuccess=mISuccess;
        return  this;
    }

    /**
     * 请求错误
     * @param mIError
     * @return
     */
    public final RestClientBuilder error(IError mIError){
        this.mIError=mIError;
        return  this;
    }

    /**
     * 请求失败
     * @param mIFailure
     * @return
     */
    public final RestClientBuilder failure(IFailure mIFailure){
        this.mIFailure=mIFailure;
        return  this;
    }

    /**
     * 请求开始和结束
     * @param mIRequest
     * @return
     */
    public final RestClientBuilder onRequest(IRequest mIRequest){
        this.mIRequest=mIRequest;
        return  this;
    }

    public final RestClientBuilder mulitPart(MultipartBody.Part part){
        this.mPart=part;
        return  this;
    }


    public final RestClient build(){
        return  new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIError,mIFailure,mBody,mPart);
    }
}
