package com.gqs.api;

import java.io.Serializable;

/**
 * @author 李志波
 * @since 2019-02-12
 */
public class PageQuery implements Serializable {


    private static final long serialVersionUID = 3058056643546401578L;

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 每页的数量
     */
    private Integer pageSize = 20;

    /**
     * 游标
     */
    private String cursorMark;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCursorMark() {
        return cursorMark;
    }

    public void setCursorMark(String cursorMark) {
        this.cursorMark = cursorMark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageQuery{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", cursorMark='").append(cursorMark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
