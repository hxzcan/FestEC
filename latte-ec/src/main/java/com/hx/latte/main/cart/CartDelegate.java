package com.hx.latte.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hx.latte.app.Latte;
import com.hx.latte.app.common.TokenCache;
import com.hx.latte.app.common.URL;
import com.hx.latte.app.common.UserMessage;
import com.hx.latte.app.delegate.bottom.BottomItemDelegate;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.pojo.CommonResponse;
import com.hx.latte.app.pojo.User;
import com.hx.latte.app.utils.storage.LattePreference;
import com.hx.latte.ec.R;
import com.hx.latte.ec.R2;
import com.hx.latte.main.cart.adapter.CartAdapter;
import com.hx.latte.main.cart.bean.GoodBean;
import com.hx.latte.main.cart.bean.GoodBeanVo;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:购物车
 */

public class CartDelegate extends BottomItemDelegate implements ISuccess{
    @BindView(R2.id.cart_delegate_clear_all)//清空
    AppCompatTextView mCartClearAll;
    @BindView(R2.id.cart_delegate_delete)//删除
    AppCompatTextView mCartDelete;
    @BindView(R2.id.cart_delegate_list)//列表
    RecyclerView mCartList;
    @BindView(R2.id.cart_delegate_check_all)//全选
    IconTextView mCartSelectAll;
    @BindView(R2.id.cart_delegate_money)//合计
    AppCompatTextView mCartMoney;
    @BindView(R2.id.cart_delegate_count_money)//结算
    AppCompatTextView mCartCount;
    private List<GoodBean> mGoodsList=null;
    private CartAdapter mCartAdapter;
    @Override
    public Object setLayout() {
        return R.layout.delegate_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.Builder()
                .url(URL.CART_GOODS_LIST)
                .params("appToken", UserMessage.USER_TOKEN)
                .params("userId",UserMessage.USER_ID)
                .success(this)
                .build()
                .get();
    }

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
            mCartAdapter=new CartAdapter(mGoodsList);
            LinearLayoutManager manager=new LinearLayoutManager(_mActivity);
            mCartList.setLayoutManager(manager);
            mCartList.setAdapter(mCartAdapter);
            mCartAdapter.notifyDataSetChanged();
        }else {
            Latte.showToast(msg);
        }
    }
}
