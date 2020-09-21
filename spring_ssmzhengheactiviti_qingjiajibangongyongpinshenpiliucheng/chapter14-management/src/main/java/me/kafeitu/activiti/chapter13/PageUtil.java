package me.kafeitu.activiti.chapter13;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具
 *
 * @author henryyan
 */
public class PageUtil {

    public static int PAGE_SIZE = 10;

    public static int[] init(Page<?> page, HttpServletRequest request) {
        int pageNumber = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("p"), "1"));//如果第一个参数为空，则返回第二个。否则返回第一个；
        page.setPageNo(pageNumber);
        if (page.getPageSize() == -1) {
            int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("ps"), String.valueOf(PAGE_SIZE)));//如果第一个参数为空，则返回第二个。否则返回第一个；
            page.setPageSize(pageSize);
        }
        int firstResult = page.getFirst() - 1;
        int maxResults = page.getPageSize();
        return new int[]{firstResult, maxResults};
    }

}
