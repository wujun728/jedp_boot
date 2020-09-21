/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyl.util.randomCode.RandomCodeUtils;
import com.jyl.util.redis.JedisService;
import com.jyl.util.servlet.HttpServletUtil;

import redis.clients.jedis.Jedis;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月7日 上午11:17:15
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private JedisService redisService;
	
	@RequestMapping("/redis")
	public void redis(ModelMap modelMap){
		
		Jedis jedis = redisService.borrow();
		try {
			jedis.set("key", "value");
			System.out.println(jedis.get("key"));
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally{
			redisService.returnResource(jedis);
		}
		redisService.borrow();
	}
	
}
