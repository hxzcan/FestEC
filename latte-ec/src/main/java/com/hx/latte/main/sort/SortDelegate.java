package com.hx.latte.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hx.latte.app.Latte;
import com.hx.latte.app.common.IShowMessage;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.pojo.CommonResponse;
import com.hx.latte.ec.R;
import com.hx.latte.main.sort.leftList.SortLeftListDelegate;
import com.hx.latte.main.sort.rightContent.SortRightContentDelegate;
import com.hx.latte.pojo.SortBean;

import java.util.List;

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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        SortLeftListDelegate sortLeftListDelegate=new SortLeftListDelegate();
        getSupportDelegate().loadRootFragment(R.id.sort_left_content,sortLeftListDelegate);
        getSupportDelegate().loadRootFragment(R.id.sort_right_content,SortRightContentDelegate.newInstance(1));
    }
}
