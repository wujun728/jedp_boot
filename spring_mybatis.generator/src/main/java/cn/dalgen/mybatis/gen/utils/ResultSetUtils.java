/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */
package cn.dalgen.mybatis.gen.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis.gen.utils: ResultSetUtils.java, v 0.1 2019-04-24 09:29 bangis.wangdf Exp $ 
 */
public class ResultSetUtils {
    /**
     * Str string.
     *
     * @param resultSet the result set
     * @param column    the column def
     * @return the string
     * @throws SQLException the sql exception
     */
    public static String getRsStr(ResultSet resultSet, String column) throws SQLException {
        if (StringUtils.equals("REMARKS", column)) {
            return resultSet.getString(column);
        }
        return StringUtils.upperCase(resultSet.getString(column));
    }

    /**
     * Str string.
     *
     * @param resultSet  the result set
     * @param column     the column
     * @param defaultVal the default val
     * @return the string
     * @throws SQLException the sql exception
     */
    public static String getRsStr(ResultSet resultSet, String column, String defaultVal)
        throws SQLException {
        String val = getRsStr(resultSet, column);
        return StringUtils.isBlank(val) ? defaultVal : val;
    }
}
