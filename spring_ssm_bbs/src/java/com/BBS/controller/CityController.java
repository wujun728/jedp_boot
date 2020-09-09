package com.BBS.controller;

import com.BBS.pojo.User;
import com.BBS.service.impl.CityserviceImpl;
import com.BBS.service.impl.UserserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dyl on 2018/7/19.
 */
@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityserviceImpl cityservice;
    @Autowired
    private UserserviceImpl userservice;

    @RequestMapping("/getcityname")
    public ModelAndView hello(ModelAndView mv, @RequestParam(value = "id") String id) {
        String name = cityservice.getCityName(id);
        mv.addObject("cityname", name);
        mv.setViewName("cityname");
        return mv;
    }

    @RequestMapping("/jquerytest")
    public ModelAndView login(ModelAndView mv) {
        mv.setViewName("jsp/index");

        return mv;
    }


    @RequestMapping(value = "/register")
    public ModelAndView register(ModelAndView mv, HttpServletRequest request, HttpServletResponse response, User user) {
        //拿到用户会话
//         (User) request.getSession().getAttribute("username");
//        String name = userservice.findByname(user.getUsername());
//        String  Pw = userservice.loginPw(user.getPassword());
//        String Pw="1";
//        if (name==null ||  Pw == null) {
//            request.setAttribute("error_msg","用户名或密码错误！");
//            mv.setViewName("/register");
//
//        }else {
//
//        }.32
        mv.setViewName("jsp/test");

        return mv;
    }

    @RequestMapping("/re")
    public ModelAndView relogin(ModelAndView modelAndView) {
        modelAndView.setViewName("jsp/login");
        return modelAndView;
    }

    @RequestMapping("/ele")
    public ModelAndView element(ModelAndView modelAndView) {
        modelAndView.setViewName("jsp/test");
        return modelAndView;
    }

    @RequestMapping("/test")
    public ModelAndView test(ModelAndView modelAndView) {
        modelAndView.setViewName("jsp/register1");
        return modelAndView;
    }

    @RequestMapping("/user")
    public ModelAndView getuserlist(ModelAndView modelAndView) {
        modelAndView.setViewName("jsp/userlist");
        return modelAndView;
    }

    @RequestMapping("/bbs")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs_jsp/index");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView Login(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs_jsp/user/Login");
        return modelAndView;
    }
    @RequestMapping("/reg")
    public ModelAndView Register(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs_jsp/user/Register");
        return modelAndView;
    }


    @RequestMapping("/index")
    public ModelAndView userindex(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs_jsp/user/index");
        return modelAndView;
    }
    @RequestMapping("/usermessage")
    public ModelAndView usermessage(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs_jsp/user/UserMessage");
        return modelAndView;
    }
    @RequestMapping("/userset")
    public ModelAndView userset(ModelAndView modelAndView) {
        modelAndView.setViewName("bbs_jsp/user/set");
        return modelAndView;
    }


}
