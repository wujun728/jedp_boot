package com.lanou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 18/2/6.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/page")
    public String page(){
        return "home";
    }
}
