package com.hx.latte.app.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hx.latte.app.activity.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by hx on 2017/9/3 0003.
 * email:362970502@qq.com
 * des:基本fragment
 */

public abstract class BaseDelegate extends SwipeBackFragment{


    private Unbinder mUnbinder=null;
    /**
     * 设置layout,可能是一个布局也可能是一个布局id
     * @return
     */
    public abstract Object setLayout();

    public  abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=null;
        if (setLayout() instanceof Integer){
            rootView=inflater.inflate((Integer)setLayout(),container,false);
        }else if (setLayout() instanceof View){
            rootView= (View) setLayout();
        }else {
            throw new ClassCastException("找不到适合的布局类型");
        }
        if (rootView!=null){
            mUnbinder= ButterKnife.bind(this,rootView);
            onBindView(savedInstanceState,rootView);
        }
        return rootView;
    }

    /**
     * 获取到所在的activity
     * @return
     */
    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=null) {
            mUnbinder.unbind();
        }
    }

}
