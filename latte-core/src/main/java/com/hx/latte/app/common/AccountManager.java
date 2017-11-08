package com.hx.latte.app.common;

import com.hx.latte.app.utils.storage.LattePreference;

/**
 * Created by hx on 2017/10/27 0027.
 * email:362970502@qq.com
 * des:管理用户信息
 */

public class AccountManager {
    public enum SignTag{
        SIGN_IN
    }

    /**
     * 设置登录状态
     * @param state
     */
    public static void setSignIn(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_IN.name(),state);
    }

    /**
     * 是否登录
     * @return
     */
    public static boolean isSign(){
        return LattePreference.getAppFlag(SignTag.SIGN_IN.name());
    }

    /**
     * 检查是否登录执行不同的回调
     * @param iUserChecker
     */
    public static void checkCount(IUserChecker iUserChecker){
        if (isSign()){
            //已经登录过
            iUserChecker.onSignIn();
        }else {
            //未登陆过
            iUserChecker.onNoSignIn();
        }
    }
}
