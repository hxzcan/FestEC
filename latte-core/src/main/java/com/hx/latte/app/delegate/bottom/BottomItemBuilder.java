package com.hx.latte.app.delegate.bottom;

import java.util.LinkedHashMap;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:把底部和对应的fragment内容连接起来
 * 建造者模式
 */

public final class BottomItemBuilder {
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS=new LinkedHashMap<>();

    static BottomItemBuilder builder(){
        return new BottomItemBuilder();
    }

    public final BottomItemBuilder addItem(BottomTabBean bean, BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }

    public final BottomItemBuilder addItem(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}
