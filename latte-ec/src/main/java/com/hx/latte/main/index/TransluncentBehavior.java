package com.hx.latte.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.hx.latte.app.ui.recyclerView.RGBValue;
import com.hx.latte.ec.R;


/**
 * Created by hexiao on 2017/11/14.
 * 设置下拉时的
 * Toolbar是要变化的控件
 */


@SuppressWarnings("ALL")
public class TransluncentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    //顶部距离
    private int mTopDistance=0;
    //颜色变化速度
    private static int SHOW_SPEED=3;
    //定义颜色
    private final RGBValue rgbValue=RGBValue.createRgbVaule(255,124,2);

    /**
     * 这个构造方法必须要存在，是通过反射的方法去读取behavior
     * @param context
     * @param attrs
     */
    public TransluncentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 被依赖的控件，根据依赖的控件的变化而变化,ToolBar根据recyclerView的变化而变化
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId()== R.id.index_recyclerView;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        //增加滑动距离
        mTopDistance+=dyConsumed;
        //toolBar的高度
        int targetheight=child.getHeight();
        //当滑动时，并且距离小于toolBar的高度时，开始调整渐变色
        if (mTopDistance>0&&mTopDistance<targetheight){
            float scale=(float) mTopDistance/targetheight;
            float alpha=scale*255;
            child.setBackgroundColor(Color.argb((int) alpha,rgbValue.red(),rgbValue.green(),rgbValue.blue()));
        }else if (mTopDistance>targetheight){
            child.setBackgroundColor(Color.rgb(rgbValue.red(),rgbValue.green(),rgbValue.blue()));
        }
    }
}
