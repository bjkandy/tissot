package com.kandy.tissot.core.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bjkandy on 2016/1/20.
 */
public class PageResult<T> implements Serializable{
	private static final long serialVersionUID = 1369984177274990188L;

	private final static int defaultPageSize = 15;//每页默认条数

    private int totalRows = 0;//总记录数
    private int curPage = 1;//当前页码
    private int pageSize = 15;//每页记录数
    private List<T> list = null;//数据列表

    public int getTotalPage() {
        return  (int) Math.ceil(this.totalRows / this.pageSize) + (this.totalRows % this.pageSize > 0 ? 1:0);
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if(pageSize<=0){
            this.pageSize = defaultPageSize;
        }
    }
    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
