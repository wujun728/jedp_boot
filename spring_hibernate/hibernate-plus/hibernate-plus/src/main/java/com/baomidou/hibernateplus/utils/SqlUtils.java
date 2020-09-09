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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;

import com.baomidou.hibernateplus.condition.DeleteWrapper;
import com.baomidou.hibernateplus.condition.SelectWrapper;
import com.baomidou.hibernateplus.condition.UpdateWrapper;
import com.baomidou.hibernateplus.condition.wrapper.Wrapper;
import com.baomidou.hibernateplus.entity.Convert;
import com.baomidou.hibernateplus.entity.EntityInfo;
import com.baomidou.hibernateplus.entity.page.Page;
import com.baomidou.hibernateplus.entity.page.Pagination;
import com.baomidou.hibernateplus.exceptions.HibernatePlusException;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Distinct;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;

/**
 * <p>
 * SqlUtils工具类
 * </p>
 *
 * @author Caratacus
 * @Date 2016-11-13
 */
public class SqlUtils {

    private static final BasicFormatterImpl sqlFormatter = new BasicFormatterImpl();
    private static final String SQL_COUNT = "SELECT COUNT(0) FROM %s %s";
    private static final String SQL_BASE_COUNT = "SELECT COUNT(0) FROM ( %s ) TOTAL";
    private static final String SQL_LIST = "SELECT %s FROM %s %s";
    private static final String SQL_DELETE = "DELETE FROM %s %s";
    private static final String SQL_UPDATE = "UPDATE %s SET %s %s";
    private static List<SelectItem> countSelectItem = null;

    /**
     * 获取select的count语句
     *
     * @param originalSql
     *            selectSQL
     * @return
     */
    public static String sqlCountOptimize(String originalSql) {
        Assert.hasLength(originalSql);
        String sqlCount;
        try {
            Select selectStatement = (Select) CCJSqlParserUtil.parse(originalSql);
            PlainSelect plainSelect = (PlainSelect) selectStatement.getSelectBody();
            Distinct distinct = plainSelect.getDistinct();
            List<Expression> groupBy = plainSelect.getGroupByColumnReferences();
            // 优化Order by
            List<OrderByElement> orderBy = plainSelect.getOrderByElements();
            // 添加包含groupby 不去除orderby
            if (CollectionUtils.isEmpty(groupBy) && CollectionUtils.isNotEmpty(orderBy)) {
                plainSelect.setOrderByElements(null);
            }
            if (distinct != null || CollectionUtils.isNotEmpty(groupBy)) {
                return String.format(SQL_BASE_COUNT, selectStatement.toString());
            }
            List<SelectItem> selectCount = countSelectItem();
            plainSelect.setSelectItems(selectCount);
            sqlCount = selectStatement.toString();
        } catch (Exception e) {
            sqlCount = String.format(SQL_BASE_COUNT, originalSql);
        }
        return sqlCount;
    }

    /**
     * 获取jsqlparser中count的SelectItem
     *
     * @return
     */
    private static List<SelectItem> countSelectItem() {
        if (CollectionUtils.isNotEmpty(countSelectItem)) {
            return countSelectItem;
        }
        Function function = new Function();
        function.setName("COUNT");
        List<Expression> expressions = new ArrayList<Expression>();
        LongValue longValue = new LongValue(0);
        ExpressionList expressionList = new ExpressionList();
        expressions.add(longValue);
        expressionList.setExpressions(expressions);
        function.setParameters(expressionList);
        countSelectItem = new ArrayList<SelectItem>();
        SelectExpressionItem selectExpressionItem = new SelectExpressionItem(function);
        countSelectItem.add(selectExpressionItem);
        return countSelectItem;
    }

    /**
     * 查询SQL拼接Order By
     *
     * @param originalSql
     *            需要拼接的SQL
     * @param page
     *            page对象
     * @return
     */
    public static String concatOrderBy(String originalSql, Pagination page) {
        if (StringUtils.isNotBlank(page.getOrderByField())) {
            StringBuilder buildSql = new StringBuilder(originalSql);
            buildSql.append(" ORDER BY ").append(page.getOrderByField());
            buildSql.append(page.isAsc() ? " ASC " : " DESC ");
            return buildSql.toString();
        }
        return originalSql;
    }

    /**
     * 格式sql
     *
     * @param boundSql
     * @param format
     * @return
     */
    public static String sqlFormat(String boundSql, boolean format) {
        if (format) {
            return sqlFormatter.format(boundSql);
        } else {
            return boundSql.replaceAll("[\\s]+", " ");
        }
    }

    /**
     * 获取hibernate实体映射List sql
     *
     * @param clazz
     * @param wrapper
     * @param page
     * @return
     */
    public static String sqlEntityList(Class clazz, Wrapper wrapper, Page page) {
        return sqlList(clazz, true, wrapper, page);
    }

    /**
     * 获取普通List sql
     *
     * @param clazz
     * @param isMapping
     *            是否映射
     * @param wrapper
     * @param page
     * @return
     */
    public static String sqlList(Class clazz, boolean isMapping, Wrapper wrapper, Page page) {
        String select;
        if (isMapping) {
            select = "*";
        } else {
            select = select(clazz);
        }
        String tableName = getTableName(clazz);
        if (!isDefault(wrapper)) {
            String sqlSelect = wrapper.getSqlSelect();
            if (page != null) {
                if (StringUtils.isNotBlank(page.getOrderByField())) {
                    wrapper.orderBy(page.getOrderByField(), page.isAsc());
                }
            }
            return String.format(SqlUtils.SQL_LIST, StringUtils.isBlank(sqlSelect) ? select : sqlSelect, tableName,
                    StringUtils.isNotBlank(wrapper.toString()) ? wrapper.toString() : StringUtils.EMPTY);
        }
        if (page != null) {
            return concatOrderBy(String.format(SqlUtils.SQL_LIST, select, tableName, StringUtils.EMPTY), page);
        }
        return String.format(SqlUtils.SQL_LIST, select, tableName, StringUtils.EMPTY);

    }

    /**
     * 获取普通List sql
     *
     * @param wrapper
     * @param page
     * @return
     */
    public static String sqlList(String sql, Wrapper wrapper, Page page) {

        if (!isDefault(wrapper)) {
            if (page != null) {
                if (StringUtils.isNotBlank(page.getOrderByField())) {
                    wrapper.orderBy(page.getOrderByField(), page.isAsc());
                }
            }
            sql += wrapper.toString();
        }
        return sql;

    }

    /**
     * 获取普通List sql
     *
     * @param clazz
     * @param wrapper
     * @param page
     * @return
     */
    public static String sqlList(Class clazz, Wrapper wrapper, Page page) {
        return sqlList(clazz, false, wrapper, page);
    }

    /**
     * 获取Count语句
     *
     * @param clazz
     * @param wrapper
     * @return
     */
    public static String sqlCount(Class clazz, Wrapper wrapper) {
        String tableName = getTableName(clazz);
        if (!isDefault(wrapper)) {
            return String.format(SqlUtils.SQL_COUNT, tableName, wrapper.toString());
        }
        return String.format(SqlUtils.SQL_COUNT, tableName, StringUtils.EMPTY);
    }

    /**
     * 根据Class获取表名
     *
     * @param clazz
     * @return
     */
    public static String getTableName(Class clazz) {
        EntityInfo entityInfo = EntityInfoUtils.getEntityInfo(clazz);
        String tableName = entityInfo.getTableName();
        if (StringUtils.isBlank(tableName)) {
            throw new HibernatePlusException("Error: Entity @Table Not Found!");
        }
        return tableName;
    }

    /**
     * 获取删除sql
     *
     * @param clazz
     * @param wrapper
     * @param <T>
     * @return
     */
    public static <T extends Convert> String sqlDelete(Class<T> clazz, Wrapper wrapper) {
        String tableName = getTableName(clazz);
        if (!isDefault(wrapper)) {
            return String.format(SqlUtils.SQL_DELETE, tableName, wrapper.toString());
        }
        return String.format(SqlUtils.SQL_DELETE, tableName, StringUtils.EMPTY);
    }

    /**
     * 获取删除sql
     *
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public static <T extends Convert> String sqlDelete(Class<T> clazz, Serializable id) {
        String tableName = getTableName(clazz);
        String keyColumn = getPrimaryKey(clazz);
        return String.format(SqlUtils.SQL_DELETE, tableName, DeleteWrapper.instance().eq(keyColumn, id).toString());
    }

    /**
     * 获取对象数据库主键
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Convert> String getPrimaryKey(Class<T> clazz) {
        EntityInfo entityInfo = EntityInfoUtils.getEntityInfo(clazz);
        String keyColumn = entityInfo.getKeyColumn();
        if (StringUtils.isBlank(keyColumn))
            // 该表没有主键时抛出异常
            throw new HibernatePlusException("The table does not have a primary key and can not perform operations!");
        return keyColumn;
    }

    /**
     * 获取Update SQL
     *
     * @param clazz
     * @param wrapper
     * @return
     */
    public static String sqlUpdate(Class clazz, Wrapper wrapper) {
        String tableName = getTableName(clazz);
        Map<String, String> setMap = wrapper.getSetMap();
        Assert.notEmpty(setMap);
        Iterator iterator = wrapper.getSetMap().entrySet().iterator();
        int _size = setMap.size();
        int i = 1;
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            builder.append(String.format("%s = %s", key, value));
            if (i != _size) {
                builder.append(",");
            }
            i++;
        }
        if (!isDefault(wrapper)) {
            return String.format(SqlUtils.SQL_UPDATE, tableName, builder.toString(), wrapper.toString());
        }
        return String.format(SqlUtils.SQL_UPDATE, tableName, builder.toString(), StringUtils.EMPTY);
    }

    /**
     * 获取select
     *
     * @param clazz
     * @return
     */
    public static String select(Class clazz) {
        return EntityInfoUtils.getEntityInfo(clazz).getSelect();
    }

    /**
     * 判断Wrapper是不是Default并且不为null
     *
     * @param wrapper
     * @return
     */
    public static Boolean isDefault(Wrapper wrapper) {
        return !(wrapper != null && wrapper != UpdateWrapper.DEFAULT && wrapper != SelectWrapper.DEFAULT
                && wrapper != DeleteWrapper.DEFAULT);
    }

}
