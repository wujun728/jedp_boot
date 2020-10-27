package com.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by zsq on 16/12/21.
 * 服务端推送
 */
public class SSEController {

    /**
     * 每 5s 向浏览器推送随机消息
     * 返回媒体类型为 text/event-stream 这是服务器端SSE的支持
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "push", produces = "text/event-stream")
    public String push() {
        Random random = new Random();
        try {
            Thread.sleep(5000);  // 5s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:test:"+random.nextInt()+"\n\n";
    }
}
