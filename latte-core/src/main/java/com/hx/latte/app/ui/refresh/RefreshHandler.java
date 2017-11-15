package com.hx.latte.app.ui.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;


import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hx.latte.app.common.IShowMessage;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;

import com.hx.latte.app.ui.recyclerView.DataConverter;

import com.hx.latte.app.ui.recyclerView.MultipleItemEntity;
import com.hx.latte.app.ui.recyclerView.MultipleRecycleAdapter;

import java.util.List;

/**
 * Created by hx on 2017/11/2 0002.
 * email:362970502@qq.com
 * des:下拉刷新的
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{
   private final SwipeRefreshLayout swipeRefreshLayout;
    private PageBean pageBean=null;//分页
    private RecyclerView recyclerView=null;//listView
    private MultipleRecycleAdapter recycleAdapter=null;//适配器
    private DataConverter dataConverter=null;//数据转换
    private IShowMessage iShowMessage;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,PageBean pageBean,RecyclerView recyclerView,
                          DataConverter dataConverter,IShowMessage iShowMessage) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.pageBean=pageBean;
        this.recyclerView=recyclerView;
        this.dataConverter=dataConverter;
        this.iShowMessage=iShowMessage;
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,RecyclerView recyclerView,
                                        DataConverter dataConverter,IShowMessage iShowMessage){
        return new RefreshHandler(swipeRefreshLayout,new PageBean(),recyclerView,dataConverter,iShowMessage);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh(){
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);
    }

    /**
     * 数据请求
     * @param url
     */
    public void firstPage(String url){
        RestClient.Builder().url(url)
                .params("pageNumber",pageBean.addInex().getmPageIndex())
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        Integer status= JSON.parseObject(response).getInteger("status");
                        if (status==0){
                            dataConverter.setJsonData(response);
                            List<MultipleItemEntity> multipleItemEntities=dataConverter.convert();
                       /* if (!multipleItemEntities.isEmpty()){
                            MultipleItemEntity multipleItemEntity=multipleItemEntities.get(0);
                            //设置总条目数和每页的数量
                            pageBean.setmTotal((Integer) multipleItemEntity.getField(MultipleFieldsEnum.TOATLE_NUMBER))
                                    .setmPageSize(10);
                        }*/
                            //设置数据
                            recycleAdapter=MultipleRecycleAdapter.create(multipleItemEntities);
                            recyclerView.setAdapter(recycleAdapter);
                            recycleAdapter.setOnLoadMoreListener(RefreshHandler.this,recyclerView);
                        }else {
                            iShowMessage.showMessage("已经没有数据了");
                        }
                    }
                })
                .build()
                .get();
    }

    //加载更多数据
    @Override
    public void onLoadMoreRequested() {
        firstPage(URL.INDEX_AD);
    }
}
