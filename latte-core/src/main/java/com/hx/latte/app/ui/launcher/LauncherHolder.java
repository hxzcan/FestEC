package com.hx.latte.app.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by hx on 2017/10/25 0025.
 * email:362970502@qq.com
 * des:轮播图的
 */

public class LauncherHolder implements Holder<Integer> {
    private AppCompatImageView mAppCompatImageView;
    @Override
    public View createView(Context context) {
        mAppCompatImageView=new AppCompatImageView(context);
        return mAppCompatImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        //每次滑动设置的图片资源
        mAppCompatImageView.setBackgroundResource(data);
    }
}
