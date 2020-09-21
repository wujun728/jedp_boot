package cn.springmvc.hibernate.dao;

import java.util.List;

import cn.springmvc.hibernate.model.News;

/**
 * @author Vincent.wang
 *
 */
public interface NewsDaoService {

    public News save(News news);

    public List<News> findNews();

    public List<News> findNewsByKeywords(String keywords);
}
