package com.hx.latte.main.cart.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hexiao on 2017/11/30.
 * 进一步的封装
 */

public class GoodBeanVo {

    private List<GoodBean> cartProductVoList;
    private BigDecimal cartTotalPrice;
    private Boolean allCheck;//是否全勾选
    private String imageHost;//访问图片的地址

    public List<GoodBean> getmGoodBeanVo() {
        return cartProductVoList;
    }

    public void setmGoodBeanVo(List<GoodBean> mGoodBeanVo) {
        this.cartProductVoList = mGoodBeanVo;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Boolean getAllCheck() {
        return allCheck;
    }

    public void setAllCheck(Boolean allCheck) {
        this.allCheck = allCheck;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    @Override
    public String toString() {
        return "GoodBeanVo{" +
                "cartProductVoList=" + cartProductVoList +
                ", cartTotalPrice=" + cartTotalPrice +
                ", allCheck=" + allCheck +
                ", imageHost='" + imageHost + '\'' +
                '}';
    }
}
