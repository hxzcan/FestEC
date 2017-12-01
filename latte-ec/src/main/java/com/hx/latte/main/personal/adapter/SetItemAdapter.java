package com.hx.latte.main.personal.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hx.latte.ec.R;
import com.hx.latte.main.personal.bean.SetItemListBean;

import java.util.List;

/**
 * Created by hexiao on 2017/11/29.
 */

public class SetItemAdapter extends BaseMultiItemQuickAdapter<SetItemListBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SetItemAdapter(List<SetItemListBean> data) {
        super(data);
        addItemType(20, R.layout.person_arrow_set);
    }

    @Override
    protected void convert(BaseViewHolder helper, SetItemListBean item) {
        switch (helper.getItemViewType()){
            case 20:
                helper.setText(R.id.tv_arrow_text,item.getmText());
                helper.setText(R.id.tv_arrow_value,item.getmValue());
                break;
        }
    }
}
