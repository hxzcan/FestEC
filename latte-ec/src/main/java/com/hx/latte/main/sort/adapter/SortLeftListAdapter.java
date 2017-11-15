package com.hx.latte.main.sort.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hx.latte.ec.R;
import com.hx.latte.pojo.SortBean;

import java.util.List;

/**
 * Created by hexiao on 2017/11/14.
 * 左侧列表适配器
 */

public class SortLeftListAdapter extends RecyclerView.Adapter<SortLeftListViewHolder> {
    private List<SortBean> sortBeanList;
    private Context mContext;

    public SortLeftListAdapter(List<SortBean> sortBeanList,Context mContext) {
        this.sortBeanList = sortBeanList;
        this.mContext=mContext;
    }

    @Override
    public SortLeftListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定布局
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_sort_left_list,parent,false);
        SortLeftListViewHolder viewHolder=SortLeftListViewHolder.creator(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SortLeftListViewHolder holder, int position) {
        //绑定数据
        SortBean sortBean=sortBeanList.get(position);
        holder.mSortListName.setText(sortBean.getName());
    }

    @Override
    public int getItemCount() {
        return sortBeanList.size();
    }
}
