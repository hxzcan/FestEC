package com.hx.latte.pojo;

import java.util.Date;

/**
 * Created by hx on 2017/10/27 0027.
 * email:362970502@qq.com
 * des:
 */

public class Category {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Long createTime;

    private Long updateTime;

    public Integer getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", sortOrder=" + sortOrder +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
