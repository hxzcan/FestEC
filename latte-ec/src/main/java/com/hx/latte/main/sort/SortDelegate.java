package com.hx.latte.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.ec.R;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:分类
 */

public class SortDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
