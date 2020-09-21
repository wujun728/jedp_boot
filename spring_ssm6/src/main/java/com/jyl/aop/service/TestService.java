/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.aop.service;

import org.apache.log4j.Logger;

import com.jyl.system.user.model.User;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月26日 下午4:55:41
 */
public class TestService {
	
	private final static Logger log = Logger.getLogger(TestService.class);
	
	public User get(long id){
		if(log.isInfoEnabled()){
			log.info("getUser method . . .");
		}
		return new User();
	}
	
	public void save(User user){
		if(log.isInfoEnabled()){
			log.info("saveUser method . . .");
		}
	}
	
	public boolean delete(long id) throws Exception{
		if(log.isInfoEnabled()){
			log.info("delete method . . .");
			throw new Exception("spring aop ThrowAdvice演示");
		}
		return false;
	}

}
