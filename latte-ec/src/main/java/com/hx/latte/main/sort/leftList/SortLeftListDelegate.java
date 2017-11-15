package com.hx.latte.main.sort.leftList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hx.latte.app.Latte;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.pojo.CommonResponse;
import com.hx.latte.app.ui.recyclerView.BaseDecoration;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;
import com.hx.latte.main.sort.adapter.SortLeftListAdapter;
import com.hx.latte.pojo.SortBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hexiao on 2017/11/14.
 * 分类左侧的分类列表
 */

public class SortLeftListDelegate extends LatteDelegate{
    @BindView(R2.id.sort_left_list)
    RecyclerView mRecyclerView;
    private List<SortBean> sortBeanList;
    private SortLeftListAdapter sortLeftListAdapter;
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_left_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
        initData();
    }


    private void initRecyclerView(){
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(BaseDecoration.creator(ContextCompat.getColor(getContext(),
                R.color.app_background),5));

    }

    private void initData(){
        sortBeanList=new ArrayList<>();
        RestClient.Builder().url(URL.SORT_PARENT)
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        Gson gson=new Gson();
                        CommonResponse<List<SortBean>> listCommonResponse=gson.fromJson(response,
                                new TypeToken<CommonResponse<List<SortBean>>>(){}.getType());
                        int status=listCommonResponse.getStatus();
                        if (status==0){
                           sortBeanList.addAll(listCommonResponse.getData());
                            handler.sendEmptyMessage(1);
                        }else if (status==1){
                            Latte.showToast(listCommonResponse.getMsg());
                        }
                    }
                })
                .build()
                .get();
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                if (sortBeanList!=null&&sortBeanList.size()>0){
                    sortLeftListAdapter=new SortLeftListAdapter(sortBeanList,_mActivity);
                    mRecyclerView.setAdapter(sortLeftListAdapter);
                    sortLeftListAdapter.notifyDataSetChanged();
                }
            }
        }
    };

}
