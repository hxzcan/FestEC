package com.hx.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by hx on 2017/8/31 0031.
 * email:362970502@qq.com
 * des:字体样式
 */

public class FontModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return ECIons.values();
    }
}
