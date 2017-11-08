package com.hx.latte.app.pojo;

import java.io.Serializable;

/**
 * Created by hx on 2017/10/27 0027.
 * email:362970502@qq.com
 * des:返回信息的结构
 */

public class CommonResponse<T> implements Serializable{
    private int status;
    private String msg;
    private T data;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}
