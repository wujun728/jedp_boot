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
package com.baomidou.hibernateplus.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 转换类型工具类
 *
 * @author Caratacus
 * @date 2016/6/24 0024
 */
public class TypeConvert {

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * 转换对象为Long
     *
     * @param obj
     * @return
     */
    public static Long toLong(Object obj) {
        return toLong(obj, null);
    }

    /**
     * 转换对象为Long
     *
     * @param obj
     * @param defaults
     * @return
     */
    public static Long toLong(Object obj, Long defaults) {
        if (obj == null) {
            return defaults;
        } else {
            if (obj.getClass() == Timestamp.class) {
                return ((Timestamp) obj).getTime();
            } else if (obj instanceof Number) {
                return ((Number) obj).longValue();
            } else {
                try {
                    return Long.parseLong(obj.toString().trim());
                } catch (Exception e) {
                    // ignore
                    return defaults;
                }
            }
        }
    }

    /**
     * 转换对象为long
     *
     * @param obj
     * @return
     */
    public static long tolong(Object obj) {
        return toLong(obj, 0L);
    }

    /**
     * 转换对象为Double
     *
     * @param obj
     * @return
     */
    public static Double toDouble(Object obj) {
        return toDouble(obj, null);
    }

    /**
     * 转换对象为Double
     *
     * @param obj
     * @param defaults
     * @return
     */
    public static Double toDouble(Object obj, Double defaults) {
        return obj == null ? defaults : Double.parseDouble(obj.toString());
    }

    /**
     * 转换对象为double
     *
     * @param obj
     * @return
     */
    public static double todouble(Object obj) {
        return toDouble(obj, 0.0);
    }

    /**
     * 转换对象为BigDecimal
     *
     * @param obj
     * @return
     */
    public static BigDecimal toBigDecimal(Object obj) {
        return toBigDecimal(obj, BigDecimal.ZERO);
    }

    /**
     * 转换对象为BigDecimal
     *
     * @param obj
     * @param defaults
     * @return
     */
    public static BigDecimal toBigDecimal(Object obj, BigDecimal defaults) {
        return StringUtils.EMPTY.equals(StringUtils.toString(obj)) ? defaults : new BigDecimal(obj.toString());
    }

    /**
     * 转换对象为int
     *
     * @param obj
     * @return
     */
    public static int toInt(Object obj) {
        return toInteger(obj, 0);
    }

    /**
     * 转换对象为Integer
     *
     * @param obj
     * @return
     */
    public static Integer toInteger(Object obj) {
        return toInteger(obj, null);
    }

    /**
     * 转换对象为Integer
     *
     * @param obj
     * @param defaults
     * @return
     */
    public static Integer toInteger(Object obj, Integer defaults) {
        if (obj == null) {
            return defaults;
        } else if (obj.toString().length() == 0) {
            return defaults;
        }
        // 添加double 转换
        else if (obj instanceof Number) {
            return ((Number) obj).intValue();
        } else {
            try {
                return Integer.valueOf(obj.toString().trim());
            } catch (Exception e) {
                // ignore
                return defaults;
            }
        }
    }

    /**
     * 获取布尔字符串
     *
     * @param bo
     * @return
     */
    public static String toBoolean(boolean bo) {
        return bo ? TRUE : FALSE;
    }

    /**
     * 获取布尔值
     *
     * @param bo
     * @return
     */
    public static boolean toBoolean(String bo) {
        return TRUE.equals(bo);
    }

    /**
     * 获取对象字符串
     *
     * @param obj
     * @return String
     */
    public static String toString(Object obj) {
        return toString(obj, StringUtils.EMPTY);
    }

    /**
     * 获取对象字符串
     *
     * @param obj
     * @return String
     */
    public static String toString(Object obj, String defaults) {
        return StringUtils.toString(obj, defaults);
    }
}
