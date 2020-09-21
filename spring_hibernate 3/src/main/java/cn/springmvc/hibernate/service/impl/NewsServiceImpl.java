package cn.springmvc.hibernate.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.hibernate.dao.NewsDaoService;
import cn.springmvc.hibernate.model.News;
import cn.springmvc.hibernate.model.User;
import cn.springmvc.hibernate.service.NewsService;

/**
 * @author Vincent.wang
 *
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDaoService newsDaoService;

    @Override
    public void addNews(News news, User user) {
        if (news != null) {
            news.setUserId(user.getId());
            news.setCreateTime(Calendar.getInstance().getTime());
            newsDaoService.save(news);
        }
    }

    @Override
    public List<News> findNews() {
        return newsDaoService.findNews();
    }

    @Override
    public List<News> findNewsByKeywords(String keywords) {
        return newsDaoService.findNewsByKeywords(keywords);
    }

}
