package com.springmvc.controller;

import com.springmvc.entity.DemoObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zsq on 16/12/21.
 */
@Controller
public class ConverterController {


    /**
     * 指定返回的媒体类型是 自定义的 application/x-wisely
     * 此处接受参数 用 @RequestBody 注解
     * @param demoObject
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/convert", produces = "application/x-wisely")
    public DemoObject convert(@RequestBody DemoObject demoObject) {
        return demoObject;
    }
}
