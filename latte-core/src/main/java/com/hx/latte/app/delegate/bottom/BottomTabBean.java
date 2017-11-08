package com.hx.latte.app.delegate.bottom;

/**
 * Created by hx on 2017/10/31 0031.
 * email:362970502@qq.com
 * des:底部内容bean
 */

public final class BottomTabBean {
    private final CharSequence ICON;//图标
    private final CharSequence TITLE;//文字

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }

    @Override
    public String toString() {
        return "BottomTabBean{" +
                "ICON=" + ICON +
                ", TITLE=" + TITLE +
                '}';
    }
}
