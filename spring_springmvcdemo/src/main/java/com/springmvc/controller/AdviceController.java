package com.springmvc.controller;

import com.springmvc.entity.DemoObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zsq on 16/12/20.
 */
@Controller
public class AdviceController {

    /**
     * 异常发生后,就返回在@ExceptionHandler 注解的全局异常处理, 这里是解析到error.jsp页面
     * 而且这边抛出异常之后,自定义的拦截器只是对请求发送前拦截执行preHandle()方法,
     * 请求完成后并没有执行postHandle()方法.
     * @param msg  获取定义在全局的model attribute
     * @param demoObject
     * @return
     */
    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, @RequestParam(required = false) DemoObject demoObject) {
        throw new IllegalArgumentException("参数有误,来自@ModelAttribute"+msg);
    }
}
