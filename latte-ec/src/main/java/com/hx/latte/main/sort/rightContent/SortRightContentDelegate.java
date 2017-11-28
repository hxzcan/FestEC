package com.hx.latte.main.sort.rightContent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hx.latte.app.Latte;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.pojo.CommonResponse;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;
import com.hx.latte.main.sort.adapter.SortRightContentAdapter;
import com.hx.latte.pojo.SecondCategoryVo;
import com.hx.latte.pojo.SortBeanOrigin;
import com.hx.latte.pojo.SortBeanVo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hexiao on 2017/11/14.
 * 右侧的加载分类内容的fragment
 */

public class SortRightContentDelegate extends LatteDelegate {
    @BindView(R2.id.sort_right_list)
    RecyclerView mRightRecyclerView;


    private static final String CONTENT_ID="CONTENT_ID";
    private int defaultContentId=-1;
    private List<SortBeanVo> sortBeanVoList=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if (bundle!=null){
            defaultContentId=bundle.getInt(CONTENT_ID);
        }
    }

    public static SortRightContentDelegate newInstance(int contentId){
        Bundle bundle=new Bundle();
        bundle.putInt(CONTENT_ID,contentId);
        SortRightContentDelegate sortRightContentDelegate=new SortRightContentDelegate();
        sortRightContentDelegate.setArguments(bundle);
        return sortRightContentDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_right_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
        initData();
    }

    private void initRecyclerView(){
        //第一个参数：左右显示几个item ；第二个参数：展示的形态
        StaggeredGridLayoutManager  manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRightRecyclerView.setLayoutManager(manager);
    }

    private void initData(){
        sortBeanVoList=new ArrayList<>();
        RestClient.Builder().url(URL.SORT_SECOND)
                .params("parentLevelId",defaultContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        Log.i("SortBeanOrigin",response);
                        Gson gson=new Gson();
                        CommonResponse<List<SortBeanOrigin>> commonResponse=gson.fromJson(response,
                                new TypeToken<CommonResponse<List<SortBeanOrigin>>>(){}.getType());
                        int status=commonResponse.getStatus();
                        if (status==0){
                            for (SortBeanOrigin origins:commonResponse.getData()){
                                String title=origins.getFirstSection();
                                SortBeanVo sortBeanVo=new SortBeanVo(true,title);
                                sortBeanVo.setFirstId(origins.getFirstId());
                                sortBeanVo.setRootParentId(origins.getRootParentId());
                                sortBeanVoList.add(sortBeanVo);
                                for (SortBeanOrigin.SecondCateoryVo second:origins.getSecondCateoryVos()) {
                                    SecondCategoryVo secondCategoryVo=new SecondCategoryVo();
                                    secondCategoryVo.setFirstParentId(second.getFirstParentId());
                                    secondCategoryVo.setSecondId(second.getSecondId());
                                    secondCategoryVo.setSecondSection(second.getSecondSection());
                                    sortBeanVoList.add(new SortBeanVo(secondCategoryVo));
                                }
                            }
                            handler.sendEmptyMessage(1);
                        }else if (status==1){
                            Latte.showToast(commonResponse.getMsg());
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
                SortRightContentAdapter sortRightContentAdapter=new SortRightContentAdapter(
                        R.layout.item_sort_right_content,R.layout.item_sort_right_content_header,
                        sortBeanVoList);
                mRightRecyclerView.setAdapter(sortRightContentAdapter);
                sortRightContentAdapter.notifyDataSetChanged();
            }
        }
    };

}
