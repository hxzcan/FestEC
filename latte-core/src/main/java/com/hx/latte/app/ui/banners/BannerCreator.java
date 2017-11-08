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
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(onItemClickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }
}
