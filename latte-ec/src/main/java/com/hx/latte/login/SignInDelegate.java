package com.hx.latte.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hx.latte.app.common.AccountManager;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.common.IShowMessage;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;
import com.hx.latte.pojo.CommonResponse;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hx on 2017/10/27 0027.
 * email:362970502@qq.com
 * des:登录页面
 */

public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.sign_in)
    AppCompatButton mSignInBtn;
    @BindView(R2.id.sign_in_username)
    TextInputEditText mEditUsername;
    @BindView(R2.id.sign_in_password)
    TextInputEditText mEditPassword;
    @BindView(R2.id.sign_in_not_have)
    AppCompatTextView mNotSignUp;
    @BindView(R2.id.sign_in_icon_other_method)
    IconTextView mIconTextView;

    private IShowMessage iShowMessage;

    //??????????
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IShowMessage){
            iShowMessage= (IShowMessage) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private boolean checkForm(){
        final String username=mEditUsername.getText().toString();
        final String password=mEditPassword.getText().toString();
        boolean isPass=true;
        if (username.isEmpty()){
            mEditUsername.setError("请输入用户名");
            isPass=false;
        }else {
            mEditUsername.setError(null);
        }
        if (password.isEmpty()||password.length()<6){
            mEditPassword.setError("请输入密码");
            isPass=false;
        }else {
            mEditPassword.setError(null);
        }
        return isPass;
    }

    //登录
    @OnClick(R2.id.sign_in)
    public void onClickSignIn(){
        if (checkForm()){
            RestClient.Builder()
                    .url("http://192.168.201.131:8080/mall/user/login.do")
                    .params("username",mEditUsername.getText().toString())
                    .params("password",mEditPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccessful(String response) {
                            Gson gson=new Gson();
                            CommonResponse commonResponse=gson.fromJson(response,
                                    new TypeToken<CommonResponse>(){}.getType());
                            if (commonResponse.getStatus()==0){
                               iShowMessage.showMessage(commonResponse.getMsg());
                                AccountManager.setSignIn(true);
                            }else if (commonResponse.getStatus()==1){
                                iShowMessage.showMessage(commonResponse.getMsg());
                            }
                        }
                    })
                    .build()
                    .post();
        }
    }

    //注册，跳转到注册
    @OnClick(R2.id.sign_in_not_have)
    public void onClickSignIn2Up(){
        start(new SignUpDelegate(),SINGLETASK);
    }

    //微信登录
    @OnClick(R2.id.sign_in_icon_other_method)
    public void onClickOtherMethod(){

    }

}