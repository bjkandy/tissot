package com.kandy.tissot.dal.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bjkandy on 2015/12/29.
 */
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -46538079178546068L;

    private Long              id;
    private Long              version          = 200L;
    private Date              createTime;
    private Date              updateTime;

    private Integer           offset;
    private Integer           size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
