package com.wuzy.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登陆后首页controller
 * Created by wuzy
 * on 2017-01-14 15:54.
 */
@Controller
@RequestMapping("/sys")
public class SysIndexController {
    /**
     * 系统后台（首页）
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "sys/index";
    }

}
