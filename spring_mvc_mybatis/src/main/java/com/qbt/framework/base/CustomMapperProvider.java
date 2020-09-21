/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.base</p>
 * <p>File: CustomMapperProvider.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月27日-下午3:47:53</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.base;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.TextSqlNode;

import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.provider.MapperProvider;

/**<p>Class: CustomMapperProvider.java</p>
 * <p>Description: </p>
 * <pre>
 *         
 * </pre>
 * @author 鲍建明
 * @date 2015年8月27日 下午3:47:53
 * @version 1.0.0
 */
public class CustomMapperProvider extends MapperProvider {

	
	/** 
	 *  <pre>
	 *	</pre>
	 * @param mapperClass
	 * @param mapperHelper
	 */
	public CustomMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}
	
	/**
	 * 更具条件来查询一个对象
	 * <pre></pre>
	 * @param ms
	 * @return
	 */
	public SqlNode selectOneByExample(MappedStatement ms) {
		 Class<?> entityClass = getSelectReturnType(ms);
	     //灏嗚繑鍥炲�淇敼涓哄疄浣撶被鍨�
	     setResultType(ms, entityClass);
	     List<SqlNode> sqlNodes = new LinkedList<SqlNode>();
	     //闈欐�鐨剆ql閮ㄥ垎:select column ... from table
	     sqlNodes.add(new StaticTextSqlNode("SELECT"));
	     IfSqlNode distinctSqlNode = new IfSqlNode(new StaticTextSqlNode("DISTINCT"), "distinct");
	     sqlNodes.add(distinctSqlNode);

	     ForEachSqlNode forEachSelectColumns = new ForEachSqlNode(ms.getConfiguration(), new TextSqlNode("${selectColumn}"), "_parameter.selectColumns", null, "selectColumn", null, null, ",");
	     IfSqlNode ifSelectColumns = new IfSqlNode(forEachSelectColumns, "@tk.mybatis.mapper.mapperhelper.OGNL@hasSelectColumns(_parameter)");
	     sqlNodes.add(ifSelectColumns);

	     IfSqlNode ifNoSelectColumns = new IfSqlNode(new StaticTextSqlNode(EntityHelper.getSelectColumns(entityClass)), "@tk.mybatis.mapper.mapperhelper.OGNL@hasNoSelectColumns(_parameter)");
	     sqlNodes.add(ifNoSelectColumns);

	     sqlNodes.add(new StaticTextSqlNode(" FROM " + tableName(entityClass)));
	     IfSqlNode ifNullSqlNode = new IfSqlNode(exampleWhereClause(ms.getConfiguration()), "_parameter != null");
	     sqlNodes.add(ifNullSqlNode);
	     IfSqlNode orderByClauseSqlNode = new IfSqlNode(new TextSqlNode("order by ${orderByClause}"), "orderByClause != null");
	     sqlNodes.add(orderByClauseSqlNode);
	     String orderByClause = EntityHelper.getOrderByClause(entityClass);
	     if (orderByClause.length() > 0) {
	         IfSqlNode defaultOrderByClauseSqlNode = new IfSqlNode(new StaticTextSqlNode("ORDER BY " + orderByClause), "orderByClause == null");
	         sqlNodes.add(defaultOrderByClauseSqlNode);
	     }
		return new MixedSqlNode(sqlNodes);
	}
	
	
}
