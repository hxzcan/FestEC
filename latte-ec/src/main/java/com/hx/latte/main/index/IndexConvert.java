package com.hx.latte.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hx.latte.app.ui.recyclerView.DataConverter;
import com.hx.latte.app.ui.recyclerView.MultipleFieldsEnum;
import com.hx.latte.app.ui.recyclerView.MultipleItemEntity;

import java.util.List;

/**
 * Created by hx on 2017/11/3 0003.
 * email:362970502@qq.com
 * des:解析json数据
 */

public class IndexConvert extends DataConverter {
    @Override
    public List<MultipleItemEntity> convert() {
        //阿里巴巴json解析
        JSONObject jsonObject= JSON.parseObject(getJsonData()).getJSONObject("data");
        Integer totalNumber=jsonObject.getInteger("total");
        Integer pages=jsonObject.getInteger("pages");
        JSONArray list=jsonObject.getJSONArray("list");
        int spanSize=0;
        int dataSize=list.size();
            for (int j=0;j<dataSize;j++){
                JSONObject dataObject=list.getJSONObject(j);
                Integer productId=dataObject.getInteger("productId");
                String name=dataObject.getString("name");
                String subTitle=dataObject.getString("subtitle");
                String mainImage=dataObject.getString("mainImage");
                String price=dataObject.getString("price");
                Integer stock=dataObject.getInteger("stock");
                Integer itemType=dataObject.getInteger("itemType");
                if (itemType==3){
                    spanSize=2;
                }else {
                    spanSize=1;
                }
               MultipleItemEntity multiItemEntity=MultipleItemEntity.builder().setField(MultipleFieldsEnum.ITEM_TYPE,itemType)
                        .setField(MultipleFieldsEnum.PRODUCT_ID,productId)
                        .setField(MultipleFieldsEnum.NAME,name)
                        .setField(MultipleFieldsEnum.DES,subTitle)
                        .setField(MultipleFieldsEnum.MIAN_IMAGE,mainImage)
                        .setField(MultipleFieldsEnum.SPAN_SIZE,spanSize)
                        .setField(MultipleFieldsEnum.PAGE_NUMBER,pages)
                        .setField(MultipleFieldsEnum.TOATLE_NUMBER,totalNumber)
                        .setField(MultipleFieldsEnum.PRICE,price)
                        .setField(MultipleFieldsEnum.STOCK,stock)
                        .build();
                multiItemEntities.add(multiItemEntity);

            }
        return multiItemEntities;
    }
}
