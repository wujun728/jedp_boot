/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: WebPathUtils.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.util.WebUtils;

/**<p>Class: WebPathUtils.java</p>
 * <p>Description: WEB项目路径工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class WebPathUtils {

	
	protected static Logger logger = LogManager.getLogger(WebPathUtils.class);
	
	/**
	 * 
	 * <p>描述：获取项目当前路径</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param request
	 * @return
	 */
	public static String getSuffixPath(HttpServletRequest request){
		try {
			return WebUtils.getRealPath(request.getServletContext(), "/");
		} catch (FileNotFoundException e) {
			logger.error(e);
		}
		return null;
	}
	
}
