package cn.dalgen.mybatis.gen.model.java;

import cn.dalgen.mybatis.gen.model.config.CfTable;
import cn.dalgen.mybatis.gen.model.dbtable.Table;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * Created by bangis.wangdf on 15/12/13. Desc
 */
public class XmlMapper {
    /**
     * The Cf table.
     */
    private CfTable cfTable;

    /**
     * The Table.
     */
    private Table table;

    /**
     * The Do class.
     */
    private DO                  doClass;

    /**
     * The Do mapper.
     */
    private DOMapper            doMapper;
    /**
     * The Result maps.
     */
    private List<ResultMap>     resultMaps = Lists.newArrayList();

    private Map<String, String> sqlMap;

    /**
     * Gets cf table.
     *
     * @return the cf table
     */
    public CfTable getCfTable() {
        return cfTable;
    }

    /**
     * Sets cf table.
     *
     * @param cfTable the cf table
     */
    public void setCfTable(CfTable cfTable) {
        this.cfTable = cfTable;
    }

    /**
     * Gets table.
     *
     * @return the table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Sets table.
     *
     * @param table the table
     */
    public void setTable(Table table) {
        this.table = table;
    }

    /**
     * Gets do class.
     *
     * @return the do class
     */
    public DO getDoClass() {
        return doClass;
    }

    /**
     * Sets do class.
     *
     * @param doClass the do class
     */
    public void setDoClass(DO doClass) {
        this.doClass = doClass;
    }

    /**
     * Gets do mapper.
     *
     * @return the do mapper
     */
    public DOMapper getDoMapper() {
        return doMapper;
    }

    /**
     * Sets do mapper.
     *
     * @param doMapper the do mapper
     */
    public void setDoMapper(DOMapper doMapper) {
        this.doMapper = doMapper;
    }

    /**
     * Gets result maps.
     *
     * @return the result maps
     */
    public List<ResultMap> getResultMaps() {
        return resultMaps;
    }

    /**
     * Add result map.
     *
     * @param resultMap the result map
     */
    public void addResultMap(ResultMap resultMap) {
        this.resultMaps.add(resultMap);
    }

    public Map<String, String> getSqlMap() {
        return sqlMap;
    }

    public void setSqlMap(Map<String, String> sqlMap) {
        this.sqlMap = sqlMap;
    }
}
