/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vonchange.headb.springjdbc.handlers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.vonchange.headb.springjdbc.bean.BeanProcessor;

/**
 * <code>ResultSetHandler</code> implementation that converts the first
 * <code>ResultSet</code> row into a JavaBean. This class is thread safe.
 * 
 * @param <T>
 *            the target bean type
 * @see org.apache.commons.dbutils.ResultSetHandler
 */
public class HeaBeanHandler<T> implements ResultSetExtractor<T> {

	/**
	 * The Class of beans produced by this handler.
	 */
	private final Class<? extends T> type;

	/**
	 * The RowProcessor implementation to use when converting rows into beans.
	 */
	private final Map<String, String> aliasMap;

	public HeaBeanHandler(Class<? extends T> type, Map<String, String> aliasMap) {
		this.type = type;
		this.aliasMap = aliasMap;
	}

	/**
	 * Convert the first row of the <code>ResultSet</code> into a bean with the
	 * <code>Class</code> given in the constructor.
	 * 
	 * @param rs
	 *            <code>ResultSet</code> to process.
	 * @return An initialized JavaBean or <code>null</code> if there were no
	 *         rows in the <code>ResultSet</code>.
	 * 
	 * @throws SQLException
	 *             if a database access error occurs
	 * @see org.apache.commons.dbutils.ResultSetHandler#handle(java.sql.ResultSet)
	 */
	@Override
	public T extractData(ResultSet rs) throws SQLException {
		return rs.next() ? this.toBean(rs, this.type) : null;
	}

	private T toBean(ResultSet rs, Class<? extends T> type) throws SQLException {		
		BeanProcessor beanProcessor = new BeanProcessor();	
		T entity = beanProcessor.newInstance(type);
		beanProcessor.instanceBean(entity, this.aliasMap);
		ResultSetMetaData rsmd = rs.getMetaData();
		entity = beanProcessor.createBean(rs, rsmd, entity, this.aliasMap);
		return entity;
	}
}
