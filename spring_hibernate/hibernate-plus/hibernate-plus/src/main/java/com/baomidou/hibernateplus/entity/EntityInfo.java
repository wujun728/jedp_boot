/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Caratacus
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.baomidou.hibernateplus.entity;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.SessionFactory;

import com.baomidou.hibernateplus.utils.CollectionUtils;
import com.baomidou.hibernateplus.utils.StringUtils;

/**
 * <p>
 * 简单的反射信息
 * </p>
 *
 * @author Caratacus
 * @Date 2016-11-23
 */
public class EntityInfo {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表主键ID 属性名
     */
    private String keyProperty;

    /**
     * 表主键ID 字段名
     */
    private String keyColumn;

    /**
     * 实体字段
     */

    private Set<EntityFieldInfo> fieldInfos;
    /**
     * select
     */
    private String select;

    /**
     * Master SessionFactory
     */
    private SessionFactory master;
    /**
     * Slave SessionFactory
     */
    private Set<SessionFactory> slaves;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKeyProperty() {
        return keyProperty;
    }

    public void setKeyProperty(String keyProperty) {
        this.keyProperty = keyProperty;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }

    public Set<EntityFieldInfo> getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(Set<EntityFieldInfo> fieldInfos) {
        this.fieldInfos = fieldInfos;
    }

    public String getSelect() {
        if (StringUtils.isBlank(select)) {
            StringBuilder selectBuild = new StringBuilder();
            selectBuild.append(getKeyColumn());
            selectBuild.append(" AS ");
            selectBuild.append(getKeyProperty());
            selectBuild.append(",");
            Set<EntityFieldInfo> fieldInfos = getFieldInfos();
            if (CollectionUtils.isNotEmpty(fieldInfos)) {
                Iterator<EntityFieldInfo> iterator = fieldInfos.iterator();
                int _size = fieldInfos.size();
                int i = 1;
                while (iterator.hasNext()) {
                    EntityFieldInfo fieldInfo = iterator.next();
                    String column = fieldInfo.getColumn();
                    String property = fieldInfo.getProperty();
                    if (i == _size) {
                        selectBuild.append(column);
                        selectBuild.append(" AS ");
                        selectBuild.append(property);
                    } else {
                        selectBuild.append(column);
                        selectBuild.append(" AS ");
                        selectBuild.append(property);
                        selectBuild.append(",");
                    }
                    i++;
                }
            }
            setSelect(selectBuild.toString());
        }
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public SessionFactory getMaster() {
        return master;
    }

    public void setMaster(SessionFactory master) {
        this.master = master;
    }

    public Set<SessionFactory> getSlaves() {
        return slaves;
    }

    public void setSlaves(Set<SessionFactory> slaves) {
        this.slaves = slaves;
    }
}
