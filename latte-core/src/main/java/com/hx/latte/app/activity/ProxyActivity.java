package com.hx.latte.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.hx.latte.R;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.utils.InputUtil;

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction()==MotionEvent.ACTION_DOWN){
            View v=getCurrentFocus();
            if (InputUtil.isShouldHideInput(v,ev)){
                InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager!=null){
                    //键盘消失
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        //必不可少，不然所有的组件都会失去TouchEvent
        if (getWindow().superDispatchTouchEvent(ev)){
            return  true;
        }
        return onTouchEvent(ev);
    }

}
