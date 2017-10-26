package com.hx.latte.app.net.rx;

import android.content.Context;

import com.hx.latte.app.net.HttpMethod;
import com.hx.latte.app.net.RestCreator;
import com.hx.latte.app.ui.loader.LatteLoader;
import com.hx.latte.app.ui.loader.LoaderStyles;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Observable;


/**
 * Created by hx on 2017/9/4 0004.
 * email:362970502@qq.com
 * des: 每次使用Builder的时候都会生成一个全新的实例
 * 里面的参数一次初始化完毕，不允许更改
 * 建造者模式
 */

public class RxRestClient {
    private final String URL;//请求地址
    private static final WeakHashMap<String,Object> PARAMS=RestCreator.getParams();//请求参数
    private final RequestBody BODY;//okHttp请求体
    private final File FILE;//上传文件
    private final LoaderStyles LOADERSTYLE;//加载进度条的样式
    private final Context CONTEXT;//上下文
    ;

    public RxRestClient(String url, WeakHashMap<String,Object> params,RequestBody body, File file
                        , LoaderStyles loaderStyles, Context context){
        this.URL=url;
        PARAMS.putAll(params);
        this.BODY=body;
        this.FILE=file;
        this.LOADERSTYLE=loaderStyles;
        this.CONTEXT=context;

    }

    public static RxRestClientBuilder Builder(){
        return new RxRestClientBuilder();
    }

    /**
     * 网络请求的处理
     * @param method
     */
    private Observable<String> request(HttpMethod method){
        final RxRestService service=RestCreator.getRxRestService();
        Observable<String> observable=null;


        if (LOADERSTYLE!=null){
            LatteLoader.showLoading(CONTEXT,LOADERSTYLE);
        }

        switch (method){
            case GET:
                observable=service.get(URL,PARAMS);
                break;
            case POST:
                observable=service.post(URL,PARAMS);
                break;
            case POST_RAW:
                observable=service.postRaw(URL,BODY);
                break;
            case PUT:
                observable=service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                observable=service.putRaw(URL,BODY);
                break;
            case DELETE:
                observable=service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody=RequestBody
                        .create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part part=MultipartBody.Part
                        .createFormData("file",FILE.getName(),requestBody);
                observable=service.upload(URL,part);
                break;
            default:
                break;
        }
        return observable;
    }



    /**
     * get请求
     */
    public final Observable<String> get(){
        return request(HttpMethod.GET);
    }

    /**
     * post请求分为带有参数的请求还是原始数据直接请求
     */
    public final Observable<String> post(){
        if (BODY==null){
            return request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("PARAMS is must null");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    /**
     * put请求
     */
    public final Observable<String> put(){
        if (BODY==null){
            return request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()){
                throw new RuntimeException("PARAMS is must null");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    /**
     * delete请求
     */
    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }

    /**
     * upload请求
     */
    public final Observable<String> upload(){
       return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download(){
       return RestCreator.getRxRestService().download(URL,PARAMS);
    }
}
