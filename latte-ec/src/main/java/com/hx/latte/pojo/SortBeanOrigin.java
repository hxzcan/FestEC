package com.hx.latte.pojo;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * Created by hexiao on 2017/11/15.
 * 封装的右侧的内容
 */

public class SortBeanOrigin {
    private Integer firstId;
    private Integer rootParentId;
    private String firstSection;
    private List<SecondCateoryVo> secondCateoryVos;

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

    public List<SecondCateoryVo> getSecondCateoryVos() {
        return secondCateoryVos;
    }

    public void setSecondCateoryVos(List<SecondCateoryVo> secondCateoryVos) {
        this.secondCateoryVos = secondCateoryVos;
    }

    @Override
    public String toString() {
        return "SortBeanOrigin{" +
                "firstId=" + firstId +
                ", rootParentId=" + rootParentId +
                ", firstSection='" + firstSection + '\'' +
                ", secondCateoryVos=" + secondCateoryVos +
                '}';
    }

    public class SecondCateoryVo{
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
}
