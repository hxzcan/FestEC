package com.hx.festec;

import android.app.Application;

import com.hx.latte.app.Latte;
import com.hx.latte.ec.icon.FontModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by hx on 2017/8/31 0031.
 * email:362970502@qq.com
 * des:
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withApiHost("http://127.0.0.1")//请求网址
                .withIcons(new FontAwesomeModule())//字体图标
                .withIcons(new FontModule())
                .config();
    }
}
