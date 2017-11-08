package com.hx.latte.main;

import android.graphics.Color;

import com.hx.latte.app.delegate.bottom.BaseBottomDelegate;
import com.hx.latte.app.delegate.bottom.BottomItemBuilder;
import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.app.delegate.bottom.BottomTabBean;
import com.hx.latte.main.cart.CartDelegate;
import com.hx.latte.main.discover.DiscoverDelegate;
import com.hx.latte.main.index.IndexDelegate;
import com.hx.latte.main.personal.PersonalDelegate;
import com.hx.latte.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:动态加布局 底部导航栏
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(BottomItemBuilder itemBuilder) {
        LinkedHashMap<BottomTabBean,BottomItemDelegate> items=new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","首页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new CartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new PersonalDelegate());
        return itemBuilder.addItem(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
