/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: SpringContextHelper.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**<p>Class: SpringContextHelper.java</p>
 * <p>Description: spring 容器工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class SpringContextHelper implements DisposableBean, ApplicationContextAware{

	private static ApplicationContext applicationContext = null;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextHelper.applicationContext = applicationContext;
	}

	public void destroy() throws Exception {
		applicationContext = null;
		
	}
	
	/**
	 *  获取Bean对象
	 * <pre>
	 * 		通过Class来获取
	 * </pre>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz){
		return (T) applicationContext.getBean(clazz);
	}
	
	/**
	 * 获取Bean对象
	 * <pre>
	 * 	通过类名来获取
	 * </pre>
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
		return (T) applicationContext.getBean(beanName);
	}
	
	
}
