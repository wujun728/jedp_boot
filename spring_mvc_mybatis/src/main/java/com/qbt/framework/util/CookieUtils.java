/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: CookieUtils.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;

/**<p>Class: CookieUtils.java</p>
 * <p>Description: cookie操作工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class CookieUtils {

	private final static int DEFALUT_AGE = -1;
	
	//域名
	private final static String DOMAIN = "";
	//路径
	private final static String PATH = "";
	
	/**
	 * 获取cookie中的值
	 * @param request
	 * @param key
	 * @return if noting return null
	 */
	public static String get(HttpServletRequest request, String key){
		Cookie[] cookies = request.getCookies();
		if (ArrayUtils.isEmpty(cookies)) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if( cookie.getName().equals(key) ){
				setCookieProperties(request, cookie);
				return cookie.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 和其他子域共享同一个cookie
	 * @param request
	 * @param cookie
	 */
	public static void setCookieProperties(HttpServletRequest request, Cookie cookie){
		if(cookie != null){
			cookie.setDomain(getDoMain(request));
			cookie.setPath(PATH);
		}
	}
	
	/**
	 * 默认游览器退出
	 * 删除该cookie
	 * @param response
	 * @param key
	 * @param value
	 */
	public static void put(HttpServletResponse response,HttpServletRequest request, String key, String value){
		put(response, request, key, value, DEFALUT_AGE);
	}
	
	/**
	 * 放入cookie
	 * @param response
	 * @param key
	 * @param value
	 * @param maxAge cookie生存时间
	 */
	public static void put(HttpServletResponse response, HttpServletRequest request, String key, String value, int maxAge){
		Cookie cookie = new Cookie(key, EncodeUtils.urlToUTF8(value));
		cookie.setHttpOnly(true);
		cookie.setMaxAge(maxAge);
		setCookieProperties(request, cookie);
		response.addCookie(cookie);
	}
	
	/**
	 * 清除cookie
	 * @param response
	 * @param request
	 * @param key
	 */
	public static void remove(HttpServletResponse response,HttpServletRequest request, String key){
		put(response, request, key, null, 0);
	}
	
	/**
	 * 移除所有的cookie
	 * @param response
	 * @param request
	 * @param key
	 */
	public static void removeAll(HttpServletResponse response,HttpServletRequest request, String key){
		Cookie[] cookies = request.getCookies();
		if (ArrayUtils.isEmpty(cookies)) {
			return ;
		}
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			cookie.setValue(null);
			cookie.setPath("");
			cookie.setDomain("");
		}
	}
	
	/**
	 * 获取服务器域名
	 * @param request
	 * @return 服务器域名
	 */
	public static String getDoMain(HttpServletRequest request){
		String serverName = request.getServerName();
		int index = serverName.indexOf(DOMAIN);
		if(index > 0){
			serverName = serverName.substring(index);
		}
		return serverName;
	}
	
}
