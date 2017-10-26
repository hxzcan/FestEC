package com.hx.latte.app.net.rx;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by hx on 2017/9/4 0004.
 * email:362970502@qq.com
 * des:网络请求服务，get,post,put,delete,download,upload
 */

public interface RxRestService {

    /**
     * get请求
     * @param url 网址
     * @param params 请求参数 Map<String,Object> key是参数名，value是参数内容
     * @return
     */
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * post请求
     * @FormUrlEncoded 这个Post氢气要加的
     * @param url 网址
     * @param params 请求参数 Map<String,Object> key是参数名，value是参数内容
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * 传入原始数据 请求参数为原始数据时不要加@FormUrlEncoded
     * @param url
     * @param requestBody
     * @return
     */
    @POST
    Observable<String > postRaw(@Url String url, @Body RequestBody requestBody);

    /**
     * put请求 和post请求类似
     * @FormUrlEncoded 这个put请求要加的
     * @param url 网址
     * @param params 请求参数 Map<String,Object> key是参数名，value是参数内容
     * @return
     */
    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> params);

    /**
     * get请求
     * @param url 网址
     * @param requestBody 原始数据
     * @return
     */
    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody requestBody);

    /**
     * delete请求 和get请求类似
     * @param url 网址
     * @param params 请求参数 Map<String,Object> key是参数名，value是参数内容
     * @return
     */

    @DELETE
    Observable<String>  delete(@Url String url, @QueryMap Map<String, Object> params);


    /**
     * download请求
     *  @Streaming 如果不加Streaming是把内容全部读取到内存再写入文件，如果内容过大会造成内存溢出
     *  加的话就是边读取边写入文件
     * @param url 网址
     * @param params 请求参数 Map<String,Object> key是参数名，value是参数内容
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);


    /**
     * download请求
     * @param url 网址
     * @param  file 上传的文件
     * @return
     */
    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);
}
