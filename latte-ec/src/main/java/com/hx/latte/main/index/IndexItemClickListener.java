package com.hx.latte.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.hx.latte.app.delegate.LatteDelegate;
import com.hx.latte.details.GoodsDetailsDelegate;

/**
 * Created by hexiao on 2017/11/14.
 * 首页每个字item的点击事件
 */

public class IndexItemClickListener extends SimpleClickListener {

    private LatteDelegate latteDelegate;

    public IndexItemClickListener(LatteDelegate latteDelegate) {
        this.latteDelegate = latteDelegate;
    }

    public static IndexItemClickListener creator(LatteDelegate latteDelegate){
        return new IndexItemClickListener(latteDelegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        latteDelegate.start(new GoodsDetailsDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
