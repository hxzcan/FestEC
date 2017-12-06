package com.hx.latte.main.cart;


import java.math.BigDecimal;

/**
 * Created by hexiao on 2017/12/1.
 * 当点击的时候会改变这个状,说明改变状态了，要把全选设为非全选状态
 */

public interface IChanged {
    /**
     * 点击了按钮
     * @param isChanged
     */
    void setChange(boolean isChanged);

    /**
     * 单个商品的总价格
     * @param singleTotalPrice
     * @param plusOrMinus 0:代表是加，1：代表是减
     */
    void setPrice(BigDecimal singleTotalPrice,Integer plusOrMinus);

    /**
     * 选择的数量
     * @param plusOrMinus
     */
    void setSelectCount(Integer plusOrMinus);
}
