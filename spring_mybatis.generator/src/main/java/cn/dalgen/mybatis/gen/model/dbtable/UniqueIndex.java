package cn.dalgen.mybatis.gen.model.dbtable;

import java.util.List;

import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;

/**
 * Created by bangis.wangdf on 15/12/5. Desc 唯一索引
 */
public class UniqueIndex {
    /**
     * The Column list.
     */
    private List<Column> columnList = Lists.newArrayList();
    /**
     * The Pk name.
     */
    private String       ukName;

    /**
     * Add column.
     *
     * @param column the column
     */
    public void addColumn(Column column) {
        this.columnList.add(column);
    }

    /**
     * Gets column list.
     *
     * @return the column list
     */
    public List<Column> getColumnList() {
        return Lists.newCopyOnWriteArrayList(columnList);
    }

    /**
     * Sets column list.
     *
     * @param columnList the column list
     */
    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public String getUkName() {
        return ukName;
    }

    public void setUkName(String ukName) {
        if (StringUtils.startsWithIgnoreCase(ukName, "index_")) {
            this.ukName = ukName.substring("index_".length());
        } else if (StringUtils.startsWithIgnoreCase(ukName, "idx_")) {
            this.ukName = ukName.substring("idx_".length());
        } else {
            this.ukName = ukName;
        }
    }
}
