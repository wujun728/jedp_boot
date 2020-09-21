/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.base</p>
 * <p>File: BaseMapper.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.base;

import org.apache.ibatis.annotations.SelectProvider;

import tk.mybatis.mapper.common.Mapper;

/**<p>Class: BaseMapper.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public interface BaseMapper<T> extends Mapper<T>{
	
	/**
	 * 按条件查询单个对象
	 * <pre></pre>
	 * @param example
	 * @return
	 */
	@SelectProvider(type = CustomMapperProvider.class, method = "dynamicSQL")
	public T selectOneByExample(Object example);
}
