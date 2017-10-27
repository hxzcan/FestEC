package com.hx.latte.app.ui.launcher;

/**
 * Created by hx on 2017/10/27 0027.
 * email:362970502@qq.com
 * des:登录接口
 */

public interface ILauncherListener {
    /**
     * 登录的状态，是已经登录进去还是没有登录进去
     */
    void onLauncherFinish(LauncherFinishTag tag);
}
