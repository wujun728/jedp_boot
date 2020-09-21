package cn.dalgen.mybatis.gen.model.config;

import cn.dalgen.mybatis.gen.model.dbtable.Column;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 详见 dtd Created by bangis.wangdf on 15/12/5. Desc
 */
public class CfTable {
    /**
     * The Sqlname.
     */
    private String sqlname;
    /**
     * The Sequence.
     */
    private String sequence;
    /**
     * The Physical name. 物理表名,分库分表使用
     */
    private String physicalName;
    /**
     * The Remark.
     */
    private String remark;
    private Long   ordinalMaxPosition = Long.MAX_VALUE;      //CDATA #IMPLIED

    private String ordinalEffectiveDay;

    /**
     * The Columns.
     */
    private List<CfColumn>    columns    = Lists.newArrayList();
    /**
     * The Result maps.
     */
    private List<CfResultMap> resultMaps = Lists.newArrayList();

    private Map<String, String> sqlMap = Maps.newHashMap();

    /**
     * The Operations.
     */
    private List<CfOperation> operations = Lists.newArrayList();

    /**
     * The Db columns.
     */
    private List<Column> dbColumns = Lists.newArrayList();

    /**
     * Gets sqlname.
     *
     * @return the sqlname
     */
    public String getSqlname() {
        return sqlname;
    }

    /**
     * Gets sequence.
     *
     * @return the sequence
     */
    public String getSequence() {
        return sequence;
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
     * Gets remarks.
     *
     * @return the remarks
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets sqlname.
     *
     * @param sqlname the sqlname
     */
    public void setSqlname(String sqlname) {
        this.sqlname = sqlname;
    }

    /**
     * Sets sequence.
     *
     * @param sequence the sequence
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
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
     * Sets remark.
     *
     * @param remark the remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * Gets result maps.
     *
     * @return the result maps
     */
    public List<CfResultMap> getResultMaps() {
        return resultMaps;
    }

    /**
     * Gets operations.
     *
     * @return the operations
     */
    public List<CfOperation> getOperations() {
        return operations;
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
     * Add result map.
     *
     * @param resultMap the result map
     */
    public void addResultMap(CfResultMap resultMap) {
        this.resultMaps.add(resultMap);
    }

    /**
     * Add operation.
     *
     * @param operation the operation
     */
    public void addOperation(CfOperation operation) {
        this.operations.add(operation);
    }

    /**
     * Gets db columns.
     *
     * @return the db columns
     */
    public List<Column> getDbColumns() {
        return dbColumns;
    }

    /**
     * Sets db columns.
     *
     * @param dbColumns the db columns
     */
    public void setDbColumns(List<Column> dbColumns) {
        this.dbColumns = dbColumns;
    }

    public Long getOrdinalMaxPosition() {
        return ordinalMaxPosition;
    }

    public void setOrdinalMaxPosition(Long ordinalMaxPosition) {
        if (ordinalMaxPosition != null) {
            this.ordinalMaxPosition = ordinalMaxPosition;
        }
    }

    public String getOrdinalEffectiveDay() {
        return ordinalEffectiveDay;
    }
    static String dataPartens[] = new String[] {
        "yyyyMMdd","yyyy-MM-dd"
    };
    public Date getOrdinalEffectiveDate() {
        Date date=null;
        if(StringUtils.isNotBlank(ordinalEffectiveDay)) {
            try {
                date = DateUtils.parseDate(ordinalEffectiveDay, dataPartens);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public void setOrdinalEffectiveDay(String ordinalEffectiveDay) {
        this.ordinalEffectiveDay = ordinalEffectiveDay;
    }

    public Map<String, String> getSqlMap() {
        return sqlMap;
    }

    public void addSqlMap(String id, String sqlText) {
        this.sqlMap.put(id, sqlText);
    }
}
