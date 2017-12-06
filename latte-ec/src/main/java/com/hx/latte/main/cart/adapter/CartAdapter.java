package com.hx.latte.main.cart.adapter;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hx.latte.app.Latte;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.common.UserMessage;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.pojo.CommonResponse;
import com.hx.latte.app.ui.glide.GlideApp;
import com.hx.latte.ec.R;
import com.hx.latte.main.cart.IChanged;
import com.hx.latte.main.cart.bean.GoodBean;
import com.hx.latte.main.cart.bean.GoodBeanVo;
import com.joanzapata.iconify.widget.IconTextView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hexiao on 2017/11/30.
 * 购物车适配器
 */

public class CartAdapter extends BaseMultiItemQuickAdapter<GoodBean,BaseViewHolder> {
    private boolean isSelectedAll=false;//是否被全选
    private IChanged changed;
    public void setSelectedAll(boolean selectedAll) {
        isSelectedAll = selectedAll;
    }


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CartAdapter(List<GoodBean> data,IChanged changed) {
        super(data);
        this.changed=changed;
        addItemType(1, R.layout.sort_list_item);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GoodBean item) {
        switch (helper.getItemViewType()){
            case 1:
                if (item!=null){
                    item.setAdapterPosition(helper.getAdapterPosition());//设置位置
                    final IconTextView isChecked=helper.getView(R.id.cart_item_check);//勾选图片
                    LinearLayout linearLayout=helper.getView(R.id.sort_item_good);
                    ImageView thumbImg=helper.getView(R.id.cart_item_image);//图片
                    IconTextView minus=helper.getView(R.id.cart_item_minus);//减少
                    final TextView quantity=helper.getView(R.id.cart_goods_quantity);//数量
                    IconTextView plus=helper.getView(R.id.cart_item_plus);//增加
                    //勾选框渲染之前改变全选状态
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
                    //勾选的点击事件
                    isChecked.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //通知界面此时是非全选
                            if (isSelectedAll){
                                changed.setChange(false);
                            }
                          Integer isSelected=item.getChecked();
                            if (isSelected==1){//选中的标记 ，点击之后变为不被选中
                                RestClient.Builder()
                                        .url(URL.CART_UNSELECT_SINGLE)
                                        .params("appToken", UserMessage.USER_TOKEN)
                                        .params("userId",UserMessage.USER_ID)
                                        .params("productId",item.getProductId())
                                        .success(new ISuccess(){
                                            @Override
                                            public void onSuccessful(String response) {
                                                Gson gson=new Gson();
                                                CommonResponse<GoodBean> data=gson.fromJson(response,
                                                        new TypeToken<CommonResponse<GoodBeanVo>>(){}.getType());
                                                Integer code=data.getStatus();
                                                if (code==0){
                                                    isChecked.setBackgroundColor(ContextCompat.getColor(mContext,R.color.dark_gray));
                                                    item.setChecked(0);
                                                    //计算价格
                                                    BigDecimal bigDecimal=item.getProductPrice().
                                                            multiply(new BigDecimal(item.getQuantity()));
                                                    changed.setPrice(bigDecimal,1);//价格的回调
                                                    changed.setSelectCount(1);//数量的回调
                                                }
                                            }
                                        })
                                        .build().get();
                            }else {//未选中的标记 ，点击之后变为被选中
                                RestClient.Builder()
                                        .url(URL.CART_SELECT_SINGLE)
                                        .params("appToken", UserMessage.USER_TOKEN)
                                        .params("userId",UserMessage.USER_ID)
                                        .params("productId",item.getProductId())
                                        .success(new ISuccess(){
                                            @Override
                                            public void onSuccessful(String response) {
                                                Gson gson=new Gson();
                                                CommonResponse<GoodBeanVo> data=gson.fromJson(response,
                                                        new TypeToken<CommonResponse<GoodBeanVo>>(){}.getType());
                                                Integer code=data.getStatus();
                                                if (code==0){
                                                    isChecked.setBackgroundColor(ContextCompat.
                                                            getColor(mContext,R.color.title_color));
                                                    item.setChecked(1);
                                                    //计算价格
                                                   BigDecimal bigDecimal=item.getProductPrice().
                                                           multiply(new BigDecimal(item.getQuantity()));
                                                    changed.setPrice(bigDecimal,0);//价格的回调
                                                    changed.setSelectCount(0);//数量的回调
                                                }
                                            }
                                        })
                                        .build().get();
                            }
                        }
                    });
                    //减少数量
                    minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Integer currentCount=item.getQuantity();
                            currentCount--;
                            if (currentCount>=1){
                                final Integer finalCurrentCount = currentCount;
                                RestClient.Builder()
                                        .url(URL.CART_ADD_GOOD)
                                        .params("appToken",UserMessage.USER_TOKEN)
                                        .params("userId", UserMessage.USER_ID)
                                        .params("count",currentCount)
                                        .params("productId",item.getProductId())
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccessful(String response) {
                                                Gson gson=new Gson();
                                                CommonResponse<GoodBeanVo> data=gson.fromJson(response,
                                                        new TypeToken<CommonResponse<GoodBeanVo>>(){}.getType());
                                                Integer code=data.getStatus();
                                                String msg=data.getMsg();
                                                if (code==0){
                                                    quantity.setText(String.valueOf(finalCurrentCount));
                                                    item.setQuantity(finalCurrentCount);
                                                    item.setProductTotalPrice(item.getProductTotalPrice().
                                                            subtract(item.getProductPrice()));
                                                    //判断是否勾选
                                                    if (item.getChecked()==1){
                                                        //计算价格
                                                        changed.setPrice(item.getProductPrice(),1);
                                                    }
                                                }else {
                                                    Latte.showToast(msg);
                                                }
                                            }
                                        }).build().post();
                            }else {
                                Latte.showToast("已经只剩一件了，忍心要全都删除吗？");
                            }
                        }
                    });
                    //添加数量
                    plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Integer count=item.getQuantity();
                            count++;
                            final Integer finalCount = count;
                            RestClient.Builder()
                                    .url(URL.CART_ADD_GOOD)
                                    .params("appToken",UserMessage.USER_TOKEN)
                                    .params("userId",UserMessage.USER_ID)
                                    .params("count",count)
                                    .params("productId",item.getProductId())
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccessful(String response) {
                                            Gson gson=new Gson();
                                            CommonResponse<GoodBeanVo> data=gson.fromJson(response,
                                                    new TypeToken<CommonResponse<GoodBeanVo>>(){}.getType());
                                            Integer code=data.getStatus();
                                            String msg=data.getMsg();
                                            if (code==0){
                                                quantity.setText(String.valueOf(finalCount));
                                                item.setQuantity(finalCount);
                                                item.setProductTotalPrice(item.getProductTotalPrice().
                                                        add(item.getProductPrice()));
                                                //判断是否勾选
                                                if (item.getChecked()==1){
                                                    //计算价格
                                                    changed.setPrice(item.getProductPrice(),0);
                                                }
                                            }else {
                                                Latte.showToast(msg);
                                            }
                                        }
                                    })
                                    .build().post();
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
}
