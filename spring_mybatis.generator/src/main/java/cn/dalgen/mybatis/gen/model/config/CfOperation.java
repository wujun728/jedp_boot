package cn.dalgen.mybatis.gen.model.config;

import cn.dalgen.mybatis.gen.enums.MultiplicityEnum;
import cn.dalgen.mybatis.gen.enums.PagingCntTypeEnum;
import cn.dalgen.mybatis.gen.enums.ParamTypeEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by bangis.wangdf on 15/12/5. Desc
 */
public class CfOperation {

    /**
     * The Name.
     */
    private String           name;
    /**
     * Paging param name
     */
    private String           paging;
    private String           useGeneratedKeys;
    private String           keyProperty;
    /**
     * Paging param name
     */
    private String           pagingCntOperation;
    /**
     * The Param type.
     */
    private ParamTypeEnum    paramType;
    /**
     * The Multiplicity.
     */
    private MultiplicityEnum multiplicity;

    private PagingCntTypeEnum pagingCntType;
    /**
     * The Remark.
     */
    private String            remark;
    /**
     * The Resulttype.
     */
    private String            resulttype;
    /**
     * The Resultmap.
     */
    private String            resultmap;
    /**
     * The Timeout.
     */
    private Long              timeout;
    /**
     * list大小限制
     */
    private String listLimit = "201";
    private String flushCache;// (false|true) #IMPLIED
    private String useCache;// (false|true) #IMPLIED
    /**
     * 方法是否废弃
     */
    private String deprecated;
    /**
     * The Cdata.
     */
    private String cdata;

    /**
     * The Cdata page count.
     */
    private String cdataPageCount;

    /**
     * The Sql desc.
     */
    private String sqlDesc;

    /**
     * The Primitive params.
     */
    private Map<String, String> primitiveParams        = Maps.newLinkedHashMap();
    /**
     * The Primitive params.
     */
    private Map<String, String> primitiveParamTestVals = Maps.newLinkedHashMap();

    /**
     * The Primitive foreach params.
     */
    private Map<String, String> primitiveForeachParams = Maps.newLinkedHashMap();

    /**
     * The Primitive foreach params.
     */
    private Map<String, List<String>> primitiveForeachOtherParams = Maps.newLinkedHashMap();

    private String kvMap = "false";

    private String mapK;
    private String mapV;
    private Map<String, String> operationParams = Maps.newHashMap();
    private boolean autoGen = false;
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
     * Gets paging.
     *
     * @return the paging
     */
    public String getPaging() {
        return paging;
    }

    /**
     * Sets paging.
     *
     * @param paging the paging
     */
    public void setPaging(String paging) {
        this.paging = paging;
    }

    /**
     * Gets param type.
     *
     * @return the param type
     */
    public ParamTypeEnum getParamType() {
        return paramType;
    }

    /**
     * Sets param type.
     *
     * @param paramType the param type
     */
    public void setParamType(ParamTypeEnum paramType) {
        this.paramType = paramType;
    }

    /**
     * Gets multiplicity.
     *
     * @return the multiplicity
     */
    public MultiplicityEnum getMultiplicity() {
        return multiplicity;
    }

    /**
     * Sets multiplicity.
     *
     * @param multiplicity the multiplicity
     */
    public void setMultiplicity(MultiplicityEnum multiplicity) {
        this.multiplicity = multiplicity;
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
     * Gets resulttype.
     *
     * @return the resulttype
     */
    public String getResulttype() {
        return resulttype;
    }

    /**
     * Sets resulttype.
     *
     * @param resulttype the resulttype
     */
    public void setResulttype(String resulttype) {
        this.resulttype = resulttype;
    }

    /**
     * Gets resultmap.
     *
     * @return the resultmap
     */
    public String getResultmap() {
        return resultmap;
    }

    /**
     * Sets resultmap.
     *
     * @param resultmap the resultmap
     */
    public void setResultmap(String resultmap) {
        this.resultmap = resultmap;
    }

    /**
     * Gets timeout.
     *
     * @return the timeout
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * Sets timeout.
     *
     * @param timeout the timeout
     */
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    /**
     * Gets cdata.
     *
     * @return the cdata
     */
    public String getCdata() {
        return cdata;
    }

    /**
     * Sets cdata.
     *
     * @param cdata the cdata
     */
    public void setCdata(String cdata) {
        this.cdata = cdata;
    }

    /**
     * Gets primitive params.
     *
     * @return the primitive params
     */
    public Map<String, String> getPrimitiveParams() {
        return primitiveParams;
    }

    /**
     * Add primitive param.
     *
     * @param attr the attr
     * @param type the type
     */
    public void addPrimitiveParam(String attr, String type) {
        this.primitiveParams.put(attr, type);
    }

    public Map<String, String> getPrimitiveParamTestVals() {
        return primitiveParamTestVals;
    }

    /**
     * Add primitive paramTestVal.
     *
     * @param attr    the attr
     * @param testVal the val
     */
    public void addPrimitiveParamTestVal(String attr, String testVal) {
        this.primitiveParamTestVals.put(attr, testVal);
    }

    /**
     * Gets primitive foreach params.
     *
     * @return the primitive foreach params
     */
    public Map<String, String> getPrimitiveForeachParams() {
        return primitiveForeachParams;
    }

    /**
     * Add primitive foreach param.
     *
     * @param itemName the item name
     * @param collName the coll name
     */
    public void addPrimitiveForeachParam(String itemName, String collName) {
        if (this.primitiveForeachParams.containsKey(itemName)) {
            this.primitiveForeachParams.put(itemName + collName, collName);
        } else {
            this.primitiveForeachParams.put(itemName, collName);
        }
    }

    /**
     * Gets primitive foreach params.
     *
     * @return the primitive foreach params
     */
    public Map<String, List<String>> getPrimitiveForeachOtherParams() {
        return primitiveForeachOtherParams;
    }

    /**
     * Add primitive foreach param.
     *
     * @param itemName the item name
     * @param javaType the coll name
     */
    public void addPrimitiveForeachOtherParam(String itemName, String collName, String javaType) {
        this.primitiveForeachOtherParams.put(itemName, Lists.newArrayList(collName, javaType));
    }

    /**
     * Gets sql desc.
     *
     * @return the sql desc
     */
    public String getSqlDesc() {
        return sqlDesc;
    }

    /**
     * Sets sql desc.
     *
     * @param sqlDesc the sql desc
     */
    public void setSqlDesc(String sqlDesc) {
        this.sqlDesc = sqlDesc;
    }

    /**
     * Gets cdata page count.
     *
     * @return the cdata page count
     */
    public String getCdataPageCount() {
        return cdataPageCount;
    }

    /**
     * Sets cdata page count.
     *
     * @param cdataPageCount the cdata page count
     */
    public void setCdataPageCount(String cdataPageCount) {
        this.cdataPageCount = cdataPageCount;
    }

    public String getKvMap() {
        return kvMap;
    }

    public void setKvMap(String kvMap) {
        if (StringUtils.isNotBlank(kvMap)) {
            this.kvMap = kvMap;
        }
    }

    public String getMapK() {
        return mapK;
    }

    public void setMapK(String mapK) {
        this.mapK = mapK;
    }

    public String getMapV() {
        return mapV;
    }

    public void setMapV(String mapV) {
        this.mapV = mapV;
    }

    public String getPagingCntOperation() {
        return pagingCntOperation;
    }

    public void setPagingCntOperation(String pagingCntOperation) {
        this.pagingCntOperation = pagingCntOperation;
    }

    public PagingCntTypeEnum getPagingCntType() {
        return pagingCntType;
    }

    public void setPagingCntType(PagingCntTypeEnum pagingCntType) {
        this.pagingCntType = pagingCntType;
    }

    public String getUseGeneratedKeys() {
        return useGeneratedKeys;
    }

    public void setUseGeneratedKeys(String useGeneratedKeys) {
        this.useGeneratedKeys = useGeneratedKeys;
    }

    public String getKeyProperty() {
        return keyProperty;
    }

    public void setKeyProperty(String keyProperty) {
        this.keyProperty = keyProperty;
    }

    public Map<String, String> getOperationParams() {
        return operationParams;
    }

    public void addOperationParam(String paramName, String paramVal) {

        this.operationParams.put(paramName, paramVal);

    }

    public boolean isAutoGen() {
        return autoGen;
    }

    public void setAutoGen(boolean autoGen) {
        this.autoGen = autoGen;
    }

    public String getFlushCache() {
        return flushCache;
    }

    public void setFlushCache(String flushCache) {
        this.flushCache = flushCache;
    }

    public String getUseCache() {
        return useCache;
    }

    public void setUseCache(String useCache) {
        this.useCache = useCache;
    }

    public String getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(String deprecated) {
        this.deprecated = deprecated;
    }

    public String getListLimit() {
        return listLimit;
    }

    public void setListLimit(String listLimit) {
        if(StringUtils.isNotBlank(listLimit)) {
            this.listLimit = listLimit;
        }
    }
}
