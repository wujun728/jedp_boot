package cn.dalgen.mybatis.gen.model.dbtable;

import cn.dalgen.mybatis.gen.enums.MySql2OracleEnum;
import org.apache.commons.lang.StringUtils;

/**
 * Created by bangis.wangdf on 15/12/4. Desc
 */
public class Column implements Comparable {
    /**
     * The Sql type.
     */
    private String sqlType;
    /**
     * The Sql type.
     */
    private String mySqlType;
    /**
     * The Java type.
     */
    private String javaType;
    /**
     * The Sql name.
     */
    private String sqlName;
    /**
     * The Java name.
     */
    private String javaName;

    /**
     * The Test val.
     */
    private String testVal;
    /**
     * The Remarks.
     */
    private String remarks;
    /**
     * The Default value.
     */
    private String defaultValue;
    /**
     * 字符长度
     */
    private String length;
    /**
     * 精度
     */
    private String precision;
    /**
     * 规模
     */
    private String scale;

    /**
     *
     */
    private Long ordinalPosition;

    /**
     * Gets remarks.
     *
     * @return the remarks
     */
    public String getRemarks() {
        if (StringUtils.isNotBlank(remarks)) {
            String result = StringUtils.replace(remarks, "'", "");
            result = StringUtils.replace(result, "\n", "  ");
            return result;
        }
        return remarks;
    }

    /**
     * Sets remarks.
     *
     * @param remarks the remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? this.remarks : remarks;
    }

    /**
     * Gets sql type.
     *
     * @return the sql type
     */
    public String getSqlType() {
        return sqlType;
    }

    /**
     * Sets sql type.
     *
     * @param sqlType the sql type
     */
    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    /**
     * Gets java type.
     *
     * @return the java type
     */
    public String getJavaType() {
        return javaType;
    }

    /**
     * Sets java type.
     *
     * @param javaType the java type
     */
    public void setJavaType(String javaType) {
        this.javaType = javaType;
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
     * Gets default value.
     *
     * @return the default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets default value.
     *
     * @param defaultValue the default value
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets test val.
     *
     * @return the test val
     */
    public String getTestVal() {
        return testVal;
    }

    /**
     * Sets test val.
     *
     * @param testVal the test val
     */
    public void setTestVal(String testVal) {
        this.testVal = testVal;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Long getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Long ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    /**
     * Compare to int.
     *
     * @param o the o
     * @return the int
     */
    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        String ojavaName = ((Column)o).getJavaName();
        if (this.javaName.length() == ojavaName.length()) {
            return this.ordinalPosition.compareTo(((Column)o).getOrdinalPosition());
        }
        if (this.javaName.equalsIgnoreCase("id")) {
            return -1;
        }
        if (this.javaName.length() > ojavaName.length()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Equals boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getMySqlType() {
        return mySqlType;
    }

    public void setMySqlType(String mySqlType) {
        this.mySqlType = mySqlType;
    }

    public String getOracleType() {
        MySql2OracleEnum oracleType = MySql2OracleEnum.getOracleType(mySqlType);
        if (StringUtils.endsWith(oracleType.getOracleType(), ")")) {
            return oracleType.getOracleType();
        }
        if (StringUtils.isNotBlank(precision) && StringUtils.isNotBlank(scale)) {
            return oracleType.getOracleType() + "(" + precision + "," + scale + ")";
        }
        if (StringUtils.isNotBlank(precision)) {
            return oracleType.getOracleType() + "(" + precision + ")";
        }
        if (StringUtils.isNotBlank(length) && !"0".equals(length)) {
            return oracleType.getOracleType() + "(" + length + ")";
        }
        return oracleType.getOracleType();
    }
}
