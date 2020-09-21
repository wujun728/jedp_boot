package cn.dalgen.mybatis.gen.model.dbtable;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 表信息 Created by bangis.wangdf on 15/12/4. Desc
 */
public class Table {
    /**
     * The Db type.
     */
    private String          dbType;
    /**
     * The Sql name.
     */
    private String            sqlName;
    /**
     * The Java name.
     */
    private String            javaName;
    /**
     * The Remark.
     */
    private String            remark;
    /**
     * The Column list.
     */
    private List<Column>      columnList    = Lists.newArrayList();

    /**
     * The Primary keys.
     */
    private PrimaryKeys       primaryKeys;

    /**
     * The Unique indexes.
     */
    private List<UniqueIndex> uniqueIndexs = Lists.newArrayList();

    /**
     * The Normal indexes.
     */
    private List<NormalIndex> normalIndexs = Lists.newArrayList();

    /**
     * The Physical name.
     */
    private String            physicalName;

    /**
     * 需要软删除
     */
    private  boolean neadSoftDelete = false;

    /**
     * Gets primary keys.
     *
     * @return the primary keys
     */
    public PrimaryKeys getPrimaryKeys() {
        return primaryKeys;
    }

    /**
     * Sets primary keys.
     *
     * @param primaryKeys the primary keys
     */
    public void setPrimaryKeys(PrimaryKeys primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    /**
     * Gets sql name.
     *
     * @return the sql name
     */
    public String getSqlName() {
        return sqlName;
    }

    /**
     * Sets sql name.
     *
     * @param sqlName the sql name
     */
    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    /**
     * Gets java name.
     *
     * @return the java name
     */
    public String getJavaName() {
        return javaName;
    }

    /**
     * Sets java name.
     *
     * @param javaName the java name
     */
    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    /**
     * Gets remark.
     *
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets remark.
     *
     * @param remark the remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Gets column list.
     *
     * @return the column list
     */
    public List<Column> getColumnList() {
        //对params进行排序
        Ordering<Column> typeOrdering = new Ordering<Column>() {
            @Override
            public int compare(Column left, Column right) {
                if (StringUtils.equalsIgnoreCase(left.getSqlName(), "ID")) {
                    return -1;
                }
                if (StringUtils.equalsIgnoreCase(right.getSqlName(), "ID")) {
                    return 1;
                }
                int cr = compare(left.getJavaType(), right.getJavaType());
                return cr == 0 ? compare(left.getJavaName(), right.getJavaName()) : cr;
            }

            private int compare(String left, String right) {
                int cr = Ints.compare(left.length(), right.length());
                return cr == 0 ? left.compareTo(right) : cr;
            }

        };
        return typeOrdering.sortedCopy(columnList);
    }

    /**
     * 根据字段名获取Column
     *
     * @param name the name
     * @return column by name
     */
    public Column getColumnByName(String name) {
        for (Column column : columnList) {
            if (StringUtils.equalsIgnoreCase(name, column.getSqlName())) {
                return column;
            }
        }
        return null;
    }

    /**
     * Add column.
     *
     * @param column the column
     */
    public void addColumn(Column column) {
        this.columnList.add(column);
    }

    /**
     * Sets column list.
     *
     * @param columnList the column list
     */
    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    /**
     * Gets physical name.
     *
     * @return the physical name
     */
    public String getPhysicalName() {
        return physicalName;
    }

    /**
     * Sets physical name.
     *
     * @param physicalName the physical name
     */
    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    /**
     * Gets unique indexs.
     *
     * @return the unique indexs
     */
    public List<UniqueIndex> getUniqueIndexs() {
        return uniqueIndexs;
    }

    /**
     * Sets unique indexs.
     *
     * @param uniqueIndexs the unique indexs
     */
    public void setUniqueIndexs(List<UniqueIndex> uniqueIndexs) {
        this.uniqueIndexs = uniqueIndexs;
    }

    /**
     * Add unique index.
     *
     * @param uniqueIndex the unique index
     */
    public void addUniqueIndex(UniqueIndex uniqueIndex) {
        uniqueIndexs.add(uniqueIndex);

    }

    /**
     * Gets normal indexes.
     *
     * @return the normal indexes
     */
    public List<NormalIndex> getNormalIndexs() {
        return normalIndexs;
    }

    /**
     * Sets normal indexes.
     *
     * @param normalIndexs the normal indexes
     */
    public void setNormalIndexs(List<NormalIndex> normalIndexs) {
        this.normalIndexs = normalIndexs;
    }

    /**
     * Add normal index.
     *
     * @param normalIndex the normal index
     */
    public void addNormalIndex(NormalIndex normalIndex) {
        normalIndexs.add(normalIndex);
    }

    public boolean isNeadSoftDelete() {
        return neadSoftDelete;
    }

    public void setNeadSoftDelete(boolean neadSoftDelete) {
        this.neadSoftDelete = neadSoftDelete;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
