package com.vonchange.headb.spring.dao;

import java.util.List;

import com.vonchange.headb.core.map.HeaGetMap;
import com.vonchange.headb.core.map.HeaMap;
import com.vonchange.headb.core.page.IPage;
import com.vonchange.headb.utils.map.HMyHashMap;

/**
 * HeaDao接口
 * 
 * @author von_change@163.com
 * @date 2015-6-14 下午10:17:44
 * @param <T>
 */
public interface IHeaDao<T> {
	/**
	 * 保存
	 * 
	 * @param entity
	 * @return T
	 */
	public T save(T entity);

	/**
	 * 查询列表
	 * 
	 * @param parameter
	 * @return List<T>
	 */
	public List<T> findList(HeaMap parameter);

	/**
	 * 查询第一条数据
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
	 * 查询唯一一条
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
	 * 删除
	 * 
	 * @param parameter
	 * @return 删除的条数
	 */
	public int delete(HeaMap parameter);

	/**
	 * 根据Id删除
	 * 
	 * @param id
	 * @return 0或1
	 */
	public int deleteById(Object id);

	/**
	 * 根据实体删除
	 * 
	 * @param entity
	 * @return 0或1
	 */
	public int delete(T entity);

	/**
	 * 更新
	 * 
	 * @param entity
	 * @param parameter
	 * @return 更新的条数
	 */
	public int update(T entity, HeaMap parameter);

	/**
	 * 更新
	 * 
	 * @param entity
	 * @param nullFields
	 *            更新为NULL值得字段
	 * @return 0或1
	 */
	public int update(T entity, String... nullFields);

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return 0或1
	 */
	public int update(T entity);

	/**
	 * 执行从xml中获取更新的Sql
	 * 
	 * @param cudSql
	 * @param parameter
	 * @return 影响的列数
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
