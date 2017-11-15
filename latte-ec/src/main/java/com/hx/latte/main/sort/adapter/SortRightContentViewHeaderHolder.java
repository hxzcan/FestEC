package com.hx.latte.main.sort.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hx.latte.ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hexiao on 2017/11/15.
 * 分类右侧内容头部的内容
 */

public class SortRightContentViewHeaderHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.sort_right_content_header_name)
    AppCompatTextView mHeaderName;
    @BindView(R2.id.sort_right_content_header_more)
    IconTextView mHeaderMore;

    public SortRightContentViewHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public static SortRightContentViewHeaderHolder creator(View itemView){
        return new SortRightContentViewHeaderHolder(itemView);
    }
}
