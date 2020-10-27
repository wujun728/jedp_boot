package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zsq on 16/12/20.
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

    @ResponseBody
    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public String index(HttpServletRequest request) {
        return "url:" +request.getRequestURL()+ "can access";
    }

    @ResponseBody
    @RequestMapping(value = "/pathVar/{id}", produces = "application/json;charset=UTF-8")
    public String demoPathVar(HttpServletRequest request, @PathVariable("id") String id) {
        return "url:" +request.getRequestURL()+ "can access, id="+id;
    }

    @ResponseBody
    @RequestMapping(value = {"/comb/name1", "/comb/name2"}, produces = "application/json;charset=UTF-8")
    public String demoCombination(HttpServletRequest request) {
        return "url:" +request.getRequestURL()+ "can access";
    }
}
