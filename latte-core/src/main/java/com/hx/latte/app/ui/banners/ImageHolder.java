package com.hx.latte.app.ui.banners;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hx.latte.app.ui.glide.GlideApp;

/**
 * Created by hexiao on 2017/11/7.
 * 存放image，显示image
 */

public class ImageHolder implements Holder<String>{

    private AppCompatImageView imageView=null;

    @Override
    public View createView(Context context) {
        imageView=new AppCompatImageView(context);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        GlideApp.with(context)
                .load(data)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(imageView);
    }
}
