/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */
package cn.dalgen.mybatis.gen.model.repository.db.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis.gen.model.repository.db.database: DataBaseInfoService.java, v 0.1 2019-04-23 16:38
 * bangis.wangdf Exp $
 */
public interface DataBaseInfoService {

    /**
     * 获取所有表注释
     * TABLE_NAME    表名
     * TABLE_COMMENT 表备注
     *
     * 获取所有表备注
     *
     * @param connection
     * @return
     * @throws SQLException
     */
    Map<String, String> getAllTableRemark(Connection connection) throws SQLException;

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
     * @throws SQLException
     */
    List<Map<String, String>> getAllColumnsByTableName(Connection connection, String tableName) throws SQLException;

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
    Map<String, String> getPrimaryKeys(Connection connection, String tableName) throws SQLException;

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
    Map<String, String> getUniqueIndexs(Connection connection, String tableName) throws SQLException;

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
    Map<String, String> getNormalIndexs(Connection connection, String tableName) throws SQLException;
}
