package com.hx.latte.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hx.latte.app.common.URL;
import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.ui.recyclerView.MultipleFieldsEnum;
import com.hx.latte.app.ui.recyclerView.MultipleItemEntity;
import com.hx.latte.app.ui.refresh.RefreshHandler;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:首页
 */

public class IndexDelegate extends BottomItemDelegate{
    @BindView(R2.id.index_refresh)
    SwipeRefreshLayout mSRefreshLayout=null;
    @BindView(R2.id.index_recyclerView)
    RecyclerView mRecyclerView=null;
    @BindView(R2.id.index_icon_scan)
    IconTextView mIconScan=null;
    @BindView(R2.id.index_search)
    AppCompatEditText mSearchEdit=null;
    @BindView(R2.id.index_icon_message)
    IconTextView mIconMessage=null;

    private RefreshHandler mRefreshHandler;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler=new RefreshHandler(mSRefreshLayout);
        //mRefreshHandler.firstPage("http://192.168.201.160:8080/hx/index/get_indexAd.do");
        RestClient.Builder().url(URL.INDEX_AD)
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        IndexConvert indexConvert=new IndexConvert();
                        indexConvert.setJsonData(response);
                        List<MultipleItemEntity> entities=indexConvert.convert();
                        String name=entities.get(0).getField(MultipleFieldsEnum.NAME);
                        Toast.makeText(_mActivity, name, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }

    //fragment第三方框架提供的 懒加载
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
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
        mSRefreshLayout.setProgressViewOffset(true,65,150);
    }

}
