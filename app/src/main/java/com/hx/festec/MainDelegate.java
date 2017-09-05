package com.hx.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hx.latte.app.Latte;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.IError;
import com.hx.latte.app.net.callback.IFailure;
import com.hx.latte.app.net.callback.ISuccess;

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
        RestClient.Builder()
                .url("http://news.baidu.com/")
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
                .get();
    }

}
