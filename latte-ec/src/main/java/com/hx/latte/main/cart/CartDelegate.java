package com.hx.latte.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.ec.R;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:购物车
 */

public class CartDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
