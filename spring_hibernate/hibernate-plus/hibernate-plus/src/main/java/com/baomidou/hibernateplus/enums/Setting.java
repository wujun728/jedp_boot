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
package com.baomidou.hibernateplus.enums;

import com.baomidou.hibernateplus.exceptions.HibernatePlusException;
import com.baomidou.hibernateplus.utils.StringUtils;

/**
 * <p>
 * 数据库主从设置枚举
 * </p>
 *
 * @author Caratacus
 * @Date 2016-12-1
 */
public enum Setting {

    MASTER("master", "主数据库"), SLAVE("slave", "从数据库");

    /** 类型 */
    private final String type;

    /** 描述 */
    private final String desc;

    /** 构造 */
    Setting(final String type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据字符串获取枚举类型
     *
     * @param type
     * @return
     */
    public static Setting getSetting(String type) {
        if (StringUtils.isNotBlank(type)) {
            Setting[] dbTypes = Setting.values();
            for (Setting dbType : dbTypes) {
                if (dbType.getType().equalsIgnoreCase(type)) {
                    return dbType;
                }
            }
        }
        throw new HibernatePlusException("Error: Master-slave Setting error !");
    }

    public String getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

}
