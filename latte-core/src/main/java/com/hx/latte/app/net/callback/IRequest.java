package com.hx.latte.app.net.callback;

/**
 * Created by hx on 2017/9/4 0004.
 * email:362970502@qq.com
 * des:请求开始和请求结束的回调
 */

public interface IRequest {
    /**
     * 请求开始
     */
    void onRequestStart();

    /**
     * 请求结束
     */
    void onRequestEnd();
}
