/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.manager.impl</p>
 * <p>File: CacheSessionManager.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015-10-28-下午12:00:27</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.manager.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.Cache;

import com.qbt.framework.manager.SessionManager;

/**<p>Class: CacheSessionManager.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015-10-28 下午12:00:27
 * @version 1.0.0
 */
public class CacheSessionManager<E> implements SessionManager<E>{

	
	
	private Cache cache;
	
	private  String cacheSessionName = "http_session_";
	
	/**
	 * 设置CacheManager
	 * <pre></pre>
	 * @param cacheManager
	 */
	public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
		this.cache = cacheManager.getCache(cacheSessionName);
	}

	@Override
	public void put(HttpServletRequest request, String key, E element) {
		cache.put(key, element);		
	}

	@Override
	public void delete(HttpServletRequest request, String key) {
		cache.evict(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(HttpServletRequest request, String key) {
		return (E) cache.get(key).get();
	}

	@Override
	public void update(HttpServletRequest request, String key, E element) {
		E e = get(request, key);
		if( e != null){
			delete(request, key);
		}
		put(request, key, element);
	}

	@Override
	public void clear(HttpServletRequest request) {
		cache.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E putIfAbsent(HttpServletRequest request, String key, E element) {
		return  (E) cache.putIfAbsent(key, element).get();
	}
	
	

	
	
}
