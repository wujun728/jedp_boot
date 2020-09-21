/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.security</p>
 * <p>File: CsrfInterceptor.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**<p>Class: CsrfInterceptor.java</p>
 * <p>Description: CSRF校验拦截器</p>
 * <pre>
 *     这里只过滤POST请求    
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class CsrfInterceptor extends HandlerInterceptorAdapter {

	
	

	private CsrfTokenManager csrfTokenManager;
	

	public void setCsrfTokenManager(CsrfTokenManager csrfTokenManager) {
		this.csrfTokenManager = csrfTokenManager;
	}


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if( "POST".equalsIgnoreCase(request.getMethod()) ){
			String token = csrfTokenManager.getRequestToken(request);
			if( StringUtils.isBlank(token) ){
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
			
			String cachetoken = csrfTokenManager.getSessionToken(request);
			if( StringUtils.isBlank(cachetoken) ){
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
			
			if( !token.equals(cachetoken) ){
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
		}
		return true;
	}
	
	
	
	/**
	 * 获取当前的URL
	 * @param request
	 * @return
	 */
	/*private String getCurrentUrl(HttpServletRequest request){
		String currentUrl = request.getRequestURL().toString();
		if( !StringUtils.isEmpty(request.getQueryString()) ){
			currentUrl += "?" + request.getQueryString();
		}
		return currentUrl;
	}*/
	
	
	
}
