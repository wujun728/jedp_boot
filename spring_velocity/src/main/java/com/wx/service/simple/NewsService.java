package com.wx.service.simple;

import java.util.List;

import com.wx.common.base.model.Page;
import com.wx.entity.simple.News;

/**
 * @author Vincent.wang
 *
 */
public interface NewsService {

    public void addNews(News news);

    public List<News> findNewsByKeywords(String keywords);

    public Page<News> findNewsByPage(Page<News> page, String keywords);

}