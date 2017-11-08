package com.hx.latte.app.weiChat.templates;

import android.app.Activity;

import com.hx.latte.app.ConfigType;
import com.hx.latte.app.Latte;
import com.hx.latte.app.weiChat.templates.callBack.IWeChatSignCallBack;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:
 */

public class LatterWechat {
    public static final String APP_ID= Latte.getConfiguration(ConfigType.WECHAT_APPID.name());
    public static final String APP_SECRET=Latte.getConfiguration(ConfigType.WECHAT_SECREAT.name());
    private final IWXAPI iwxapi;
    private IWeChatSignCallBack iWeChatSignCallBack=null;
    private static final class Holder{
        private static final LatterWechat latterWechat=new LatterWechat();
    }

    public static LatterWechat getInstance(){
        return Holder.latterWechat;
    }

    private  LatterWechat() {
        final Activity activity=Latte.getConfiguration(ConfigType.ACTIVITY.name());
        iwxapi= WXAPIFactory.createWXAPI(activity,APP_ID,true);
        iwxapi.registerApp(APP_ID);
    }

    public final IWXAPI getIwxapi() {
        return iwxapi;
    }

    public IWeChatSignCallBack getiWeChatSignCallBack() {
        return iWeChatSignCallBack;
    }
    public LatterWechat onSignSuccess(IWeChatSignCallBack callBack){
        this.iWeChatSignCallBack=callBack;
        return this;
    }

    public final void signIn(){
        final SendAuth.Req req=new SendAuth.Req();
        req.scope="snsapi_userinfo";
        req.state="random_state";
        iwxapi.sendReq(req);
    }


}
