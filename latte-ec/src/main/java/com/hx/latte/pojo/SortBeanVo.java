package com.hx.latte.pojo;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by hexiao on 2017/11/15.
 */

public class SortBeanVo extends SectionEntity<SecondCategoryVo> {
    private Integer firstId;
    private Integer rootParentId;
    private String firstSection;

    public SortBeanVo(SecondCategoryVo secondCategoryVo) {
        super(secondCategoryVo);
    }

    public SortBeanVo(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public Integer getRootParentId() {
        return rootParentId;
    }

    public void setRootParentId(Integer rootParentId) {
        this.rootParentId = rootParentId;
    }

    public String getFirstSection() {
        return firstSection;
    }

    public void setFirstSection(String firstSection) {
        this.firstSection = firstSection;
    }

    @Override
    public String toString() {
        return "SortBeanVo{" +
                "firstId=" + firstId +
                ", rootParentId=" + rootParentId +
                ", firstSection='" + firstSection + '\'' +
                '}';
    }
}
