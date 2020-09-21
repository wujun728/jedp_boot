package cn.dalgen.mybatis.gen.enums;

import cn.dalgen.mybatis.gen.exception.DalgenException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bangis.wangdf on 15/12/5. 字段映射枚举
 */
public enum TypeMapEnum {
    /**
     * Char type map enum.
     */
    BLOB("BLOB", "byte[]"),
    /**
     * Char type map enum.
     */
    CHAR("CHAR", "String"),
    /**
     * Varchar type map enum.
     */
    VARCHAR("VARCHAR", "String"),
    /**
     * Varchar type map enum.
     */
    VARCHAR2("VARCHAR", "String"),
    NVARCHAR2("VARCHAR", "String"),
    /**
     * Longvarchar type map enum.
     */
    LONGVARCHAR("LONGVARCHAR", "String"),
    /**
     * Longvarchar type map enum.
     */
    TEXT("VARCHAR", "String"),
    /**
     * Numeric type map enum.
     */
    NUMERIC("NUMERIC", "java.math.BigDecimal"),
    /**
     * Numeric type map enum.
     */
    NUMBER("DECIMAL", "Long"),

    /**
     * Decimal type map enum.
     */
    DECIMAL("DECIMAL", "java.math.BigDecimal"),
    /**
     * Bit type map enum.
     */
    BIT("BIT", "Boolean"),
    /**
     * Tinyint type map enum.
     */
    TINYINT("TINYINT", "Boolean"),
    /**
     * Smallint type map enum.
     */
    SMALLINT("SMALLINT", "Integer"),

    /**
     * Int type map enum.
     */
    INT("INTEGER", "Integer"),

    /**
     * Integer type map enum.
     */
    INTEGER("INTEGER", "Integer"),
    /**
     * Bigint type map enum.
     */
    BIGINT("BIGINT", "Long"),
    /**
     * Real type map enum.
     */
    REAL("REAL", "Float"),
    /**
     * Float type map enum.
     */
    FLOAT("FLOAT", "Double"),
    /**
     * Double type map enum.
     */
    DOUBLE("DOUBLE", "Double"),
    /**
     * Binary type map enum.
     */
    BINARY("BINARY", "byte"),
    /**
     * Varbinary type map enum.
     */
    VARBINARY("VARBINARY", "byte"),
    /**
     * Longvarbinary type map enum.
     */
    LONGVARBINARY("LONGVARBINARY", "byte"),
    /**
     * Date type map enum.
     */
    DATE("DATE", "java.sql.Date"),
    /**
     * Time type map enum.
     */
    TIME("TIME", "java.sql.Time"),
    /**
     * Datetime type map enum.
     */
    DATETIME("TIMESTAMP", "java.sql.Time"),
    /**
     * Timestamp type map enum.
     */
    TIMESTAMP("TIMESTAMP", "java.sql.Timestamp"),

    OTHER("OTHER", "OTHER");

    /**
     * The Jdbc type.
     */
    //
    private String jdbcType;
    /**
     * The Java type.
     */
    private String javaType;

    /**
     * Instantiates a new Type map enum.
     *
     * @param jdbcType the jdbc type
     * @param javaType the java type
     */
    private TypeMapEnum(String jdbcType, String javaType) {
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    private static Map<String, TypeMapEnum> codeLookup = new HashMap<String, TypeMapEnum>();

    static {
        for (TypeMapEnum type : TypeMapEnum.values()) {
            codeLookup.put(type.name(), type);
        }
    }

    public static TypeMapEnum getByJdbcType(String jdbcType) {
        TypeMapEnum type = codeLookup.get(jdbcType);
        if (type != null) {
            return type;
        }
        throw new DalgenException("类型转换错误:" + jdbcType);
    }

    /**
     * Gets by jdbc type.
     *
     * @param jdbcType the jdbc type
     * @return the by jdbc type
     */
    public static TypeMapEnum getByJdbcTypeWithOther(String jdbcType) {
        TypeMapEnum type = codeLookup.get(jdbcType);
        if (type != null) {
            return type;
        }
        System.out.println(jdbcType +"   OTHER");
        return OTHER;
    }

    /**
     * Gets jdbc type.
     *
     * @return the jdbc type
     */
    public String getJdbcType() {
        return jdbcType;
    }

    /**
     * Gets java type.
     *
     * @return the java type
     */
    public String getJavaType() {
        return javaType;
    }
}
