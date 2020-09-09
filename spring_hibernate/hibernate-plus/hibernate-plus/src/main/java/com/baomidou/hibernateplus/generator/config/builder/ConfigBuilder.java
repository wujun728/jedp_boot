/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.hibernateplus.generator.config.builder;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.hibernateplus.generator.config.ConstVal;
import com.baomidou.hibernateplus.generator.config.DataSourceConfig;
import com.baomidou.hibernateplus.generator.config.GlobalConfig;
import com.baomidou.hibernateplus.generator.config.PackageConfig;
import com.baomidou.hibernateplus.generator.config.StrategyConfig;
import com.baomidou.hibernateplus.generator.config.TemplateConfig;
import com.baomidou.hibernateplus.generator.config.po.TableField;
import com.baomidou.hibernateplus.generator.config.po.TableInfo;
import com.baomidou.hibernateplus.generator.config.rules.DbType;
import com.baomidou.hibernateplus.generator.config.rules.NamingStrategy;
import com.baomidou.hibernateplus.generator.config.rules.QuerySQL;
import com.baomidou.hibernateplus.utils.StringUtils;

/**
 * 配置汇总 传递给文件生成工具
 *
 * @author YangHu, tangguo, hubin
 * @since 2016-08-30
 */
public class ConfigBuilder {

    /**
     * 模板路径配置信息
     */
    private final TemplateConfig template;
    /**
     * SQL连接
     */
    private Connection connection;
    /**
     * SQL语句类型
     */
    private QuerySQL querySQL;
    private String superModelClass;
    private String superDaoClass;
    private String superDaoImplClass;
    /**
     * service超类定义
     */
    private String superServiceClass;
    private String superServiceImplClass;
    private String superControllerClass;
    /**
     * 数据库表信息
     */
    private List<TableInfo> tableInfoList;
    /**
     * 包配置详情
     */
    private Map<String, String> packageInfo;
    /**
     * 路径配置信息
     */
    private Map<String, String> pathInfo;
    /**
     * 策略配置
     */
    private StrategyConfig strategyConfig;

    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;

    /**
     * 在构造器中处理配置
     *
     * @param packageConfig    包配置
     * @param dataSourceConfig 数据源配置
     * @param strategyConfig   表配置
     */
    public ConfigBuilder(PackageConfig packageConfig, DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig,
                         TemplateConfig template, GlobalConfig globalConfig) {
        // 全局配置
        if (null == globalConfig) {
            this.globalConfig = new GlobalConfig();
        } else {
            this.globalConfig = globalConfig;
        }
        // 包配置
        if (null == packageConfig) {
            handlerPackage(this.globalConfig.getOutputDir(), new PackageConfig());
        } else {
            handlerPackage(this.globalConfig.getOutputDir(), packageConfig);
        }
        handlerDataSource(dataSourceConfig);
        // 策略配置
        if (null == strategyConfig) {
            this.strategyConfig = new StrategyConfig();
        } else {
            this.strategyConfig = strategyConfig;
        }
        handlerStrategy(this.strategyConfig);
        // 模板配置
        if (null == template) {
            this.template = new TemplateConfig();
        } else {
            this.template = template;
        }
    }

    // ************************ 曝露方法 BEGIN*****************************

    /**
     * 所有包配置信息
     *
     * @return 包配置
     */
    public Map<String, String> getPackageInfo() {
        return packageInfo;
    }

    /**
     * 所有路径配置
     *
     * @return 路径配置
     */
    public Map<String, String> getPathInfo() {
        return pathInfo;
    }

    public String getSuperModelClass() {
        return superModelClass;
    }

    public String getSuperDaoClass() {
        return superDaoClass;
    }

    public String getSuperDaoImplClass() {
        return superDaoImplClass;
    }

    /**
     * 获取超类定义
     *
     * @return 完整超类名称
     */
    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    /**
     * 表信息
     *
     * @return 所有表信息
     */
    public List<TableInfo> getTableInfoList() {
        return tableInfoList;
    }

    /**
     * 模板路径配置信息
     *
     * @return 所以模板路径配置信息
     */
    public TemplateConfig getTemplate() {
        return template == null ? new TemplateConfig() : template;
    }

    // ****************************** 曝露方法 END**********************************

    /**
     * 处理包配置
     *
     * @param config PackageConfig
     */
    private void handlerPackage(String outputDir, PackageConfig config) {
        packageInfo = new HashMap<String, String>();
        packageInfo.put(ConstVal.MODULENAME, config.getModuleName());
        packageInfo.put(ConstVal.PO, joinPackage(config.getParent(), config.getPo()));
        packageInfo.put(ConstVal.VO, joinPackage(config.getParent(), config.getVo()));
        packageInfo.put(ConstVal.DAO, joinPackage(config.getParent(), config.getDao()));
        packageInfo.put(ConstVal.DAOIMPL, joinPackage(config.getParent(), config.getDaoImpl()));
        packageInfo.put(ConstVal.SERIVCE, joinPackage(config.getParent(), config.getService()));
        packageInfo.put(ConstVal.SERVICEIMPL, joinPackage(config.getParent(), config.getServiceImpl()));
        packageInfo.put(ConstVal.CONTROLLER, joinPackage(config.getParent(), config.getController()));

        pathInfo = new HashMap<String, String>();
        pathInfo.put(ConstVal.PO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.PO)));
        pathInfo.put(ConstVal.VO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.VO)));
        pathInfo.put(ConstVal.DAO_PATH, joinPath(outputDir, packageInfo.get(ConstVal.DAO)));
        pathInfo.put(ConstVal.DAOIMPL_PATH, joinPath(outputDir, packageInfo.get(ConstVal.DAOIMPL)));
        pathInfo.put(ConstVal.SERIVCE_PATH, joinPath(outputDir, packageInfo.get(ConstVal.SERIVCE)));
        pathInfo.put(ConstVal.SERVICEIMPL_PATH, joinPath(outputDir, packageInfo.get(ConstVal.SERVICEIMPL)));
        pathInfo.put(ConstVal.CONTROLLER_PATH, joinPath(outputDir, packageInfo.get(ConstVal.CONTROLLER)));
    }

    /**
     * 处理数据源配置
     *
     * @param config DataSourceConfig
     */
    private void handlerDataSource(DataSourceConfig config) {
        connection = config.getConn();
        querySQL = getQuerySQL(config.getDbType());
    }

    /**
     * 处理数据库表 加载数据库表、列、注释相关数据集
     *
     * @param config StrategyConfig
     */
    private void handlerStrategy(StrategyConfig config) {
        processTypes(config);
        tableInfoList = getTablesInfo(config);
    }

    /**
     * 处理superClassName,IdClassType,IdStrategy配置
     *
     * @param config 策略配置
     */
    private void processTypes(StrategyConfig config) {
        if (StringUtils.isBlank(config.getSuperServiceClass())) {
            superServiceClass = ConstVal.SUPERD_SERVICE_CLASS;
        } else {
            superServiceClass = config.getSuperServiceClass();
        }
        if (StringUtils.isBlank(config.getSuperServiceImplClass())) {
            superServiceImplClass = ConstVal.SUPERD_SERVICEIMPL_CLASS;
        } else {
            superServiceImplClass = config.getSuperServiceImplClass();
        }
        if (StringUtils.isBlank(config.getSuperDaoClass())) {
            superDaoClass = ConstVal.SUPERD_DAO_CLASS;
        } else {
            superDaoClass = config.getSuperDaoClass();
        }
        if (StringUtils.isBlank(config.getSuperDaoImplClass())) {
            superDaoImplClass = ConstVal.SUPERD_DAOIMPL_CLASS;
        } else {
            superDaoImplClass = config.getSuperDaoImplClass();
        }
        superModelClass = config.getSuperModelClass();
        superControllerClass = config.getSuperControllerClass();
    }

    /**
     * 处理表对应的类名称
     *
     * @param tableList   表名称
     * @param strategy    命名策略
     * @param tablePrefix
     * @return 补充完整信息后的表
     */
    private List<TableInfo> processTable(List<TableInfo> tableList, NamingStrategy strategy, String tablePrefix) {
        for (TableInfo tableInfo : tableList) {
            tableInfo.setPoName(NamingStrategy.capitalFirst(processName(tableInfo.getName(), strategy, tablePrefix)));
            tableInfo.setVoName(NamingStrategy.capitalFirst(processName(tableInfo.getName(), strategy, tablePrefix)));
            if (StringUtils.isNotBlank(globalConfig.getDaoName())) {
                tableInfo.setDaoName(String.format(globalConfig.getDaoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setDaoName(tableInfo.getPoName() + ConstVal.DAO);
            }
            if (StringUtils.isNotBlank(globalConfig.getDaoImplName())) {
                tableInfo.setDaoImplName(String.format(globalConfig.getDaoImplName(), tableInfo.getPoName()));
            } else {
                tableInfo.setDaoImplName(tableInfo.getPoName() + ConstVal.DAOIMPL);
            }
            if (StringUtils.isNotBlank(globalConfig.getServiceName())) {
                tableInfo.setServiceName(String.format(globalConfig.getServiceName(), tableInfo.getPoName()));
            } else {
                tableInfo.setServiceName(tableInfo.getPoName() + ConstVal.SERIVCE);
            }
            if (StringUtils.isNotBlank(globalConfig.getServiceImplName())) {
                tableInfo.setServiceImplName(String.format(globalConfig.getServiceImplName(), tableInfo.getPoName()));
            } else {
                tableInfo.setServiceImplName(tableInfo.getPoName() + ConstVal.SERVICEIMPL);
            }
            if (StringUtils.isNotBlank(globalConfig.getControllerName())) {
                tableInfo.setControllerName(String.format(globalConfig.getControllerName(), tableInfo.getPoName()));
            } else {
                tableInfo.setControllerName(tableInfo.getPoName() + ConstVal.CONTROLLER);
            }
            if (StringUtils.isNotBlank(globalConfig.getPoName())) {
                tableInfo.setPoName(String.format(globalConfig.getPoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setPoName(tableInfo.getPoName() + ConstVal.PO);
            }
            if (StringUtils.isNotBlank(globalConfig.getVoName())) {
                tableInfo.setVoName(String.format(globalConfig.getVoName(), tableInfo.getPoName()));
            } else {
                tableInfo.setVoName(tableInfo.getPoName() + ConstVal.VO);
            }
        }
        return tableList;
    }

    /**
     * 获取所有的数据库表信息
     *
     * @return 表信息
     */
    private List<TableInfo> getTablesInfo(StrategyConfig config) {
        boolean isInclude = (null != config.getInclude() && config.getInclude().length > 0);
        boolean isExclude = (null != config.getExclude() && config.getExclude().length > 0);
        if (isInclude && isExclude) {
            throw new RuntimeException("<strategy> 标签中 <include> 与 <exclude> 只能配置一项！");
        }
        List<TableInfo> tableList = new ArrayList<TableInfo>();
        Set<String> notExistTables = new HashSet<String>();
        NamingStrategy strategy = config.getNaming();
        NamingStrategy fieldStrategy = config.getFieldNaming();
        PreparedStatement pstate = null;
        try {
            pstate = connection.prepareStatement(querySQL.getTableCommentsSql());
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                String tableName = results.getString(querySQL.getTableName());
                if (StringUtils.isNotBlank(tableName)) {
                    String tableComment = results.getString(querySQL.getTableComment());
                    TableInfo tableInfo = new TableInfo();
                    if (isInclude) {
                        for (String includeTab : config.getInclude()) {
                            if (includeTab.equalsIgnoreCase(tableName)) {
                                tableInfo.setName(tableName);
                                tableInfo.setComment(tableComment);
                            } else {
                                notExistTables.add(includeTab);
                            }
                        }
                    } else if (isExclude) {
                        for (String excludeTab : config.getExclude()) {
                            if (!excludeTab.equalsIgnoreCase(tableName)) {
                                tableInfo.setName(tableName);
                                tableInfo.setComment(tableComment);
                            } else {
                                notExistTables.add(excludeTab);
                            }
                        }
                    } else {
                        tableInfo.setName(tableName);
                        tableInfo.setComment(tableComment);
                    }
                    if (StringUtils.isNotBlank(tableInfo.getName())) {
                        List<TableField> fieldList = getListFields(tableInfo.getName(), fieldStrategy);
                        tableInfo.setFields(fieldList);
                        tableList.add(tableInfo);
                    }
                } else {
                    System.err.println("当前数据库为空！！！");
                }
            }
            // 将已经存在的表移除
            for (TableInfo tabInfo : tableList) {
                notExistTables.remove(tabInfo.getName());
            }
            if (notExistTables.size() > 0) {
                System.err.println("表 " + notExistTables + " 在数据库中不存在！！！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (pstate != null) {
                    pstate.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return processTable(tableList, strategy, config.getTablePrefix());
    }

    /**
     * 将字段信息与表信息关联
     *
     * @param tableName 表名称
     * @param strategy  命名策略
     * @return 表信息
     */
    private List<TableField> getListFields(String tableName, NamingStrategy strategy) throws SQLException {
        boolean havedId = false;

        PreparedStatement pstate = connection.prepareStatement(String.format(querySQL.getTableFieldsSql(), tableName));
        ResultSet results = pstate.executeQuery();

        List<TableField> fieldList = new ArrayList<TableField>();
        while (results.next()) {
            TableField field = new TableField();
            String key = results.getString(querySQL.getFieldKey());
            // 避免多重主键设置，目前只取第一个找到ID，并放到list中的索引为0的位置
            boolean isId = StringUtils.isNotBlank(key) && key.toUpperCase().equals("PRI");
            // 处理ID
            if (isId && !havedId) {
                field.setKeyFlag(true);
                havedId = true;
            } else {
                field.setKeyFlag(false);
            }
            // 处理其它信息
            field.setName(results.getString(querySQL.getFieldName()));
            if (strategyConfig.includeSuperModelColumns(field.getName())) {
                // 跳过公共字段
                continue;
            }
            field.setType(results.getString(querySQL.getFieldType()));
            field.setPropertyName(processName(field.getName(), strategy));
            field.setPropertyType(processFiledType(field.getType()));
            field.setComment(results.getString(querySQL.getFieldComment()));
            fieldList.add(field);
        }
        return fieldList;
    }

    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (StringUtils.isBlank(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", "\\" + File.separator);
        return parentDir + packageName;
    }

    /**
     * 连接父子包名
     *
     * @param parent     父包名
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    private String joinPackage(String parent, String subPackage) {
        if (StringUtils.isBlank(parent)) {
            return subPackage;
        }
        return parent + "." + subPackage;
    }

    /**
     * 处理字段类型
     *
     * @return 转换成JAVA包装类型
     */
    private String processFiledType(String type) {
        if (QuerySQL.MYSQL == querySQL) {
            return processMySqlType(type);
        } else if (QuerySQL.ORACLE == querySQL) {
            return processOracleType(type);
        }
        return null;
    }

    /**
     * 处理字段名称
     *
     * @return 根据策略返回处理后的名称
     */
    private String processName(String name, NamingStrategy strategy) {
        return processName(name, strategy, null);
    }

    /**
     * 处理字段名称
     *
     * @param name
     * @param strategy
     * @param tablePrefix
     * @return 根据策略返回处理后的名称
     */
    private String processName(String name, NamingStrategy strategy, String tablePrefix) {
        String propertyName = "";
        if (strategy == NamingStrategy.remove_prefix_and_camel) {
            propertyName = NamingStrategy.removePrefixAndCamel(name, tablePrefix);
        } else if (strategy == NamingStrategy.underline_to_camel) {
            propertyName = NamingStrategy.underlineToCamel(name);
        } else if (strategy == NamingStrategy.remove_prefix) {
            propertyName = NamingStrategy.removePrefix(name, tablePrefix);
        } else {
            propertyName = name;
        }
        return propertyName;
    }

    /**
     * MYSQL字段类型转换
     *
     * @param type 字段类型
     * @return JAVA类型
     */
    private String processMySqlType(String type) {
        String t = type.toLowerCase();
        if (t.contains("char") || t.contains("text")) {
            return "String";
        } else if (t.contains("bigint")) {
            return "Long";
        } else if (t.contains("int")) {
            return "Integer";
        } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
            return "Date";
        } else if (t.contains("text")) {
            return "String";
        } else if (t.contains("bit")) {
            return "Boolean";
        } else if (t.contains("decimal")) {
            return "BigDecimal";
        } else if (t.contains("blob")) {
            return "byte[]";
        } else if (t.contains("float")) {
            return "Float";
        } else if (t.contains("double")) {
            return "Double";
        } else if (t.contains("json") || t.contains("enum")) {
            return "String";
        }
        return "String";
    }

    /**
     * ORACLE字段类型转换
     *
     * @param type 字段类型
     * @return JAVA类型
     */
    private String processOracleType(String type) {
        String t = type.toUpperCase();
        if (t.contains("CHAR")) {
            return "String";
        } else if (t.contains("DATE") || t.contains("TIMESTAMP")) {
            return "Date";
        } else if (t.contains("NUMBER")) {
            if (t.matches("NUMBER\\(+\\d{1}+\\)")) {
                return "Integer";
            } else if (t.matches("NUMBER\\(+\\d{2}+\\)")) {
                return "Long";
            }
            return "Double";
        } else if (t.contains("FLOAT")) {
            return "Float";
        } else if (t.contains("BLOB")) {
            return "Object";
        } else if (t.contains("RAW")) {
            return "byte[]";
        }
        return "String";
    }

    /**
     * 获取当前的SQL类型
     *
     * @return DB类型
     */
    private QuerySQL getQuerySQL(DbType dbType) {
        for (QuerySQL qs : QuerySQL.values()) {
            if (qs.getDbType().equals(dbType.getValue())) {
                return qs;
            }
        }
        return QuerySQL.MYSQL;
    }

    public StrategyConfig getStrategyConfig() {
        return strategyConfig;
    }

    public void setStrategyConfig(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public void setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
    }

}
