/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.filter</p>
 * <p>File: SessionFilter.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月30日-上午11:45:16</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qbt.framework.util.CookieUtils;
import com.qbt.framework.util.RandomUtil;

/**<p>Class: SessionFilter.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015年8月30日 上午11:45:16
 * @version 1.0.0
 */
public class SessionFilter  implements Filter{
	
	
	public static final String SESSION_KEY = "session_key";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		synchronized (request) {
			String seesionId = CookieUtils.get(request, SESSION_KEY);
			if( StringUtils.isBlank(seesionId) ){
				CookieUtils.put(response, request, SESSION_KEY, RandomUtil.buildUUID());
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
