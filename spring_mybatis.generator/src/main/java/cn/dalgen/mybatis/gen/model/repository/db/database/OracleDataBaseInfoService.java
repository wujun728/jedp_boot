/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */
package cn.dalgen.mybatis.gen.model.repository.db.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import cn.dalgen.mybatis.gen.utils.CamelCaseUtils;
import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import cn.dalgen.mybatis.gen.utils.ResultSetUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis.gen.model.repository.db.database: MySqlDataBaseInfoService.java, v 0.1 2019-04-23 16:39
 * bangis.wangdf Exp $
 */
public class OracleDataBaseInfoService implements DataBaseInfoService {

    private static final String TABLE_COMMENT
        = "SELECT TABLE_NAME,TABLE_TYPE,COMMENTS as TABLE_COMMENT from USER_TAB_COMMENTS ";

    private Map<String, List<Map<String, String>>> tableCloumnsCacheMap = Maps.newHashMap();

    private static final String TABLE_CLOUMNS =
        "select  t.TABLE_NAME,t.COLUMN_NAME,t.DATA_TYPE,t.COLUMN_ID as ORDINAL_POSITION,t"
            + ".DATA_DEFAULT as COLUMN_DEFAULT,c.comments as COLUMN_COMMENT  ,t.DATA_LENGTH as C_LENGTH,t"
            + ".DATA_PRECISION as C_PRECISION,t.DATA_SCALE as C_SCALE "
            + "from user_tab_columns t  "
            + "left join user_col_comments c on t.table_name = c.table_name and t.column_name = c.column_name  "
            + "order by t.TABLE_NAME,t.COLUMN_ID asc";


    private             Map<String, Map<String, String>> tablePrimaryKeyCacheMap = Maps.newHashMap();
    public static final String                           TABLE_PRIMARY_KEY
                                                                                 =
        "SELECT au.table_name ,uc.column_name,au.index_name as PK_NAME FROM user_cons_columns uc JOIN "
            + "user_constraints au ON uc.constraint_name=au.constraint_name WHERE au.constraint_type='P' AND au"
            + ".table_name not like 'BIN%' order by au.table_name";
    private             Map<String, Map<String, String>> tableUKCacheMap         = Maps.newHashMap();
    private             Map<String, Map<String, String>> tableIdxCacheMap        = Maps.newHashMap();
    public static final String                           TABLE_INDEX
                                                                                 =
        "SELECT ui.table_name,ui.INDEX_NAME,uc.COLUMN_NAME,uc.COLUMN_POSITION FROM user_indexes ui JOIN user_ind_columns uc ON uc"
            + ".table_name=ui.table_name AND uc.index_name=ui.index_name WHERE "
            + " ui.uniqueness='${uniqueness}' "
            + " ORDER BY ui.table_name,ui.INDEX_NAME,uc.column_position ASC";

    public static void main(String[] args) {
        System.out.println(TABLE_CLOUMNS);
    }

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
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(TABLE_COMMENT);
        while (resultSet.next()) {
            result.put(StringUtils.upperCase(resultSet.getString("TABLE_NAME")),
                resultSet.getString("TABLE_COMMENT"));
        }
        statement.close();
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
        String upperCaseTableName = StringUtils.upperCase(tableName);
        if (MapUtils.isEmpty(tableCloumnsCacheMap)) {
            String sql = currSql(upperCaseTableName, TABLE_CLOUMNS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String _tableName = resultSet.getString("TABLE_NAME");
                List<Map<String, String>> maps = tableCloumnsCacheMap.get(_tableName);
                if (CollectionUtils.isEmpty(maps)) {
                    maps = Lists.newArrayList();
                    tableCloumnsCacheMap.put(_tableName, maps);
                }
                Map<String, String> columnMap = Maps.newHashMap();
                columnMap.put("ORDINAL_POSITION", resultSet.getString("ORDINAL_POSITION"));
                columnMap.put("COLUMN_NAME", ResultSetUtils.getRsStr(resultSet, "COLUMN_NAME"));
                columnMap.put("COLUMN_DEFAULT", ResultSetUtils.getRsStr(resultSet, "COLUMN_DEFAULT"));
                columnMap.put("COLUMN_COMMENT", ResultSetUtils.getRsStr(resultSet, "COLUMN_COMMENT"));
                columnMap.put("DATA_TYPE", ResultSetUtils.getRsStr(resultSet, "DATA_TYPE"));
                columnMap.put("C_LENGTH", ResultSetUtils.getRsStr(resultSet, "C_LENGTH"));
                columnMap.put("C_PRECISION", ResultSetUtils.getRsStr(resultSet, "C_PRECISION"));
                columnMap.put("C_SCALE", ResultSetUtils.getRsStr(resultSet, "C_SCALE"));
                maps.add(columnMap);

            }
            statement.close();
        }
        return tableCloumnsCacheMap.get(upperCaseTableName);
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
        String upperCaseTableName = StringUtils.upperCase(tableName);
        if (MapUtils.isEmpty(tablePrimaryKeyCacheMap)) {

            String sql = currSql(upperCaseTableName, TABLE_PRIMARY_KEY);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String _tableName = resultSet.getString("TABLE_NAME");
                Map<String, String> pkMap = tablePrimaryKeyCacheMap.get(_tableName);
                if (MapUtils.isEmpty(pkMap)) {
                    pkMap = Maps.newHashMap();
                    tablePrimaryKeyCacheMap.put(_tableName, pkMap);
                }
                String pkName = CamelCaseUtils.toCapitalizeCamelCase(ResultSetUtils.getRsStr(resultSet, "PK_NAME"));
                pkMap.put(ResultSetUtils.getRsStr(resultSet, "COLUMN_NAME"), pkName);
            }
            statement.close();
        }
        return tablePrimaryKeyCacheMap.get(upperCaseTableName);
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
        String upperCaseTableName = StringUtils.upperCase(tableName);
        if (unique) {
            if (MapUtils.isEmpty(tableUKCacheMap)) {
                cacheUkIdx(connection, tableUKCacheMap, upperCaseTableName, unique);
            }
            Map<String, String> stringStringMap = tableUKCacheMap.get(upperCaseTableName);
            if (MapUtils.isNotEmpty(stringStringMap)) {
                result.putAll(stringStringMap);
            }
        } else {
            if (MapUtils.isEmpty(tableIdxCacheMap)) {
                cacheUkIdx(connection, tableIdxCacheMap, upperCaseTableName, unique);
            }
            Map<String, String> stringStringMap = tableIdxCacheMap.get(upperCaseTableName);
            if (MapUtils.isNotEmpty(stringStringMap)) {
                result.putAll(stringStringMap);
            }
        }
    }

    private void cacheUkIdx(Connection connection, Map<String, Map<String, String>> tableUKIdxCacheMap,
                            String tableName, boolean unique) throws SQLException {
        String upperCaseTableName = StringUtils.upperCase(tableName);
        String sql = currSql(upperCaseTableName, TABLE_INDEX);
        String _uniqueness = unique ? "UNIQUE" : "NONUNIQUE";
        sql = StringUtils.replace(sql, "${uniqueness}", _uniqueness);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String _tableName = resultSet.getString("TABLE_NAME");
            Map<String, String> ukidxMap = tableUKIdxCacheMap.get(_tableName);
            if (MapUtils.isEmpty(ukidxMap)) {
                ukidxMap = Maps.newHashMap();
                tableUKIdxCacheMap.put(_tableName, ukidxMap);
            }
            String indexName = CamelCaseUtils.toCapitalizeCamelCase(
                ConfigUtil.getConfig().dealIndexName(ResultSetUtils.getRsStr(resultSet,
                    "INDEX_NAME")));
            ukidxMap.put(ResultSetUtils.getRsStr(resultSet, "COLUMN_NAME"), indexName);
        }
        statement.close();
    }

    /**
     * 构造sql
     *
     * @param tableName
     * @param tablePrimaryKey
     * @return
     */
    private String currSql(String tableName, String tablePrimaryKey) {
        String schema = ConfigUtil.getCurrentDb().getPropertyMapVal("schema");
        String sql = StringUtils.replace(tablePrimaryKey, "${tableSchema}", schema);
        sql = StringUtils.replace(sql, "${tableName}", StringUtils.upperCase(tableName));
        return sql;
    }
}
