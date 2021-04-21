package com.kandy.tissot.dal.param;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bjkandy on 2015/12/29.
 */
public abstract class BaseParam implements Serializable {
    private static final long serialVersionUID    = -4045214226807657088L;

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

    public Date getcreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getupdateTime() {
        return updateTime;
    }

    public void setupdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOffset() {
        return offset;
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
