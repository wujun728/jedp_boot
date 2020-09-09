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
package com.baomidou.hibernateplus.condition.wrapper;

import java.util.Collection;

import com.baomidou.hibernateplus.enums.SqlLike;
import com.baomidou.hibernateplus.utils.CollectionUtils;
import com.baomidou.hibernateplus.utils.StringUtils;

/**
 * <p>
 * 实现AbstractSQL ，实现WHERE条件自定义
 * </p>
 *
 * @author yanghu , Caratacus
 * @Date 2016-08-22
 */
@SuppressWarnings("serial")
public class SqlPlus extends AbstractSQL<SqlPlus> {

    private final String IS_NOT_NULL = " IS NOT NULL";
    private final String IS_NULL = " IS NULL";
    private final String SQL_LIKE = " LIKE {0}";
    private final String SQL_BETWEEN_AND = " BETWEEN {0} AND {1}";

    @Override
    public SqlPlus getSelf() {
        return this;
    }

    /**
     * 将LIKE语句添加到WHERE条件中
     *
     * @param column
     *            字段名
     * @param value
     *            like值,无需前后%
     * @param type
     *            like值,无需前后%
     * @return
     */
    public SqlPlus LIKE(String column, String value, SqlLike type) {
        handerLike(column, value, type, false);
        return this;
    }

    /**
     * 将LIKE语句添加到WHERE条件中
     *
     * @param column
     *            字段名
     * @param value
     *            like值,无需前后%
     * @param type
     * @return
     */
    public SqlPlus NOT_LIKE(String column, String value, SqlLike type) {
        handerLike(column, value, type, true);
        return this;
    }

    /**
     * IS NOT NULL查询
     *
     * @param columns
     *            以逗号分隔的字段名称
     * @return this
     */
    public SqlPlus IS_NOT_NULL(String columns) {
        handerNull(columns, IS_NOT_NULL);
        return this;
    }

    /**
     * IS NULL查询
     *
     * @param columns
     *            以逗号分隔的字段名称
     * @return
     */
    public SqlPlus IS_NULL(String columns) {
        handerNull(columns, IS_NULL);
        return this;
    }

    /**
     * 处理LIKE操作
     *
     * @param column
     *            字段名称
     * @param value
     *            like匹配值
     * @param isNot
     *            是否为NOT LIKE操作
     */
    private void handerLike(String column, String value, SqlLike type, boolean isNot) {
        if (StringUtils.isNotBlank(column) && StringUtils.isNotBlank(value)) {
            StringBuilder inSql = new StringBuilder();
            inSql.append(column);
            if (isNot) {
                inSql.append(" NOT");
            }
            inSql.append(StringUtils.sqlArgsFill(SQL_LIKE, StringUtils.concatLike(value, type)));
            WHERE(inSql.toString());
        }
    }

    /**
     * 将IN语句添加到WHERE条件中
     *
     * @param column
     *            字段名
     * @param value
     *            List集合
     * @return
     */
    public SqlPlus IN(String column, Collection<?> value) {
        handerIn(column, value, false);
        return this;
    }

    /**
     * 将IN语句添加到WHERE条件中
     *
     * @param column
     *            字段名
     * @param value
     *            List集合
     * @return
     */
    public SqlPlus NOT_IN(String column, Collection<?> value) {
        handerIn(column, value, true);
        return this;
    }

    /**
     * 将IN语句添加到WHERE条件中
     *
     * @param column
     *            字段名
     * @param value
     *            逗号拼接的字符串
     * @return
     */
    public SqlPlus IN(String column, String value) {
        handerIn(column, value, false);
        return this;
    }

    /**
     * 将IN语句添加到WHERE条件中
     *
     * @param column
     *            字段名
     * @param value
     *            逗号拼接的字符串
     * @return
     */
    public SqlPlus NOT_IN(String column, String value) {
        handerIn(column, value, true);
        return this;
    }

    /**
     * 将EXISTS语句添加到WHERE条件中
     *
     * @param value
     * @return
     */
    public SqlPlus EXISTS(String value) {
        handerExists(value, false);
        return this;
    }

    /**
     * 处理EXISTS操作
     *
     * @param value
     * @param isNot
     *            是否为NOT EXISTS操作
     */
    private void handerExists(String value, boolean isNot) {
        if (StringUtils.isNotBlank(value)) {
            StringBuilder inSql = new StringBuilder();
            if (isNot) {
                inSql.append(" NOT");
            }
            inSql.append(" EXISTS (").append(value).append(")");
            WHERE(inSql.toString());
        }
    }

    /**
     * 将NOT_EXISTS语句添加到WHERE条件中
     *
     * @param value
     * @return
     */
    public SqlPlus NOT_EXISTS(String value) {
        handerExists(value, true);
        return this;
    }

    /**
     * 处理IN操作
     *
     * @param column
     *            字段名称
     * @param value
     *            集合List
     * @param isNot
     *            是否为NOT IN操作
     */
    private void handerIn(String column, Collection<?> value, boolean isNot) {
        if (StringUtils.isNotBlank(column) && CollectionUtils.isNotEmpty(value)) {
            StringBuilder inSql = new StringBuilder();
            inSql.append(column);
            if (isNot) {
                inSql.append(" NOT");
            }
            inSql.append(" IN ");
            inSql.append(StringUtils.quotaMarkList(value));
            WHERE(inSql.toString());
        }
    }

    /**
     * 处理IN操作
     *
     * @param column
     *            字段名称
     * @param value
     *            逗号拼接的字符串
     * @param isNot
     *            是否为NOT IN操作
     */
    private void handerIn(String column, String value, boolean isNot) {
        if (StringUtils.isNotBlank(column) && StringUtils.isNotBlank(value)) {
            StringBuilder inSql = new StringBuilder();
            inSql.append(column);
            if (isNot) {
                inSql.append(" NOT");
            }
            inSql.append(" IN (").append(value).append(")");
            WHERE(inSql.toString());
        }
    }

    /**
     * 处理BETWEEN_AND操作
     *
     * @param column
     *            字段名称
     * @param val1
     * @param val2
     */
    public SqlPlus BETWEEN_AND(String column, String val1, String val2) {
        between(column, val1, val2);
        return this;
    }

    /**
     * 处理BETWEEN_AND操作
     *
     * @param column
     *            字段名称
     * @param val1
     * @param val2
     */
    private void between(String column, String val1, String val2) {
        if (StringUtils.isNotBlank(column) && StringUtils.isNotBlank(val1) && StringUtils.isNotBlank(val2)) {
            StringBuilder betweenSql = new StringBuilder();
            betweenSql.append(column);
            betweenSql.append(StringUtils.sqlArgsFill(SQL_BETWEEN_AND, StringUtils.quotaMark(val1), StringUtils.quotaMark(val2)));
            WHERE(betweenSql.toString());
        }
    }

    /**
     * 以相同的方式处理null和notnull
     *
     * @param columns
     *            以逗号分隔的字段名称
     * @param sqlPart
     *            SQL部分
     */
    private void handerNull(String columns, String sqlPart) {
        if (StringUtils.isNotBlank(columns)) {
            String[] cols = columns.split(",");
            for (String col : cols) {
                if (StringUtils.isNotBlank(col.trim())) {
                    WHERE(col + sqlPart);
                }
            }
        }
    }
}
