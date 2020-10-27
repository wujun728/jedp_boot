/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jyl.util.ParamChecker;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月7日 上午9:38:19
 */
public class DateFormatter {
	
	/**
     * 年月日.
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 年月日,时分秒.
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 私有构造函数避免这个util类被实例化.
     */
    private DateFormatter() {
        // empty
    }

    /**
     * 将所给的日期date按所给的pattern格式化.
     * 
     * @param date 需要格式化的日期.
     * @param pattern pattern,具体可见
     *            <code>java.text.SimpleDateFormat</code>
     *            的文档说明.
     * @return 格式化后的字符串
     * @throws IllegalArgumentException
     *             如果date为null或是pattern为空.
     */
    public static String format(Date date, String pattern) {
        ParamChecker.checkNull(date, "date");
        ParamChecker.checkEmpty(pattern, "pattern");
        SimpleDateFormat dataFormat = new SimpleDateFormat(pattern);

        return dataFormat.format(date);
    }

    /**
     * 将所给的日期date按YYYY_MM_DD_HH_MM_SS格式化.
     * 
     * @param date 需要格式化的日期.
     * @return 格式化后的字符串.
     * @throws IllegalArgumentException 如果date为null.
     */
    public static String format(Date date) {
        ParamChecker.checkNull(date, "date");
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }
    
    public static String yyyyMMdd(Date date){
    	return format(date, YYYY_MM_DD);
    }

    /**
     * 将所给的日期按所给的pattern格式化.
     * 
     * @param dateStr 需要格式化得日期.
     * @param pattern 具体可见
     *            <code>java.text.SimpleDateFormat</code>
     *            的文档说明.
     * @return 格式化后Date对象.
     * @throws IllegalArgumentException
     *             如果date为null或是pattern为空.
     * @throws ParseException 日期格式化时抛出的异常.
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        ParamChecker.checkEmpty(dateStr, "dateStr");
        ParamChecker.checkEmpty(pattern, "pattern");
        SimpleDateFormat dataFormat = new SimpleDateFormat(pattern);

        return dataFormat.parse(dateStr);
    }

}
