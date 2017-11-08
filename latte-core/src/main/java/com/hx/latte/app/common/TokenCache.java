package com.hx.latte.app.common;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hx on 2017/11/3 0003.
 * email:362970502@qq.com
 * des:
 */

public class TokenCache {

    //初始化cache
   public static LoadingCache<String,Object> localCache=CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .maximumSize(10000)
            .expireAfterWrite(24, TimeUnit.HOURS)
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                    return "null";
                }
            });


    public static void setKey(String key,Object value){
        localCache.put(key,value);
    }

    public static Object getValue(String key){
        Object value=null;
        try {
            value=localCache.get(key);
            if (("null").equals(value)){
                return null;
            }else {
                return value;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
