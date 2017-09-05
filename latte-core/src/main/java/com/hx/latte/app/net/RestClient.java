package com.hx.latte.app.net;

import android.content.Context;

import com.hx.latte.app.net.callback.IError;
import com.hx.latte.app.net.callback.IFailure;
import com.hx.latte.app.net.callback.IRequest;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.net.callback.RequestCallBacks;
import com.hx.latte.app.ui.LatteLoader;
import com.hx.latte.app.ui.LoaderStyles;


import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by hx on 2017/9/4 0004.
 * email:362970502@qq.com
 * des: 每次使用Builder的时候都会生成一个全新的实例
 * 里面的参数一次初始化完毕，不允许更改
 * 建造者模式
 */

public class RestClient {
    private final String URL;//请求地址
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();//请求参数
    private final IRequest REQUEST;//请求
    private final ISuccess SUCCESSFUL;//请求成功
    private final IError ERROR;//请求出错
    private final IFailure FAILURE;//请求失败
    private final RequestBody BODY;//okHttp请求体
    private final File FILE;//上传文件
    private final LoaderStyles LOADERSTYLE;//加载进度条的样式
    private final Context CONTEXT;//上下文


    public RestClient(String url,WeakHashMap<String,Object> params,IRequest request,ISuccess success,
                        IError error, IFailure failure,RequestBody body,File file
                        ,LoaderStyles loaderStyles,Context context){
        this.URL=url;
        PARAMS.putAll(params);
        this.REQUEST=request;
        this.SUCCESSFUL=success;
        this.ERROR=error;
        this.FAILURE=failure;
        this.BODY=body;
        this.FILE=file;
        this.LOADERSTYLE=loaderStyles;
        this.CONTEXT=context;
    }

    public static RestClientBuilder Builder(){
        return new RestClientBuilder();
    }

    /**
     * 网络请求的处理
     * @param method
     */
    private void request(HttpMethod method){
        final RestService service=RestCreator.getRestService();
        Call<String> call=null;

        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
        if (LOADERSTYLE!=null){
            LatteLoader.showLoading(CONTEXT,LOADERSTYLE);
        }

        switch (method){
            case GET:
                call=service.get(URL,PARAMS);
                break;
            case POST:
                call=service.post(URL,PARAMS);
                break;
            case POST_RAW:
                call=service.postRaw(URL,BODY);
                break;
            case PUT:
                call=service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call=service.putRaw(URL,BODY);
                break;
            case DELETE:
                call=service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody=RequestBody
                        .create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part part=MultipartBody.Part
                        .createFormData("file",FILE.getName(),requestBody);
                call=service.upload(URL,part);
                break;
            default:
                break;
        }
        if (call!=null){
            //这是在子线程中进行网络操作；
            // call.execute()是在主线程中，要另起一个子线程进行操作
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallBacks(REQUEST,SUCCESSFUL,ERROR,FAILURE,LOADERSTYLE);
    }

    /**
     * get请求
     */
    public final void get(){
        request(HttpMethod.GET);
    }

    /**
     * post请求分为带有参数的请求还是原始数据直接请求
     */
    public final void post(){
        if (BODY==null){
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("PARAMS is must null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    /**
     * put请求
     */
    public final void put(){
        if (BODY==null){
            request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("PARAMS is must null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    /**
     * delete请求
     */
    public final void delete(){
        request(HttpMethod.DELETE);
    }

    /**
     * upload请求
     */
    public final void upload(){
        request(HttpMethod.UPLOAD);
    }
}
