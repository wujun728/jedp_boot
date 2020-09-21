/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: CalendarUtil.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatterBuilder;

/**<p>Class: CalendarUtil.java</p>
 * <p>Description: 日期的工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class CalendarUtil {
	
	/**
	 * yyyy-MM-dd
	 */
	public final static String FMT_Y_M_D = "yyyy-MM-dd";
	
	/**
	 * yyyyMMdd
	 */
	public final static String FMT_YMD = "yyyyMMdd";
	
	/**
	 * yyyy-MM
	 */
	public final static String FMT_Y_M = "yyyy-MM";
	
	/**
	 * yyyy
	 */
	public final static String FMT_Y = "yyyy";
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public final static String FMT_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd HH:mm
	 */
	public final static String FMT_Y_M_D_H_M = "yyyy-MM-dd HH:mm";
	
	/**
	 * yyyy-MM-dd HH
	 */
	public final static String FMT_Y_M_D_H = "yyyy-MM-dd HH";
	
	
	
	/**
	 * 获取当前时间戳
	 * @return long
	 */
	public static long getCurrentTime(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 格式化当前的日期
	 * @param format
	 * @return
	 */
	public static String toString(String format){
		return DateTime.now().toString(format);
	}
	
	/**
	 * 指定日期格式化
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(String date, String format){
		return new DateTime(date).toString(format);
	}
	
	
	
	/**
	 * 指定日期格式化
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(Date date, String format){
		return new DateTime(date).toString(format);
	}
	
	/**
	 * 时间戳转换成2014-04-15日期
	 * @param date
	 * @return String
	 */
	public static String toString(long date){
		if(date == 0){
			return null;
		}
		return new DateTime(date).toString(FMT_Y_M_D); 
	}
	
	/**
	 * 时间戳转换日期
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(long date, String format){
		if(date == 0){
			return null;
		}
		return new DateTime(date).toString(format); 
	}
	
	/**
	 * 格式化日期转成long
	 * @param date  
	 * @return
	 */
	public static long toLong(String date){
		if(StringUtils.isBlank(date)){
			return 0;
		}
		return DateTime.parse(date).getMillis();
	}
	
	/**
	 * 按指定的格式格式化日期转成long
	 * @param date
	 * @param format
	 * @return
	 */
	public static long toLong(String date, String format){
		if(StringUtils.isBlank(date)){
			return 0;
		}
		return DateTimeFormat.forPattern(format).parseMillis(date);
	}
	
	
	
	/**
	 * 添加秒，
	 * 负数为前几秒
	 * 正数为后几秒
	 * @param seconds
	 * @return
	 */
	public static long addSeconds(int seconds){
		return DateTime.now().plusSeconds(seconds).getMillis();
	}
	
	/**
	 * 添加秒，
	 * 负数为前几秒
	 * 正数为后几秒
	 * @param seconds
	 * @return
	 */
	public static long addSeconds(long time, int seconds){
		return new DateTime(time).plusSeconds(seconds).getMillis();
	}
	
	
	
	/**
	 * 添加分钟，
	 * 负数为前几分
	 * 正数为后几分
	 * @param minute
	 * @return
	 */
	public static long addMinute(int minute){
		return DateTime.now().plusMinutes(minute).getMillis();
	}
	
	/**
	 * 添加分钟，
	 * 负数为前几分
	 * 正数为后几分
	 * @param minute
	 * @return
	 */
	public static long addMinute(long time, int minute){
		return new DateTime(time).plusMinutes(minute).getMillis();
	}
	
	
	/**
	 * 添加小时，
	 * 负数为前几时
	 * 正数为后几时
	 * @param hours
	 * @return
	 */
	public static long addHours(long time, int hours){
		return new DateTime(time).plusHours(hours).getMillis();
	}
	
	/**
	 * 添加小时，
	 * 负数为前几时
	 * 正数为后几时
	 * @param hours
	 * @return
	 */
	public static long addHours(int hours){
		return DateTime.now().plusHours(hours).getMillis();
	}
	
	
	/**
	 * 添加日期，
	 * 负数为前几天
	 * 正数为后几天
	 * @param day
	 * @return
	 */
	public static long addDay(int day){
		return DateTime.now().plusDays(day).getMillis();
	}
	
	/**
	 * 添加日期，
	 * 负数为前几天
	 * 正数为后几天
	 * @param day
	 * @return
	 */
	public static long addDay(long time, int day){
		return new DateTime(time).plusDays(day).getMillis();
	}
	
	/**
	 * 添加月
	 * 负数为前几月
	 * 正数为后几月
	 * @param months
	 * @return
	 */
	public static long addMoths(int months){
		return DateTime.now().plusMonths(months).getMillis();
	}
	
	/**
	 * 添加月
	 * 负数为前几月
	 * 正数为后几月
	 * @param months
	 * @return
	 */
	public static long addMoths(long time, int months){
		return new DateTime(time).plusMonths(months).getMillis();
	}
	
	/**
	 * 添加年
	 * 负数为前几年
	 * 正数为后几年
	 * @param years
	 * @return
	 */
	public static long addYear(int years){
		return DateTime.now().plusYears(years).getMillis();
	}
	
	/**
	 * 添加年
	 * 负数为前几年
	 * 正数为后几年
	 * @param years
	 * @return
	 */
	public static long addYear(long time, int years){
		return new DateTime(time).plusYears(years).getMillis();
	}
	
	/**
	 * 
	 * <p>描述：获取指定小时的时间戳</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param hours
	 * @return
	 */
	public static long getMillisOfHours(int hours){
		return getMillisOf(FMT_Y_M_D_H, DateTime.now().plusHours(hours).toString(FMT_Y_M_D_H));
	}
	

	
	/**
	 * 
	 * <p>描述：获取指定分的时间戳</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param minutes
	 * @return
	 */
	public static long getMillisOfMinutes(int minutes){
		return getMillisOf(FMT_Y_M_D_H_M, DateTime.now().plusMinutes(minutes).toString(FMT_Y_M_D_H_M));
	}
	
	/**
	 * 
	 * <p>描述：获取指定秒的时间戳</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param seconds
	 * @return
	 */
	public static long getMillisOfSeconds(int seconds){
		return getMillisOf(FMT_Y_M_D_H_M_S, DateTime.now().plusSeconds(seconds).toString(FMT_Y_M_D_H_M_S));
	}
	/**
	 * 
	 * <p>描述：获取指定天的时间戳</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param days
	 * @return
	 */
	public static long getMillisOfDay(int days){
		return getMillisOf(FMT_Y_M_D, DateTime.now().plusDays(days).toString(FMT_Y_M_D));
	}
	
	/**
	 * 
	 * <p>描述：获取指定周的时间戳</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param weeks
	 * @return
	 */
	public static long getMillisOfWeeks(int weeks){
		return getMillisOf(FMT_Y_M_D, DateTime.now().plusWeeks(weeks).toString(FMT_Y_M_D));
	}
	
	/**
	 * 
	 * <p>描述：获取指定月的时间戳</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param months
	 * @return
	 */
	public static long getMillisOfMonths(int months){
		return getMillisOf(FMT_Y_M, DateTime.now().plusMonths(months).toString(FMT_Y_M));
	}
	
	/**
	 * 
	 * <p>描述：获取指定年的时间戳</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param years
	 * @return
	 */
	public static long getMillisOfYears(int years){
		return getMillisOf(FMT_Y, DateTime.now().plusYears(years).toString(FMT_Y));
	}
	
	/******************************************************* private *************************************************************************/
	
	private static long getMillisOf(String pattern, String dateTime){
		return new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter().parseMillis(dateTime);
	}
	
	
	
}
