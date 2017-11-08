package com.hx.latte.app.ui.recyclerView;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by hexiao on 2017/11/7.
 */

public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view){
        return new MultipleViewHolder(view);
    }
}
