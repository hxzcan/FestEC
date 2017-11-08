package com.hx.latte.app.weiChat.templates;


import com.hx.latte.app.weiChat.templates.Http.BaseWechatEntryActivity;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:
 */

public class WXEntryTemplate extends BaseWechatEntryActivity {



    @Override
    public void onSignSuccess(String userInfo) {
        LatterWechat.getInstance().getiWeChatSignCallBack().onSignInSuccess(userInfo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        //finish的时候不需要任何的动画效果
        overridePendingTransition(0,0);
    }
}
