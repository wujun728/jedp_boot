package com.vonchange.headb.spring.dao;

import java.util.List;

import com.vonchange.headb.core.map.HeaGetMap;
import com.vonchange.headb.core.map.HeaMap;
import com.vonchange.headb.core.page.IPage;
import com.vonchange.headb.utils.map.HMyHashMap;

public interface IHeaViewDao<T> {
	/**
	 * 查询列表
	 * 
	 * @param parameter
	 * @return List<T>
	 */
	public List<T> findList(HeaMap parameter);

	/**
	 * 查询第一条
	 * 
	 * @param parameter
	 * @return T
	 */
	public T findFirst(HeaMap parameter);

	/**
	 * 查询唯一一条
	 * 
	 * @param parameter
	 * @return T
	 */
	public T findOne(HeaMap parameter);

	/**
	 * 根据Id查找
	 * 
	 * @param id
	 * @return T
	 */
	public T findById(Object id);

	/**
	 * 查找唯一一条
	 * 
	 * @param parameter
	 * @return HeaGetMap
	 */
	public HeaGetMap findUnique(HeaMap parameter);
	public long count(HeaMap parameter);
	/**
	 * 分页查找
	 * 
	 * @param page
	 * @param parameter
	 * @return IPage<T>
	 */
	public IPage<T> findPage(IPage<T> page, HeaMap parameter);

	/**
	 * 执行从xml获取的更新Sql
	 * 
	 * @param cudSql
	 * @param parameter
	 * @return 影响的条数
	 */
	public int cud(String cudSql, HMyHashMap parameter);

	/**
	 * 执行存储过程
	 * 
	 * @param procedureName
	 * @param parameter
	 * @return HeaGetMap
	 */
	public HeaGetMap callProcedure(String procedureName, Object... parameter);

	/**
	 * 执行存储过程
	 * 
	 * @param procedureName
	 * @param parameter
	 * @return List<HeaGetMap>
	 */
	public List<HeaGetMap> callProcedureList(String procedureName, Object... parameter);
}
