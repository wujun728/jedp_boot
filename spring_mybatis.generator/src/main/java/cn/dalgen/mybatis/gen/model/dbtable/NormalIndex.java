package cn.dalgen.mybatis.gen.model.dbtable;

import java.util.List;

import com.google.common.collect.Lists;
import com.mysql.jdbc.StringUtils;

/**
 * Created by bangis.wangdf on 15/12/5. Desc 普通索引
 */
public class NormalIndex {
    /**
     * The Column list.
     */
    private List<Column> columnList = Lists.newArrayList();
    /**
     * The idx name.
     */
    private String       idxName;

    /**
     * Add column.
     *
     * @param column the column
     */
    public void addColumn(Column column) {
        if(column!=null) {
            this.columnList.add(column);
        }
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

    public String getIdxName() {
        return idxName;
    }

    public void setIdxName(String idxName) {
        if (StringUtils.startsWithIgnoreCase(idxName, "index_")) {
            this.idxName = idxName.substring("index_".length());
        } else if (StringUtils.startsWithIgnoreCase(idxName, "idx_")) {
            this.idxName = idxName.substring("idx_".length());
        } else {
            this.idxName = idxName;
        }
    }
}
