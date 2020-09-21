package cn.springmvc.hibernate.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.springmvc.hibernate.model.News;
import cn.springmvc.hibernate.service.NewsService;
import cn.springmvc.hibernate.web.command.NewsCommand;
import cn.springmvc.hibernate.web.util.WebUtil;
import cn.springmvc.hibernate.web.validator.NewsValidator;

/**
 * @author Vincent.wang
 *
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    /*
     * 表单提交日期绑定
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNews() {
        return "news/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("mewsCommand") NewsCommand command, BindingResult result) {
        new NewsValidator().validate(command, result);
        if (result.hasErrors()) {
            return "news/add";
        }
        News news = new News();
        news.setTitle(command.getTitle());
        news.setDescription(command.getDescription());
        news.setAddress(command.getAddress());
        news.setNewsTime(command.getNewsTime());
        newsService.addNews(news, WebUtil.getUser());
        return "news/search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        return "news/search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public List<News> search(@RequestParam(value = "keywords", required = false) String keywords) {
        return newsService.findNewsByKeywords(keywords);
    }
}
