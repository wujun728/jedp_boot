/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */
package cn.dalgen.mybatis.gen.model.repository.db.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.dalgen.mybatis.gen.utils.CamelCaseUtils;
import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import cn.dalgen.mybatis.gen.utils.ResultSetUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

/**
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis.gen.model.repository.db.database: MySqlDataBaseInfoService.java, v 0.1 2019-04-23 16:39
 * bangis.wangdf Exp $
 */
public class MySqlDataBaseInfoService implements DataBaseInfoService {

    private static final String table_comment
        = "SELECT table_name,table_comment FROM information_schema.TABLES where  table_type = 'BASE TABLE'";

    private static final String table_cloumns = "SELECT COLUMN_NAME,DATA_TYPE,"
        + "COLUMN_COMMENT,ORDINAL_POSITION,COLUMN_DEFAULT,CHARACTER_MAXIMUM_LENGTH as C_LENGTH,NUMERIC_PRECISION as "
        + "C_PRECISION,NUMERIC_SCALE as C_SCALE FROM INFORMATION_SCHEMA.Columns WHERE  "
        + "table_name='${tableName}' AND "
        + "table_schema='${tableSchema}' order by ordinal_position asc";

    /**
     * TABLE_NAME    表名
     * TABLE_COMMENT 表备注
     *
     * 获取所有表备注
     *
     * @param connection
     * @return
     */
    @Override
    public Map<String, String> getAllTableRemark(Connection connection) throws SQLException {
        Map<String, String> result = Maps.newHashMap();
        final PreparedStatement pstmt = connection.prepareStatement(table_comment);
        final ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            result.put(StringUtils.upperCase(resultSet.getString("TABLE_NAME")),
                resultSet.getString("TABLE_COMMENT"));
        }
        return result;
    }

    /**
     * 获取表字段，类型，注释等
     *
     * ORDINAL_POSITION 字段顺序
     * COLUMN_NAME      字段名
     * DATA_TYPE        字段类型
     * COLUMN_DEFAULT   字段默认值
     * COLUMN_COMMENT   字段备注
     *
     * @param connection
     * @param tableName
     * @return
     */
    @Override
    public List<Map<String, String>> getAllColumnsByTableName(Connection connection, String tableName)
        throws SQLException {
        String schema = ConfigUtil.getCurrentDb().getPropertyMapVal("schema");
        String sql = StringUtils.replace(table_cloumns, "${tableSchema}", schema);
        sql = StringUtils.replace(sql, "${tableName}", tableName);
        final PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        List<Map<String, String>> result = Lists.newArrayList();

        while (resultSet.next()) {
            Map<String, String> columnMap = Maps.newHashMap();
            columnMap.put("ORDINAL_POSITION", resultSet.getString("ORDINAL_POSITION"));
            columnMap.put("COLUMN_NAME", ResultSetUtils.getRsStr(resultSet, "COLUMN_NAME"));
            columnMap.put("COLUMN_DEFAULT", ResultSetUtils.getRsStr(resultSet, "COLUMN_DEFAULT"));
            columnMap.put("COLUMN_COMMENT", ResultSetUtils.getRsStr(resultSet, "COLUMN_COMMENT"));
            columnMap.put("DATA_TYPE", ResultSetUtils.getRsStr(resultSet, "DATA_TYPE"));
            columnMap.put("C_LENGTH", ResultSetUtils.getRsStr(resultSet, "C_LENGTH"));
            columnMap.put("C_PRECISION", ResultSetUtils.getRsStr(resultSet, "C_PRECISION"));
            columnMap.put("C_SCALE", ResultSetUtils.getRsStr(resultSet, "C_SCALE"));
            result.add(columnMap);

        }
        return result;
    }

    /**
     * 获取数据库主键
     * key: 字段名
     * val: 主键名  注意转成驼峰结构
     *
     * @param connection
     * @param tableName
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, String> getPrimaryKeys(Connection connection, String tableName) throws SQLException {

        Map<String, String> result = Maps.newHashMap();
        ResultSet resultSet = connection.getMetaData().getPrimaryKeys(null, null, StringUtils.lowerCase(tableName));

        while (resultSet.next()) {
            String pkName = CamelCaseUtils.toCapitalizeCamelCase(ResultSetUtils.getRsStr(resultSet, "PK_NAME"));
            result.put(ResultSetUtils.getRsStr(resultSet, "COLUMN_NAME"), pkName);
        }
        return result;
    }

    /**
     * 获取数据库唯一约束
     * key: 字段名
     * val: 主键名  注意转成驼峰结构
     *
     * @param connection
     * @param tableName
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, String> getUniqueIndexs(Connection connection, String tableName) throws SQLException {
        Map<String, String> result = Maps.newHashMap();
        getDBIndex(connection, tableName, result, true);
        return result;
    }

    /**
     * 获取数据库普通索引
     * key: 字段名
     * val: 主键名  注意转成驼峰结构
     *
     * @param connection
     * @param tableName
     * @return
     * @throws SQLException
     */
    @Override
    public Map<String, String> getNormalIndexs(Connection connection, String tableName) throws SQLException {
        Map<String, String> result = Maps.newHashMap();
        getDBIndex(connection, tableName, result, false);
        return result;
    }

    private void getDBIndex(Connection connection, String tableName, Map<String, String> result, boolean unique)
        throws SQLException {
        ResultSet indexResultSet = connection.getMetaData().getIndexInfo(null, null, StringUtils.lowerCase(tableName),
            unique,
            false);
        while (indexResultSet.next()) {
            String index_name = ResultSetUtils.getRsStr(indexResultSet,
                "INDEX_NAME");
            String uniqueName = CamelCaseUtils.toCapitalizeCamelCase(
                ConfigUtil.getConfig().dealIndexName(index_name));
            result.put(ResultSetUtils.getRsStr(indexResultSet, "COLUMN_NAME"), uniqueName);
        }
    }
}
