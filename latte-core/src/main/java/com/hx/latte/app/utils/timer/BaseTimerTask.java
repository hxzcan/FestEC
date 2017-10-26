package com.hx.latte.app.utils.timer;

import java.util.TimerTask;

/**
 * Created by hx on 2017/10/24 0024.
 * email:362970502@qq.com
 * des:定时器
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run() {
        //接口回调时要判断是否为空，避免错误
        if (mITimerListener!=null){
            mITimerListener.onTimer();
        }
    }
}
