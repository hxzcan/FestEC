package com.hx.latte.app.common;

/**
 * Created by hx on 2017/10/27 0027.
 * email:362970502@qq.com
 * des:检查用户是否登录
 */

public interface IUserChecker {
    /**
     * 已登录
      */
    void onSignIn();

    /**
     * 未登录
     */
    void onNoSignIn();
}
