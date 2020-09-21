/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.base.impl</p>
 * <p>File: BaseServiceImpl.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月27日-上午8:57:04</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.base.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qbt.framework.base.BaseMapper;
import com.qbt.framework.base.BaseService;
import com.qbt.framework.bean.Pagination;

/**<p>Class: BaseServiceImpl.java</p>
 * <p>Description: 业务基础实现类</p>
 * <pre>
 *          提供所有业务基础的通用方法
 * </pre>
 * @author 鲍建明
 * @date 2015年8月27日 上午8:57:04
 * @version 1.0.0
 */
public class BaseServiceImpl<T> implements BaseService<T>{

	@Autowired
	protected BaseMapper<T> baseMapper;
	
	protected Example example;
	
	
	/***************************** save ****************************************/
	
	/**
	 * {@inheritDoc}
	 */
	public int save(T entity) {
		return baseMapper.insert(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public int saveSelective(T entity) {
		return baseMapper.insertSelective(entity);
	}
	
	/***************************** update ****************************************/
	
	/**
	 * {@inheritDoc}
	 */
	public int updateByPrimaryKey(T entity) {
		return baseMapper.updateByPrimaryKey(entity);
	}

	
	/**
	 * {@inheritDoc}
	 */
	public int updateByPrimaryKeySelective(T entity) {
		return baseMapper.updateByPrimaryKeySelective(entity);
	}

	

	/***************************** delete ****************************************/
	
	/**
	 * {@inheritDoc}
	 */
	public int deleteByPrimaryKey(Serializable key) {
		return baseMapper.deleteByPrimaryKey(key);
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(T entity) {
		return baseMapper.delete(entity);
	}

	

	/***************************** select ****************************************/
	
	/**
	 * {@inheritDoc}
	 */
	public int count(T entity) {
		return baseMapper.selectCount(entity);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public T selectByPrimaryKey(Serializable key) {
		return baseMapper.selectByPrimaryKey(key);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public List<T> findByPage(int pageNum, int pageSize){
		 PageHelper.startPage(pageNum, pageSize);
		 return baseMapper.select(null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<T> findByPage(Pagination pagination){
		setPage(pagination);
		return baseMapper.select(null);
	}


	/**
	 * {@inheritDoc}
	 */
	public PageInfo<T> searchByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<T> result = baseMapper.select(null);
		return new PageInfo<T>(result);
	}

	/**
	 * {@inheritDoc}
	 */
	public PageInfo<T> searchByPage(Pagination pagination){
		setPage(pagination);
		List<T> result = baseMapper.select(null);
		return new PageInfo<T>(result);
	}
	

	
	
	/************************************** protected ********************************************/
	
	/**
	 * 创建一个全新Example
	 * <pre>
	 * 	
	 * </pre>
	 * @param entityClass
	 * @return
	 */
	protected Example createExample(Class<?> entityClass){
		example = new Example(entityClass);
		return example;
	}
	
	/**
	 * 创建一个全新Example
	 * <pre>
	 * 	
	 * </pre>
	 * @param entityClass
	 * @return
	 */
	protected Example createExample(T entity){
		example = new Example(entity.getClass());
		return example;
	}
	
	
	/**
	 * 获取当前的Example
	 * <pre>
	 * 
	 * </pre>
	 * @return
	 */
	protected Example getExample(){
		return example;
	}
	
	/**
	 * 更新一个实体
	 * <pre>
	 * 		按条件来更新一个实体，null值不更新
	 * </pre>
	 * @param entity
	 * @param example
	 * @return
	 */
	protected int updateByExampleSelective(T entity, Example example) {
		return baseMapper.updateByExampleSelective(entity, example);
	}

	/**
	 * 更新一个实体
	 * <pre>
	 * 		按条件来更新一个实体，null值更新
	 * </pre>
	 * @param entity
	 * @param example
	 * @return
	 */
	protected int updateByExample(T entity, Example example) {
		return baseMapper.updateByExample(entity, example);
	}
	
	/**
	 * 删除一个实体
	 * <pre>
	 * 		根据Example条件删除数据
	 * </pre>
	 * @param entity
	 * @return
	 */
	protected int deleteByExample(Example example) {
		return baseMapper.deleteByExample(example);
	}
	
	/**
	 *  查询总数
	 * <pre>
	 * 		根据Example条件进行查询总数
	 * </pre>
	 * @return
	 */
	protected int countByExample(Example example) {
		return baseMapper.selectCountByExample(example);
	}
	
	/**
	 * 查询单个实体
	 * <pre>
	 * 		根据条件来查询单个实体
	 * </pre>
	 * @param example
	 * @return
	 */
	protected T selectByExample(Example example){
		return baseMapper.selectOneByExample(example);
	}
	
	/**
	 *  查询实体集合
	 * <pre>
	 * 	根据Example条件进行查询
	 * </pre>
	 * @param example
	 * @return
	 */
	protected List<T> findByExample(Example example) {
		return baseMapper.selectByExample(example);
	}
	
	/**
	 * 查询实体集合
	 * <pre>
	 * 	根据Example条件进行查询带分页
	 * 
	 * </pre>
	 * @param example
	 * @param pagination
	 * @return
	 */
	protected List<T> findByExample(Example example, Pagination pagination){
		setPage(pagination);
		return baseMapper.selectByExample(example);
	}
	
	/**
	 *  查询实体集合分页对象
	 * <pre>
	 * 		根据example条件和分页
	 * </pre>
	 * @param example
	 * @param pageNum 
	 * @param pageSize
	 * @return PageInfo<T>
	 */
	protected PageInfo<T> searchByExample(Example example, Pagination pagination) {
		 setPage(pagination);
		 List<T> result = baseMapper.selectByExample(example);
		return new PageInfo<T>(result);
	}
	
	
	
	/************************************** private ********************************************/
	
	protected void setPage(Pagination pagination){
		if(pagination == null){
			pagination = new Pagination();
		}
		String order = pagination.toOrderString();
		if(StringUtils.isNotBlank(order)) {
			PageHelper.startPage(pagination.getPageNow(), pagination.getPageSize(), order);
		}else {
			PageHelper.startPage(pagination.getPageNow(), pagination.getPageSize());
		}
	}
	
}
