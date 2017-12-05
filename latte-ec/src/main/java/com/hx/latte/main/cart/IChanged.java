package com.hx.latte.main.cart;



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

}
