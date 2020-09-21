package db.mysql.process.metadata;

import db.mysql.env.RuntimeEnv;
import db.mysql.model.DataBaseTypeEnum;
import db.mysql.model.MySqlData;
import db.mysql.model.TableData;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * db.mysql.process.metadata
 *
 * @author mymx.jlh
 */
public class PostgresMetaDataProcess implements DataBaseMetaDataProcess {
    private DatabaseMetaData databaseMetaData;
    private Connection con = null;
    private List<String> tables;


    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        tables = null;
        Class.forName(DataBaseTypeEnum.Postgres.getDriver());
        String url = RuntimeEnv.pp.getUrl();
        String user = RuntimeEnv.pp.getUser();
        String password = RuntimeEnv.pp.getPassword();
        DriverManager.setLoginTimeout(5);
        con = DriverManager.getConnection(url, user, password);
        databaseMetaData = con.getMetaData();
    }

    @Override
    public TableData getTableData(String tableName) {
        TableData tableData = new TableData();
        tableData.setTableName(tableName);
        tableData.setColumns(this.getTableColumns(tableName));
        Optional<MySqlData> optionalMySqlData = tableData.getColumns().stream().filter(m -> !m.getIsAuto().equals("NO")).findFirst();
        optionalMySqlData.ifPresent(mySqlData -> tableData.setAutoKey(mySqlData.getPropertiesName()));
        return tableData;
    }

    public List<MySqlData> getTableColumns(String tableName) {
        List<MySqlData> mySqlDataList = new ArrayList<MySqlData>();
        try {

            Set<String> keySet = new HashSet<>();
            ResultSet rs;
            rs = databaseMetaData.getPrimaryKeys(null, RuntimeEnv.pp.getSchema(), tableName);
            while (rs.next()) {
                keySet.add(rs.getString("COLUMN_NAME"));
            }
            rs = databaseMetaData.getColumns(null, RuntimeEnv.pp.getSchema(), tableName, "%");
            while (rs.next()) {
                MySqlData mySqlData = new MySqlData();
                mySqlData.setColumnName(rs.getString("COLUMN_NAME"));
                mySqlData.setPropertiesName(RuntimeEnv.pp.isUnderlineToCamel()? db.mysql.process.StringUtils.underLineToCamel(mySqlData.getColumnName(),true):mySqlData.getColumnName());
                mySqlData.setTypeId(rs.getInt("DATA_TYPE"));
                mySqlData.setTypeName(rs.getString("TYPE_NAME"));
                mySqlData.setJavaTypeName(DataBaseTypeEnum.Postgres.getTypeSwitch().transfer(mySqlData.getTypeName()));
                mySqlData.setIsBetween(DataBaseTypeEnum.Postgres.getTypeSwitch().isBetween(mySqlData.getTypeName()));
                mySqlData.setTypeName(DataBaseTypeEnum.Postgres.getTypeSwitch().changeType(mySqlData.getTypeName()));
                mySqlData.setRemarks(StringUtils.defaultIfBlank(rs.getString("REMARKS"), ""));
                mySqlData.setNullAble(rs.getInt("NULLABLE"));
                mySqlData.setColumnDef(rs.getString("COLUMN_DEF"));
                mySqlData.setIsAuto(rs.getString("IS_AUTOINCREMENT"));
                if (keySet.contains(mySqlData.getColumnName())) {
                    mySqlData.setIsKey(1);
                } else {
                    mySqlData.setIsKey(0);
                }
                mySqlDataList.add(mySqlData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mySqlDataList;
    }

    @Override
    public List<String> getTableList() {
        try {
            if (tables == null) {
                this.tables = new ArrayList<>();
                ResultSet rs = databaseMetaData.getTables(null, RuntimeEnv.pp.getSchema(), null, new String[]{"TABLE"});
                while (rs.next()) {
                    this.tables.add(rs.getString("TABLE_NAME"));
                }
                return this.tables;
            } else {
                return tables;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
