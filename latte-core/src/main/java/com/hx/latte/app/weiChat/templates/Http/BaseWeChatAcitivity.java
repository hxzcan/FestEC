package com.hx.latte.app.weiChat.templates.Http;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hx.latte.app.weiChat.templates.LatterWechat;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:微信登录
 */

public abstract class BaseWeChatAcitivity extends AppCompatActivity implements IWXAPIEventHandler {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        LatterWechat.getInstance().getIwxapi().handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        LatterWechat.getInstance().getIwxapi().handleIntent(getIntent(),this);
    }

}
