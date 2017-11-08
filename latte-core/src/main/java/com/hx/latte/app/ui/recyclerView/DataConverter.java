package com.hx.latte.app.ui.recyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by hx on 2017/11/2 0002.
 * email:362970502@qq.com
 * des:数据转换
 */

public abstract class DataConverter {
    protected ArrayList<MultipleItemEntity> multiItemEntities=new ArrayList<>();

    private String jsonData=null;

    public  abstract List<MultipleItemEntity> convert();

    public DataConverter setJsonData(String jsonData){
        this.jsonData=jsonData;
        return this;
    }

    public String getJsonData(){
        if (jsonData==null||jsonData.isEmpty()){
            throw new NullPointerException("jsonData IS null");
        }
        return jsonData;
    }

}
