package com.hx.festec;


import com.hx.latte.app.activity.ProxyActivity;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.launcher.LauncherDelegate;

public class MainActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
