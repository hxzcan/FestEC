package com.hx.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by hx on 2017/8/31 0031.
 * email:362970502@qq.com
 * des:
 */

public enum  ECIons implements Icon{
    icon_sacn('\ue66e');

    private char character;
    ECIons(char character){
        this.character=character;
    }
    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return character;
    }
}
