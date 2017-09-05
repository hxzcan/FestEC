package com.hx.latte.app.net.callback;


import android.os.Handler;

import com.hx.latte.app.Latte;
import com.hx.latte.app.ui.LatteLoader;
import com.hx.latte.app.ui.LoaderStyles;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hx on 2017/9/4 0004.
 * email:362970502@qq.com
 * des:请求的回调
 */

public class RequestCallBacks implements Callback<String>{
    private final IRequest REQUEST;//请求
    private final ISuccess SUCCESSFUL;//请求成功
    private final IError ERROR;//请求出错
    private final IFailure FAILURE;//请求失败
    private final LoaderStyles LOADERSTYLE;
    //Handler尽量声明成static类型，避免内存泄漏
    private static final Handler HANDLER=new Handler();
    public RequestCallBacks( IRequest request, ISuccess success,IError error, IFailure failure
    ,LoaderStyles loaderStyles){
        this.REQUEST=request;
        this.SUCCESSFUL=success;
        this.ERROR=error;
        this.FAILURE=failure;
        this.LOADERSTYLE=loaderStyles;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESSFUL!=null){
                    SUCCESSFUL.onSuccessful(response.body());
                }
            }
        }else {
            if (ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }
       stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null){
            FAILURE.onFailure(t.getMessage());
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void  stopLoading(){
        //延迟1秒进度条消失
        if (LOADERSTYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }
}
