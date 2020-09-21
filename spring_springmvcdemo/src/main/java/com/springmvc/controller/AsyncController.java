package com.springmvc.controller;

import com.springmvc.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by zsq on 16/12/21.
 */
@Controller
public class AsyncController {

    @Autowired
    PushService pushService;

    @ResponseBody
    @RequestMapping(value = "/defer")
    public DeferredResult<String> deferredCall() {
        return pushService.getAsyncUpdate();
    }
}
