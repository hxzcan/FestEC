package com.hx.latte.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hx.latte.app.Latte;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.common.UserMessage;
import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.pojo.CommonResponse;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;
import com.hx.latte.main.cart.adapter.CartAdapter;
import com.hx.latte.main.cart.bean.GoodBean;
import com.hx.latte.main.cart.bean.GoodBeanVo;
import com.joanzapata.iconify.widget.IconTextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:购物车
 */

public class CartDelegate extends BottomItemDelegate implements IChanged{
    private static  final String TAG="CartDelegate";
    @BindView(R2.id.cart_delegate_clear_all)//清空
    AppCompatTextView mCartClearAll;
    @BindView(R2.id.cart_delegate_delete)//删除
    AppCompatTextView mCartDelete;
    @BindView(R2.id.cart_delegate_list)//列表
    RecyclerView mCartList;
    @BindView(R2.id.cart_delegate_select_all_lay)
    LinearLayoutCompat mCartSelectAllLay;
    @BindView(R2.id.cart_delegate_check_all)//全选
    IconTextView mCartSelectAll;
    @BindView(R2.id.cart_delegate_money)//合计金额
    AppCompatTextView mCartMoney;
    @BindView(R2.id.cart_no_good)
    ViewStubCompat mViewStubCompat;//用来填充其他布局

    private List<GoodBean> mGoodsList=null;
    private CartAdapter mCartAdapter;
    private List<Integer> selectedGoodId;//存放要删除的产品id
    private String ids;//选中删除的id
    private BigDecimal totalPrice;//总价
    private Integer mSelectCount=0;//勾选的数目；
    private Integer mTotalSize=0;//购物车内的商品种类的个数，不是数量的个数

    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mCartSelectAllLay.setTag(0);//设置唯一标记
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //加载数据
        RestClient.Builder()
                .url(URL.CART_GOODS_LIST)
                .params("appToken", UserMessage.USER_TOKEN)
                .params("userId",UserMessage.USER_ID)
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        Gson gson=new Gson();
                        CommonResponse<GoodBeanVo> data=gson.fromJson(response,
                                new TypeToken<CommonResponse<GoodBeanVo>>(){}.getType());
                        Integer code=data.getStatus();
                        String msg=data.getMsg();
                        if (code==0){
                            GoodBeanVo goodBeanVo=data.getData();
                            mGoodsList=goodBeanVo.getmGoodBeanVo();
                            mCartAdapter=new CartAdapter(mGoodsList,CartDelegate.this);
                            LinearLayoutManager manager=new LinearLayoutManager(_mActivity);
                            mCartList.setLayoutManager(manager);
                            mCartList.setAdapter(mCartAdapter);
                            mCartAdapter.notifyDataSetChanged();

                            if (mGoodsList.isEmpty()){
                                mTotalSize=0;
                            }else {
                                //如果是已经是全部勾选了，则设置全部勾选
                                if (goodBeanVo.getAllCheck()){
                                    mCartSelectAll.setTextColor(ContextCompat.getColor(_mActivity,R.color.title_color));
                                    mCartSelectAllLay.setTag(1);
                                    mCartAdapter.setSelectedAll(true);
                                }else {
                                    //没有全勾选先计算出勾选出来的数量
                                    for (GoodBean goodBean : mGoodsList) {
                                        if (goodBean.getChecked()==1){
                                            mSelectCount++;
                                        }
                                    }
                                }
                                //
                                if (goodBeanVo.getCartTotalPrice()==null){
                                    totalPrice=new BigDecimal(0);
                                    mCartMoney.setText("￥"+0.00);
                                }else {
                                    totalPrice=goodBeanVo.getCartTotalPrice();
                                    mCartMoney.setText("￥"+goodBeanVo.getCartTotalPrice());
                                }
                                mTotalSize=mGoodsList.size();//商品种类的总个数
                            }

                            checkItemCount();
                        }else {
                            Latte.showToast(msg);
                        }
                    }
                })
                .build()
                .get();
    }

    /**
     *  全选按钮点击
     */
    @OnClick(R2.id.cart_delegate_select_all_lay)
    public void OnClickSelectAll(){
        mGoodsList=mCartAdapter.getData();//从新获取数据
        Log.i(TAG,mGoodsList.toString());
        int tag= (int) mCartSelectAllLay.getTag();
        if (tag==0){//设置为全选
            RestClient.Builder().url(URL.CART_SELECT_ALL)
                    .params("appToken",UserMessage.USER_TOKEN)
                    .params("userId",UserMessage.USER_ID)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccessful(String response) {
                            Gson gson=new Gson();
                            CommonResponse<GoodBean> data=gson.fromJson(response,
                                    new TypeToken<CommonResponse<GoodBeanVo>>(){}.getType());
                            Integer code=data.getStatus();
                            String msg=data.getMsg();
                            if (code==0){
                                mCartSelectAll.setTextColor(ContextCompat.getColor(_mActivity,R.color.title_color));
                                mCartSelectAllLay.setTag(1);
                                mCartAdapter.setSelectedAll(true);
                                totalPrice=new BigDecimal(0);
                                for (GoodBean goodBean : mGoodsList) {
                                    goodBean.setChecked(1);
                                    //计算价格
                                    totalPrice=totalPrice.add(goodBean.getProductTotalPrice());
                                }
                                mCartAdapter.notifyDataSetChanged();//更新改变的内容 这个不会闪动
                                //有问题？只更新改变的内容 这个会闪动
                                //mCartAdapter.notifyItemRangeChanged(0,mCartAdapter.getItemCount());
                                mCartMoney.setText("￥"+totalPrice);
                                mSelectCount=mTotalSize;//全选的时候把数量置为总数
                            }else {
                                Latte.showToast(msg);
                            }
                        }
                    })
                    .build().get();
        }else if (tag==1){//设置为全不选
            RestClient.Builder().url(URL.CART_USELECT_ALL)
                    .params("appToken",UserMessage.USER_TOKEN)
                    .params("userId",UserMessage.USER_ID)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccessful(String response) {
                            Gson gson=new Gson();
                            CommonResponse<GoodBean> data=gson.fromJson(response,
                                    new TypeToken<CommonResponse<GoodBeanVo>>(){}.getType());
                            Integer code=data.getStatus();
                            String msg=data.getMsg();
                            if (code==0){
                                mCartSelectAll.setTextColor(ContextCompat.getColor(_mActivity,R.color.dark_gray));
                                mCartSelectAllLay.setTag(0);
                                mCartAdapter.setSelectedAll(false);
                                for (GoodBean goodBean : mGoodsList) {
                                    goodBean.setChecked(0);
                                }
                                mCartAdapter.notifyDataSetChanged();
                                totalPrice=new BigDecimal(0);//把计算价格的变量设置为0
                                mCartMoney.setText("￥"+0.00);//把价格设置为0
                                mSelectCount=0;//设置全不选的时候置为0
                                //mCartAdapter.notifyItemRangeChanged(0,mCartAdapter.getItemCount());
                            }else {
                                Latte.showToast(msg);
                            }
                        }
                    })
                    .build().get();
        }
    }

    /**
     * 删除选中的
     */
    @OnClick(R2.id.cart_delegate_delete)
    public void onClickDeleteSelected(){
        mGoodsList=mCartAdapter.getData();//获取到数据
        //存放要删除的id
        selectedGoodId = new ArrayList<>();
        for (GoodBean goodBean :mGoodsList) {//遍历是1的
            if (goodBean.getChecked()==1){
                selectedGoodId.add(goodBean.getProductId());//产品的id
            }
        }
        //转换成以，分割的字符串
        ids = Joiner.on(",").join(selectedGoodId);
        RestClient.Builder()
                .url(URL.CART_DELETE_GOODS)
                .params("appToken",UserMessage.USER_TOKEN)
                .params("userId",UserMessage.USER_ID)
                .params("productIds", ids)
                .success(new ISuccess(){
                    @Override
                    public void onSuccessful(String response) {
                        Gson gson=new Gson();
                        CommonResponse<GoodBean> data=gson.fromJson(response,
                                new TypeToken<CommonResponse<GoodBeanVo>>(){}.getType());
                        Integer code=data.getStatus();
                        if (code==0){
                            List<GoodBean> temp=new ArrayList<>();//存放要删除的集合
                            for (int i = 0; i < mGoodsList.size(); i++) {
                                if (mGoodsList.get(i).getChecked()==1){
                                    temp.add(mGoodsList.get(i));
                                   // mGoodsList.remove(i);//集合中移除
                                  //  mCartAdapter.notifyItemRemoved(i);//适配器中移除
                                    BigDecimal bigDecimal=mGoodsList.get(i).getProductPrice().
                                            multiply(new BigDecimal(mGoodsList.get(i).getQuantity()));
                                    totalPrice=totalPrice.subtract(bigDecimal);
                                }
                            }
                            mGoodsList.removeAll(temp);//一次性从集合移除
                            mCartAdapter.notifyDataSetChanged();//通知改变
                            mCartMoney.setText("￥"+totalPrice);
                            checkItemCount();
                            mTotalSize=mTotalSize-temp.size();//删除时改变总个数
                            mSelectCount=0;//如果删除勾选的，则说明不存在勾选的，设置为0
                        }else {
                            Latte.showToast("购物车为空或者没有选择要删除的物品");
                        }
                    }
                })
                .build().post();

    }

    /**
     * 清空购物车
     */
    @OnClick(R2.id.cart_delegate_clear_all)
    public void onClickClearAll(){
        RestClient.Builder().url(URL.CART_CLEAR_ALL)
                .params("appToken",UserMessage.USER_TOKEN)
                .params("userId",UserMessage.USER_ID)
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        Gson gson=new Gson();
                        CommonResponse<String> data=gson.fromJson(response,
                                new TypeToken<CommonResponse<String>>(){}.getType());
                        Integer code=data.getStatus();
                        if (code==0){
                            mCartAdapter.getData().clear();
                            mCartAdapter.notifyDataSetChanged();
                            mCartSelectAll.setTextColor(ContextCompat.getColor(_mActivity,R.color.dark_gray));
                            mCartSelectAllLay.setTag(0);
                            mCartAdapter.setSelectedAll(false);
                            checkItemCount();
                            totalPrice=new BigDecimal(0);//把计算价格的变量设置为0
                            mCartMoney.setText("￥"+0.00);//把价格设置为0
                            mTotalSize=0;//清空总数设置为0
                            mSelectCount=0;//如果清空，则说明不存在勾选的，设置为0
                        }else {
                            Latte.showToast("购物车为空或者没有选择要删除的物品");
                        }
                    }
                })
                .build().get();
    }

    /**
     * 结算
     */
    @OnClick(R2.id.cart_delegate_count_money)
    public void onClickBalanceAccounts(){
        Latte.showToast(totalPrice+"");
    }

    /**
     * 当在是全选的时候，点击一个，不是全选了，就把全选按钮置为原来的颜色
     * @param isChanged
     */
    @Override
    public void setChange(boolean isChanged) {
        if (!isChanged){
            mCartSelectAll.setTextColor(ContextCompat.getColor(_mActivity,R.color.dark_gray));
            mCartSelectAllLay.setTag(0);
            mCartAdapter.setSelectedAll(false);
        }else {
            mCartSelectAll.setTextColor(ContextCompat.getColor(_mActivity,R.color.title_color));
            mCartSelectAllLay.setTag(1);
            mCartAdapter.setSelectedAll(true);
        }
    }

    /**
     * 设置合计总价
     * @param singleTotalPrice
     * @param plusOrMinus
     */
    @Override
    public void setPrice(BigDecimal singleTotalPrice,Integer plusOrMinus) {
        if (plusOrMinus==0){
            totalPrice=totalPrice.add(singleTotalPrice);
            mCartMoney.setText("￥"+totalPrice);
        }else if (plusOrMinus==1){
            totalPrice=totalPrice.subtract(singleTotalPrice);
            mCartMoney.setText("￥"+totalPrice);
        }
    }

    @Override
    public void setSelectCount(Integer plusOrMinus) {
        if (plusOrMinus==0){
            mSelectCount++;
        }else if (plusOrMinus==1){
            mSelectCount--;
        }
        //如果等于总的数量就设置为全选
        if (mSelectCount==mTotalSize){
            mCartSelectAll.setTextColor(ContextCompat.getColor(_mActivity,R.color.title_color));
            mCartSelectAllLay.setTag(1);
            mCartAdapter.setSelectedAll(true);
        }else {//非全选
            mCartSelectAll.setTextColor(ContextCompat.getColor(_mActivity,R.color.dark_gray));
            mCartSelectAllLay.setTag(0);
            mCartAdapter.setSelectedAll(false);
        }
    }


    /**
     * 检查购物车内是否有商品
     */
    private void checkItemCount(){
        int count=mCartAdapter.getItemCount();
        if (count==0){
            final View stubView=mViewStubCompat.inflate();
            final AppCompatTextView tvToBuy=stubView.findViewById(R.id.cart_tv_go_shopping);
            final IconTextView icon=stubView.findViewById(R.id.cart_is_null);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Latte.showToast("去购物");
                }
            });
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Latte.showToast("去购物");
                }
            });
            mCartList.setVisibility(View.GONE);
        }else {
            mCartList.setVisibility(View.VISIBLE);
        }
    }

}
