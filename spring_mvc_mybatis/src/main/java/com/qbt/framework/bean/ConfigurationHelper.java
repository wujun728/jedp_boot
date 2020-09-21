/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.bean</p>
 * <p>File: ConfigurationHelper.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.bean;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**<p>Class: ConfigurationHelper.java</p>
 * <p>Description: 读取配置文件的工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class ConfigurationHelper  {
	
	protected static Logger logger = LogManager.getLogger(ConfigurationHelper.class);

	public static Configuration getConfiguration(String fileName){
		try {
			return new PropertiesConfiguration(fileName);
		} catch (ConfigurationException e) {
			logger.error("加载【" +fileName+"】配置文件失败" , e);
		}
		return null;
	}
	
}
