package com.hx.latte.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.hx.latte.R;
import com.hx.latte.app.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by hx on 2017/9/3 0003.
 * email:362970502@qq.com
 * des:activity，包含fragment
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContains(savedInstanceState);
    }

    private void initContains(@Nullable Bundle savedInstanceState){
        //V7包中的fragment
        final ContentFrameLayout contentFrameLayout=new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_container);
        setContentView(contentFrameLayout);
        if (savedInstanceState==null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //垃圾回收，但是不一定执行
        System.gc();
        System.runFinalization();
    }
}
