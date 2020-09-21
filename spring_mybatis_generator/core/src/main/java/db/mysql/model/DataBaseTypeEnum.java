package db.mysql.model;

import db.mysql.process.DataTypeSwitch;
import db.mysql.process.metadata.DataBaseMetaDataProcess;
import db.mysql.process.metadata.MysqlMetaDataProcess;
import db.mysql.process.metadata.PostgresMetaDataProcess;

/**
 * db.mysql.model
 *
 * @author mymx.jlh
 * @date 2018/1/21 20:25
 * 不同数据库的适配枚举
 */
public enum DataBaseTypeEnum {
    Mysql("mysql","com.mysql.jdbc.Driver",new  MysqlMetaDataProcess(),"`",new DataTypeSwitch("mysql")),
    Oracle("oracle","oracle.jdbc.driver.OracleDriver",null,"`",null),
    Postgres("postgres","org.postgresql.Driver",new PostgresMetaDataProcess(),"\"", new DataTypeSwitch("postgre"))
    ;


    DataBaseTypeEnum(String dataBaseTypeName, String driver, DataBaseMetaDataProcess metaDataProcess, String sense,DataTypeSwitch typeSwitch) {
        this.dataBaseTypeName = dataBaseTypeName;
        this.driver = driver;
        this.metaDataProcess = metaDataProcess;
        this.sense = sense;
        this.typeSwitch = typeSwitch;
    }

    /**
     * 数据库类型
     */
    private String dataBaseTypeName;
    /**
     * 驱动
     */
    private String driver;
    /**
     * 元数据处理策略对象
     */
    private DataBaseMetaDataProcess metaDataProcess;
    /**
     * 数据库字段名称转义符
     */
    private String sense;
    /**
     * 数据库类型与java类型适配器
     */
    private DataTypeSwitch typeSwitch;

    public DataBaseMetaDataProcess getMetaDataProcess() {
        return metaDataProcess;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    public DataTypeSwitch getTypeSwitch() {
        return typeSwitch;
    }

    public void setTypeSwitch(DataTypeSwitch typeSwitch) {
        this.typeSwitch = typeSwitch;
    }

    public void setMetaDataProcess(DataBaseMetaDataProcess metaDataProcess) {
        this.metaDataProcess = metaDataProcess;
    }

    public String getDataBaseTypeName() {
        return dataBaseTypeName;
    }

    public void setDataBaseTypeName(String dataBaseTypeName) {
        this.dataBaseTypeName = dataBaseTypeName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public static DataBaseTypeEnum getByName(String dataBaseTypeName){
        for (DataBaseTypeEnum dataBaseTypeEnum : DataBaseTypeEnum.values()){
            if (dataBaseTypeEnum.dataBaseTypeName.equals(dataBaseTypeName)){
                return dataBaseTypeEnum;
            }
        }
        throw new RuntimeException("illegal database type");
    }
}
