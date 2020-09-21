/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.security</p>
 * <p>File: CsrfInterceptor.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.qbt.framework.manager.SessionManager;
import com.qbt.framework.util.RandomUtil;

/**<p>Class: CsrfRequestDataValueProcessor.java</p>
 * <p>Description: csrf token管理器</p>
 * <pre>
 *     csrf token管理器
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class CsrfTokenManager {
	
	// 隐藏域参数名称
	public static final String CSRF_PARAM_NAME = "csrfToken";
	
	public static final String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CsrfTokenManager.class
			.getName() + ".tokenval";
	
	private SessionManager<String> sessionManager;

	public void setSessionManager(SessionManager<String> sessionManager) {
		this.sessionManager = sessionManager;
	}

	/**
	 * 创建Token
	 * <pre></pre>
	 * @param request
	 * @param response
	 * @return
	 */
	public String createTokenForRequest(HttpServletRequest request) {
		String token = null;
		synchronized (request) {
			token = sessionManager.get(request, CSRF_PARAM_NAME);
			if( StringUtils.isBlank(token)){
				token = RandomUtil.buildUUID();
				sessionManager.put(request, CSRF_PARAM_NAME, token);
			}
			
			/*String seesionId = CookieUtils.get(request, SessionFilter.SESSION_KEY);
			if( StringUtils.isNotBlank(seesionId) ) {
				token = sessionManager.get(request, seesionId);
				if( StringUtils.isBlank(token) ){
					token = RandomUtil.buildUUID();
					sessionManager.put(seesionId, token);
				}
			}*/
		}
		return token;
	}
	
	/**
	 * 通过Request来获取token
	 * <pre></pre>
	 * @param request
	 * @return
	 */
	public  String getRequestToken(HttpServletRequest request){
		String token = request.getHeader(CSRF_PARAM_NAME);
		if( StringUtils.isBlank(token)){
			token = request.getParameter(CSRF_PARAM_NAME);
		}
		return token;
	}
	
	/**
	 * 获取SessionManager中的token
	 * <pre></pre>
	 * @param request
	 * @return
	 */
	public String getSessionToken(HttpServletRequest request){
		return sessionManager.get(request, CSRF_PARAM_NAME);
	}
	
	
	
}
