package com.hx.latte.app.common;

import com.hx.latte.app.utils.storage.LattePreference;

/**
 * Created by hexiao on 2017/12/1.
 * 用户的信息
 */

public class UserMessage {
    //token值
    public static final String USER_TOKEN= LattePreference.getCustomAppProfile("token");
    //userId
    public static final String USER_ID=LattePreference.getCustomAppProfile("userId");
}
