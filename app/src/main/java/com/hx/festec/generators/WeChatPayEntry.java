package com.hx.festec.generators;


import com.example.annotations.PayEntryGenerator;
import com.hx.latte.app.weiChat.templates.WXPayEntryTemplate;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:
 */
@PayEntryGenerator(
        packageName="com.hx.festec",
        payEntryTemplete= WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
