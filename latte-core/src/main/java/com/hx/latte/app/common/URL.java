package com.hx.latte.app.common;

/**
 * 网络请求地址
 * Created by hexiao on 2017/11/7.
 */

public class URL {
    //数据前缀
    public static final String URL_PRIX="http://192.168.201.160:8080/hx/";
    //图片请求前缀
    public static final String IMAGE_PRIX="http://192.168.201.160:8080/image/";
    //注册
    public static final String SIGN_UP=URL_PRIX+"user/register.do";
    //登陆
    public static final String SIGN_IN=URL_PRIX+"user/login.do";
    //首页商品展示
    public static final String INDEX_AD=URL_PRIX+"index/get_indexAd.do";

    //所有的父分类
    public static final String SORT_PARENT=URL_PRIX+"category/category_parentLevel.do";
    //二级分类 参数int parentLevelId
    public static final String SORT_SECOND=URL_PRIX+"category/category_childLevel.do";
}
