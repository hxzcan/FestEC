package com.hx.latte.app.net.interceptors;

import android.support.annotation.RawRes;
import android.util.Log;

import com.hx.latte.app.utils.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by hx on 2017/9/12 0012.
 * email:362970502@qq.com
 * des:具体过滤器的实现
 */

public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.RAW_ID = rawId;
    }

    private Response getResponse(Chain chain,String json){
        return  new Response.Builder().
                code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("ok")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int RAW_ID){
        String json= FileUtil.getRawFile(RAW_ID);
        return getResponse(chain,json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url=chain.request().url().toString();
        //如果请求的包含了传入的url，得到json数据
        Log.i("XXXXXXXX",url);
        if (url.contains(DEBUG_URL)){
            return debugResponse(chain,RAW_ID);
        }else {//否则原样返回
            return chain.proceed(chain.request());
        }
    }


}
