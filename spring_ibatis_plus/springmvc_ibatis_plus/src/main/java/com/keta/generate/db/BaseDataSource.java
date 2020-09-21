package com.keta.generate.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.keta.generate.util.Resources;
import com.keta.generate.vo.Table;

/**
 * 数据库表信息工具
 * 
 * @author 00fly
 * @version [版本号, 2016-11-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class BaseDataSource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDataSource.class);
    
    // 使用ThreadLocal存储当前线程中的Connection对象
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    
    /**
     * 获取数据库连接
     * 
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @see [类、类#方法、类#成员]
     */
    protected Connection getConnection()
    {
        Connection connection = threadLocal.get();
        try
        {
            if (connection == null)
            {
                org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
                dataSource.setDriverClassName(Resources.JDBC_DRIVER);
                dataSource.setUrl(Resources.JDBC_URL);
                dataSource.setUsername(Resources.JDBC_USERNAME);
                dataSource.setPassword(Resources.JDBC_PASSWORD);
                connection = dataSource.getConnection();
                // 把 connection绑定到当前线程上
                threadLocal.set(connection);
            }
        }
        catch (SQLException e)
        {
            LOGGER.error("error", e);
            throw new RuntimeException("Failed to get DataBase connection");
        }
        return connection;
    }
    
    /**
     * 获取指定表的表结构
     * 
     * @param tableName
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public abstract Table getTable(String tableName)
        throws SQLException;
    
    /**
     * 查询数据库表字段名
     * 
     * @param tableName
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public List<String> queryColumnNames(String tableName)
        throws SQLException
    {
        LOGGER.info("querySql: {}", tableName);
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<String> columnNames = new ArrayList<>();
        try
        {
            conn = getConnection();
            if (conn != null)
            {
                String sql = String.format("select * from %s", tableName);
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                ResultSetMetaData md = rs.getMetaData();
                int columnCount = md.getColumnCount();
                for (int i = 1; i <= columnCount; i++)
                {
                    columnNames.add(md.getColumnName(i));
                }
                ps.close();
                rs.close();
            }
        }
        catch (SQLException e)
        {
            LOGGER.error("QuerySql Error!", e);
        }
        finally
        {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
            if (conn != null && conn.getAutoCommit())
            {
                freeConnection();
            }
            LOGGER.info("BaseDAOImpl querySql end ");
        }
        return columnNames;
    }
    
    /**
     * 释放数据库连接
     * 
     * @see [类、类#方法、类#成员]
     */
    public void freeConnection()
    {
        LOGGER.info("------释放数据库连接-------");
        try
        {
            Connection conn = threadLocal.get();
            if (conn != null)
            {
                conn.close();
                threadLocal.remove(); // 解除当前线程上绑定conn
            }
        }
        catch (SQLException e)
        {
            LOGGER.error("error", e);
        }
    }
}
