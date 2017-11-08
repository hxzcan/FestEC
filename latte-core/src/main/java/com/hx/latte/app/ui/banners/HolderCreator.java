package com.hx.latte.app.ui.banners;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by hexiao on 2017/11/7.
 * 首页轮播图
 */

public class HolderCreator implements CBViewHolderCreator {
    @Override
    public Object createHolder() {
        return new ImageHolder();
    }
}
