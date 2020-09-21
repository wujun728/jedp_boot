package com.vonchange.headb.spring.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.vonchange.headb.core.AbstractHeaDao;
import com.vonchange.headb.core.map.HeaGetMap;
import com.vonchange.headb.spring.dao.IHeaDao;
import com.vonchange.headb.spring.dao.IHeaViewDao;
import com.vonchange.headb.spring.template.IHeaJdbcTemplate;
import com.vonchange.headb.springjdbc.handlers.HeaBeanHandler;
import com.vonchange.headb.springjdbc.handlers.HeaBeanListHandler;
import com.vonchange.headb.springjdbc.handlers.HeaGetMapHandler;
import com.vonchange.headb.springjdbc.handlers.HeaGetMapListHandler;
import com.vonchange.headb.springjdbc.handlers.ScalarHandler;
import com.vonchange.headb.utils.convert.HConvertUtils;
import com.vonchange.headb.utils.logger.HLogUtils;
import com.vonchange.headb.utils.sql.HSqlFill;
import com.vonchange.headb.utils.string.HStringUtils;

/**
 * 依赖DbUtils的操作的抽象类
 * 
 * @author von_change@163.com
 * @date 2015-6-14 下午10:15:38
 * @param <T>
 */
public abstract class HeaDaoImpl<T> extends AbstractHeaDao<T> implements IHeaDao<T>, IHeaViewDao<T> {

	@Resource
	private IHeaJdbcTemplate heaJdbcTemplate;

	protected void setJdbcTemplate(IHeaJdbcTemplate heaJdbcTemplate) {
		this.heaJdbcTemplate = heaJdbcTemplate;
	}

	private void logSql(String sql, Object[] params) {
		// logger.debug(HLogUtils.format("sql为:", sql));
		logger.info(HLogUtils.attr("参数为:", params));
		logger.info(HLogUtils.format("生成的sql为:", HSqlFill.fill(sql, params)));
	}

	private String generateMyCountSql(String sql) {
		String lowerSql = sql.toLowerCase();
		int begin = lowerSql.indexOf("from");
		int guoIndex = lowerSql.lastIndexOf(")");
		int end = lowerSql.length();
		int orderIndex=0;
		if (guoIndex > 0) {
			 orderIndex=sql.substring(guoIndex).lastIndexOf("order");
			if(orderIndex>0){
				end = guoIndex + orderIndex;
			}
		}else{
			orderIndex=sql.lastIndexOf("order");
		     if(orderIndex>0){
					end = guoIndex + orderIndex;
			}	
		}	
		String result = sql.substring(begin, end);
		return HStringUtils.format("select count(1)  {0} ", result);
	}

	protected long countMySqlResult(String sql, Object[] params) {
		String countSql = generateMyCountSql(sql);
		Object result = findBy(countSql, 1, params);
		return HConvertUtils.toLong(result);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object findBy(String sql, int columnIndex, Object[] params) {
		logSql(sql, params);
		Object object = heaJdbcTemplate.query(sql, new ScalarHandler(columnIndex), params);
		return object;
	}

	protected List<T> queryList(String sql, Object[] params, Map<String, String> aliasMap) {
		logSql(sql, params);
		List<T> list = heaJdbcTemplate.query(sql, new HeaBeanListHandler<T>(getEntityType(), aliasMap), params);
		return list;
	}

	protected T queryOne(String sql, Object[] params, Map<String, String> aliasMap) {
		logSql(sql, params);
		T bean = heaJdbcTemplate.query(sql, new HeaBeanHandler<T>(getEntityType(), aliasMap), params);// conn,
		return bean;
	}

	protected HeaGetMap queryUnique(String sql, Object[] params, Map<String, String> aliasMap) {
		logSql(sql, params);
		HeaGetMap map = heaJdbcTemplate.query(sql, new HeaGetMapHandler(), params);
		return map;
	}

	protected Object insert(String sql, Object[] params) {
		logSql(sql, params);
		Object object = heaJdbcTemplate.insert(sql, new ScalarHandler<Object>(), params);
		return object;
	}

	protected int delete(String sql, Object[] params) {
		logSql(sql, params);
		return update(sql, params);
	}

	protected int update(String sql, Object[] params) {
		logSql(sql, params);
		int affectedRows = heaJdbcTemplate.update(sql, params);
		return affectedRows;
	}

	protected HeaGetMap callProc(String sql, Object[] params) {
		logSql(sql, params);
		HeaGetMap map = heaJdbcTemplate.queryProc(sql, new HeaGetMapHandler(), params);
		return map;
	}

	protected List<HeaGetMap> callProcList(String sql, Object[] params) {
		logSql(sql, params);
		List<HeaGetMap> mapList = heaJdbcTemplate.queryProc(sql, new HeaGetMapListHandler(), params);
		return mapList;
	}
	protected void test(){
		//heaJdbcTemplate.
	}

}
