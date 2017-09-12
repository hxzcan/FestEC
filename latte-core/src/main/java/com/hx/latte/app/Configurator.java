package com.hx.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by hx on 2017/8/31 0031.
 * email:362970502@qq.com
 * des:存储新的配置结构
 */

public class Configurator {
    //WeakHashMap在不使用的时候会及时回收,存储一些配置文件
    private static final HashMap<Object,Object> LATTE_CONFIGURE=new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();//存放字体样式
    private static  final ArrayList<Interceptor> INTERCEPTORS=new ArrayList<>();//存放拦截器

    //线程安全懒汉式
    private Configurator(){
        LATTE_CONFIGURE.put(ConfigType.CONFIG_READY.name(),false);//初始化还没有完成
    }

    public static  Configurator getInstance(){
        return  Holder.INSTANCE;
    }

    private static class Holder{
        private static final Configurator INSTANCE=new Configurator();
    }

    public final void config(){
        initIcons();//初始化字体图标
        LATTE_CONFIGURE.put(ConfigType.CONFIG_READY.name(),true);//配置文件准备好了
    }

    /**
     * 获取配置项内容
     * @return
     */
    public final HashMap<Object,Object> getLatteConfigures(){
        return LATTE_CONFIGURE;
    }

    /**
     * 配置网络请求的地址
     * @param host 192.168.1.1
     * @return
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGURE.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    /**
     * 检查配置是否完成
     */
    private void checkConfiguration(){
        final boolean isReady= (boolean) LATTE_CONFIGURE.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 获取配置项
     * @param key
     * @param <T> 泛型
     * @return
     */
    public final <T> T getConfiguration(Enum<ConfigType> key){
        //noinspection unchecked
        checkConfiguration();
        return (T) LATTE_CONFIGURE.get(key.name());
    }

    /**
     * 初始化字体
     */
    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer=Iconify.with(ICONS.get(0));
            for (int i = 1; i <ICONS.size() ; i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 配置字体
     * @param descriptor
     * @return
     */
    public final Configurator withIcons(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 配置过滤器
     * @param interceptor
     * @return
     */
   public final Configurator withInterceptor(Interceptor interceptor){
       INTERCEPTORS.add(interceptor);
       LATTE_CONFIGURE.put(ConfigType.INTERCEPTORS.name(),INTERCEPTORS);
       return this;
   }

    /**
     * 配置过滤器
     * @param interceptors 多个过滤器
     * @return
     */
   public final Configurator withInterceptor(ArrayList<Interceptor> interceptors){
       INTERCEPTORS.addAll(interceptors);
       LATTE_CONFIGURE.put(ConfigType.INTERCEPTORS.name(),INTERCEPTORS);
       return this;
   }
}
