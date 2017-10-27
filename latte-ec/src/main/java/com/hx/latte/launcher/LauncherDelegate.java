package com.hx.latte.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.hx.latte.app.common.AccountManager;
import com.hx.latte.app.common.IUserChecker;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.ui.launcher.ILauncherListener;
import com.hx.latte.app.ui.launcher.LauncherFinishTag;
import com.hx.latte.app.ui.launcher.ScrollLauncherTag;
import com.hx.latte.app.utils.storage.LattePreference;
import com.hx.latte.app.utils.timer.BaseTimerTask;
import com.hx.latte.app.utils.timer.ITimerListener;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by hx on 2017/10/24 0024.
 * email:362970502@qq.com
 * des:启动页
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcer_timer)
    AppCompatTextView appCompatTextView;

    private Timer mTimer=null;//定义的定时器
    private int mCount=5;//倒计时

    private ILauncherListener iLauncherListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            iLauncherListener= (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer(){
        mTimer=new Timer();
        final BaseTimerTask timerTask=new BaseTimerTask(this);
        mTimer.schedule(timerTask,0,1000);
    }

    @OnClick(R2.id.tv_launcer_timer)
    public void onClick(){
        if (mTimer!=null){
            mTimer.cancel();
            mTimer=null;
            checkShowScroll();
        }
    }

    //是否展示启动滚动页面
    private void checkShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.IS_FIRST_LAUNCHER_APP.name())){
            AccountManager.setSignIn(false);
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登陆了app
            AccountManager.checkCount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (iLauncherListener!=null){
                        iLauncherListener.onLauncherFinish(LauncherFinishTag.SIGNED_IN);
                    }
                }

                @Override
                public void onNoSignIn() {
                    if (iLauncherListener!=null){
                        iLauncherListener.onLauncherFinish(LauncherFinishTag.UNSIGNED_IN);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        //计时器
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (appCompatTextView!=null){
                    appCompatTextView.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer!=null){
                            mTimer.cancel();
                            mTimer=null;
                            checkShowScroll();
                        }
                    }
                }
            }
        });
    }
}
