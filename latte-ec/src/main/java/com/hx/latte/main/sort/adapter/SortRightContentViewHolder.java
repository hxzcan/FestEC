package com.hx.latte.main.sort.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hx.latte.ec.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hexiao on 2017/11/15.
 * 分类右侧内容的布局
 */

public class SortRightContentViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.sort_right_content_image)
    AppCompatImageView mContentImage;
    @BindView(R2.id.sort_right_content_text)
    AppCompatTextView mContentMsg;

    public SortRightContentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public static SortRightContentViewHolder creator(View itemView){
        return new SortRightContentViewHolder(itemView);
    }
}
