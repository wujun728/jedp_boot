/**<p>Project: </p>
 * <p>Package:	com.qbt.bean</p>
 * <p>File: SessionManager.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015-10-27-下午2:38:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.manager;

import javax.servlet.http.HttpServletRequest;

/**<p>Class: SessionManager.java</p>
 * <p>Description: </p>
 * <pre>
 *     Session管理器    
 * </pre>
 * @author 鲍建明
 * @date 2015-10-27 下午2:38:43
 * @version 1.0.0
 */
public interface SessionManager<E> {

	/**
	 * 存入
	 * <pre></pre>
	 * @param request
	 * @param key
	 * @param element
	 */
	public void put(HttpServletRequest request, String key, E element);
	
	/**
	 * 删除
	 * <pre></pre>
	 * @param request
	 * @param key
	 */
	public void delete(HttpServletRequest request, String key);
	
	/**
	 * 获取
	 * <pre></pre>
	 * @param request
	 * @param key
	 * @return
	 */
	public E get(HttpServletRequest request, String key);
	
	/**
	 * 更新
	 * <pre></pre>
	 * @param request
	 * @param key
	 * @param element
	 */
	public void update(HttpServletRequest request, String key, E element);
	
	/**
	 * 清空
	 * <pre></pre>
	 * @param request
	 */
	public void clear(HttpServletRequest request);
	
	/**
	 * 
	 * <pre>
	 * 		E e = get(request, key);
			if( e == null){
				put(request, key, element);
				return null;
			}else{
				return e;
			}
	 * 
	 * 
	 * </pre>
	 * @param request
	 * @param key
	 * @return
	 */
	public E putIfAbsent(HttpServletRequest request, String key, E element);
	
}
