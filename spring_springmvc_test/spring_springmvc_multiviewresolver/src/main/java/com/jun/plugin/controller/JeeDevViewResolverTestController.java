package com.jun.plugin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.vo.User;

/**
 * 多视图解析器支持示例
 * 
 * @author Wujun
 *         http://localhost:8080/view/demo/test2
 *         http://localhost:8080/view/demo/test3
 */
@Controller
@RequestMapping(value = "/demo")
public class JeeDevViewResolverTestController {
	private static Log logger = LogFactory.getLog(JeeDevViewResolverTestController.class);

	@RequestMapping(value = "/test1")
	public String test1(HttpServletRequest request, ModelMap map) {
		logger.info("使用JSP视图解析器");
		map.put("message", "hello world jsp view ");
		return "jspTest.jsp";
	}

	@RequestMapping(value = "/test2")
	public String test2(HttpServletRequest request, ModelMap map) {
		logger.info("使用Freemarker视图解析器");
		map.put("name", "hello world Freemarker view ");
		return "hello.ftl";
	}

	@RequestMapping(value = "/test3")
	public String test3(HttpServletRequest request, ModelMap map) {
		logger.info("使用Velocity视图解析器");
		map.put("name", "hello world");
		return "/html/demo.htm";
	}

	@RequestMapping(value = "/test4")
	@ResponseBody
	public String test4(HttpServletRequest request, ModelMap map, @RequestBody User user) {
		map.put("name", "hello world");
		return "sucess";
	}

	@RequestMapping(value = "/test5")
	@ResponseBody
	public Object test(HttpSession session) {
		Map m = new HashMap<>();
		m.put("abc", "123");
		return m;
	}
}