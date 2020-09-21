/**<p>Project: </p>
 * <p>Package:	com.qbt.bean</p>
 * <p>File: HttpSessionManager.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015-10-27-下午2:42:42</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.manager.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.qbt.framework.manager.SessionManager;

/**<p>Class: HttpSessionManager.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015-10-27 下午2:42:42
 * @version 1.0.0
 */
public class HttpSessionManager<E> implements SessionManager<E>{

	@Override
	public void put(HttpServletRequest request, String key, E element) {
		request.getSession().setAttribute(key, element);
	}

	@Override
	public void delete(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(HttpServletRequest request, String key) {
		return (E) request.getSession().getAttribute(key);
	}

	@Override
	public void update(HttpServletRequest request, String key, E element) {
		HttpSession session = request.getSession();
		session.removeAttribute(key);
		session.setAttribute(key, element);
	}

	@Override
	public void clear(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> keys = session.getAttributeNames();
		while( keys.hasMoreElements()){
			String key = keys.nextElement();
			session.removeAttribute(key);
		}
	}

	@Override
	public E putIfAbsent(HttpServletRequest request, String key, E element) {
		E e = get(request, key);
		if( e == null){
			put(request, key, element);
			return null;
		}else{
			return e;
		}
	}

	
	
}
