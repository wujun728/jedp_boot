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
package com.baomidou.hibernateplus.condition;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.hibernateplus.condition.wrapper.Wrapper;
import com.baomidou.hibernateplus.utils.StringUtils;

/**
 * <p>
 * Update条件构造器
 * </p>
 *
 * @author Caratacus
 * @date 2016-12-2
 */
@SuppressWarnings({"rawtypes", "serial"})
public class UpdateWrapper extends Wrapper {

    /**
     * DEFAULT
     */
    public static final UpdateWrapper DEFAULT = UpdateWrapper.instance();
    /**
     * SQL SET内容，例如：set id = 1
     */
    protected final Map<String, String> setMap = new HashMap<String, String>();

    /**
     * 获取实例
     */
    public static UpdateWrapper instance() {
        return new UpdateWrapper();
    }

    /**
     * 执行一次set操作
     *
     * @param key
     * @param value
     * @return
     */
    public UpdateWrapper set(String key, String value) {
        setMap.put(key, value);
        return this;
    }

    /**
     * 执行多次set操作
     *
     * @param setMap
     * @return
     */
    public UpdateWrapper sets(Map<String, String> setMap) {
        this.setMap.putAll(setMap);
        return this;
    }

    /**
     * 获取setMap
     *
     * @return
     */
    @Override
    public Map<String, String> getSetMap() {
        return setMap;
    }

    /**
     * SQL 片段
     */
    @Override
    public String getSqlSegment() {
        /*
		 * 无条件
		 */
        String sqlWhere = sql.toString();
        if (StringUtils.isBlank(sqlWhere)) {
            return StringUtils.EMPTY;
        }

        return sqlWhere;
    }

}
