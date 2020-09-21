/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: ValidateUtils.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;

/**<p>Class: ValidateUtils.java</p>
 * <p>Description: 校验工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class ValidateUtils
{
	// url地址验证正则表达式
	public static final String REGULAR_URL = "^(http|https|ftp)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?$";

	// IP地址验证正则表达式
	public static final String REGULAR_IP_ADDRESS = "^(((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?))?$";

	// 身份证验证正则表达式
	public static final String REGULAR_ID_CARD = "^([1-9][0-9]{14}|[1-9][0-9]{17}|[1-9][0-9]{16}[x,X])?$";

	// 邮政编码验证正则表达式
	public static final String REGULAR_ZIP_CODE = "^([1-9][0-9]{5})?$";

	// 电话验证正则表达式
	public static final String REGULAR_PHONE = "^([0-9]{3,4}-[0-9]{7,8}(-[0-9]{2,6})?)?$";

	// 邮箱验证正则表达式
	public static final String REGULAR_EMIAL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

	// 手机验证正则表达式
	// public static final String REGULAR_MOBILE = "^(1[0-9]{10})?$";
	//public static final String REGULAR_MOBILE = "^(1[3-8]+\\d{9})?$";
	public static final String REGULAR_MOBILE = "^(1\\d{10})$";

	// 金额验证正则表达式
	public static final String REGULAR_MONEY = "^((([1-9]{1}\\d{0,9})|([0]{1}))(\\.(\\d){1,2})?)?$";

	/**
	 * 私有构造器，防止类的实例化
	 */
	private ValidateUtils()
	{
		super();
	}
	
	/**
	 * 邮件地址格式验证(支持批量验证，各邮件地址用";"分隔)
	 * 
	 * @param value 			要检查的字符串
	 * @return boolean 		是否为正确的电话格式
	 */
	public static boolean isEmail(String value)
	{
		return validate(value, REGULAR_EMIAL);
	}

	/**
	 * 电话号码格式验证(支持批量验证，各电话号码用";"分隔)
	 * 
	 * @param value 			要检查的字符串
	 * @return boolean 		是否为正确的电话格式
	 */
	public static boolean isTelFormat(String value)
	{
		return validate(value, REGULAR_PHONE);
	}

	/**
	 * 邮政编码格式验证 6位(支持批量验证，各邮政编码用";"分隔)
	 * 
	 * @param value 		要检查的字符串
	 * @return boolean 		是否正确的邮编格式
	 */
	public static boolean isZipCode(String value)
	{
		return validate(value, REGULAR_ZIP_CODE);
	}

	/**
	 * 身份证号码格式验证 15位或18位(支持批量验证，各身份证号码用";"分隔)
	 * 
	 * @param value 		要检查的字符串
	 * @return boolean 		是否正确的身份证号码格式
	 */
	public static boolean isIDCard(String value)
	{
		return validate(value, REGULAR_ID_CARD);
	}

	/**
	 * 手机号码格式验证(支持批量验证，各手机号码用";"分隔)
	 * 
	 * @param value 		 要检查的字符串
	 * @return boolean 		是否正确的手机号码格式
	 */
	public static boolean isMobile(String value)
	{
		// 正则表达式参考 @link http://my.oschina.net/william1/blog/4752
		return validate(value, REGULAR_MOBILE);
	}

	/**
	 * IP地址格式验证(支持批量验证，各IP地址用";"分隔)
	 * 
	 * @param value 		要检查的字符串
	 * @return boolean 	是否正确的IP地址格式
	 */
	public static boolean isIpAddress(String value)
	{
		return validate(value, REGULAR_IP_ADDRESS);
	}

	/**
	 * URL地址格式验证(支持批量验证，各URL地址用";"分隔)
	 * @param value 		要检查的字符串
	 * @return boolean 	是否正确的URL地址格式
	 */
	public static boolean isUrl(String value)
	{
		return validate(value, REGULAR_URL);
	}

	/**
	 * 数值大小验证（最小约束）
	 * @param value
	 * @param min
	 * @return
	 */
	public static boolean minValue(int value, int min)
	{
		return GenericValidator.minValue(value, min);
	}

	public static boolean minValue(long value, long min)
	{
		return GenericValidator.minValue(value, min);
	}

	public static boolean minValue(double value, double min)
	{
		return GenericValidator.minValue(value, min);
	}

	public static boolean minValue(float value, float min)
	{
		return GenericValidator.minValue(value, min);
	}

	/**
	 * 数值大小验证（最大约束）
	 * @param value
	 * @param max
	 * @return
	 */
	public static boolean maxValue(int value, int max)
	{
		return GenericValidator.maxValue(value, max);
	}

	public static boolean maxValue(long value, long max)
	{
		return GenericValidator.maxValue(value, max);
	}

	public static boolean maxValue(double value, double max)
	{
		return GenericValidator.maxValue(value, max);
	}

	public static boolean maxValue(float value, float max)
	{
		return GenericValidator.maxValue(value, max);
	}
	
	/**
	 * 数值大小验证（范围约束）
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isInRange(byte value, byte min, byte max)
	{
		return GenericValidator.isInRange(value, min, max);
	}

	public static boolean isInRange(int value, int min, int max)
	{
		return GenericValidator.isInRange(value, min, max);
	}

	public static boolean isInRange(float value, float min, float max)
	{
		return GenericValidator.isInRange(value, min, max);
	}

	public static boolean isInRange(short value, short min, short max)
	{
		return GenericValidator.isInRange(value, min, max);
	}

	public static boolean isInRange(long value, long min, long max)
	{
		return GenericValidator.isInRange(value, min, max);
	}

	public static boolean isInRange(double value, double min, double max)
	{
		return GenericValidator.isInRange(value, min, max);
	}
	
	/**
	 * 金额格式验证（范围约束）
	 * @param price 			金额
	 * @param min 			最小值（包含）　
	 * @param max 			最大值（包含）　
	 * @return
	 */
	public static Boolean isMoney(BigDecimal price, BigDecimal min, BigDecimal max)
	{
		boolean bool = true;
		bool = (null != price);
		if (bool)
		{
			bool = matchRegexp(price.toString(), REGULAR_MONEY);
		}
		if (bool && null != min)
		{
			bool = (price.compareTo(min) >= 0);
		}
		if (bool && null != max)
		{
			bool = (price.compareTo(max) <=0);
		}
		return bool;
	}
	
	/**
	 * 对象非空验证
	 * @param obj
	 * @return
	 */
	public static boolean isNotNull(Object obj)
	{
		return !isNull(obj);
	}
	
	/**
	 * 对象为空验证
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj)
	{
		if (obj instanceof String)
		{
			return StringUtils.isBlank((String)obj);
		}
		else 
		{
			return null == obj;
		}
	}
	
	/**
	 * 集合对象为空验证
	 * @param obj 集合或数组
	 * @return
	 */
	public static boolean isEmpty(Object collection)
	{
		if (null == collection) return true;
		if (collection instanceof Collection)
		{
			return CollectionUtils.isEmpty((Collection<?>)collection);
		}
		if (collection.getClass().isArray())
		{
			if (collection instanceof Object[])
			{
				return  ((Object[])collection).length == 0;
			}
			if (collection instanceof long[])
			{
				return  ((long[])collection).length == 0;
			}
			if (collection instanceof Long[])
			{
				return  ((Long[])collection).length == 0;
			}
			if (collection instanceof int[])
			{
				return  ((int[])collection).length == 0;
			}
			if (collection instanceof Integer[])
			{
				return  ((Integer[])collection).length == 0;
			}
			if (collection instanceof short[])
			{
				return  ((short[])collection).length == 0;
			}
			if (collection instanceof Short[])
			{
				return  ((Short[])collection).length == 0;
			}
			if (collection instanceof char[])
			{
				return  ((char[])collection).length == 0;
			}
			if (collection instanceof String[])
			{
				return  ((String[])collection).length == 0;
			}
			if (collection instanceof byte[])
			{
				return  ((byte[])collection).length == 0;
			}
			if (collection instanceof Byte[])
			{
				return  ((Byte[])collection).length == 0;
			}
			if (collection instanceof double[])
			{
				return  ((double[])collection).length == 0;
			}
			if (collection instanceof Double[])
			{
				return  ((Double[])collection).length == 0;
			}
			if (collection instanceof float[])
			{
				return  ((float[])collection).length == 0;
			}
			if (collection instanceof Float[])
			{
				return  ((Float[])collection).length == 0;
			}
			if (collection instanceof boolean[])
			{
				return  ((boolean[])collection).length == 0;
			}
			if (collection instanceof Boolean[])
			{
				return  ((Boolean[])collection).length == 0;
			}
		}
		return false;
	}
	
	/**
	 * 集合对象非空验证
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object collection)
	{
		return !isEmpty(collection);
	}
	
	/**
	 * 数值格式验证
	 * @param value
	 * @return
	 */
	public static boolean isByte(String value) 
	{
		return GenericValidator.isByte(value);
	}

	public static boolean isShort(String value)
	{
		return GenericValidator.isShort(value);
	}

	public static boolean isInt(String value)
	{
		return GenericValidator.isInt(value);
	}

	public static boolean isLong(String value)
	{
		return GenericValidator.isLong(value);
	}

	public static boolean isFloat(String value)
	{
		return GenericValidator.isFloat(value);
	}

	public static boolean isDouble(String value)
	{
		return GenericValidator.isDouble(value);
	}
	
	/**
	 * 日期格式验证
	 * 
	 * @param value 			要检查的字符串
	 * @param datePattern 		日期格式
	 * @param strict 			是否严格匹配
	 * @return boolean 		是否为正确的日期及格式
	 */
	public static boolean isDate(String value, String datePattern, boolean strict)
	{
		return GenericValidator.isDate(value, datePattern, strict);
	}
    
	/**
	 * 字符串长度验证（最小约束）
	 * @param value
	 * @param max
	 * @return
	 */
	public static boolean minLength(String value, int min) {
		return GenericValidator.minLength(value, min);
	}

	/**
	 * 字符串长度验证（最大约束）
	 * @param value
	 * @param max
	 * @return
	 */
	public static boolean maxLength(String value, int max) {
		return GenericValidator.maxLength(value, max); 
	}
	
	/**
	 *  字符串长度验证（范围约束）
	 * @param str 			要检查的字符串
	 * @param min 			最小长度（包含）
	 * @param max 			最大长度（包含）
	 * @return boolean 		是否通过验证
	 */
	public static boolean islengthRange(String str, int min, int max)
	{
		return  GenericValidator.minLength(str, min) && GenericValidator.maxLength(str, max);
	}
	
	/**
	 * 字符串存储空间验证（最小约束）
	 * @param value
	 * @param charsetName	字符集
	 * @param max
	 * @return
	 */
	public static boolean minSize(String value, String charsetName, int min) {
		return GenericValidator.minValue(bytesLength(value, charsetName), min);
	}

	/**
	 * 字符串存储空间验证（最大约束）
	 * @param value
	 * @param charsetName	字符集
	 * @param max
	 * @return
	 */
	public static boolean maxSize(String value, String charsetName, int max) {
		return GenericValidator.minValue(bytesLength(value, charsetName), max);
	}
	
	/**
	 * 字符串存储空间验证（范围约束）
	 * @param value 			要检查的字符串
	 * @param charsetName	字符集
	 * @param min 			最小长度（包含）
	 * @param max 			最大长度（包含）
	 * @return boolean 		是否通过验证
	 */
	public static boolean isSizeRange(String value, String charsetName, int min, int max)
	{
		return GenericValidator.isInRange(bytesLength(value, charsetName), min, max);
	}
	
	/**
	 * 正则匹配
	 * 
	 * @param value 		待匹配的字符串
	 * @param regexp 		正则表达式
	 * @return boolean 	是否匹配
	 */
	public static boolean matchRegexp(String value, String regexp)
	{
		return GenericValidator.matchRegexp(value, regexp);
	}
	
	/**
	 * 批量正则匹配
	 * 
	 * @param values 			待匹配的字符串（多个带匹配的字符串用 ";" 分开）
	 * @param regex 			正则表达式
	 * @return boolean 		是否匹配
	 */
	public static boolean validate(String values, String regex)
	{
		if (StringUtils.isBlank(values) || StringUtils.isBlank(regex)) return false;
		
		// 全角转半角 
		char c[] = values.toCharArray();
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == '\u3000')
			{
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F')
			{
				c[i] = (char) (c[i] - 65248);

			}
		}
		values = StringUtils.trimToEmpty(new String(c));
		
		if (!StringUtils.contains(values, ";"))// 单匹配
		{
			return matchRegexp(values, regex);
		}
		else// 批量匹配
		{
			String[] strs = values.split(";");
			for (int i = 0; i < strs.length; i++)
			{
				String str = strs[i];
				if (!matchRegexp(str, regex))
				{
					return false;
				}
			}
			return true;
		}
	}
	
	// =====================================================================================
	
	/**
	 * 计算一个字符串的存储空间（单位字节）
	 * @param charsetName 	字符集名称
	 * @param str 			指定的字符串
	 * @return int 			字符数
	 */
	public static int bytesLength(String str, String charsetName)
	{
		int size = 0;
		if (StringUtils.isNotBlank(str))
		{
			try
			{
				size = str.getBytes(charsetName).length;
			} catch (UnsupportedEncodingException e) {
				size = str.getBytes().length;
			}
		}
		return size;
	}
}