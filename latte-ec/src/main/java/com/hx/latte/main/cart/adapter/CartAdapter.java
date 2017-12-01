package com.hx.latte.main.cart.adapter;

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.ui.glide.GlideApp;
import com.hx.latte.ec.R;
import com.hx.latte.main.cart.bean.GoodBean;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Created by hexiao on 2017/11/30.
 * 购物车适配器
 */

public class CartAdapter extends BaseMultiItemQuickAdapter<GoodBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CartAdapter(List<GoodBean> data) {
        super(data);
        addItemType(1, R.layout.sort_list_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodBean item) {
        switch (helper.getItemViewType()){
            case 1:
                if (item!=null){
                    IconTextView isChecked=helper.getView(R.id.cart_item_check);//勾选图片
                    LinearLayout linearLayout=helper.getView(R.id.sort_item_good);
                    ImageView thumbImg=helper.getView(R.id.cart_item_image);//图片
                    IconTextView minus=helper.getView(R.id.cart_item_minus);//减少
                    TextView quantity=helper.getView(R.id.cart_goods_quantity);//数量
                    IconTextView plus=helper.getView(R.id.cart_item_plus);//增加
                    //1：勾选 ；0：未勾选
                    if (item.getChecked()==1){
                        isChecked.setBackgroundColor(ContextCompat.getColor(mContext,R.color.title_color));
                    }else {
                        isChecked.setBackgroundColor(ContextCompat.getColor(mContext,R.color.dark_gray));
                    }
                    helper.setText(R.id.cart_item_good_title,item.getProductName());
                    helper.setText(R.id.cart_item_good_des,item.getProductSubtitle());
                    helper.setText(R.id.cart_item_good_price,String.valueOf(item.getProductPrice()));
                    quantity.setText(String.valueOf(item.getQuantity()));
                    GlideApp.with(mContext).load(URL.IMAGE_PRIX+item.getProductMainImage())
                            .fitCenter().centerCrop().into(thumbImg);
                }
                break;
            default:
                break;
        }
    }
}
