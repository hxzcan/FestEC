package com.hx.latte.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.hx.latte.app.common.AccountManager;
import com.hx.latte.app.common.IUserChecker;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.ui.launcher.ILauncherListener;
import com.hx.latte.app.ui.launcher.LauncherFinishTag;
import com.hx.latte.app.ui.launcher.LauncherHolderCreator;
import com.hx.latte.app.ui.launcher.ScrollLauncherTag;
import com.hx.latte.app.utils.storage.LattePreference;
import com.hx.latte.ec.R;


import java.util.ArrayList;

/**
 * Created by hx on 2017/10/25 0025.
 * email:362970502@qq.com
 * des:滑动的启动页
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener{
    //轮播图
    private ConvenientBanner<Integer> mConvenientBanner=null;
    //存放轮播的图片
    private static final ArrayList<Integer> INTEGERS=new ArrayList<>();
    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        mConvenientBanner.setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }


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
        mConvenientBanner=new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    //这是点击的之后有效果，并不是滑动的最后一张
        @Override
   public void onItemClick(int position) {
            //如果滑动的是最后一张
        if (position==INTEGERS.size()-1){
            //是第一次进入就设置为true
            LattePreference.setAppFlag(ScrollLauncherTag.IS_FIRST_LAUNCHER_APP.name(),true);
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

}
