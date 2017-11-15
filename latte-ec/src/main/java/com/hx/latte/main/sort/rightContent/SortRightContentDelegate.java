package com.hx.latte.main.sort.rightContent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.ec.R;

/**
 * Created by hexiao on 2017/11/14.
 * 右侧的加载分类内容的fragment
 */

public class SortRightContentDelegate extends LatteDelegate {
    private static final String CONTENT_ID="CONTENT_ID";
    private int defaultContentId=-1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if (bundle!=null){
            defaultContentId=bundle.getInt(CONTENT_ID);
        }
    }

    public static SortRightContentDelegate newInstance(int contentId){
        Bundle bundle=new Bundle();
        bundle.putInt(CONTENT_ID,contentId);
        SortRightContentDelegate sortRightContentDelegate=new SortRightContentDelegate();
        sortRightContentDelegate.setArguments(bundle);
        return sortRightContentDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_right_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
