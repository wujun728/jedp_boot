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
package com.baomidou.hibernateplus.generator.config;

import com.baomidou.hibernateplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 策略配置项
 * </p>
 *
 * @author YangHu, tangguo, hubin
 * @since 2016/8/30
 */
public class StrategyConfig {

    /**
     * 数据库表映射到实体的命名策略
     */
    private NamingStrategy naming = NamingStrategy.nochange;

    private NamingStrategy fieldNaming;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 自定义继承的Model类全称，带包名
     */
    private String superModelClass;

    /**
     * 自定义基础的Model类，公共字段
     */
    private String[] superModelColumns;

    /**
     * 自定义继承的Dao类全称，带包名
     */
    private String superDaoClass = ConstVal.SUPERD_DAO_CLASS;
    /**
     * 自定义继承的DaoImpl类全称，带包名
     */
    private String superDaoImplClass = ConstVal.SUPERD_DAOIMPL_CLASS;

    /**
     * 自定义继承的Service类全称，带包名
     */
    private String superServiceClass = ConstVal.SUPERD_SERVICE_CLASS;

    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    private String superServiceImplClass = ConstVal.SUPERD_SERVICEIMPL_CLASS;

    /**
     * 自定义继承的Controller类全称，带包名
     */
    private String superControllerClass;

    /*
     * 需要包含的表名（与exclude二选一配置）
     */
    private String[] include = null;

    /**
     * 需要排除的表名
     */
    private String[] exclude = null;
    /**
     * 【实体】是否生成字段常量（默认 false）<br>
     * -----------------------------------<br>
     * public static final String ID = "test_id";
     */
    private boolean modelColumnConstant = false;

    /**
     * 【实体】是否为构建者模型（默认 false）<br>
     * -----------------------------------<br>
     * public User setName(String name) { this.name = name; return this; }
     */
    private boolean modelBuliderModel = false;

    public NamingStrategy getNaming() {
        return naming;
    }

    public void setNaming(NamingStrategy naming) {
        this.naming = naming;
    }

    public NamingStrategy getFieldNaming() {
        return fieldNaming;
    }

    public void setFieldNaming(NamingStrategy fieldNaming) {
        this.fieldNaming = fieldNaming;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getSuperModelClass() {
        return superModelClass;
    }

    public void setSuperModelClass(String superModelClass) {
        this.superModelClass = superModelClass;
    }

    public boolean includeSuperModelColumns(String fieldName) {
        if (null != superModelColumns) {
            for (String column : superModelColumns) {
                if (column.contains(fieldName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String[] getSuperModelColumns() {
        return superModelColumns;
    }

    public void setSuperModelColumns(String[] superModelColumns) {
        this.superModelColumns = superModelColumns;
    }

    public String getSuperDaoClass() {
        return superDaoClass;
    }

    public void setSuperDaoClass(String superDaoClass) {
        this.superDaoClass = superDaoClass;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public void setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public void setSuperServiceImplClass(String superServiceImplClass) {
        this.superServiceImplClass = superServiceImplClass;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    public void setSuperControllerClass(String superControllerClass) {
        this.superControllerClass = superControllerClass;
    }

    public String[] getInclude() {
        return include;
    }

    public void setInclude(String[] include) {
        this.include = include;
    }

    public String[] getExclude() {
        return exclude;
    }

    public void setExclude(String[] exclude) {
        this.exclude = exclude;
    }

    public boolean isModelColumnConstant() {
        return modelColumnConstant;
    }

    public void setModelColumnConstant(boolean modelColumnConstant) {
        this.modelColumnConstant = modelColumnConstant;
    }

    public boolean isModelBuliderModel() {
        return modelBuliderModel;
    }

    public void setModelBuliderModel(boolean modelBuliderModel) {
        this.modelBuliderModel = modelBuliderModel;
    }

    public String getSuperDaoImplClass() {
        return superDaoImplClass;
    }

    public void setSuperDaoImplClass(String superDaoImplClass) {
        this.superDaoImplClass = superDaoImplClass;
    }
}
