package com.hx.latte.app.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hx.latte.R;
import com.hx.latte.R2;
import com.hx.latte.app.delegate.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:底部按钮布局
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener{

    private final ArrayList<BottomItemDelegate> ITEM_DELEGATE=new ArrayList<>();
    private final ArrayList<BottomTabBean> TAB_BEAN=new ArrayList<>();

    private LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS=new LinkedHashMap<>();
    private int mCurrentDelegate=0;
    private int mIndexDelegate=0;
    private int mClickColor= Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomLinearLayout;

    public abstract LinkedHashMap<BottomTabBean,BottomItemDelegate> setItems(BottomItemBuilder itemBuilder);

    public abstract int setIndexDelegate();

    @ColorInt//表示必须是个颜色
    public abstract int setClickColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate=setIndexDelegate();
        if (setClickColor()!=0){
            mClickColor=setClickColor();
        }
        final BottomItemBuilder itemBuilder= BottomItemBuilder.builder();
        LinkedHashMap<BottomTabBean,BottomItemDelegate> items=setItems(itemBuilder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean,BottomItemDelegate> item:ITEMS.entrySet()){
            BottomTabBean bottomTabBean=item.getKey();
            BottomItemDelegate bottomItemDelegate=item.getValue();
            TAB_BEAN.add(bottomTabBean);
            ITEM_DELEGATE.add(bottomItemDelegate);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size=ITEMS.size();
        for (int i=0;i<size;i++){
            //绑定布局，第一个参数是子布局，第二个是父布局
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_icon_text_layout,mBottomLinearLayout,true);
            //取出每一个RelativeLayout里面的内容
            final RelativeLayout item= (RelativeLayout) mBottomLinearLayout.getChildAt(i);
            //设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            final IconTextView itemIcon= (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle= (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean tabBean=TAB_BEAN.get(i);
            //初始化数据
            itemIcon.setText(tabBean.getIcon());
            itemTitle.setText(tabBean.getTitle());
            //如果循环的正好是选中的的那个Tab
            if (i==mIndexDelegate){
                itemIcon.setTextColor(mClickColor);
                itemTitle.setTextColor(mClickColor);
            }
        }
        final SupportFragment[] delegateArray=ITEM_DELEGATE.toArray(new SupportFragment[size]);
        getSupportDelegate().loadMultipleRootFragment(R.id.delegate_container,mIndexDelegate,delegateArray);
    }

    /**
     * 重置颜色
     */
    private void reSetColor(){
        final int count=mBottomLinearLayout.getChildCount();
        for (int i=0;i<count;i++){
            final RelativeLayout item= (RelativeLayout) mBottomLinearLayout.getChildAt(i);
            final IconTextView icon= (IconTextView) item.getChildAt(0);
            final AppCompatTextView title= (AppCompatTextView) item.getChildAt(1);
            icon.setTextColor(Color.GRAY);
            title.setTextColor(Color.GRAY);
        }
    }

    //监听
    @Override
    public void onClick(View view) {
        final int tag= (int) view.getTag();
        reSetColor();
        final RelativeLayout item= (RelativeLayout) mBottomLinearLayout.getChildAt(tag);
        final IconTextView icon= (IconTextView) item.getChildAt(0);
        final AppCompatTextView title= (AppCompatTextView) item.getChildAt(1);
        icon.setTextColor(mClickColor);
        title.setTextColor(mClickColor);
        //第一个参数需要显示的fragment,第二个需要隐藏的fragment
       getSupportDelegate().showHideFragment(ITEM_DELEGATE.get(tag),ITEM_DELEGATE.get(mCurrentDelegate));
        mCurrentDelegate=tag;
    }

    /**
     * 把没有推掉的栈都退出 退出 app
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount()>1){
            popChild();
        }else {
            _mActivity.finish();
        }
        return true;
    }
}
