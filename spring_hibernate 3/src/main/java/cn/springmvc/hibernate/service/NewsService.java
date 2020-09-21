package cn.springmvc.hibernate.service;

import java.util.List;

import cn.springmvc.hibernate.model.News;
import cn.springmvc.hibernate.model.User;

/**
 * @author Vincent.wang
 *
 */
public interface NewsService {

    public void addNews(News news, User user);

    public List<News> findNews();

    public List<News> findNewsByKeywords(String keywords);

}