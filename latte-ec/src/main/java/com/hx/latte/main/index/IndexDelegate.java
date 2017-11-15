package com.hx.latte.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hx.latte.app.Latte;
import com.hx.latte.app.common.IShowMessage;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.app.ui.recyclerView.BaseDecoration;
import com.hx.latte.app.ui.refresh.RefreshHandler;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;
import com.hx.latte.main.EcBottomDelegate;
import com.joanzapata.iconify.widget.IconTextView;


import butterknife.BindView;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:首页
 */

public class IndexDelegate extends BottomItemDelegate implements IShowMessage{
    @BindView(R2.id.index_refresh)
    SwipeRefreshLayout mSRefreshLayout=null;
    @BindView(R2.id.index_recyclerView)
    RecyclerView mRecyclerView=null;
    @BindView(R2.id.index_icon_scan)
    IconTextView mIconScan=null;//扫描
    @BindView(R2.id.index_search)
    AppCompatEditText mSearchEdit=null;//搜索
    @BindView(R2.id.index_icon_message)
    IconTextView mIconMessage=null;//消息图标

    private RefreshHandler mRefreshHandler;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler= RefreshHandler.create(mSRefreshLayout,mRecyclerView,new IndexConvert(),this);
        //mRefreshHandler.firstPage("http://192.168.201.160:8080/hx/index/get_indexAd.do");
    }

    //fragment第三方框架提供的 懒加载
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerViewLayout();
        mRefreshHandler.firstPage(URL.INDEX_AD);
    }

    /**
     * 初始化下拉刷新
     */
    private void initRefreshLayout(){
        //设置颜色
        mSRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //设置球的动作，下拉的时候会由小变大，回弹的时候由大变小，第二个参数，起始的高度，第三个终止的高度
        mSRefreshLayout.setProgressViewOffset(true,70,150);
    }

    /**
     * 初始化recyclerView的布局样式
     * 这里是网格布局
     */
    private void initRecyclerViewLayout(){
        GridLayoutManager manager=new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(
                BaseDecoration.creator(ContextCompat.getColor(getContext(),R.color.app_background),5));
        //设置每个item的监听事件
        EcBottomDelegate delegate=getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.creator(delegate));
    }

    @Override
    public void showMessage(String message) {
        Latte.showToast(message);
    }
}
