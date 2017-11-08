package com.hx.latte.app.ui.recyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Created by hx on 2017/11/2 0002.
 * email:362970502@qq.com
 * des:
 */

public class MultipleItemEntity implements MultiItemEntity {
    //会在内存不足的时候进行内存释放
    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE=new ReferenceQueue<>();

    private final LinkedHashMap<Object,Object> MULTIPLE_FIELD=new LinkedHashMap<>();

    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE=
            new SoftReference<LinkedHashMap<Object, Object>>(MULTIPLE_FIELD,ITEM_QUEUE);
    //为了限制recyclerView的样式


    public static MultiItemEntityBuilder builder(){
        return new MultiItemEntityBuilder();
    }

     MultipleItemEntity(LinkedHashMap<Object,Object> map) {
        FIELDS_REFERENCE.get().putAll(map);
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFieldsEnum.ITEM_TYPE);
    }

    public  <T> T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public  LinkedHashMap<?,?> getFileds(){
        return FIELDS_REFERENCE.get();
    }

}
