package cn.dalgen.mybatis.gen.model.java;

import java.util.List;

import cn.dalgen.mybatis.gen.model.config.CfAssociation;
import cn.dalgen.mybatis.gen.model.config.CfCollection;
import cn.dalgen.mybatis.gen.model.dbtable.Column;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.apache.commons.lang.StringUtils;

/**
 * Created by bangis.wangdf on 15/12/13. Desc
 */
public class ResultMap extends DO {
    /**
     * The Name.
     */
    private String       name;
    /**
     * The Type.
     */
    private String       type;

    private String              extend = "none";
    /**
     * The Column list.
     */
    private List<Column> columnList = Lists.newArrayList();

    /**
     * The associations.
     */
    private List<CfAssociation> associations = Lists.newArrayList();

    /**
     * The collections.
     */
    private List<CfCollection>  collections  = Lists.newArrayList();
    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
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
     * Add column.
     *
     * @param column the column
     */
    public void addColumn(Column column) {
        this.columnList.add(column);
    }

    public List<CfAssociation> getAssociations() {
        return associations;
    }

    public void setAssociations(List<CfAssociation> associations) {
        this.associations = associations;
    }

    public List<CfCollection> getCollections() {
        return collections;
    }

    public void setCollections(List<CfCollection> collections) {
        this.collections = collections;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }
}
