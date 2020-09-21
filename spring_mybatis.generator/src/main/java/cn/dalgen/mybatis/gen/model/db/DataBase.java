package cn.dalgen.mybatis.gen.model.db;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.util.Map;

/**
 * Created by bangis.wangdf on 15/12/3. Desc
 */
public class DataBase {
    /**
     * The Name.
     */
    private String              name;
    /**
     * The Driver class.
     */
    private String              driverClass;
    /**
     * The Type.
     */
    private String              type;
    /**
     * The Gen package.
     */
    private String              genPackage;

    /**
     * The Gen package path.
     */
    private String              genPackagePath;

    private Map<String, String> subPackage  = Maps.newHashMap();

    /**
     * The Gen dal common package.
     */
    private String              genDalCommonPackage;

    /**
     * The Gen dal common package path.
     */
    private String              genDalCommonPackagePath;
    /**
     * The Table path.
     */
    private String              tablePath;
    /**
     * The Property map.
     */
    private Map<String, String> propertyMap = Maps.newHashMap();


    /**
     * 需要删除或替换的表前缀.
     */
    private Map<String, String>   tablePrefixs      = Maps.newHashMap();

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets table prefixs.
     *
     * @return the table prefixs
     */
    public Map<String, String> getTablePrefixs() {
        return tablePrefixs;
    }

    /**
     * Add table prefixs.
     *
     * @param tablePrefix the table prefix
     * @param replace the replace
     */
    public void addTablePrefixs(String tablePrefix, String replace) {

        if (StringUtils.isNotBlank(replace) && !StringUtils.endsWith(tablePrefix, "_")) {
            tablePrefix = tablePrefix + "_";
        }
        if (StringUtils.isNotBlank(replace) && !StringUtils.endsWith(replace, "_")) {
            replace = replace + "_";
        }
        this.tablePrefixs.put(tablePrefix, replace == null ? "" : replace);
    }


    /**
     * Gets gen package.
     *
     * @return the gen package
     */
    public String getGenPackage() {
        return genPackage;
    }

    /**
     * Gets gen package.
     *
     * @return the gen package
     */
    public String getGenPackage(String sub) {
        String basePackage = genPackage + "." + getSubPackage(sub);
        if(StringUtils.startsWith(getSubPackage(sub),"/")){
            basePackage = StringUtils.substring(getSubPackage(sub),1);
        }
        return basePackage;
    }

    /**
     * Sets gen package.
     *
     * @param genPackage the gen package
     */
    public void setGenPackage(String genPackage) {
        this.genPackage = genPackage;
    }

    /**
     * Gets gen package path.
     *
     * @return the gen package path
     */
    public String getGenPackagePath() {
        return genPackagePath;
    }

    /**
     * Sets gen package path.
     *
     * @param genPackagePath the gen package path
     */
    public void setGenPackagePath(String genPackagePath) {
        this.genPackagePath = genPackagePath;
    }

    /**
     * Gets table path.
     *
     * @return the table path
     */
    public String getTablePath() {
        return tablePath;
    }

    /**
     * Sets table path.
     *
     * @param tablePath the table path
     */
    public void setTablePath(String tablePath) {
        this.tablePath = tablePath;
    }

    /**
     * Add property.
     *
     * @param key the key
     * @param value the value
     */
    public void addProperty(String key, String value) {
        this.propertyMap.put(key, value);
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
     * Gets driver class.
     *
     * @return the driver class
     */
    public String getDriverClass() {
        return driverClass;
    }

    /**
     * Sets driver class.
     *
     * @param driverClass the driver class
     */
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets property map.
     *
     * @return the property map
     */
    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    /**
     * Gets property map val.
     *
     * @param key the key
     * @return the property map val
     */
    public String getPropertyMapVal(String key) {
        return this.propertyMap.get(key);
    }

    /**
     * Sets property map.
     *
     * @param propertyMap the property map
     */
    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    /**
     * Gets gen dal common package.
     *
     * @return the gen dal common package
     */
    public String getGenDalCommonPackage() {
        return genDalCommonPackage;
    }

    /**
     * Sets gen dal common package.
     *
     * @param genDalCommonPackage the gen dal common package
     */
    public void setGenDalCommonPackage(String genDalCommonPackage) {
        this.genDalCommonPackage = genDalCommonPackage;
    }

    /**
     * Gets gen dal common package path.
     *
     * @return the gen dal common package path
     */
    public String getGenDalCommonPackagePath() {
        return genDalCommonPackagePath;
    }

    /**
     * Sets gen dal common package path.
     *
     * @param genDalCommonPackagePath the gen dal common package path
     */
    public void setGenDalCommonPackagePath(String genDalCommonPackagePath) {
        this.genDalCommonPackagePath = genDalCommonPackagePath;
    }

    public String getSubPackage(String key) {
        String result = subPackage.get(key);
        if (StringUtils.isBlank(result)) {
            result = key;
        }
        return result;
    }

    public void addSubPackage(String key, String val) {
        this.subPackage.put(key, val);
    }
}
