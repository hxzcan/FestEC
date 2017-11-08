package com.hx.latte.app;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by hx on 2017/8/31 0031.
 * email:362970502@qq.com
 * des:final修饰不能被继承，主要一些静态的全局方法
 */

public final class Latte {

    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context);
        return Configurator.getInstance();
    }

    public  static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigures();
    }

    public static <T> T getConfiguration(Object key){
        return (T) getConfigurations().get(key);
    }
    /**
     * 获取全局的Context
     * @return
     */
    public static Context getApplication(){
        return (Context)getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

    /**
     * t弹出提示
     * @param msg 提示信息
     */
    public static void showToast(String msg){
        Toast.makeText(Latte.getApplication(),msg,Toast.LENGTH_SHORT).show();
    }


    public static Handler  getHandler(){
        return (Handler) getConfigurations().get(ConfigType.HANDLER.name());
    }

}
