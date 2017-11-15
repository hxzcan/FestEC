package com.hx.latte.app.ui.recyclerView;

import android.support.annotation.ColorInt;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by hexiao on 2017/11/8.
 */

public class BaseDecoration extends DividerItemDecoration{
    public BaseDecoration(@ColorInt final int color, final int size){
        setDividerLookup(new DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return new Divider.Builder().size(size).color(color).build();
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder().size(size).color(color).build();
            }
        });
    }

    public static BaseDecoration creator(int color,int size){
        return new BaseDecoration(color,size);
    }
}
