package com.ceking.entity;

import java.util.List;

/**
 * @author ceking
 * @desc
 * @date 2020/12/21 20:29
 */
public class Page<T> {
    public  static final  int PAGE_SIZE=4;
    private  int totalCount;
    private  int totalPage;
    private  int pageSize =PAGE_SIZE;
    private  int pageIndex;
    private List<T> data;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private  String url;
    public Page() {
    }

    public Page(int totalCount, int totalPage, int pageSize, int pageIndex, List<T> data,String url) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.data = data;
        this.url =url;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageIndex > totalPage) {
            pageIndex = totalPage;
        }
        this.pageIndex = pageIndex;
    }

    public List<T> getData() {
        return data;
    }

    public  void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", data=" + data +
                ", url='" + url + '\'' +
                '}';
    }
}
