package com.hx.latte.app.ui.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.hx.latte.app.Latte;
import com.hx.latte.app.common.TokenCache;
import com.hx.latte.app.net.RestClient;
import com.hx.latte.app.net.callback.ISuccess;
import com.hx.latte.app.pojo.User;

/**
 * Created by hx on 2017/11/2 0002.
 * email:362970502@qq.com
 * des:下拉刷新的
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {
   private final SwipeRefreshLayout swipeRefreshLayout;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh(){
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);
    }

    public void firstPage(String url){
        User user= (User) TokenCache.getValue("user");
        RestClient.Builder().url(url)
                .params("userId",user.getId())
                .params("appToken",user.getToken())
                .success(new ISuccess() {
                    @Override
                    public void onSuccessful(String response) {
                        Toast.makeText(Latte.getApplication(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
