/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Caratacus
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.baomidou.hibernateplus.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;

import com.baomidou.hibernateplus.entity.Convert;

/**
 * <p>
 * Hibernate帮助类
 * </p>
 *
 * @author Caratacus
 * @date 2016-10-14
 */
public class HibernateUtils {

    private static final Logger logger = Logger.getLogger(HibernateUtils.class);

    private static final String BASE_COUNT = "SELECT COUNT(0) FROM ";
    private static final String BASE_LIST = " FROM ";

    /**
     * 生成当前对象的HQL
     *
     * @param clazz
     * @return
     */
    public static String getListHql(Class clazz) {
        return getListHql(clazz, Collections.EMPTY_MAP);
    }

    /**
     * BASE LIST HQL
     *
     * @param clazz
     * @return
     */
    private static StringBuilder getBaseListHql(Class clazz) {
        return getBaseHql(clazz, BASE_LIST);
    }

    /**
     * 基本的hql
     *
     * @param clazz
     * @return
     */
    private static StringBuilder getBaseHql(Class clazz, String baseHql) {
        StringBuilder builder = new StringBuilder(baseHql);
        builder.append(clazz.getSimpleName());
        return builder;
    }

    /**
     * 生成当前对象的HQL
     *
     * @param clazz
     * @param params
     * @return
     */
    public static String getListHql(Class clazz, Map<String, Object> params) {
        return getListHql(StringUtils.EMPTY, clazz, params);
    }

    /**
     * 生成当前对象的HQL
     *
     * @param order
     * @param clazz
     * @param params
     * @return
     */
    public static String getListHql(String order, Class clazz, Map<String, Object> params) {
        StringBuilder builder = getBaseListHql(clazz);
        getWhere(builder, params);
        getOrderby(order, builder);
        return builder.toString();
    }

    /**
     * 生成HQL ORDER BY SQL
     *
     * @param builder
     * @param builder
     * @return
     */
    private static void getOrderby(String order, StringBuilder builder) {
        if (StringUtils.isNotBlank(order)) {
            builder.append(" ORDER BY ");
            builder.append(order);
        }
    }

    /**
     * 简单的count hql
     *
     * @param clazz
     * @return
     */
    private static StringBuilder getBaseCountHql(Class clazz) {
        return getBaseHql(clazz, BASE_COUNT);
    }

    /**
     * 生成当前对象的HQL
     *
     * @param clazz
     * @param params
     * @return
     */
    public static String getCountHql(Class clazz, Map<String, Object> params) {
        StringBuilder builder = getBaseCountHql(clazz);
        getWhere(builder, params);
        return builder.toString();
    }

    /**
     * 生成HQL WHERE SQL
     *
     * @param builder
     * @param params
     * @return
     */
    private static void getWhere(StringBuilder builder, Map<String, Object> params) {
        if (MapUtils.isNotEmpty(params)) {
            List<String> list = new ArrayList<String>();
            list.addAll(params.keySet());
            builder.append(" WHERE ");
            for (int i = 0; i < list.size(); i++) {
                String property = list.get(i);
                Object value = params.get(property);
                builder.append(property);
                if (i == list.size() - 1) {
                    setByMap(builder, property, value);
                } else {
                    setAndByMap(builder, property, value);
                }
            }
        }
    }

    /**
     * sql语句连接
     *
     * @param builder
     * @param property
     * @param object
     * @return
     */
    private static void setByMap(StringBuilder builder, String property, Object object) {
        if (object.getClass().isArray() || object instanceof List)
            builder.append(" IN ");
        else
            builder.append(" = ");
        builder.append(":");
        builder.append(property);
    }

    /**
     * sql语句连接And
     *
     * @param builder
     * @param property
     * @param object
     * @return
     */
    private static void setAndByMap(StringBuilder builder, String property, Object object) {
        if (object.getClass().isArray() || object instanceof List)
            builder.append(" IN ");
        else
            builder.append(" = ");
        builder.append(":");
        builder.append(property);
        builder.append(" AND ");
    }

    /**
     * 设置hibernate参数
     *
     * @param query
     * @param key
     * @param obj
     * @return
     */
    public static void setParams(Query query, String key, Object obj) {
        if (obj.getClass().isArray()) {
            query.setParameterList(key, (Object[]) obj);
        } else if (obj instanceof List) {
            query.setParameterList(key, (List) obj);
        } else {
            query.setParameter(key, obj);
        }
    }

    /**
     * 设置分页
     *
     * @param page
     * @param rows
     * @param query
     * @return
     */
    public static void setPage(int page, int rows, Query query) {
        if (0 != rows) {
            // 只判断row , 如果page异常 初始化为1
            page = TypeConvert.toInteger(page, 1);
            query.setFirstResult((page - 1) * rows).setMaxResults(rows);
        }
    }

    /**
     * 获取Entity SQLQuery对象
     *
     * @param sql
     * @param factory
     * @return
     */
    public static Query getEntitySqlQuery(Class<? extends Convert> cls, String sql, SessionFactory factory, Boolean isCurrent) {
        return getSqlQuery(cls, sql, factory, isCurrent);
    }

    /**
     * 获取SQLQuery对象
     *
     * @param sql
     * @param factory
     * @return
     */
    public static Query getSqlQuery(Class<? extends Convert> cls, String sql, SessionFactory factory, Boolean isCurrent) {
        System.err.println("Execute SQL：" + SqlUtils.sqlFormat(sql, true));
        SQLQuery sqlQuery = getSession(factory, isCurrent).createSQLQuery(sql);
        if (cls != null) {
            sqlQuery.addEntity(cls);
        }
        return sqlQuery;
    }

    /**
     * 获取SQLQuery对象
     *
     * @param sql
     * @param factory
     * @return
     */
    public static Query getSqlQuery(String sql, SessionFactory factory, Boolean isCurrent) {
        return getSqlQuery(null, sql, factory, isCurrent);
    }

    /**
     * 获取HQLQuery对象
     *
     * @param hql
     * @param factory
     * @param isCurrent
     *            是否为当前事务的session
     * @return
     */
    public static Query getHqlQuery(String hql, SessionFactory factory, Boolean isCurrent) {
        System.err.println("Execute HQL：" + SqlUtils.sqlFormat(hql, true));
        return getSession(factory, isCurrent).createQuery(hql);
    }

    /**
     * 获得当前事务的session
     *
     * @param factory
     * @return
     */
    /**
     * 获取Session
     *
     * @param factory
     * @param isCurrent
     *            是否为当前事务的session
     * @return
     */
    public static Session getSession(SessionFactory factory, Boolean isCurrent) {
        return isCurrent ? factory.getCurrentSession() : factory.openSession();
    }

}
