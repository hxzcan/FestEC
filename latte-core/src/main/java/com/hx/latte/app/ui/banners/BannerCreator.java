package com.hx.latte.app.ui.banners;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.hx.latte.R;

import java.util.ArrayList;

/**
 * Created by hexiao on 2017/11/7.
 * 轮播图公用
 */

public class BannerCreator {

    public static  void setDefault(ConvenientBanner<String> convenientBanner,
                                   ArrayList<String> banners, OnItemClickListener onItemClickListener){
        convenientBanner.setPages(new HolderCreator(),banners)
            .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})//设置小圆点样式
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//指示器的样式
            .setOnItemClickListener(onItemClickListener)//点击监听
            .setPageTransformer(new DefaultTransformer())
            .startTurning(3000)//多少秒换一张图片
            .setCanLoop(true);//是否循环
    }
}
