package com.hx.latte.app.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by hx on 2017/9/5 0005.
 * email:362970502@qq.com
 * des:以一种缓存的方式来创建load，不需要每次使用load的时候都反射一次，可以节省性能
 */

public class LoaderCreator {

    private static final WeakHashMap<String,Indicator> LOADING_MAP=new WeakHashMap<>();
    static AVLoadingIndicatorView create(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView=new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type)==null){
            Indicator indicator=getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    /**
     * 以反射的形式加载进度条
     * @param name 进度条的名字
     * @return
     */
    private static Indicator getIndicator(String name){
        if (name==null||name.isEmpty()){
            return  null;
        }
        final StringBuilder drawClassName=new StringBuilder();
        if (!name.contains(".")){
            //通过反射来进行拼接
            final String defaultPackageName=AVLoadingIndicatorView.class.getPackage().getName();
            drawClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawClassName.append(name);
        try {
            final Class<?> drawableClass=Class.forName(drawClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
