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
package com.vonchange.headb.springjdbc.bean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import com.vonchange.headb.core.constant.HeaConstant;
import com.vonchange.headb.core.exception.MyRuntimeException;
import com.vonchange.headb.utils.bean.HBeanUtils;
import com.vonchange.headb.utils.convert.HConvertUtils;
import com.vonchange.headb.utils.map.HHashMap;
import com.vonchange.headb.utils.orm.HOrmUtil;
import com.vonchange.headb.utils.string.HStringUtils;

/**
 *bean处理器
 * @author von_change@163.com
 * @date 2015-6-14 下午10:12:33
 */
public class BeanProcessor {
	/**
	 * 创建bean
	 * @param rs
	 * @param rsmd
	 * @param entity
	 * @param aliasMap
	 * @return T
	 * @throws SQLException
	 */
	public <T> T createBean(ResultSet rs, ResultSetMetaData rsmd, T entity, Map<String, String> aliasMap) throws SQLException {
		int cols = rsmd.getColumnCount();
		for (int col = 1; col <= cols; col++) {
			String columnName = rsmd.getColumnLabel(col);
			Object value = rs.getObject(col);
			if (null == columnName || 0 == columnName.length()) {
				columnName = rsmd.getColumnName(col);
			}
			if (columnName.indexOf(HeaConstant.DIANINALIAS) != -1) {
				String alias = HStringUtils.substringBefore(columnName, HeaConstant.DIANINALIAS);
				String fieldName = aliasMap.get(alias);
				columnName = fieldName + "." + HStringUtils.substringAfter(columnName, HeaConstant.DIANINALIAS);
			}
			String fieldName = HOrmUtil.toFiled(columnName);
			if (null != value) {
				Class<?> targetType = HBeanUtils.getPropertyType(entity, fieldName);
				if (null != targetType) {
					value = HConvertUtils.toObject(value, targetType);
					HBeanUtils.setProperty(entity, fieldName, value);
				}
				
			}
	
		}
		return entity;
	}

	/**
	 *实例化bean
	 * @param class
	 * @return T
	 */
	public <T> T newInstance(Class<? extends T> c) {
		try {
			return c.newInstance();

		} catch (InstantiationException e) {
			throw new MyRuntimeException("Cannot create " + c.getName() + ": " + e.getMessage());

		} catch (IllegalAccessException e) {
			throw new MyRuntimeException("Cannot create " + c.getName() + ": " + e.getMessage());
		}
	}

	/**
	 * 初始化bean
	 * @param entity
	 * @param aliasMap
	 */
	public <T> void instanceBean(T entity, Map<String, String> aliasMap) {
		Map<String, Boolean> instanceMap = new HHashMap<String, Boolean>();
		if (null != aliasMap && !aliasMap.isEmpty()) {
			for (Entry<String, String> entry : aliasMap.entrySet()) {
				String fieldName = entry.getValue();
				int num = HStringUtils.countMatches(fieldName, ".");
				String[] strs = HStringUtils.split(fieldName, ".");
				for (int i = 0; i < num + 1; i++) {
					String fieldStr = HStringUtils.substringBefore(strs, ".", i + 1);
					if (null == instanceMap.get(fieldStr)) {
						Class<?> clazz = (Class<?>) HBeanUtils.getPropertyType(entity, fieldStr);
						boolean flag=HBeanUtils.hasProperty(entity, fieldStr);
						if(flag){
							HBeanUtils.setProperty(entity, fieldStr, this.newInstance(clazz));
							instanceMap.put(fieldStr, true);
						}	
					}
				}
			}
		}
	}

}
