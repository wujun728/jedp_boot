/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */
package cn.dalgen.mybatis.gen.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis.gen.enums: MySql2OracleEnum.java, v 0.1 2019-06-10 20:10 bangis.wangdf Exp $ 
 */
public enum  MySql2OracleEnum {
    BIGINT("BIGINT","NUMBER"),
    DOUBLE("DOUBLE","NUMBER(20,10)"),
    INTEGER("INTEGER","NUMBER"),
    CHAR("CHAR","CHAR"),
    DATE("DATE","DATE"),
    DATETIME("DATETIME","DATE"),
    TIMESTAMP("TIMESTAMP","DATE"),
    DECIMAL("DECIMAL","NUMBER"),
    INT("INT","NUMBER(10,0)"),
    VARCHAR("VARCHAR","VARCHAR"),
    VARCHAR2("VARCHAR","VARCHAR2"),
    TEXT("TEXT","VARCHAR2(2000)"),
    TINYINT("TINYINT","CHAR(1)"),
    OTHER("OTHER","OTHER"),
    ;

    private static final Map<String, MySql2OracleEnum> mysqlMap = new HashMap<String, MySql2OracleEnum>();
    private static final Map<String, MySql2OracleEnum> oracleMap = new HashMap<String, MySql2OracleEnum>();

    static {
        for (MySql2OracleEnum item : MySql2OracleEnum.values()) {
            mysqlMap.put(item.getMysqlType(), item);
            oracleMap.put(item.getOracleType(), item);
        }
    }
    MySql2OracleEnum(String mysqlType, String oracleType) {
        this.mysqlType = mysqlType;
        this.oracleType = oracleType;
    }



    private String mysqlType;
    private String oracleType;

    public String getMysqlType() {
        return mysqlType;
    }

    public String getOracleType() {
        return oracleType;
    }

    public static MySql2OracleEnum getMysqlType(String oracleType) {
        MySql2OracleEnum mySql2OracleEnum = oracleMap.get(oracleType);
        if(mySql2OracleEnum==null){
            return OTHER;
        }
        return mySql2OracleEnum;
    }
    public static MySql2OracleEnum getOracleType(String mysqlType) {
        MySql2OracleEnum mySql2OracleEnum = mysqlMap.get(mysqlType);
        if(mySql2OracleEnum==null){
            return OTHER;
        }
        return mySql2OracleEnum;
    }
}
