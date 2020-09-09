package com.BBS.utils;



import java.util.List;

/**
 * Created by zlq on 2019/5/3.
 * 自定义分页工具类
 */
public class PageUtils<T> {
    private int pageSize=10;//每页显示的数据
    private int totalCount ;//总条数
    private int start;//开始条数
    private int pageNo;//当前页
    private int totalPages;//总页数
    protected Object parameter; //可设置参数
    private List<T> pagelist;//数据源

    public PageUtils() {

    }
    public PageUtils( int totalCount) {
        super();
        this.totalCount = totalCount;
    }
    /*
     *获取下一条数据
     */
    public int getCurrentPageNo() {
        return start / pageSize + 1;
    }

    /*
      判断是否有下一条数据
     */
    public boolean getHasNextPage() {
        return getCurrentPageNo() < totalPages;
    }

    //判断当前页是否大于1
    public boolean getHasPavPage() {
        return getCurrentPageNo() > 1;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    //设置当前页数据的开始条数
    public int getStart(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        } else if (getTotalPages() > 0 && pageNo > getTotalPages()) {
            pageNo = getTotalPages();
        }
        start = (pageNo - 1) * pageSize;
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    //获取数据中页数
    public int getTotalPages() {
        totalPages = totalCount / pageSize;
        if (totalPages % pageSize != 0) {
            totalPages++;
        }
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getPagelist() {
        return pagelist;
    }

    public void setPagelist(List<T> pagelist) {
        this.pagelist = pagelist;
    }


    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
