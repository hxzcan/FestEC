package com.hx.latte.app.net;

import com.hx.latte.app.ConfigType;
import com.hx.latte.app.Configurator;
import com.hx.latte.app.Latte;
import com.hx.latte.app.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hx on 2017/9/4 0004.
 * email:362970502@qq.com
 * des：初始化网络请求
 */

public class RestCreator {


    /**
     * 构建全局的Retrofit
     */
    public static final class RetrofitHolder{
        public static  final  String BASE_URL= (String) Latte.getConfigurations()
                .get(ConfigType.API_HOST.name());
        public static  final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHodler.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * 构建okHttp
     */
    public static final class OkHttpHodler{
        public static final int TIME_OUT=60;
        public static final OkHttpClient.Builder BUILDER=new OkHttpClient.Builder();
        public static final ArrayList<Interceptor> INTERCEPTORS= Latte.getConfiguration(
                ConfigType.INTERCEPTORS);
        //向okhttp中添加拦截器
        private static OkHttpClient.Builder addInterceptors(){
           if (INTERCEPTORS!=null&&INTERCEPTORS.isEmpty()){
               for (Interceptor interceptor : INTERCEPTORS) {
                BUILDER.addInterceptor(interceptor);
               }
           }
           return BUILDER;
        }
        //把 OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()改为如下
        public static final OkHttpClient OK_HTTP_CLIENT=addInterceptors()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * service接口
     */
    public static final class RestServiceHolder{
        public static final RestService REST_SERVICE=RetrofitHolder
                .RETROFIT_CLIENT
                .create(RestService.class);

    }

    public static RestService getRestService(){
        return  RestServiceHolder.REST_SERVICE;
    }


    /**
     * service接口
     */
    public static final class RxRestServiceHolder{
        public static final RxRestService REST_SERVICE=RetrofitHolder
                .RETROFIT_CLIENT
                .create(RxRestService.class);

    }

    public static RxRestService getRxRestService(){
        return  RxRestServiceHolder.REST_SERVICE;
    }
    /**
     * 初始化params,在RestClientBuilder中使用
     */
    public static final class ParamsHodler{
       public static final WeakHashMap<String,Object> PARMAS=new WeakHashMap<>();
    }

    /**
     * 获取Params
     * @return
     */
    public static WeakHashMap<String,Object> getParams(){
        return ParamsHodler.PARMAS;
    }
}
