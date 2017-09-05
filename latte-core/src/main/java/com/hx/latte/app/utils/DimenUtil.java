package com.hx.latte.app.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.hx.latte.app.Latte;

/**
 * Created by hx on 2017/9/5 0005.
 * email:362970502@qq.com
 * des:测量工具
 */

public class DimenUtil {

    /**
     * 获取屏幕的宽
     * @return
     */
    public static int getScreenWidth(){
        //获取到资源
        final Resources resources= Latte.getApplication().getResources();
        final DisplayMetrics metrics=resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     * @return
     */
    public static int getScreenHeight(){
        final Resources resources=Latte.getApplication().getResources();
        final DisplayMetrics metrics=resources.getDisplayMetrics();
        return  metrics.heightPixels;
    }
}
