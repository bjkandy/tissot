package com.kandy.tissot.web.common.layui;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kandy on 2021/1/25.
 */
public class LayQuery extends LinkedHashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    //当前页码
    private int page;
    //每页条数
    private int limit;

    public LayQuery(Map<String, Object> params){
        this.putAll(params);
        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
