package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:绕过微信的限制
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    String packageName();
    Class<?> entryTemplete();
}
