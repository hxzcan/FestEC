package com.hx.latte.app.ui.recyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by hx on 2017/11/3 0003.
 * email:362970502@qq.com
 * des:
 */

public class MultiItemEntityBuilder {
    private final LinkedHashMap<Object,Object> FIELDS=new LinkedHashMap<>();

    public MultiItemEntityBuilder() {
        //先清除以前的数据
        FIELDS.clear();
    }

    //设置类型
    public MultiItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFieldsEnum.ITEM_TYPE,itemType);
        return this;
    }

    //设置属性
    public MultiItemEntityBuilder setField(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }

    //设置所有的属性
    public MultiItemEntityBuilder setAllFields(LinkedHashMap<Object,Object> fields){
        FIELDS.putAll(fields);
        return this;
    }

    public  MultipleItemEntity build(){
        return new MultipleItemEntity(FIELDS);
    }




}
