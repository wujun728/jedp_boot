/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util;

import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月7日 上午9:40:09
 */
public class ParamChecker {
	
	 /**
     * 私有构造函数避免这个util类被实例化.
     */
    private ParamChecker() {
        // empty
    }

    /**
     * 检查所给的参数是否为null.
     * 
     * @param obj 被检查的参数
     * @param paramName 被检查的参数名称
     * @throws IllegalArgumentException 如果所给参数为null.
     */
    public static void checkNull(Object obj, String paramName) {
        if (obj == null) {
            throw new IllegalArgumentException("Parameter with name " + paramName + " can not be null");
        }
    }

    /**
     * 检查所给的字符串是否为空. 字符串为空是指字符串为null或者str.trim().length为0.
     * 
     * @param str 被检查的参数
     * @param paramName 被检查的参数名称
     * @throws IllegalArgumentException 如果字符串为空
     */
    public static void checkEmpty(String str, String paramName) {
        checkNull(str, paramName);
        if (str.trim().length() == 0) {
            throw new IllegalArgumentException("Parameter with name " + paramName + " can not be empty");
        }
    }

    /**
     * 检查第一个参数number是否为非负数.
     * 
     * @param number 被检查的参数
     * @param paramName 被检查的参数名称
     * @throws IllegalArgumentException 如果number为负数
     */
    public static void checkNonNegative(int number, String paramName) {
        if (number < 0) {
            throw new IllegalArgumentException("Parameter with name " + paramName + " is negative number");
        }
    }

    /**
     * 检查第一个参数number是否为非负数.
     * 
     * @param number 被检查的参数
     * @param paramName 被检查的参数名称
     * @throws IllegalArgumentException 如果number为负数
     */
    public static void checkNonNegative(double number, String paramName) {
        if (number < 0) {
            throw new IllegalArgumentException("Parameter with name " + paramName + " is negative number");
        }
    }

    /**
     * 检查集合collection是否为空.
     * 
     * @param collection 集合.
     * @param paramName 集合名称.
     * @throws IllegalArgumentException 如果collection为空.
     */
    public static void checkEmpty(Collection<?> collection, String paramName) {
        checkNull(collection, paramName);
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Collection with name " + paramName + " can not be empty");
        }
    }

    /**
     * 检查集合collection是否为空.
     * 
     * @param collection 集合.
     * @param paramName 集合名称.
     * @throws IllegalArgumentException 如果collection为空.
     */
    /*public static void checkContainsNull(Collection<?> collection, String paramName) {
        checkNull(collection, paramName);
        if (collection.contains(null)) {
            throw new IllegalArgumentException("Collection with name " + paramName + " can not contain null");
        }
    }*/

    /**
     * 检查weekday是否在正常的星期范围内,即[1,7].
     * 
     * @param weekday 被检查的参数.
     * @param paramName 被检查的参数名称.
     * @throws IllegalArgumentException 如果weekday不在[1,7]范围内.
     */
    public static void checkInWeekDay(int weekday, String paramName) {
        if (weekday < Calendar.SUNDAY || weekday > Calendar.SATURDAY) {
            throw new IllegalArgumentException("Parameter with name " + paramName + " out of scope [1,7]");
        }
    }

    /**
     * 检查所给的字符串是否为空. 字符串为空是指字符串为null或者str.trim().length为0.
     * 如果为空返回true, 否则返回false.
     * 
     * @param str 被检查的参数
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

}
