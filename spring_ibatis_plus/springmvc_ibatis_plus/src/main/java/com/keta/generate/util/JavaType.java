package com.keta.generate.util;

import org.apache.commons.lang3.StringUtils;

/**
 * JDBC Types转换Java Types
 * 
 */
public enum JavaType
{
    STRING("String"), CHAR("String"), VARCHAR("String"), VARCHAR2("String"), LONGVARCHAR("String"), TEXT("String"), MEDIUMTEXT("String"), LONGTEXT("String"), NUMERIC("BigDecimal"), DECIMAL(
        "BigDecimal"), BIT("Boolean"), TINYINT("Integer"), SMALLINT("Short"), INTEGER("Integer"), BIGINT("Long"), NUMBER("Long"), REAL("Float"), FLOAT("Double"), DOUBLE("Double"), BINARY(
            "byte[]"), VARBINARY("byte[]"), LONGVARBINARY("byte[]"), TINYBLOB(
                "byte[]"), BLOB("byte[]"), MEDIUMBLOB("byte[]"), LONGBLOB("byte[]"), CLOB("String"), DATE("Date"), DATETIME("Date"), TIME("Date"), TIMESTAMP("Timestamp"), INT("Integer");
    private String typeName;
    
    private JavaType(String typeName)
    {
        this.typeName = typeName;
    }
    
    public String getTypeName()
    {
        return typeName;
    }
    
    public static String getJavaType(String sqlType)
    {
        JavaType javaType = null;
        if (sqlType == null || "".equals(sqlType))
        {
            return STRING.getTypeName();
        }
        sqlType = StringUtils.trimToEmpty(sqlType.replace("UNSIGNED", ""));
        javaType = JavaType.valueOf((sqlType.replaceAll("\\(\\d{1,9}\\)$", "")).toUpperCase());
        if (javaType == null)
        {
            return STRING.getTypeName();
        }
        return javaType.getTypeName();
    }
    
    public static void main(String[] args)
    {
        System.out.println("STRING:" + JavaType.getJavaType("STRING"));
        System.out.println("CHAR:" + JavaType.getJavaType("CHAR"));
        System.out.println("DATE:" + JavaType.getJavaType("DATE"));
    }
}
