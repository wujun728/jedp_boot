package db.mysql.process.metadata;

import db.mysql.env.RuntimeEnv;
import db.mysql.model.DataBaseTypeEnum;
import db.mysql.model.MySqlData;
import db.mysql.model.TableData;
import db.mysql.process.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * db.mysql.process.metadata
 *
 * @author mymx.jlh
 * @date 2018/1/23 11:48
 */
public class MysqlMetaDataProcess implements DataBaseMetaDataProcess {

    private DatabaseMetaData databaseMetaData;
    private Connection con = null;
    private List<String> tables;

    @Override
    public void connect() throws ClassNotFoundException, SQLException {
        tables = null;
        Class.forName(DataBaseTypeEnum.Mysql.getDriver());
        String url = RuntimeEnv.pp.getUrl()+"/"+ RuntimeEnv.pp.getSchema();
        String user = RuntimeEnv.pp.getUser();
        String password = RuntimeEnv.pp.getPassword();
        DriverManager.setLoginTimeout(5);
        con = DriverManager.getConnection(url, user, password);
        databaseMetaData= con.getMetaData();
    }

    public List<String> getTableList (){
        try {
            if (tables==null) {
                tables = new ArrayList<>();
                ResultSet rs = databaseMetaData.getTables(RuntimeEnv.pp.getSchema(), RuntimeEnv.pp.getSchema(), null, new String[]{"TABLE"});
                while (rs.next()) {
                    tables.add(rs.getString("TABLE_NAME"));
                }
                return tables;
            }
            else {
                return tables;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    /**
     * 获得表或视图中的所有列信息
     */
    public List<MySqlData> getTableColumns(String tableName) {
        List<MySqlData> mySqlDataList = new ArrayList<MySqlData>();
        try{

            Set<String> keySet = new HashSet<>();
            ResultSet rs;
            rs =databaseMetaData.getPrimaryKeys(RuntimeEnv.pp.getSchema(), RuntimeEnv.pp.getSchema(),tableName);
            while (rs.next()){
                keySet.add(rs.getString("COLUMN_NAME"));
            }
            rs = databaseMetaData.getColumns(RuntimeEnv.pp.getSchema(), RuntimeEnv.pp.getSchema(), tableName, "%");
            while (rs.next()){
                MySqlData mySqlData =new MySqlData();
                mySqlData.setColumnName(rs.getString("COLUMN_NAME"));
                mySqlData.setPropertiesName(RuntimeEnv.pp.isUnderlineToCamel()? StringUtils.underLineToCamel(mySqlData.getColumnName(),true):mySqlData.getColumnName());
                mySqlData.setTypeId(rs.getInt("DATA_TYPE"));
                mySqlData.setTypeName(rs.getString("TYPE_NAME"));
                mySqlData.setJavaTypeName(DataBaseTypeEnum.Mysql.getTypeSwitch().transfer(mySqlData.getTypeName()));
                mySqlData.setIsBetween(DataBaseTypeEnum.Mysql.getTypeSwitch().isBetween(mySqlData.getTypeName()));
                mySqlData.setRemarks(rs.getString("REMARKS"));
                mySqlData.setNullAble(rs.getInt("NULLABLE"));
                mySqlData.setColumnDef(rs.getString("COLUMN_DEF"));
                mySqlData.setIsAuto(rs.getString("IS_AUTOINCREMENT"));
                if (keySet.contains(mySqlData.getColumnName())){
                    mySqlData.setIsKey(1);
                }else {
                    mySqlData.setIsKey(0);
                }
                mySqlDataList.add(mySqlData);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return mySqlDataList;
    }

    private void getIndexInfo(String tableName) {
        try {
            ResultSet rs3 = databaseMetaData.getIndexInfo(RuntimeEnv.pp.getSchema(), RuntimeEnv.pp.getSchema(), tableName, false, true);
            while (rs3.next()) {
                System.out.println("数据库名: "+ rs3.getString(1));
                System.out.println("表模式: "+ rs3.getString(2));
                System.out.println("表名称: "+ rs3.getString(3));
                System.out.println("索引值是否可以不唯一: "+ rs3.getString(4));
                System.out.println("索引类别: "+ rs3.getString(5));
                System.out.println("索引名称: "+ rs3.getString(6));
                System.out.println("索引类型: "+ rs3.getString(7));
                System.out.println("索引中的列序列号: "+ rs3.getString(8));
                System.out.println("列名称: "+ rs3.getString(9));
                System.out.println("列排序序列: "+ rs3.getString(10));
                System.out.println("TYPE为 tableIndexStatistic时它是表中的行数否则它是索引中唯一值的数量: "+ rs3.getString(11));
                System.out.println("TYPE为 tableIndexStatisic时它是用于表的页数否则它是用于当前索引的页数: "+ rs3.getString(12));
                System.out.println("过滤器条件: "+ rs3.getString(13));
            }
            rs3.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


    public TableData getTableData(String tableName){
        TableData tableData = new TableData();
        tableData.setTableName(tableName);
        tableData.setColumns(this.getTableColumns(tableName));
        Optional<MySqlData> optionalMySqlData =tableData.getColumns().stream().filter(m->!m.getIsAuto().equals("NO")).findFirst();
        optionalMySqlData.ifPresent(mySqlData -> tableData.setAutoKey(mySqlData.getPropertiesName()));
        return tableData;
    }



}
