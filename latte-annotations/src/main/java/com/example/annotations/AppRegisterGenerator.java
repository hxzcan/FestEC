package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:
 * @Target(ElementType.TYPE)//告诉编译器是用在类上的
 * @Retention(RetentionPolicy.SOURCE)//告诉编译器在处理的时候是在编译源码的时候处理
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface AppRegisterGenerator {

    String packageName();

    Class<?> registerTemplete();
}
