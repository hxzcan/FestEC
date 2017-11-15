package com.hx.latte.app.ui.recyclerView;

import com.google.auto.value.AutoValue;

/**
 * Created by hexiao on 2017/11/14.
 * 颜色值
 */

@AutoValue
public abstract class  RGBValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RGBValue createRgbVaule(int red,int green,int blue){
        return new AutoValue_RGBValue(red,green,blue);
    }
}
