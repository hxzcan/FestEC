package com.hx.latte.app.delegate.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by hexiao on 2017/11/28.
 * 用来与原生进行交互
 */

public class LatteWebInterface {
    private WebDelegate webDelegate;

    public LatteWebInterface(WebDelegate webDelegate) {
        this.webDelegate = webDelegate;
    }

    public static LatteWebInterface creator(WebDelegate webDelegate){
        return new LatteWebInterface(webDelegate);
    }

    /**
     * js调用的返回值
     * @param params
     */
    public String event(String params){
        String action= JSON.parseObject(params).getString("action");
        return null;
    }
}
