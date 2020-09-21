/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.aop;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jyl.aop.service.TestService;
import com.jyl.system.user.model.User;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月26日 下午4:54:19
 */
public class Test {
	
	private final static Logger log = Logger.getLogger(Test.class);
	
	public static void main(String[] args) {
		
		//启动Spring容器
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext-aop.xml");
		
		//获取service组件
		TestService testService = (TestService) context.getBean("testService");
		
		User user = testService.get(1L);
		testService.save(user);
		try {
			testService.delete(1L);
		} catch (Exception e) {
			if(log.isDebugEnabled()){
				log.debug("Delete user : " + e.getMessage());
			}
		}
	}

}
