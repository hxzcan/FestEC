package com.hx.latte.main.sort.adapter;


import android.support.v7.widget.AppCompatImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.ui.glide.GlideApp;
import com.hx.latte.ec.R;
import com.hx.latte.pojo.SortBeanVo;

import java.util.List;

/**
 * Created by hexiao on 2017/11/15.
 * 右边内容的适配器
 */

public class SortRightContentAdapter extends BaseSectionQuickAdapter<SortBeanVo,BaseViewHolder>{


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SortRightContentAdapter(int layoutResId, int sectionHeadResId, List<SortBeanVo> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SortBeanVo item) {
        helper.setText(R.id.sort_right_content_header_name,item.header);
        helper.addOnClickListener(R.id.sort_right_content_header_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SortBeanVo item) {
        helper.setText(R.id.sort_right_content_text,item.t.getSecondSection());
        AppCompatImageView imageView=helper.getView(R.id.sort_right_content_image);
        GlideApp.with(mContext).load(URL.IMAGE_PRIX+"huawei_mate10.jpg").into(imageView);
    }
}
