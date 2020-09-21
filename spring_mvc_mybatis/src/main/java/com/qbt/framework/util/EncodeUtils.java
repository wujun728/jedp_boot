/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: EncodeUtils.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qbt.framework.constant.Constant;

/**<p>Class: EncodeUtils.java</p>
 * <p>Description: 编码和转码的工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class EncodeUtils {

	protected static Logger logger = LogManager.getLogger(EncodeUtils.class);
	
	/**
	 * URLEncoder转成UTF-8编码
	 * @param str
	 * @param encode
	 * @return
	 */
	public static String urlToUTF8(String str){
		try {
			return URLEncoder.encode(str, Constant.UTF8);
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return null;
	}
}
