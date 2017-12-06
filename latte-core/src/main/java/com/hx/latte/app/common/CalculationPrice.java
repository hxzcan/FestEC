package com.hx.latte.app.common;

import java.math.BigDecimal;

/**
 * Created by hexiao on 2017/12/5.
 * 计算价格
 */

public class CalculationPrice {

    /**
     * 相加
     * @return
     */
    public static BigDecimal add(Double a,Double b){
        BigDecimal b1=new BigDecimal(Double.toString(a));
        BigDecimal b2=new BigDecimal(Double.toString(b));
        return  b1.add(b2);
    }

    /**
     * 相减
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal substract(Double a,Double b){
        BigDecimal b1=new BigDecimal(Double.toString(a));
        BigDecimal b2=new BigDecimal(Double.toString(b));
        return  b1.subtract(b2);
    }

    /**
     * 相乘
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal multiply(Double a,Double b){
        BigDecimal b1=new BigDecimal(Double.toString(a));
        BigDecimal b2=new BigDecimal(Double.toString(b));
        return  b1.multiply(b2);
    }

    /**
     * 相除
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal divide(Double a,Double b){
        BigDecimal b1=new BigDecimal(Double.toString(a));
        BigDecimal b2=new BigDecimal(Double.toString(b));
        return  b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);//四舍五入
    }
}
