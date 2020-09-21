package ${packageName}.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PaginationSupport<T>
{
    private List<T> items; // 当前页的数据
    
    private long next; // 下一页
    
    private long pageNo; // 当前页号
    
    private long pageSize; // 每页大小
    
    private long pre; // 上一页
    
    private long total; // 总记录数
    
    private long totalPage; // 总页数
    
    public PaginationSupport(long total, int pageNo, int pageSize)
    {
        items = new ArrayList<>();
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        setTotal(total);
    }
    
    public List<T> getItems()
    {
        return items;
    }
    
    public long getNext()
    {
        return next;
    }
    
    public long getPageNo()
    {
        return pageNo;
    }
    
    public long getPageSize()
    {
        return pageSize;
    }
    
    public long getTotal()
    {
        return total;
    }
    
    public long getTotalPage()
    {
        return totalPage;
    }
    
    public void setItems(List<T> items)
    {
        this.items = items;
    }
    
    public void setNext(long next)
    {
        this.next = next;
    }
    
    public void setPageNo(long pageNo)
    {
        this.pageNo = pageNo;
    }
    
    public void setPageSize(long pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public long getPre()
    {
        return pre;
    }
    
    public void setPre(long pre)
    {
        this.pre = pre;
    }
    
    public void setTotal(long totalcount)
    {
        total = totalcount;
        if (totalcount == 0)
        {
            totalPage = 0;
        }
        else
        {
            totalPage = 1 + (totalcount - 1) / pageSize;
        }
        if (pageNo < 1)
        {
            pageNo = 1;
        }
        else if (pageNo > totalPage)
        {
            pageNo = totalPage;
        }
        if (1 == pageNo)
        {
            pre = 1;
        }
        else
        {
            pre = pageNo - 1;
        }
        if (totalPage == pageNo)
        {
            next = pageNo;
        }
        else
        {
            next = pageNo + 1;
        }
    }
    
    public void setTotalPage(long totalPage)
    {
        this.totalPage = totalPage;
    }
}

