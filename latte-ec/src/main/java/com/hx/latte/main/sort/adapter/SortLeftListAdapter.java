package com.hx.latte.main.sort.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.ec.R;
import com.hx.latte.main.sort.SortDelegate;
import com.hx.latte.main.sort.rightContent.SortRightContentDelegate;
import com.hx.latte.pojo.SortBean;

import java.util.List;

/**
 * Created by hexiao on 2017/11/14.
 * 左侧列表适配器
 */

public class SortLeftListAdapter extends RecyclerView.Adapter<SortLeftListViewHolder> {
    private List<SortBean> sortBeanList;
    private SortDelegate sortDelegate;
    private Context mContext;
    private int prePosition=0;
    public SortLeftListAdapter(List<SortBean> sortBeanList,Context mContext,SortDelegate sortDelegate) {
        this.sortBeanList = sortBeanList;
        this.mContext=mContext;
        this.sortDelegate=sortDelegate;
    }

    @Override
    public SortLeftListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定布局
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_sort_left_list,parent,false);
        SortLeftListViewHolder viewHolder=SortLeftListViewHolder.creator(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SortLeftListViewHolder holder, final int position) {
        //绑定数据
        final SortBean sortBean=sortBeanList.get(position);
        holder.mSortListName.setText(sortBean.getName());
        boolean isSelect=sortBean.isSelect();
        //点击事件
        holder.mSortListName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition=holder.getAdapterPosition();
                if (prePosition!=currentPosition){
                    //还原上一个点击的状态
                    sortBeanList.get(prePosition).setSelect(false);
                    notifyItemChanged(prePosition);

                    //更新点击的
                    sortBeanList.get(currentPosition).setSelect(true);
                    notifyItemChanged(currentPosition);
                    int id=sortBeanList.get(currentPosition).getId();
                    showRightContent(id);
                    Log.i("xxxxxxxx",id+"");
                    prePosition=currentPosition;
                }
            }
        });
        if (isSelect){
            //如果点击了，则改变颜色
            holder.mSortListName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.sort_left_list_item_bg));
            holder.mSortListName.setTextColor(ContextCompat.getColor(mContext,R.color.text_color));
            holder.mLeftLine.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorAccent));
        }else {
            //没有点击
            holder.mSortListName.setBackgroundColor(ContextCompat.getColor(mContext,R.color.text_color));
            holder.mSortListName.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
            holder.mLeftLine.setBackgroundColor(ContextCompat.getColor(mContext,R.color.text_color));
        }
    }

    @Override
    public int getItemCount() {
        return sortBeanList.size();
    }

    /**
     * 根据左边点击的item来显示右边的内容
     * @param contentId
     */
    public void showRightContent(Integer contentId){
        if (contentId==null){
            contentId=sortBeanList.get(0).getId();
        }
        SortRightContentDelegate rightContentDelegate=SortRightContentDelegate.newInstance(contentId);
        switchRightContent(rightContentDelegate);
    }

    /**
     * 新的替换就的fragment
     * @param contentDelegate
     */
    private void switchRightContent(SortRightContentDelegate contentDelegate){
        LatteDelegate latteDelegate=sortDelegate.findChildFragment(SortRightContentDelegate.class);
        if (latteDelegate!=null){
            latteDelegate.replaceFragment(contentDelegate,false);
        }
    }

}
