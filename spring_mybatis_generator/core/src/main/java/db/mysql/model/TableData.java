package db.mysql.model;

import java.util.List;

/**
 * db.mysql
 *
 * @author mymx.jlh
 * @date 2017/12/14 10:10
 */
public class TableData {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 主键名
     */
    private String autoKey;

    /**
     * 数据库列
     */
    private List<MySqlData> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAutoKey() {
        return autoKey;
    }

    public void setAutoKey(String autoKey) {
        this.autoKey = autoKey;
    }

    public List<MySqlData> getColumns() {
        return columns;
    }

    public void setColumns(List<MySqlData> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "TableData{" +
                "tableName='" + tableName + '\'' +
                ", autoKey='" + autoKey + '\'' +
                ", columns=" + columns +
                '}';
    }
}
