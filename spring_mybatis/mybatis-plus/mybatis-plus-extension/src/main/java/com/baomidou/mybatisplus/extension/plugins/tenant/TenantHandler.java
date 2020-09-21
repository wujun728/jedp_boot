/*
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
package com.baomidou.mybatisplus.extension.plugins.tenant;

import net.sf.jsqlparser.expression.Expression;

/**
 * <p>
 * 租户处理器（ TenantId 行级 ）
 * </p>
 *
 * @author hubin
 * @since 2017-08-31
 */
public interface TenantHandler {

    /**
     * 获取租户值
     *
     * @return 租户值
     */
    Expression getTenantId();

    /**
     * 获取租户字段名
     *
     * @return 租户字段名
     */
    String getTenantIdColumn();

    /**
     * 根据表名判断是否进行过滤
     *
     * @param tableName 表名
     * @return 是否进行过滤
     */
    boolean doTableFilter(String tableName);
}
