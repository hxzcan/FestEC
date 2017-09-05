package com.hx.latte.app.net;

import com.hx.latte.app.ConfigType;
import com.hx.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hx on 2017/9/4 0004.
 * email:362970502@qq.com
 * des：初始化网络请求
 */

public class RestCreator {

    public static RestService getRestService(){
        return  RestServiceHolder.REST_SERVICE;
    }

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
                .build();
    }

    /**
     * 构建okHttp
     */
    public static final class OkHttpHodler{
        public static final int TIME_OUT=60;
        public static final OkHttpClient OK_HTTP_CLIENT=new OkHttpClient.Builder()
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
