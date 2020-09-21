package cn.dalgen.mybatis.gen.datasources;

import java.sql.Connection;

import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

/**
 * Created by bangis.wangdf on 15/12/4. Desc
 */
public class DBConnectionFactory {
    /**
     * The constant LOG.
     */
    private static final Log LOG = new SystemStreamLog();

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            LOG.debug("==== init connection");
            if (StringUtils
                .equalsIgnoreCase("mysql", ConfigUtil.getCurrentDb().getType())) {
                JdbcConnectionDriver jdbcConnection = new JdbcConnectionDriver();
                connection = jdbcConnection.getConnection(ConfigUtil.getCurrentDb());

            } else if (StringUtils
                .equalsIgnoreCase("oracle", ConfigUtil.getCurrentDb().getType())) {
                JdbcConnectionDriver jdbcConnection = new JdbcConnectionDriver();
                connection = jdbcConnection.getConnection(ConfigUtil.getCurrentDb());
            } else if (StringUtils
                .equalsIgnoreCase("tddl-mysql", ConfigUtil.getCurrentDb().getType())) {
                DalgenConnectionDriver driver = (DalgenConnectionDriver)Class.forName("TDDL").newInstance();
                connection = driver.getConnection(ConfigUtil.getCurrentDb());
            } else {
                DalgenConnectionDriver driver = (DalgenConnectionDriver)Class.forName(
                    ConfigUtil.getCurrentDb().getType()).newInstance();
                connection = driver.getConnection(ConfigUtil.getCurrentDb());
            }
            Validate.notNull(connection, "====connection error ");
        } catch (Exception e) {
            LOG.error("", e);
        }
        ConfigUtil.getCurrentDb().setConnection(connection);
        return connection;

    }

}
