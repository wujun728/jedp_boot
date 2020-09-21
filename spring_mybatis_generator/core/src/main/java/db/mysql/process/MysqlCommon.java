package db.mysql.process;

import db.mysql.env.RuntimeEnv;
import db.mysql.model.DataBaseTypeEnum;
import db.mysql.model.TableData;
import db.mysql.process.metadata.DataBaseMetaDataProcess;

import java.sql.SQLException;
import java.util.List;

/**
 * PACKAGE_NAME
 * Created by ASUS on 2017/7/16.
 * 10:08
 */
public class MysqlCommon {
    private DataBaseMetaDataProcess dataBaseMetaDataProcess;

    public MysqlCommon() throws SQLException, ClassNotFoundException {
        dataBaseMetaDataProcess  = DataBaseTypeEnum.getByName(RuntimeEnv.pp.getDataBaseType()).getMetaDataProcess();
        dataBaseMetaDataProcess.connect();
    }


    public List<String> getTableList() throws SQLException, ClassNotFoundException {
        List<String> tables = dataBaseMetaDataProcess.getTableList();
        if (tables==null || tables.size()==0){
            dataBaseMetaDataProcess.connect();
            tables = dataBaseMetaDataProcess.getTableList();
        }
        return tables;
    }

    public TableData getTableData(String tableName) throws SQLException, ClassNotFoundException {
        TableData tableData= dataBaseMetaDataProcess.getTableData(tableName);
        if (tableData == null || tableData.getColumns()==null || tableData.getColumns().size()==0){
            dataBaseMetaDataProcess.connect();
            tableData = dataBaseMetaDataProcess.getTableData(tableName);
        }
        return tableData;
    }

}
