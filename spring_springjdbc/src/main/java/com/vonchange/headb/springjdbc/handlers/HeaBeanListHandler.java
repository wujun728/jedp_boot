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
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.vonchange.headb.springjdbc.bean.BeanProcessor;

/**
 * <code>ResultSetHandler</code> implementation that converts a
 * <code>ResultSet</code> into a <code>List</code> of beans. This class is
 * thread safe.
 *
 * @param <T> the target bean type
 * @see org.apache.commons.dbutils.ResultSetHandler
 */
public class HeaBeanListHandler<T> implements ResultSetExtractor<List<T>> {

    /**
     * The Class of beans produced by this handler.
     */
    private final Class<? extends T> type;

    /**
     * The RowProcessor implementation to use when converting rows
     * into beans.
     */
    private final Map<String,String> aliasMap;

    /**
     * Creates a new instance of BeanListHandler.
     *
     * @param type The Class that objects returned from <code>handle()</code>
     * are created from.
     */
  

    /**
     * Creates a new instance of BeanListHandler.
     *
     * @param type The Class that objects returned from <code>handle()</code>
     * are created from.
     * @param convert The <code>RowProcessor</code> implementation
     * to use when converting rows into beans.
     */
    public HeaBeanListHandler(Class<? extends T> type, Map<String,String> aliasMap) {
        this.type = type;
        this.aliasMap = aliasMap;
    }

    /**
     * Convert the whole <code>ResultSet</code> into a List of beans with
     * the <code>Class</code> given in the constructor.
     *
     * @param rs The <code>ResultSet</code> to handle.
     *
     * @return A List of beans, never <code>null</code>.
     *
     * @throws SQLException if a database access error occurs
     * @see org.apache.commons.dbutils.RowProcessor#toBeanList(ResultSet, Class)
     */
    @Override
    public List<T> extractData(ResultSet rs) throws SQLException {
    	
    	 return this.toBeanList(rs, type);
    }

    private  List<T> toBeanList(ResultSet rs,  Class<? extends T> type) throws SQLException {
  	  List<T> results = new ArrayList<T>();
      if (!rs.next()) {
          return results;
      }
      BeanProcessor beanProcessor =new BeanProcessor();
      T entity  =null;
   	 ResultSetMetaData rsmd = rs.getMetaData();
          do {
        	  entity =	beanProcessor.newInstance(type);
              beanProcessor.instanceBean(entity,this.aliasMap); 
        	  entity=beanProcessor.createBean(rs, rsmd, entity,this.aliasMap);
              results.add(entity);
           } while (rs.next());
           return results;
    }
	
}
