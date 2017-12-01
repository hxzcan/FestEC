package com.hx.latte.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;
import com.hx.latte.main.personal.adapter.SetItemAdapter;
import com.hx.latte.main.personal.bean.ListItemType;
import com.hx.latte.main.personal.bean.SetItemListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:首页
 */

public class PersonalDelegate extends BottomItemDelegate{
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mItemSetRecyclerView;
    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        SetItemListBean itemListBean1=new SetItemListBean.Builder().setmId(1)
                .setmText("收获地址")
                .setmItemType(ListItemType.SET_TYPE1)
                .build();
        SetItemListBean itemListBean2=new SetItemListBean.Builder().setmId(2)
                .setmText("系统管理")
                .setmItemType(ListItemType.SET_TYPE1)
                .build();
        List<SetItemListBean> listBeen=new ArrayList<>();
        listBeen.add(itemListBean1);
        listBeen.add(itemListBean2);

        LinearLayoutManager manager=new LinearLayoutManager(_mActivity);
        mItemSetRecyclerView.setLayoutManager(manager);
        mItemSetRecyclerView.setAdapter(new SetItemAdapter(listBeen));
    }
}
