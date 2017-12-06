package com.hx.latte.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hx.latte.app.common.AccountManager;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.pojo.CommonResponse;
import com.hx.latte.app.common.IShowMessage;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hx on 2017/10/26 0026.
 * email:362970502@qq.com
 * des:注册界面
 */

public class SignUpDelegate extends LatteDelegate{
    @BindView(R2.id.sign_up_username)
    TextInputEditText mEditUsername;
    @BindView(R2.id.sign_up_email)
    TextInputEditText mEditEmail;
    @BindView(R2.id.sign_up_phone)
    TextInputEditText mEditPhone;
    @BindView(R2.id.sign_up_password)
    TextInputEditText mEditPassword;
    @BindView(R2.id.sign_up_again_password)
    TextInputEditText mEditAgainPassword;
    @BindView(R2.id.sign_up_question)
    TextInputEditText mEditQuestion;
    @BindView(R2.id.sign_up_answer)
    TextInputEditText mEditAnswer;
    @BindView(R2.id.sign_up)
    AppCompatButton mSignUp;
    @BindView(R2.id.have_signUp)
    AppCompatTextView mSignIn;

    private IShowMessage iShowMessage;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IShowMessage){
            iShowMessage= (IShowMessage) activity;
        }
    }

    @Override
    public Object setLayout() {
       return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private boolean checkForm(){
        final String username=mEditUsername.getText().toString();
        final String email=mEditEmail.getText().toString();
        final String phone=mEditPhone.getText().toString();
        final String password=mEditPassword.getText().toString();
        final String againPassword=mEditAgainPassword.getText().toString();
        boolean isPass=true;
        if (username.isEmpty()){
            mEditUsername.setError("请输入用户名");
            isPass=false;
        }else {
            mEditUsername.setError(null);
        }
        //判断是否是邮箱
        if (email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEditEmail.setError("请输入正确的邮箱地址");
            isPass=false;
        }else {
            mEditEmail.setError(null);
        }
        //加上正则表达式验证手机号
        if (phone.isEmpty()||phone.length()!=11){
            mEditPhone.setError("手机号码格式错误");
            isPass=false;
        }else {
            mEditPhone.setError(null);
        }

        if (password.isEmpty()||password.length()<6){
            mEditPassword.setError("请输入密码");
            isPass=false;
        }else {
            mEditPassword.setError(null);
        }

        if (againPassword.isEmpty()){
            mEditAgainPassword.setError("请再次输入密码");
            isPass=false;
        }else if (!againPassword.equals(password)){
            mEditAgainPassword.setError("输入的密码不一致");
            isPass=false;
        }else {
            mEditAgainPassword.setError(null);
        }
        return isPass;
    }
    //注册
    @OnClick(R2.id.sign_up)
    public void onClickSignUp( ){
        if (checkForm()){
            RestClient.Builder().url(URL.SIGN_UP)
                    .params("username",mEditUsername.getText().toString())
                    .params("password",mEditPassword.getText().toString())
                    .params("email",mEditEmail.getText().toString())
                    .params("phone",mEditPhone.getText().toString())
                    .params("question",mEditQuestion.getText().toString())
                    .params("answer",mEditAnswer.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccessful(String response) {
                            Gson gson=new Gson();
                           CommonResponse commonResponse=gson.fromJson(response,new TypeToken<CommonResponse>(){}.getType());
                            if (commonResponse.getStatus()==0){
                                iShowMessage.showMessage(commonResponse.getMsg());
                                AccountManager.setSignIn(true);
                                start(new SignInDelegate());
                            }else if (commonResponse.getStatus()==1){
                                iShowMessage.showMessage(commonResponse.getMsg());
                            }
                        }
                    })
                    .build()
                    .post();
        }
    }
    //跳转到登录
    @OnClick(R2.id.have_signUp)
    public void onClickSignUp2In(){
        start(new SignInDelegate());
    }
}
