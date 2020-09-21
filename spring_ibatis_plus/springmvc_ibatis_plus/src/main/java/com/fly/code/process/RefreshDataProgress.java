package com.fly.code.process;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.dbutils.DbUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * 
 * 刷新数据库表进度条线程
 * 
 * @author 00fly
 * @version [版本号, 2017年5月3日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RefreshDataProgress implements IRunnableWithProgress
{
    static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    
    static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    
    private String url;
    
    private String userName;
    
    private String passWord;
    
    private String driver;
    
    // 表名列表
    private Set<String> tableNameSet = new TreeSet<>();
    
    public RefreshDataProgress(String driver, String url, String userName, String passWord, Set<String> tableNameSet)
    {
        super();
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
        this.tableNameSet = tableNameSet;
    }
    
    @Override
    public void run(IProgressMonitor monitor)
        throws InvocationTargetException, InterruptedException
    {
        monitor.beginTask("刷新数据", IProgressMonitor.UNKNOWN);
        monitor.subTask("获取数据库连接中......");
        Connection connection = null;
        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, passWord);
            monitor.subTask("获取数据表名列表中......");
            getAllTableName(driver, connection);
            monitor.done();
        }
        catch (ClassNotFoundException e)
        {
            throw new InvocationTargetException(e.getCause(), "加载数据库驱动失败!");
        }
        catch (SQLException e)
        {
            throw new InvocationTargetException(e.getCause(), "获取连接失败,请检查URL,用户名和密码,并确认网络状况!");
        }
        finally
        {
            DbUtils.closeQuietly(connection);
        }
    }
    
    private void getAllTableName(String driver, Connection con)
        throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 清空原来保存的表名信息
            tableNameSet.clear();
            String sql = (ORACLE_DRIVER.equals(driver) ? "select table_name from user_tables order by table_name" : "show tables");
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next())
            {
                for (int i = 1; i <= columnCount; i++)
                {
                    Object obj = rs.getObject(i);
                    tableNameSet.add(obj.toString());
                }
            }
        }
        finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pstmt);
        }
    }
}