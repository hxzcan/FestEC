package com.hx.festec;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.hx.latte.app.activity.ProxyActivity;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.launcher.LauncherDelegate;
import com.hx.latte.launcher.LauncherScrollDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar=getSupportActionBar();
        //隐藏标题栏
        if (actionBar!=null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
