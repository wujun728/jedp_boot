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

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import com.baomidou.hibernateplus.enums.SqlLike;
import com.baomidou.hibernateplus.utils.MapUtils;
import com.baomidou.hibernateplus.utils.StringUtils;

/**
 * <p>
 * 条件构造抽象类，定义T-SQL语法
 * </p>
 *
 * @author hubin , yanghu , Dyang , Caratacus
 * @Date 2016-11-7
 */
@SuppressWarnings("serial")
public abstract class Wrapper implements Serializable {

    /**
     * 实现了TSQL语法的SQL实体
     */
    protected final SqlPlus sql = new SqlPlus();

    /**
     * 获取select(SelectWrapper实现)
     *
     * @return
     */
    public String getSqlSelect() {
        return StringUtils.EMPTY;
    }

    /**
     * 获取setMap (UpdateWrapper实现)
     *
     * @return
     */
    public Map<String, String> getSetMap() {
        return Collections.emptyMap();
    }

    /**
     * SQL 片段 (子类实现)
     */
    public abstract String getSqlSegment();

    /**
     * 重写toString方法
     *
     * @return
     */
    public String toString() {
        return getSqlSegment();
    }

    /**
     * <p>
     * SQL中WHERE关键字跟的条件语句
     * </p>
     * <p>
     * eg: ew.where("name='zhangsan'").where("id={0}","123");
     * <p>
     * 输出: WHERE (NAME='zhangsan' AND id=123)
     * </p>
     *
     * @param sqlWhere
     *            where语句
     * @param params
     *            参数集
     * @return this
     */
    public Wrapper where(String sqlWhere, Object... params) {
        sql.WHERE(formatSql(sqlWhere, params));
        return this;
    }

    /**
     * <p>
     * 等同于SQL的"field=value"表达式
     * </p>
     *
     * @param column
     * @param params
     * @return
     */
    public Wrapper eq(String column, Object params) {
        sql.WHERE(formatSql(String.format("%s = {0}", column), params));
        return this;
    }

    /**
     * <p>
     * 等同于SQL的"field=value"表达式
     * </p>
     *
     * @param params
     * @return
     */

    public Wrapper allEq(Map<String, Object> params) {
        if (MapUtils.isNotEmpty(params)) {
            Iterator iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
                Object value = entry.getValue();
                if (StringUtils.checkValNotNull(value)) {
                    sql.WHERE(formatSql(String.format("%s = {0}", entry.getKey()), entry.getValue()));
                }

            }

        }
        return this;
    }

    /**
     * <p>
     * 等同于SQL的"field>value"表达式
     * </p>
     *
     * @param column
     * @param params
     * @return
     */
    public Wrapper gt(String column, Object params) {
        sql.WHERE(formatSql(String.format("%s > {0}", column), params));
        return this;
    }

    /**
     * <p>
     * 等同于SQL的"field>=value"表达式
     * </p>
     *
     * @param column
     * @param params
     * @return
     */
    public Wrapper ge(String column, Object params) {
        sql.WHERE(formatSql(String.format("%s >= {0}", column), params));
        return this;
    }

    /**
     * <p>
     * 等同于SQL的"field<value"表达式
     * </p>
     *
     * @param column
     * @param params
     * @return
     */
    public Wrapper lt(String column, Object params) {
        sql.WHERE(formatSql(String.format("%s < {0}", column), params));
        return this;
    }

    /**
     * <p>
     * 等同于SQL的"field<=value"表达式
     * </p>
     *
     * @param column
     * @param params
     * @return
     */
    public Wrapper le(String column, Object params) {
        sql.WHERE(formatSql(String.format("%s <= {0}", column), params));
        return this;
    }

    /**
     * <p>
     * AND 连接后续条件
     * </p>
     *
     * @param sqlAnd
     *            and条件语句
     * @param params
     *            参数集
     * @return this
     */
    public Wrapper and(String sqlAnd, Object... params) {
        sql.AND().WHERE(formatSql(sqlAnd, params));
        return this;
    }

    /**
     * <p>
     * 使用AND连接并换行
     * </p>
     * <p>
     * eg: ew.where("name='zhangsan'").and("id=11").andNew("statu=1"); 输出： WHERE
     * (name='zhangsan' AND id=11) AND (statu=1)
     * </p>
     *
     * @param sqlAnd
     *            AND 条件语句
     * @param params
     *            参数值
     * @return this
     */
    public Wrapper andNew(String sqlAnd, Object... params) {
        sql.AND_NEW().WHERE(formatSql(sqlAnd, params));
        return this;
    }

    /**
     * <p>
     * 添加OR条件
     * </p>
     *
     * @param sqlOr
     *            or 条件语句
     * @param params
     *            参数集
     * @return this
     */
    public Wrapper or(String sqlOr, Object... params) {
        sql.OR().WHERE(formatSql(sqlOr, params));
        return this;
    }

    /**
     * <p>
     * 使用OR换行，并添加一个带()的新的条件
     * </p>
     * <p>
     * eg: ew.where("name='zhangsan'").and("id=11").orNew("statu=1"); 输出： WHERE
     * (name='zhangsan' AND id=11) OR (statu=1)
     * </p>
     *
     * @param sqlOr
     *            AND 条件语句
     * @param params
     *            参数值
     * @return this
     */
    public Wrapper orNew(String sqlOr, Object... params) {
        sql.OR_NEW().WHERE(formatSql(sqlOr, params));
        return this;
    }

    /**
     * <p>
     * SQL中groupBy关键字跟的条件语句
     * </p>
     * <p>
     * eg: ew.where("name='zhangsan'").groupBy("id,name")
     * </p>
     *
     * @param columns
     *            SQL 中的 Group by 语句，无需输入 Group By 关键字
     * @return this
     */
    public Wrapper groupBy(String columns) {
        sql.GROUP_BY(columns);
        return this;
    }

    /**
     * <p>
     * SQL中having关键字跟的条件语句
     * </p>
     * <p>
     * eg: ew.groupBy("id,name").having("id={0}",22).and("password is not null")
     * </p>
     *
     * @param sqlHaving
     *            having关键字后面跟随的语句
     * @param params
     *            参数集
     * @return EntityWrapper
     */
    public Wrapper having(String sqlHaving, Object... params) {
        sql.HAVING(formatSql(sqlHaving, params));
        return this;
    }

    /**
     * <p>
     * SQL中orderby关键字跟的条件语句
     * </p>
     * <p>
     * eg: ew.groupBy("id,name").having("id={0}",22).and("password is not null"
     * ).orderBy("id,name")
     * </p>
     *
     * @param columns
     *            SQL 中的 order by 语句，无需输入 Order By 关键字
     * @return this
     */
    public Wrapper orderBy(String columns) {
        sql.ORDER_BY(columns);
        return this;
    }

    /**
     * <p>
     * SQL中orderby关键字跟的条件语句，可根据变更动态排序
     * </p>
     *
     * @param columns
     *            SQL 中的 order by 语句，无需输入 Order By 关键字
     * @param isAsc
     *            是否为升序
     * @return this
     */
    public Wrapper orderBy(String columns, boolean isAsc) {
        if (StringUtils.isNotBlank(columns)) {
            sql.ORDER_BY(columns + (isAsc ? " ASC" : " DESC"));
        }
        return this;
    }

    /**
     * LIKE条件语句，value中无需前后%
     *
     * @param column
     *            字段名称
     * @param value
     *            匹配值
     * @return this
     */
    public Wrapper like(String column, String value) {
        sql.LIKE(column, value, SqlLike.DEFAULT);
        return this;
    }

    /**
     * NOT LIKE条件语句，value中无需前后%
     *
     * @param column
     *            字段名称
     * @param value
     *            匹配值
     * @return this
     */
    public Wrapper notLike(String column, String value) {
        sql.NOT_LIKE(column, value, SqlLike.DEFAULT);
        return this;
    }

    /**
     * LIKE条件语句，value中无需前后%
     *
     * @param column
     *            字段名称
     * @param value
     *            匹配值
     * @param type
     * @return this
     */
    public Wrapper like(String column, String value, SqlLike type) {
        sql.LIKE(column, value, type);
        return this;
    }

    /**
     * NOT LIKE条件语句，value中无需前后%
     *
     * @param column
     *            字段名称
     * @param value
     *            匹配值
     * @param type
     * @return this
     */
    public Wrapper notLike(String column, String value, SqlLike type) {
        sql.NOT_LIKE(column, value, type);
        return this;
    }

    /**
     * is not null 条件
     *
     * @param columns
     *            字段名称。多个字段以逗号分隔。
     * @return this
     */
    public Wrapper isNotNull(String columns) {
        sql.IS_NOT_NULL(columns);
        return this;
    }

    /**
     * is not null 条件
     *
     * @param columns
     *            字段名称。多个字段以逗号分隔。
     * @return this
     */
    public Wrapper isNull(String columns) {
        sql.IS_NULL(columns);
        return this;
    }

    /**
     * EXISTS 条件语句，目前适配mysql及oracle
     *
     * @param value
     *            匹配值
     * @return this
     */
    public Wrapper exists(String value) {
        sql.EXISTS(value);
        return this;
    }

    /**
     * NOT EXISTS条件语句
     *
     * @param value
     *            匹配值
     * @return this
     */
    public Wrapper notExists(String value) {
        sql.NOT_EXISTS(value);
        return this;
    }

    /**
     * IN 条件语句，目前适配mysql及oracle
     *
     * @param column
     *            字段名称
     * @param value
     *            逗号拼接的字符串
     * @return this
     */
    public Wrapper in(String column, String value) {
        sql.IN(column, value);
        return this;
    }

    /**
     * NOT IN条件语句
     *
     * @param column
     *            字段名称
     * @param value
     *            逗号拼接的字符串
     * @return this
     */
    public Wrapper notIn(String column, String value) {
        sql.NOT_IN(column, value);
        return this;
    }

    /**
     * IN 条件语句，目前适配mysql及oracle
     *
     * @param column
     *            字段名称
     * @param value
     *            匹配值 List集合
     * @return this
     */
    public Wrapper in(String column, Collection<?> value) {
        sql.IN(column, value);
        return this;
    }

    /**
     * NOT IN 条件语句，目前适配mysql及oracle
     *
     * @param column
     *            字段名称
     * @param value
     *            匹配值 List集合
     * @return this
     */
    public Wrapper notIn(String column, Collection<?> value) {
        sql.NOT_IN(column, value);
        return this;
    }

    /**
     * IN 条件语句，目前适配mysql及oracle
     *
     * @param column
     *            字段名称
     * @param value
     *            匹配值 object数组
     * @return this
     */
    public Wrapper in(String column, Object[] value) {
        sql.IN(column, Arrays.asList(value));
        return this;
    }

    /**
     * NOT IN 条件语句，目前适配mysql及oracle
     *
     * @param column
     *            字段名称
     * @param value
     *            匹配值 object数组
     * @return this
     */
    public Wrapper notIn(String column, Object... value) {
        sql.NOT_IN(column, Arrays.asList(value));
        return this;
    }

    /**
     * betwwee 条件语句
     *
     * @param column
     *            字段名称
     * @param val1
     * @param val2
     * @return this
     */
    public Wrapper between(String column, String val1, String val2) {
        sql.BETWEEN_AND(column, val1, val2);
        return this;
    }

    /**
     * 为了兼容之前的版本,可使用where()或and()替代
     *
     * @param sqlWhere
     *            where sql部分
     * @param params
     *            参数集
     * @return this
     */
    public Wrapper addFilter(String sqlWhere, Object... params) {
        return and(sqlWhere, params);
    }

    /**
     * <p>
     * 根据判断条件来添加条件语句部分 使用 andIf() 替代
     * </p>
     * <p>
     * eg: ew.filterIfNeed(false,"name='zhangsan'").where("name='zhangsan'")
     * .filterIfNeed(true,"id={0}",22)
     * <p>
     * 输出: WHERE (name='zhangsan' AND id=22)
     * </p>
     *
     * @param need
     *            是否需要添加该条件
     * @param sqlWhere
     *            条件语句
     * @param params
     *            参数集
     * @return this
     */
    public Wrapper addFilterIfNeed(boolean need, String sqlWhere, Object... params) {
        return need ? where(sqlWhere, params) : this;
    }

    /**
     * <p>
     * SQL注入内容剥离
     * </p>
     *
     * @param value
     *            待处理内容
     * @return this
     */
    protected String stripSqlInjection(String value) {
        return value.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
    }

    /**
     * <p>
     * 格式化SQL
     * </p>
     *
     * @param sqlStr
     *            SQL语句部分
     * @param params
     *            参数集
     * @return this
     */
    protected String formatSql(String sqlStr, Object... params) {
        return formatSqlIfNeed(true, sqlStr, params);
    }

    /**
     * <p>
     * 根据需要格式化SQL
     * </p>
     *
     * @param need
     *            是否需要格式化
     * @param sqlStr
     *            SQL语句部分
     * @param params
     *            参数集
     * @return this
     */
    protected String formatSqlIfNeed(boolean need, String sqlStr, Object... params) {
        if (!need || StringUtils.isBlank(sqlStr)) {
            return null;
        }
        return StringUtils.sqlArgsFill(sqlStr, params);
    }
}
