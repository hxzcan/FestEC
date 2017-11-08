package com.hx.latte.app.weiChat.templates.Http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.IError;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.weiChat.templates.LatterWechat;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:登录微信的
 */

public abstract class BaseWechatEntryActivity extends BaseWeChatAcitivity {
    //用户登录成功的回调
    public abstract void onSignSuccess(String usrInfo);

    //微信发送请求到第三方应用的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用fa发送请求到微信的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code=((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl=new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(LatterWechat.APP_ID)
                .append("&secret=")
                .append(LatterWechat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl){
        //网络请求登录微信
        RestClient.Builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        final JSONObject jsonObject= JSON.parseObject(response);
                        final String accessToken=jsonObject.getString("access_token");
                        final String openId=jsonObject.getString("openid");
                        final StringBuilder userUrl=new StringBuilder();
                        userUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");
                        getUserInfo(userUrl.toString());
                    }
                })
                .build()
                .get();;
    }

    /**
     * 获取用户的真正信息
     * @param userInfoUrl
     */
    private void getUserInfo(String userInfoUrl){
        RestClient.Builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        //抽象方法
                        onSignSuccess(response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
