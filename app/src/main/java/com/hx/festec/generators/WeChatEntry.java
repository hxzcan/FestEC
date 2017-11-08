package com.hx.festec.generators;

import com.example.annotations.EntryGenerator;
import com.hx.latte.app.weiChat.templates.WXEntryTemplate;

/**
 * Created by hx on 2017/10/30 0030.
 * email:362970502@qq.com
 * des:
 */
@EntryGenerator(
        packageName="com.hx.festec",
        entryTemplete= WXEntryTemplate.class
)
public interface WeChatEntry {

}
