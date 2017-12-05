package com.hx.latte.main.cart.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;


import java.math.BigDecimal;

/**
 * Created by hexiao on 2017/11/30.
 * 购物车中的商品信息
 */

public class GoodBean implements MultiItemEntity {
    private Integer cartId;//购物车的id

    private Integer userId;//用户id

    private Integer productId;//商品id

    private Integer quantity;//购物车中数量

    private Integer checked;//是否勾选

    private String productName;//产品名

    private String productSubtitle;//标题

    private String productMainImage;//主图

    private BigDecimal productPrice;//单个价格

    private BigDecimal productTotalPrice;//总价

    private Integer productStock;//库存

    private Integer productStatus;

    private String limitQuantity;//限制商品数量的返回结果

    private Integer mItemType=1;//根据这个来设置布局的样式

    private Integer adapterPosition=0;//在适配器中的位置

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSubtitle() {
        return productSubtitle;
    }

    public void setProductSubtitle(String productSubtitle) {
        this.productSubtitle = productSubtitle;
    }

    public String getProductMainImage() {
        return productMainImage;
    }

    public void setProductMainImage(String productMainImage) {
        this.productMainImage = productMainImage;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }


    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    @Override
    public int getItemType() {
        return mItemType;
    }

    public void setmItemType(Integer mItemType) {
        this.mItemType = mItemType;
    }

    public Integer getAdapterPosition() {
        return adapterPosition;
    }

    public void setAdapterPosition(Integer adapterPosition) {
        this.adapterPosition = adapterPosition;
    }

    @Override
    public String toString() {
        return "GoodBean{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", checked=" + checked +
                ", productName='" + productName + '\'' +
                ", productSubtitle='" + productSubtitle + '\'' +
                ", productMainImage='" + productMainImage + '\'' +
                ", productPrice=" + productPrice +
                ", productTotalPrice=" + productTotalPrice +
                ", productStock=" + productStock +
                ", productStatus=" + productStatus +
                ", limitQuantity='" + limitQuantity + '\'' +
                ", mItemType=" + mItemType +
                ", adapterPosition=" + adapterPosition +
                '}';
    }
}
