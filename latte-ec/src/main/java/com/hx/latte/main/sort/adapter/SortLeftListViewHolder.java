package com.hx.latte.main.sort.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hx.latte.ec.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hexiao on 2017/11/14.
 * 复用的viewHolder
 */

public class SortLeftListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.item_sort_left_text)
    AppCompatTextView mSortListName;
    @BindView(R2.id.left_line)
    View mLeftLine;

    public SortLeftListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public static SortLeftListViewHolder creator(View itemView){
        return new SortLeftListViewHolder(itemView);
    }
}
