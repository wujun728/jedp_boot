package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zsq on 16/12/20.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }
}
