/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: DoubleUtils.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

/**<p>Class: DoubleUtils.java</p>
 * <p>Description: 数值的工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class DoubleUtils {

	/**
	 * 默认保留2位数
	 */
	private static final int SCALE_NUM = 2;
	
	
	/**
	 * 数值保留两位小数
	 * 
	 * @param convert
	 *            转换前数值
	 * @return 转换后字符串
	 */
	public static String toString(Double dou) {
		return toString(String.valueOf(dou), SCALE_NUM);
	}
	
	/**
	 * 数值保留两位小数
	 * @param sDouble
	 * @return 没有返回0.00d
	 */
	public static Double toDouble(String sDouble){
		return NumberUtils.toDouble(toString(sDouble, SCALE_NUM), 0.00d);
	}
	
	
	
	/**
	 * 数值保留二位小数
	 * @param sFloat
	 * @return 没有返回0.00f
	 */
	public static Float toFloat(String sFloat){
		return NumberUtils.toFloat(toString(sFloat, SCALE_NUM), 0.00f);
	}
	
	/**
	 * 相加，返回保留后的2位数值
	 * @param dou1
	 * @param dou2
	 * @return 没有返回0.00f
	 */
	public static Double add(String dou1, String dou2){
		BigDecimal b = new BigDecimal(dou1);
		BigDecimal b2 = new BigDecimal(dou2);
		return toDouble(b.add(b2).toString());
	}
	
	/**
	 * 相加，返回保留后的2位数值
	 * @param dou1
	 * @param dou2
	 * @return 没有返回0.00f
	 */
	public static BigDecimal add(BigDecimal dou1, BigDecimal dou2){
		return toBigDecimal(dou1.add(dou2).toString(), SCALE_NUM);
	}
	
	
	/**
	 * 除法，返回保留后的2位数值，四舍五入
	 * @param str1 被除数
	 * @param str2 除数
	 * @return
	 */
	public static Double division(String str1, String str2){
		BigDecimal b = new BigDecimal(str1);
		BigDecimal b2 = new BigDecimal(str2);
		return b.divide(b2, SCALE_NUM, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 除法，返回保留后的2位数值，四舍五入
	 * @param str1 被除数
	 * @param str2 除数
	 * @return
	 */
	public static BigDecimal division(BigDecimal str1, BigDecimal str2){
		return toBigDecimal(str1.divide(str2).toString(), SCALE_NUM);
	}
	
	
	/**
	 * 乘法，保留2位数值
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static Double multiply(String str1, String str2){
		BigDecimal b = new BigDecimal(str1);
		BigDecimal b2 = new BigDecimal(str2);
		return toDouble(b.multiply(b2).toString());
	}
	
	
	/**
	 * 乘法，保留2位数值
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal str1, BigDecimal str2){
		return toBigDecimal(str1.multiply(str2).toString(), SCALE_NUM);
	}
	
	
	/**
	 * 减法，str1 - str2
	 * 保留2位数值
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static Double subtract(String str1, String str2){
		BigDecimal b = new BigDecimal(str1);
		BigDecimal b2 = new BigDecimal(str2);
		return toDouble(b.subtract(b2).toString());
	}
	
	
	/**
	 * 减法，str1 - str2
	 * 保留2位数值
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal str1, BigDecimal str2){
		return toBigDecimal(str1.subtract(str2).toString(), SCALE_NUM);
	}
	
	
	
	/**
	 * 格式化数字
	 * @param str 数字字符串
	 * @param length 保留几位数
	 * @return
	 */
	private static String toString(String str, int length){
		if(NumberUtils.isNumber(str)) {
			BigDecimal b = new BigDecimal(str);
			return b.setScale(length, BigDecimal.ROUND_HALF_UP).toString();
		}
		return null;
	}
	
	/**
	 * 格式化数字
	 * @param str 数字字符串
	 * @param length 保留几位数
	 * @return
	 */
	private static BigDecimal toBigDecimal(String str, int length){
		if(NumberUtils.isNumber(str)) {
			BigDecimal b = new BigDecimal(str);
			return b.setScale(length, BigDecimal.ROUND_HALF_UP);
		}
		return null;
	}
	
	
}
