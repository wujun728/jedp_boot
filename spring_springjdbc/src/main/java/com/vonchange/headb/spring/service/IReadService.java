package com.vonchange.headb.spring.service;

import java.util.List;

import com.vonchange.headb.core.map.HeaMap;
import com.vonchange.headb.core.page.IPage;

/**
 *查询Service
 * @author von_change@163.com
 * @date 2015-6-15 下午6:15:34
 * @param <T>
 */
public interface IReadService<T> {
	/**
	 * 根据ID查询
	 * @param id
	 * @return T
	 */
	public T findById(Object id);

	/**
	 * 查询第一条
	 * @param parameter
	 * @return T
	 */
	public T findFirst(HeaMap parameter);

	/**
	 * 查询列表
	 * @param parameter
	 * @return List<T>
	 */
	public List<T> findList(HeaMap parameter);

	/**
	 *分页查询
	 * @param page
	 * @param parameter
	 * @return List<T>
	 */
	public IPage<T> findPage(IPage<T> page, HeaMap parameter);
	/**
	 * 分页方式封装到List
	 * @param parameter
	 * @param pageSize
	 * @return List<T> 
	 */
	public List<T> pageToList(HeaMap parameter, Integer pageSize);
}
