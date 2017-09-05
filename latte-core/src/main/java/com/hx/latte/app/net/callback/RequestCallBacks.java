package com.hx.latte.app.net.callback;


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
    public RequestCallBacks( IRequest request, ISuccess success,IError error, IFailure failure){
        this.REQUEST=request;
        this.SUCCESSFUL=success;
        this.ERROR=error;
        this.FAILURE=failure;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE!=null){
            FAILURE.onFailure(t.getMessage());
        }
        if (REQUEST!=null){
            REQUEST.onRequestEnd();
        }
    }
}
