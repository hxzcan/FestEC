package com.hx.festec.generators;

import com.example.annotations.AppRegisterGenerator;
import com.hx.latte.app.weiChat.templates.WXAppRegisterTemlate;


/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:
 */
@AppRegisterGenerator(
        packageName="com.hx.festec",
        registerTemplete= WXAppRegisterTemlate.class
)
public interface WeChatAppRegister {
}
