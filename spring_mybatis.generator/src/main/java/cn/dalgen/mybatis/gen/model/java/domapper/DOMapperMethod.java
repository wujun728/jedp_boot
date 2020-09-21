package cn.dalgen.mybatis.gen.model.java.domapper;

import cn.dalgen.mybatis.gen.model.java.DOMapper;
import cn.dalgen.mybatis.gen.utils.CamelCaseUtils;
import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by bangis.wangdf on 15/12/13. Desc
 */
public class DOMapperMethod implements Cloneable {
    private DOMapper doMapper;
    /**
     * The Return class.
     */
    private String   returnClass;
    /**
     * The Name.
     */
    private String   name;

    private String pagingCntOperation;
    /**
     * The Paging name.
     */
    private String pagingName;
    /**
     * 方法是否废弃
     */
    private String deprecated;
    /**
     * The Desc.
     */
    private String desc;
    /**
     * The Sql.
     */
    private String sql;
    /**
     * list大小限制
     */
    private String listLimit = "201";
    /**
     * The Is paging.
     */
    private String pagingFlag = "false";

    /**
     * The Is paging.
     */
    private String pagingCustomFlag = "false";

    private String kvMap = "false";

    private String mapK;
    private String mapV;
    private String mapKType;
    private String mapVType;
    /**
     * The Params.
     */
    private List<DOMapperMethodParam> params          = Lists.newLinkedList();
    private Map<String, String>       operationParams = Maps.newHashMap();

    /**
     * Gets return class.
     *
     * @return the return class
     */
    public String getReturnClass() {
        return returnClass;
    }

    /**
     * Sets return class.
     *
     * @param returnClass the return class
     */
    public void setReturnClass(String returnClass) {
        this.returnClass = returnClass;
    }

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
     * Gets params.
     *
     * @return the params
     */
    public List<DOMapperMethodParam> getParams() {
        if (StringUtils.equals(ConfigUtil.getConfig().getExtParam("MethodParamSort"), "false")) {
            return params;
        }
        //对params进行排序
        Ordering<DOMapperMethodParam> byLengthOrdering = new Ordering<DOMapperMethodParam>() {
            @Override
            public int compare(DOMapperMethodParam left, DOMapperMethodParam right) {
                int cr = compare(left.getParamType(), right.getParamType());
                return cr == 0 ? compare(left.getParam(), right.getParam()) : cr;
            }

            private int compare(String left, String right) {
                int cr = Ints.compare(left.length(), right.length());
                return cr == 0 ? left.compareTo(right) : cr;
            }

        };
        if (CollectionUtils.isNotEmpty(params) && params.size() > 1) {
            return byLengthOrdering.sortedCopy(params);
        }
        return params;
    }

    /**
     * Add param.
     *
     * @param param the param
     */
    public void addParam(DOMapperMethodParam param) {
        if (!this.params.contains(param)) {
            this.params.add(param);
        }
    }

    /**
     * Sets params.
     *
     * @param params the params
     */
    public void setParams(List<DOMapperMethodParam> params) {
        this.params = params;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets sql.
     *
     * @return the sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * Sets sql.
     *
     * @param sql the sql
     */
    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * Gets paging flag.
     *
     * @return the paging flag
     */
    public String getPagingFlag() {
        return pagingFlag;
    }

    /**
     * Sets paging flag.
     *
     * @param pagingFlag the paging flag
     */
    public void setPagingFlag(String pagingFlag) {
        this.pagingFlag = pagingFlag;
    }

    /**
     * Gets paging name.
     *
     * @return the paging name
     */
    public String getPagingName() {
        return pagingName;
    }

    /**
     * Sets paging name.
     *
     * @param pagingName the paging name
     */
    public void setPagingName(String pagingName) {
        this.pagingName = pagingName;
    }

    public String getKvMap() {
        return kvMap;
    }

    public void setKvMap(String kvMap) {
        this.kvMap = kvMap;
    }

    public String getMapK() {
        return mapK;
    }

    public void setMapK(String mapK) {
        this.mapK = CamelCaseUtils.toCapitalizeCamelCase(mapK);
    }

    public String getMapV() {
        return mapV;
    }

    public void setMapV(String mapV) {
        this.mapV = CamelCaseUtils.toCapitalizeCamelCase(mapV);
    }

    public String getMapKType() {
        return mapKType;
    }

    public void setMapKType(String mapKType) {
        this.mapKType = mapKType;
    }

    public String getMapVType() {
        return mapVType;
    }

    public void setMapVType(String mapVType) {
        this.mapVType = mapVType;
    }

    public String getPagingCntOperation() {
        return pagingCntOperation;
    }

    public void setPagingCntOperation(String pagingCntOperation) {
        this.pagingCntOperation = pagingCntOperation;
    }

    public String getPagingCustomFlag() {
        return pagingCustomFlag;
    }

    public void setPagingCustomFlag(String pagingCustomFlag) {
        this.pagingCustomFlag = pagingCustomFlag;
    }

    public String getOperationParam(String key) {
        return operationParams.get(key);
    }

    public void setOperationParams(Map<String, String> operationParams) {
        this.operationParams = operationParams;
    }

    public void setDoMapper(DOMapper doMapper) {
        this.doMapper = doMapper;
    }

    List<String> sigleTypes= Lists.newArrayList("Date","Set","List","Map");
    public boolean neetParam() {
        if (this.getParams() != null && this.getParams().size() > 1) {
            return true;
        }
        if (this.getParams() == null || this.getParams().size() < 1) {
            return false;
        }
        String _type = this.getParams().get(0).getParamType();
        String param = this.getParams().get(0).getParam();
        if("list".equals(param)){
            return false;
        }
        if("set".equals(param)){
            return false;
        }
        if("map".equals(param)){
            return false;
        }
        if(sigleTypes.contains(_type)){
            return true;
        }
        for (String sigleType : sigleTypes) {
            if(_type.startsWith(sigleType+"<")){
                return true;
            }
        }

        if (CollectionUtils.isNotEmpty(this.doMapper.getImportLists())) {
            for (String _import : this.doMapper.getImportLists()) {
                if (_import.endsWith("." + _type) == true) {
                    return false;
                }
            }
        }
        return true;
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
        this.listLimit = listLimit;
    }
}
