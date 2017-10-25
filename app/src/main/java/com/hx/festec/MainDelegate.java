package com.hx.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hx.latte.app.Latte;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.RestCreator;
import com.hx.latte.app.net.callback.IError;
import com.hx.latte.app.net.callback.IFailure;
import com.hx.latte.app.net.callback.ISuccess;

import java.util.WeakHashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hx on 2017/9/3 0003.
 * email:362970502@qq.com
 * des:这是一个fragment
 */

public class MainDelegate extends LatteDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testHttp();
    }

    public void testHttp(){
       /* RestClient.Builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        Latte.showToast(response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Latte.showToast(msg);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(String msg) {
                        Latte.showToast(msg);
                    }
                })
                .build()
                .get();*/

        RestCreator.getRxRestService().get("",new WeakHashMap<String,Object >())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });
    }

}
