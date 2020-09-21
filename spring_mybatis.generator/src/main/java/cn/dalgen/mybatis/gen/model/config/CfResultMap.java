package cn.dalgen.mybatis.gen.model.config;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by bangis.wangdf on 15/12/5. Desc
 */
public class CfResultMap {
    /**
     * The Name.
     */
    private String              name;
    /**
     * The Type. A fully qualified Java class name, or a type alias (see the
     * table above for the list of built-in type aliases).
     */
    private String              type;

    private String              extend = "none";

    /**
     * The Remark.
     */
    private String              remark;
    /**
     * The Columns.
     */
    private List<CfColumn>      columns      = Lists.newArrayList();

    /**
     * The associations.
     */
    private List<CfAssociation> associations = Lists.newArrayList();

    /**
     * The collections.
     */
    private List<CfCollection>  collections  = Lists.newArrayList();

    private List<String>        imports      = Lists.newArrayList();
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
     * Gets columns.
     *
     * @return the columns
     */
    public List<CfColumn> getColumns() {
        return columns;
    }

    /**
     * Add column.
     *
     * @param column the column
     */
    public void addColumn(CfColumn column) {
        this.columns.add(column);
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
     * Gets associations.
     *
     * @return the associations
     */
    public List<CfAssociation> getAssociations() {
        return associations;
    }

    /**
     * Add association.
     *
     * @param association the association
     */
    public void addAssociation(CfAssociation association) {
        this.associations.add(association);
    }

    /**
     * Gets collections.
     *
     * @return the collections
     */
    public List<CfCollection> getCollections() {
        return collections;
    }

    /**
     * Add collection.
     *
     * @param collection the collection
     */
    public void addCollection(CfCollection collection) {
        this.collections.add(collection);
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public List<String> getImports() {
        return imports;
    }

    public void assImport(String _import) {
        this.imports.add(_import);
    }
}
