package db.mysql.process;

import com.alibaba.fastjson.JSONObject;
import db.mysql.env.RuntimeEnv;
import org.apache.commons.lang3.StringUtils;

public class DataTypeSwitch {


    public static JSONObject defaultJSON ;

    private String switchType ;

    public DataTypeSwitch(String switchType) {
        this.switchType = switchType;
    }

    static {
        defaultJSON = new JSONObject();
        JSONObject mysql = new JSONObject();
        defaultJSON.put("mysql",mysql);
        JSONObject transfer = new JSONObject();
        JSONObject isBetween = new JSONObject();
        JSONObject changeType = new JSONObject();
        mysql.put("transfer",transfer);
        mysql.put("isBetween",isBetween);
        mysql.put("changeType",changeType);

        transfer.put("VARCHAR","String");
        transfer.put("ENUM","String");
        transfer.put("BLOB","byte[]");
        transfer.put("BINARY","byte[]");
        transfer.put("VARBINARY","byte[]");
        transfer.put("JSON","String");
        transfer.put("CHAR","String");
        transfer.put("TEXT","String");
        transfer.put("INTEGER","Long");
        transfer.put("BIGINT","Long");
        transfer.put("TINYINT","Integer");
        transfer.put("SMALLINT","Integer");
        transfer.put("MEDIUMINT","Integer");
        transfer.put("INT","Integer");
        transfer.put("FLOAT","Float");
        transfer.put("DOUBLE","Double");
        transfer.put("DECIMAL","java.math.BigDecimal");
        transfer.put("DATE","java.time.LocalDate");
        transfer.put("TIME","java.time.LocalTime");
        transfer.put("TIMESTAMP","java.time.LocalDateTime");
        transfer.put("MEDIUMTEXT","String");
        transfer.put("LONGTEXT","String");
        transfer.put("DATETIME","java.time.LocalDateTime");
        transfer.put("BIT","Integer");


        isBetween.put("VARCHAR","no");
        isBetween.put("ENUM","no");
        isBetween.put("BLOB","no");
        isBetween.put("BINARY","no");
        isBetween.put("VARBINARY","no");
        isBetween.put("JSON","no");
        isBetween.put("CHAR","no");
        isBetween.put("TEXT","no");
        isBetween.put("INTEGER","yes");
        isBetween.put("BIGINT","yes");
        isBetween.put("TINYINT","yes");
        isBetween.put("SMALLINT","yes");
        isBetween.put("MEDIUMINT","yes");
        isBetween.put("INT","yes");
        isBetween.put("FLOAT","yes");
        isBetween.put("DOUBLE","yes");
        isBetween.put("DECIMAL","yes");
        isBetween.put("DATE","yes");
        isBetween.put("TIME","yes");
        isBetween.put("TIMESTAMP","yes");
        isBetween.put("MEDIUMTEXT","no");
        isBetween.put("LONGTEXT","no");
        isBetween.put("DATETIME","yes");
        isBetween.put("BIT","yes");
        isBetween.put("INT8","yes");
        isBetween.put("INT4","yes");
        isBetween.put("INT2","yes");
        isBetween.put("BOOL","no");
        isBetween.put("FLOAT4","yes");
        isBetween.put("FLOAT8","yes");
        isBetween.put("BIGSERIAL","yes");
        isBetween.put("SERIAL","yes");


        JSONObject postgre = new JSONObject();
        defaultJSON.put("postgre",postgre);
        transfer = new JSONObject();
        isBetween = new JSONObject();
        changeType = new JSONObject();
        postgre.put("transfer",transfer);
        postgre.put("isBetween",isBetween);
        postgre.put("changeType",changeType);

        transfer.put("VARCHAR","String");
        transfer.put("CHAR","String");
        transfer.put("TEXT","String");
        transfer.put("INTEGER","Long");
        transfer.put("BIGINT","Long");
        transfer.put("TINYINT","Integer");
        transfer.put("SMALLINT","Integer");
        transfer.put("MEDIUMINT","Integer");
        transfer.put("INT","Integer");
        transfer.put("FLOAT","Float");
        transfer.put("DOUBLE","Double");
        transfer.put("DECIMAL","java.math.BigDecimal");
        transfer.put("DATE","java.time.LocalDate");
        transfer.put("TIME","java.time.LocalTime");
        transfer.put("TIMESTAMP","java.time.LocalDateTime");
        transfer.put("MEDIUMTEXT","String");
        transfer.put("LONGTEXT","String");
        transfer.put("DATETIME","java.time.LocalDateTime");
        transfer.put("BIT","String");
        transfer.put("INT8","Long");
        transfer.put("INT4","Integer");
        transfer.put("INT2","Integer");
        transfer.put("BOOL","Boolean");
        transfer.put("FLOAT4","Float");
        transfer.put("FLOAT8","Double");
        transfer.put("BIGSERIAL","Long");
        transfer.put("SERIAL","Integer");


        isBetween.put("INTEGER","yes");
        isBetween.put("BIGINT","yes");
        isBetween.put("TINYINT","yes");
        isBetween.put("SMALLINT","yes");
        isBetween.put("MEDIUMINT","yes");
        isBetween.put("INT","yes");
        isBetween.put("FLOAT","yes");
        isBetween.put("DOUBLE","yes");
        isBetween.put("DECIMAL","yes");
        isBetween.put("DATE","yes");
        isBetween.put("TIME","yes");
        isBetween.put("TIMESTAMP","yes");
        isBetween.put("DATETIME","yes");
        isBetween.put("INT8","yes");
        isBetween.put("INT4","yes");
        isBetween.put("INT2","yes");
        isBetween.put("FLOAT4","yes");
        isBetween.put("FLOAT8","yes");
        isBetween.put("BIGSERIAL","yes");
        isBetween.put("SERIAL","yes");


        changeType.put("TINYINT","int");
        changeType.put("SMALLINT","int");
        changeType.put("MEDIUMINT","int");
        changeType.put("DOUBLE","DECIMAL");
        changeType.put("MEDIUMTEXT","TEXT");
        changeType.put("LONGTEXT","TEXT");
        changeType.put("BIT","BIT");
        changeType.put("BOOL","BOOLEAN");
        changeType.put("BIGSERIAL","BIGINT");
        changeType.put("SERIAL","int");


    }

    public String transfer(String typeName) {

        JSONObject jsonObject = RuntimeEnv.readerSwitch(defaultJSON);
        JSONObject transfer = jsonObject.getJSONObject(switchType).getJSONObject("transfer");
        typeName = typeName.replaceAll(" UNSIGNED", "");
        String res = transfer.getString(typeName);
        if (org.apache.commons.lang3.StringUtils.isBlank(res)){
            throw new IllegalArgumentException(typeName + " can't find transfer object ,please edit "+RuntimeEnv.storagePath+RuntimeEnv.switchFile);
        }

        return res;

    }

    public String isBetween(String typeName) {
        JSONObject jsonObject = RuntimeEnv.readerSwitch(defaultJSON);
        JSONObject isBetween = jsonObject.getJSONObject(switchType).getJSONObject("isBetween");
        typeName = typeName.replaceAll(" UNSIGNED", "").toUpperCase();
        String res = isBetween.getString(typeName);
        if (StringUtils.isBlank(res)){
            return "no";
        }
        return res;

    }

    public String changeType(String typeName) {

        JSONObject jsonObject = RuntimeEnv.readerSwitch(defaultJSON);
        JSONObject changeType = jsonObject.getJSONObject(switchType).getJSONObject("changeType");
        typeName = typeName.replaceAll(" UNSIGNED", "").toUpperCase();
        if (StringUtils.isBlank(changeType.getString(typeName))){
            return typeName;
        }
        return changeType.getString(typeName);

    }

}
