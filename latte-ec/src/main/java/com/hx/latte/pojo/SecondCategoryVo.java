package com.hx.latte.pojo;

/**
 * Created by hexiao on 2017/11/15.
 */

public class SecondCategoryVo {
    private Integer secondId;
    private Integer firstParentId;
    private String secondSection;

    public Integer getSecondId() {
        return secondId;
    }

    public void setSecondId(Integer secondId) {
        this.secondId = secondId;
    }

    public Integer getFirstParentId() {
        return firstParentId;
    }

    public void setFirstParentId(Integer firstParentId) {
        this.firstParentId = firstParentId;
    }

    public String getSecondSection() {
        return secondSection;
    }

    public void setSecondSection(String secondSection) {
        this.secondSection = secondSection;
    }

    @Override
    public String toString() {
        return "SecondCateoryVo{" +
                "secondId=" + secondId +
                ", firstParentId=" + firstParentId +
                ", secondSection='" + secondSection + '\'' +
                '}';
    }
}
