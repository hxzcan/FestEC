package com.hx.latte.app.net.download;

import android.os.AsyncTask;

import com.hx.latte.app.net.RestCreator;
import com.hx.latte.app.net.callback.IError;
import com.hx.latte.app.net.callback.IFailure;
import com.hx.latte.app.net.callback.IRequest;
import com.hx.latte.app.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hx on 2017/9/11 0011.
 * email:362970502@qq.com
 * des:下载文件
 */

public class DownloadHandler {
    private final String URL;//请求地址
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();//请求参数
    private final IRequest REQUEST;//请求
    private final ISuccess SUCCESSFUL;//请求成功
    private final IError ERROR;//请求出错
    private final IFailure FAILURE;//请求失败
    private final String DOWNLOAD_DIR;//下载到的文件
    private final String EXTENSION;//后缀
    private final String NAME;//文件名

    public DownloadHandler(String url, IRequest resquest, ISuccess successful, IError error,
                           IFailure failure, String downloadDir, String extension, String name)
    {
        this.URL = url;
        this.REQUEST = resquest;
        this.SUCCESSFUL = successful;
        this.ERROR = error;
        this.FAILURE = failure;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public final void handleDownload(){
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                       if (response.isSuccessful()){
                           final ResponseBody body=response.body();//请求体
                           SaveFileTask saveFileTask=new SaveFileTask(REQUEST,SUCCESSFUL);
                           //线程池的方式执行 必须在主线程中实现执行线程的方法
                           // saveFileTask.execute();是以队列的方式执行
                           saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                   DOWNLOAD_DIR,EXTENSION,NAME,body);
                           //这里一定要判断文件是否下载不全
                           if (saveFileTask.isCancelled()){
                               if (REQUEST!=null){
                                   REQUEST.onRequestEnd();
                               }
                           }
                       }else {
                           if (ERROR != null) {
                               ERROR.onError(response.code(), response.message());
                           }
                       }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE!=null){
                            FAILURE.onFailure(t.getMessage());
                        }
                    }
                });
    }
}
