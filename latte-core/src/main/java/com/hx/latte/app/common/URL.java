package com.hx.latte.app.common;

/**
 * 网络请求地址
 * Created by hexiao on 2017/11/7.
 */

public class URL {
    //数据前缀
    public static final String URL_PRIX="http://192.168.201.174:8080/hx/";
    //图片请求前缀
    public static final String IMAGE_PRIX="http://192.168.201.174:8080/image/";

    //----------------------------------登陆 注册----------------------------------//
    //注册
    public static final String SIGN_UP=URL_PRIX+"user/register.do";
    //登陆
    public static final String SIGN_IN=URL_PRIX+"user/login.do";

    //-------------------------------------首页--------------------------------//
    //首页商品展示
    public static final String INDEX_AD=URL_PRIX+"index/get_indexAd.do";

    //----------------------------------分类---------------------------------//
    //所有的父分类
    public static final String SORT_PARENT=URL_PRIX+"category/category_parentLevel.do";
    //二级分类 参数int parentLevelId
    public static final String SORT_SECOND=URL_PRIX+"category/category_childList.do";

    //--------------------购物车------------------------//
    //购物车的所有商品 参数 appToken；userId
    public static final String CART_GOODS_LIST=URL_PRIX+"cart/get_productFromCart.do";
    //全选 参数 appToken；userId
    public static final String CART_SELECT_ALL=URL_PRIX+"select_all.do";
    //全不选 参数 appToken；userId
    public static final String CART_NUSELECT_ALL=URL_PRIX+"unSelect_all.do";
    //单个选中 参数 appToken；userId;productId
    public static final String CART_SELECT_SINGLE=URL_PRIX+"select_single.do";
    //单个取消选中 参数 appToken；userId;productId
    public static final String CART_SELECT_UNSINGLE=URL_PRIX+"unSelect_single.do";
    //添加数量 参数appToken；userId;count;productId
    public static final String CART_ADD_GOOD=URL_PRIX+"update_product2Cart.do";
    //删除购物车的商品，一个或者多个 参数 appToken；userId;productIds
    public static final String CART_DELETE_GOODS=URL_PRIX+"delete_productFromCart.do";
    //清空购物车
    public static final String CART_CLEAR_ALL=URL_PRIX+"";

}
