package db.mysql.process.metadata;

import db.mysql.model.TableData;

import java.sql.SQLException;
import java.util.List;

/**
 * db.mysql.process.metadata
 *
 * @author mymx.jlh
 * @date 2018/1/23 11:44
 * 数据库读取元数据接口
 */
public interface DataBaseMetaDataProcess {

    void connect() throws ClassNotFoundException, SQLException;


    TableData getTableData(String tableName);


    List<String> getTableList();

}
