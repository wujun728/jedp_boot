/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.base</p>
 * <p>File: BaseService.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月27日-上午8:56:42</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.base;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qbt.framework.bean.Pagination;

/**<p>Class: BaseService.java</p>
 * <p>Description: 业务基础接口</p>
 * <pre>
 *         提供所有业务基础的通用方法
 * </pre>
 * @author 鲍建明
 * @date 2015年8月27日 上午8:56:42
 * @version 1.0.0
 */
public interface BaseService<T> {

	
	/**
	 * 保存一个实体
	 * 
	 * <pre>
	 * 		字段为null也会保存，不使用数据库默认值
	 * </pre>
	 * 
	 * @param entity
	 * @return
	 */
	public int save(T entity);

	/**
	 * 保存一个实体
	 * 
	 * <pre>
	 * 字段null的使用数据库默认值
	 * </pre>
	 * 
	 * @param entity
	 * @return
	 */
	public int saveSelective(T entity);

	/**
	 * 更新一个实体
	 * 
	 * <pre>
	 * 		字段null 会更新
	 * </pre>
	 * 
	 * @param entity
	 * @return
	 */
	public int updateByPrimaryKey(T entity);

	/**
	 * 更新一个实体
	 * 
	 * <pre>
	 * 		不更新字段为null的
	 * </pre>
	 * 
	 * @return
	 */
	public int updateByPrimaryKeySelective(T entity);
	
	
	
	/**
	 * 删除一个实体
	 * <pre>
	 * 		根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * </pre>
	 * @param key
	 * @return
	 */
	public int deleteByPrimaryKey(Serializable key);
	
	/**
	 * 删除一个实体
	 * <pre>
	 * 		根据实体属性作为条件进行删除，查询条件使用等号
	 * </pre>
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
	
	
	/**
	 * 查询总数
	 * <pre>
	 * 		根据实体中的属性查询总数，查询条件使用等号
	 * </pre>
	 * @param key
	 * @return
	 */
	public int count(T entity);
	
	
	/**
	 * 查询单个实体
	 * <pre>
	 * 		根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 * </pre>
	 * @param key
	 * @return
	 */
	public T selectByPrimaryKey(Serializable key);
	
	/**
	 *  查询实体集合
	 * <pre>
	 * 		分页查询
	 * </pre>
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<T> findByPage(int pageNum, int pageSize);
	
	/**
	 * 查询实体集合
	 * <pre>
	 * 		分页查询可设置排序
	 * </pre>
	 * @param page
	 * @return
	 */
	public List<T> findByPage(Pagination pagination);
	
	/**
	 * 查询实体集合分页对象
	 * <pre>
	 * 		分页查询
	 * </pre>
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<T> searchByPage(int pageNum, int pageSize);
	
	/**
	 * 查询实体集合分页对象
	 * <pre>
	 * 		分页查询
	 * </pre>
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<T> searchByPage(Pagination pagination);
	
	
	
}
