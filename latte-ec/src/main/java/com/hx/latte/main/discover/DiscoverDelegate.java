package com.hx.latte.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.ec.R;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:发现
 */

public class DiscoverDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
