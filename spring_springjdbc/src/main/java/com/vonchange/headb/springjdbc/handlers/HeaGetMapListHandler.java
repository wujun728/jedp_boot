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
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.vonchange.headb.core.map.HeaGetMap;
import com.vonchange.headb.utils.orm.HOrmUtil;

/**
 * <code>ResultSetHandler</code> implementation that converts the first
 * <code>ResultSet</code> row into a <code>Map</code>. This class is thread
 * safe.
 * 
 * @see org.apache.commons.dbutils.ResultSetHandler
 */
public class HeaGetMapListHandler implements ResultSetExtractor<List<HeaGetMap>> {

	/**
	 * Converts the first row in the <code>ResultSet</code> into a
	 * <code>Map</code>.
	 * 
	 * @param rs
	 *            <code>ResultSet</code> to process.
	 * @return A <code>Map</code> with the values from the first row or
	 *         <code>null</code> if there are no rows in the
	 *         <code>ResultSet</code>.
	 * 
	 * @throws SQLException
	 *             if a database access error occurs
	 * 
	 * @see org.apache.commons.dbutils.ResultSetHandler#handle(java.sql.ResultSet)
	 */
	@Override
	public List<HeaGetMap> extractData(ResultSet rs) throws SQLException {
		return this.toMapList(rs);
	}

	private List<HeaGetMap> toMapList(ResultSet rs) throws SQLException {
		List<HeaGetMap> result = new ArrayList<HeaGetMap>();
		if (!rs.next()) {
			return result;
		}
		HeaGetMap map = null;
		ResultSetMetaData rsmd = rs.getMetaData();
		do {
			map = new HeaGetMap();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				String columnName = rsmd.getColumnLabel(i);
				if (null == columnName || 0 == columnName.length()) {
					columnName = rsmd.getColumnName(i);
				}
				columnName = HOrmUtil.toFiled(columnName);
				map.put(columnName, rs.getObject(i));
			}
			result.add(map);
		} while (rs.next());
		return result;
	}
}
