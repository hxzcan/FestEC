package com.hx.festec;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.hx.latte.app.activity.ProxyActivity;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.ui.launcher.ILauncherListener;
import com.hx.latte.app.ui.launcher.LauncherFinishTag;
import com.hx.latte.app.common.IShowMessage;
import com.hx.latte.launcher.LauncherDelegate;
import com.hx.latte.login.SignInDelegate;
import com.hx.latte.main.EcBottomDelegate;


public class MainActivity extends ProxyActivity implements IShowMessage,ILauncherListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (Build.VERSION.SDK_INT>21){//透明式状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
        //StatusBarUtil.setStatusBarColor(this,getResources().getColor(android.R.color.holo_orange_light));
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

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(LauncherFinishTag tag) {
        switch (tag){
            case SIGNED_IN:
                start(new EcBottomDelegate());
                break;
            case UNSIGNED_IN:
                start(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
