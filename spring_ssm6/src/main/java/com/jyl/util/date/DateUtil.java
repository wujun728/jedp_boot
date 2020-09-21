/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

import com.jyl.util.ParamChecker;

/**
 * 日期工具类
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年6月7日 上午9:35:37
 */
public class DateUtil {
	
	/**
	 * 日期时间格式
	 */
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 一周为7天
     */
    public static final int DAY_OF_WEEK = 7;

    /**
     * 一天为1000 * 60 * 60 * 24毫秒.
     */
    public static final int DAY_MILLISECOND = 1000 * 60 * 60 * 24;

    /**
     * 23时.
     */
    private static final int HOUR_23 = 23;

    /**
     * 59分.
     */
    private static final int MINUTE_59 = 59;

    /**
     * 59秒.
     */
    private static final int SECOND_59 = 59;

    /**
     * 999毫秒.
     */
    private static final int MILLISECOND_999 = 999;

    public static final String YEAR_MONTH = "yyyy-MM";

    public static final String DEFAULT_TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * 日期时间格式
     */
    static final String UTIL_FORMAT_YYYYMMDD = "yyyy-MM-dd";
    
    /**
     * 日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 常用格式数组
     */
    public static final String[] patterns = new String[] { DATE_TIME_FORMAT, DATE_FORMAT };
    
    public static final Logger log = Logger.getLogger(DateUtil.class);

    /**
     * 获取星期一, 星期一作为一周的开始
     */
    public static Date getMonday() {
        return getMonday(new Date());
    }
    
    public static Date parse(String dateStr) {
        try {
            return DateFormatter.parse(dateStr, "yyyy-MM-dd");
        } catch (ParseException e) {
        	log.error("ParseException", e);
            new RuntimeException(e);
        }
        return new Date();
    }
    
    /**
     * 获得当前时间的时间戳
     * 
     * @return
     */
    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(getNowDate().getTime());
    }
    public static Date getNowDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取星期一, 星期一作为一周的开始
     */
    public static Date getMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int add = 2 - (dayOfWeek == 1 ? 8 : dayOfWeek);
        calendar.add(Calendar.DAY_OF_WEEK, add);
        return calendar.getTime();
    }

    /**
     * 获取当前时间字符串
     * 
     * @return yyyyMMdd 格式
     */
    public static String getNowDateToyyyyMMdd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new java.util.Date());
    }

    /**
     * 获取当前年月字符串
     * 
     * @return yyMM 格式
     */
    public static String getYearMonthToyyMM() {
        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        return format.format(new java.util.Date());
    }

    /**
     * 获取当前时间字符串
     * 
     * @return yyyyMMdd 格式
     */
    public static String getNowTime() {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        return format.format(new java.util.Date());
    }

    /**
     * 获取年
     * 
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);

    }

    /**
     * 获取一年的第一天
     * 
     * @param date
     * @return
     */
    public static Date getFirstDateOfYear(Date date) {

        int year = getYear(date);
        return getFirstDateOfYear(year).getTime();
    }

    /**
     * 每月的第一天
     */
    public static Date getFirstDateOfMonth(Date date) {
        int[] ymd = DateUtil.getYMD(date);
        return buildDate(ymd[0], ymd[1], 1, 0, 0, 0);
    }

    /**
     * 获取一年的第一天
     * 
     * @return
     */
    public static Calendar getFirstDateOfYear(int year) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);

        // 月份从0开始
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar;
    }

    /**
     * 判断是否是闰年
     * 
     * @param year
     * @return
     */
    public static boolean isLoopYear(int year) {

        boolean flag = false;

        if (year % 4 != 0) {
            flag = false;
        } else if (year % 100 != 0) {
            flag = true;
        } else if (year % 400 != 0) {
            flag = false;
        } else {
            flag = true;
        }

        return flag;
    }

    /**
     * 根据日期字符串格式和常用的格式化模式解析化成Date对象.
     * 
     * @param datetimeText
     * @return
     * @throws ParseException
     */
    public static Date parseDateWithCommonPattern(String datetimeText) {
        if (StringUtils.isBlank(datetimeText))
            return null;
        try {
            return DateUtils.parseDate(datetimeText, patterns);
        } catch (ParseException e) {
            log.error("根据日期字符串格式和常用的格式化模式解析化成Date对象出错:", e);
            return null;
        }
    }

    public static String getFormatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 将日期中的时分秒去掉，只留下年月日 例如一个日期为2012-01-01 11：11：11 去掉时分秒之后，为2012-01-01
     * 
     * @param date
     * @return
     */
    public static Date eraseTimeFromDate(Date date) {

        try {
            String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");
            return DateFormatter.parse(dateStr, "yyyy-MM-dd");
        } catch (ParseException e) {
        	log.error("ParseException", e);
            new RuntimeException(e);
        }
        return new Date();
    }

    /**
     * 将日期格式化为字符串 例如，日期为2010-01-01 11:11:11 执行之后，会返回一个字符串2010-01-01
     * 
     * @param date
     * @return
     */
    public static String formatDateToStr(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }

    /**
     * 获取date所在月份的每天的日期
     * 
     * @param date
     * @return
     */
    public static List<Date> getAllTheDateOftheMonth(Date date) {
        List<Date> dateList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int maxDay = calendar.getMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= maxDay; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            dateList.add(DateUtil.eraseTimeFromDate(calendar.getTime()));
        }
        return dateList;
    }

    public static Date getNewDateByAddingSomeDay(Date startDate, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getPureDate(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int year, month, day;
        year = gregorianCalendar.get(Calendar.YEAR);
        month = gregorianCalendar.get(Calendar.MONTH);
        day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar gregorianCalendar1 = new GregorianCalendar(year,
                month, day);
        Date temp = new Date(gregorianCalendar1.getTimeInMillis());
        return temp;
    }


    /**
     * 
     * @param orignalDate
     * @param hourAmount
     *            偏移小时，+表示向后，-表示向前。
     * @return
     */
    public static Date addHours(Date orignalDate, int hourAmount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(orignalDate);
        cal.add(Calendar.HOUR, hourAmount);
        return cal.getTime();
    }


    public static Date addSomeDay(Date date, int i) {
        return new Date(date.getTime() + (long) i * 1000 * 60 * 60 * 24);
    }

    /**
     * 静态函数：获得当前日期
     * 
     * @return date Date类型
     */
    public static Date getCurDate() {
        try {
            long curtime = System.currentTimeMillis();
            Date date = new Date(curtime);
            return StringToDate(DateToString(date));
        } catch (Exception e) {
        	log.error("Exception", e);
            return null;
        }
    }

    /**
     * 获取一个日期延迟几个月之后的日期，月份可以为负数，代之向前后退了几个月 例如，2012-01-01向后延迟6个月为2012-07-01
     * 
     * @param date
     * @param monthNum
     * @return
     */
    public static Date addMonth(Date date, int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthNum);
        return calendar.getTime();
    }
    
    /**
     * 比较两个时间大小的值
     * firstDate>secondDate返回1
     * firstDate<secondDate返回-1
     * firstDate=secondDate返回0
     */
    public static int compareDate(Date firstDate, Date secondDate) {
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(firstDate);
        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(secondDate);
        
        if(firstCalendar.getTimeInMillis() > secondCalendar.getTimeInMillis()){
            return 1;
        }
        if(firstCalendar.getTimeInMillis() == secondCalendar.getTimeInMillis()){
            return 0;
        }
        if(firstCalendar.getTimeInMillis() < secondCalendar.getTimeInMillis()){
            return -1;
        }
        return 0;
    }

    /**
     * 判断两个日期是否在同一年
     * 
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static boolean isAtTheSameYear(Date firstDate, Date secondDate) {
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(firstDate);

        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(secondDate);

        if (firstCalendar.get(Calendar.YEAR) == secondCalendar
                .get(Calendar.YEAR)) {
            return true;
        }
        return false;
    }

    /**
     * 比较日期date1是否大于date2
     * 
     * @param comparedField
     *            比较字段,有时只需要比较天,或时. 并不需要精确到毫秒
     */
    public static boolean isGreatThan(Date date1, Date date2, int comparedField) {

        date1 = DateUtils.truncate(date1, comparedField);
        date2 = DateUtils.truncate(date2, comparedField);

        return date1.getTime() > date2.getTime();
    }

    /**
     * 根据年月日构造一个日期. 时分秒都是0
     */
    public static Date buildDate(int year, int month, int date) {
        int hourOfDay = 0;
        int minute = 0;
        int second = 0;

        return buildDate(year, month, date, hourOfDay, minute, second);
    }

    /**
     * 根据年月日时分秒构造一个日期.
     */
    public static Date buildDate(int year, int month, int date, int hourOfDay,
            int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hourOfDay, minute, second);
        // 注意: 这里要清空毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取年月日
     */
    public static int[] getYMD(Date date1) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);

        return new int[] { calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) };

    }
    
    /**
     * 获取当前年月日. 月份从1开始
     */
    public static int[] getYMD() {

        Calendar calendar = Calendar.getInstance();

        return new int[] { calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE) };
    }

//    /**
//     * 获取当前天
//     */
//    public static int getCurrentDate() {
//        return getYMD()[2];
//    }

    /**
     * 获取当前月份, 月份从1开始
     */
    public static int getCurrentMonth() {
        return getYMD()[1];
    }

    /**
     * 获取当前年份
     */
    public static int getCurrentYear() {
        return getYMD()[0];
    }

    /**
     * 在一年内的所有天数
     */
    public static int getDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }
    
    public static int calcYearMonthDifference(Date date, Date date2){
        return calcYearMonth(date) - calcYearMonth(date2);
    }

    /**
     * 年份 * 12 + 月份
     */
    public static int calcYearMonth(Date date){
        int[] ymd = getYMD(date);
        return ymd[0] * 12 + ymd[1];
    }
    
    /**
     * 比较两个日期的年份和月份
     */
    public static int compareYearMonth(Date date, Date date2) {

        int[] ymd = getYMD(date);
        int[] ymd2 = getYMD(date2);

        for (int i = 0; i < 2; i++) {
            if (ymd[i] > ymd2[i]) {
                return 1;
            } else if (ymd[i] == ymd2[i]) {
                continue;
            } else {
                return -1;
            }
        }
        return 0;
    }
    
    /**
     * 比较两个日期的年份和月份
     */
    public static int compareYMD(Date date, Date date2) {

        int[] ymd = getYMD(date);
        int[] ymd2 = getYMD(date2);

        for (int i = 0; i < 3; i++) {
            if (ymd[i] > ymd2[i]) {
                return 1;
            } else if (ymd[i] == ymd2[i]) {
                continue;
            } else {
                return -1;
            }
        }
        return 0;
    }
    
    /**
     * 计算两个日期之间相差多少天
     * @param firstDate
     * @param secondDate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date firstDate,Date secondDate) throws ParseException    
    {    
        firstDate = eraseTimeFromDate(firstDate);
        secondDate = eraseTimeFromDate(secondDate);
        Calendar cal = Calendar.getInstance();    
        cal.setTime(firstDate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(secondDate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
        return Integer.parseInt(String.valueOf(between_days));           
    }

    /**
     * 比较两个日期的年月日,取比较小的值
     */
    public static Date minYMD(Date date, Date date2) {
        if(date == null){
            return date2;
        }
        if(date2 == null){
            return date;
        }
        if(compareYMD(date, date2) >= 0){
            return date2;
        }
        return date;
    }    
    
    /**
     * 比较两个日期的年月日,取比较大的值
     */
    public static Date maxYMD(Date date, Date date2) {
        if(date == null){
            return date2;
        }
        if(date2 == null){
            return date;
        }
        if(compareYMD(date, date2) >= 0){
            return date;
        }
        return date2;
    }

    /**
     * 设置最大的时分秒
     */
    public static Date setMaxHMS(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    
    	/**
	 * 设置最小的时分秒,毫秒
	 */
	public static Date setMinHMS(Date date) {
		
		if(date == null){
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	} 
    
    /**
     * 根据当前日期获取下一年日期
     */
    public static Date getNextYearDate(Date date) {
        if(date != null) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
        }
        return null;
    }
    
    public static java.sql.Date addSomeDays(final java.sql.Date sqlDate,
            final int n) {
        final java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(utilDate);
        calendar.add(Calendar.DATE, n);
        return new java.sql.Date(calendar.getTime().getTime());
    }

    /**
     * Transform timestamp to string format: 2003-06-19 used in Modify workweek
     * to set the form value
     * 
     * @param ts
     * @return
     */
    public static String DateToString(final Date dt) {
        String str = "";
        if (dt != null) {
            str = new SimpleDateFormat("yyyy-MM-dd").format(dt);
        }
        return str;
    }

    /**
     * 用于挣值图日期格式化
     * 
     * @param dt
     * @return
     */
    public static String DateToString_yyMMdd(final Date dt) {
        String str = "";
        if (dt != null) {
            str = new SimpleDateFormat("yy/MM/dd").format(dt);
        }
        return str;
    }

    public static String getDay(final int day) {
        switch (day) {
        case 0:
            return "星期日";
        case 1:
            return "星期一";
        case 2:
            return "星期二";
        case 3:
            return "星期三";
        case 4:
            return "星期四";
        case 5:
            return "星期五";
        case 6:
            return "星期六";
        default:
            return "";
        }
    }

    public static Date nowSqlDate() {
        return StringToDate(DateToString(new Date(
                System.currentTimeMillis())));
    }

    /**
     * Convert a String to Date
     * 
     * @param str
     * @return
     */
    public static Date StringToDate(final String str) {
        if (null == str || str.equals("")) {
            return null;
        }
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = null;
        try {
            date1 = df.parse(str);
        } catch (final Exception e) {
        	log.error("Exception", e);
            return null;
        }
        return new Date(date1.getTime());
    }

    /**
     * Transform String to Timestamp,used in Modify workweek
     * 
     * @param str
     * @return
     */
    public static Timestamp StringToTimestamp(final String str) {
        Timestamp ts = null;
        final SimpleDateFormat df = new SimpleDateFormat(UTIL_FORMAT_YYYYMMDD);
        try {
            if (df.parse(str) != null) {
                ts = new Timestamp(df.parse(str).getTime());
                return ts;
            } else {
                return null;
            }
        } catch (final Exception e) {
        	log.error("Exception", e);
            return null;
        }
    }

    /**
     * Transform timestamp to string format: 2003-06-19 used in Modify workweek
     * to set the form value
     * 
     * @param ts
     * @return
     */
    public static String TimestampToString(final Timestamp ts) {
        String str = "";
        if (ts != null) {
            final Date date1 = new Date(ts.getTime());
            str = new SimpleDateFormat(UTIL_FORMAT_YYYYMMDD).format(date1);
        }
        return str;
    }
    
    
    /**
     * 构造方法.
     */
    private DateUtil() {
        // empty
    }
    
    /**
	 * Date对象转换成 "2010-01-01 11:11:11" 日期时间格式字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatToDateTimeText(Date date) {
		return DateFormatUtils.format(date, DATE_TIME_FORMAT);
	}

    /**
     * 获取日期date在自己所在周中处于周几. 具体可见
     * <code>java.util.Date.getDay</code> 的文档说明.
     * 
     * @param date Date日期.
     * @return 日期date在自己所在周中处于周几.
     * @throws IllegalArgumentException 如果date为null.
     */
    public static int getDayOfWeek(Date date) {
        ParamChecker.checkNull(date, "date");
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);

        return cl.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 获取日期date在自己所在周中处于周几. 具体可见
     * <code>java.util.Date.getDay</code> 的文档说明.
     * @param year 
     * @param month 
     * @param date 
     * 
     * @return 日期date在自己所在周中处于周几.
     * @throws IllegalArgumentException 如果date为null.
     */
    public static int getDayOfWeek(int year,int month,int date) {
        ParamChecker.checkNull(date, "date");
        Calendar cl = Calendar.getInstance();
        cl.set(year,month,date);

        return cl.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取date的最开始时间,即00时00分00秒000毫秒.
     * 
     * @param date Date日期.
     * @return 获取date的最开始时间.
     * @throws IllegalArgumentException 如果date为null.
     */
    public static Date getFirstTimeInDay(Date date) {
        ParamChecker.checkNull(date, "date");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取date的最后时间,即23时59分59秒999毫秒.
     * 
     * @param date Date日期.
     * @return date的最后时间.
     * @throws IllegalArgumentException 如果date为null.
     */
    public static Date getLastTimeInDay(Date date) {
        ParamChecker.checkNull(date, "date");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, HOUR_23);
        calendar.set(Calendar.MINUTE, MINUTE_59);
        calendar.set(Calendar.SECOND, SECOND_59);
        calendar.set(Calendar.MILLISECOND, MILLISECOND_999);

        return calendar.getTime();
    }

    /**
     * 获取日期date所在周的weekDay所在的时间.
     * 
     * @param date Date日期.
     * @param firstDayOfWeek 本周开始的weekDay.
     * @param weekDay 周几.
     * @return 日期date所在周的weekDay所在的时间.
     * @throws IllegalArgumentException 如果date为null.
     * @throws IllegalArgumentException
     *             如果firstDayOfWeek小于1或者大于7.
     * @throws IllegalArgumentException 如果weekDay小于1或者大于7.
     */
    public static Date getWeekDayDate(Date date, int firstDayOfWeek, int weekDay) {
        ParamChecker.checkNull(date, "date");
        ParamChecker.checkInWeekDay(firstDayOfWeek, "firstDayOfWeek");
        ParamChecker.checkInWeekDay(weekDay, "weekDay");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(firstDayOfWeek);
        c.set(Calendar.DAY_OF_WEEK, weekDay);

        return c.getTime();
    }

    /**
     * 以天为单位对所给的date求偏移. 不会改变时间部分
     * <p>
     * 例如：2008.2.3-20:53:40 向前2天,则返回 2008.2.5-20:53:40
     * 
     * @param date 日期.
     * @param amount 偏移天数, +表示向后, -表示向前.
     * @return 所给的时间点求偏移.
     * @throws IllegalArgumentException 如果date为null或者为空.
     */
    public static Date addDays(Date date, int amount) {
        ParamChecker.checkNull(date, "date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);

        return calendar.getTime();
    }

    /**
     * 获取日期date所在月的第一天的日期,不会改变时间部分.
     * <p>
     * 例如：2008.2.15-20:53:40 ,则返回 2008.2.1-20:53:40
     * 
     * @param date 日期.
     * @return 日期所在月的第一天的日期.
     * @throws IllegalArgumentException 如果date为null.
     */
    public static Date getFirstDayInMonth(Date date) {
        ParamChecker.checkNull(date, "date");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);

        return c.getTime();
    }

    /**
     * 获取日期date所在月的最后一天的日期,不会改变时间部分.
     * <p>
     * 例如：2008.2.15-20:53:40 ,则返回 2008.2.29-20:53:40
     * 
     * @param date 日期.
     * @return 日期所在月的最后一天的日期.
     * @throws IllegalArgumentException 如果date为null.
     */
    public static Date getLastDayInMonth(Date date) {
        ParamChecker.checkNull(date, "date");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.roll(Calendar.DATE, -1);

        return c.getTime();
    }

    /**
     * 获取开始日期结束日期之间的天数.
     * <p>
     * 注意：计算之前会先截去日期的时间部分
     * <p>
     * 例如：计算 2008.2.3-23:59:59 与 2008.2.4-00:00:00 之间的天数,
     * 则先将起止日期处理为 2008.2.3 与 2008.2.4 ,他们之间相差一天,所以最后返回的结果为1.
     * 
     * @param startDate 开始日期.
     * @param endDate 结束日期.
     * @return 开始日期结束日期之间的天数.
     * @throws IllegalArgumentException
     *             如果开始日期为null或者结束日期为null.
     * @throws ParseException 如果开始日期或者结束日期格式化错误.
     */
    public static long daysBetweenDates(Date startDate, Date endDate) throws ParseException {
        ParamChecker.checkNull(startDate, "startDate");
        ParamChecker.checkNull(endDate, "endDate");
        /*
         * 不能直接用时间相减再除,因为夏令时会让有些天只有23小时有些25小时导致错误。
         * 所以先把日期转换成字符串,再生成那个字符串的GMT时间,再用GMT去计算天数. GMT没有夏令时.
         */
        DateFormat format = new SimpleDateFormat(DateFormatter.YYYY_MM_DD);
        String startDateString = format.format(getFirstTimeInDay(startDate));
        String endDateString = format.format(getFirstTimeInDay(endDate));
        DateFormat gmtFormat = new SimpleDateFormat(DateFormatter.YYYY_MM_DD);
        Calendar gmtCal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
        gmtFormat.setCalendar(gmtCal);
        Date sDate = gmtFormat.parse(startDateString);
        Date eDate = gmtFormat.parse(endDateString);

        return (eDate.getTime() - sDate.getTime()) / DAY_MILLISECOND;
    }
    
    /**
     * 获取日期date所在周的周一的日期,不会改变时间部分.
     * @param date 日期.
     * @return 日期所在周的周一的日期.
     * @throws IllegalArgumentException 如果date为null.
     */
    public static Date getFirstDayInWeek(Date date){
        ParamChecker.checkNull(date, "date");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return c.getTime();
    }
    
    /**
     * 获取日期date所在周的周日的日期,不会改变时间部分.
     * @param date 日期.
     * @return 日期所在周的周日的日期.
     * @throws IllegalArgumentException 如果date为null.
     */
    public static Date getLastDayInWeek(Date date){
        ParamChecker.checkNull(date, "date");
        Date lastDateInWeek = 
            addDays(getFirstDayInWeek(date), DAY_OF_WEEK - 1);

        return lastDateInWeek;
    }

    /**
     * 返回当前所在周的日期跨度
     * @return 返回结果为二维时间数组，第一个元素代表周一的日期，第二个元素代表周日的日期
     */
    public static Date[] getCurrentWeekSpan(){
        Date[] dates = new Date[2];
        dates[0] = getFirstDayInWeek(new Date());
        dates[1] = getLastDayInWeek(new Date());
        return dates;
    }
    
    /**
     * 返回当前所在日期前N周的日期跨度, N代表参数weeks
     * @param weeks 前几周
     * @return 返回结果为二维时间数组，第一个元素代表周一的日期，第二个元素代表周日的日期
     */
    public static Date[] getPreWeeksSpan(int weeks){
        Date[] dates = new Date[2];
        dates[0] = addDays(
                getFirstDayInWeek(new Date()), DAY_OF_WEEK * weeks * -1); 
        dates[1] = addDays(dates[0], DAY_OF_WEEK - 1);
        return dates;
    }
    
    /**
     * 返回当前所在日期后N周的日期跨度, N代表参数weeks
     * @param weeks 后几周
     * @return 返回结果为二维时间数组，第一个元素代表周一的日期，第二个元素代表周日的日期
     */
    public static Date[] getNextWeeksSpan(int weeks){
        Date[] dates = new Date[2];
        dates[0] = addDays(
                getFirstDayInWeek(new Date()), DAY_OF_WEEK * weeks); 
        dates[1] = addDays(dates[0], DAY_OF_WEEK - 1);
        return dates;
    }
    
    /**
     * 返回当前所在月的日期跨度
     * @return 返回结果为二维时间数组，第一个元素代表周一的日期，第二个元素代表周日的日期
     */
    public static Date[] getCurrentMonthSpan(){
        Date[] dates = new Date[2];
        dates[0] = getFirstDayInMonth(new Date());
        dates[1] = getLastDayInMonth(new Date());
        return dates;
    }
    
    /**
     * 返回当前日期的上一个月的日期跨度
     * @return 返回结果为二维时间数组，第一个元素代表周一的日期，第二个元素代表周日的日期
     */
    public static Date[] getLastMonthSpan(){
        Date[] dates = new Date[2];
        dates[1] = addDays(getFirstDayInMonth(new Date()), -1);
        dates[0] = getFirstDayInMonth(dates[1]);
        return dates;
    }

    /**
     * 返回当前日期的下一个月的日期跨度
     * @return 返回结果为二维时间数组，第一个元素代表周一的日期，第二个元素代表周日的日期
     */
    public static Date[] getNextMonthSpan(){
        Date[] dates = new Date[2];
        dates[0] = addDays(getLastDayInMonth(new Date()), 1);
        dates[1] = getLastDayInMonth(dates[0]);
        return dates; 
    }

	/**
	 * 将字符类型的日期转换为java.sql.Date类型的数据.
	 * 
	 * @param dateString 日期字符串"2010-12-20".
	 * @return
	 * @throws ParseException 
	 */
	public static java.sql.Date string2Date(String dateString) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);// util类型
		java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());// sql类型

		return dateTime;
	}

	/**
	 * 将字符类型的日期转换为java.sql.Date类型的数据.
	 * 
	 * @param dateString 日期字符串"2010-12-20 14:21:12.123".
	 * @return
	 * @throws java.text.ParseException
	 */
	public static java.sql.Timestamp string2Time(String dateString)
			throws java.text.ParseException {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS",
				Locale.ENGLISH);// 设定格式
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);// util类型
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());

		return dateTime;
	}

	/**
	 * 获取当前的日期时间表示，并将最后3为精度去掉，这是为了与数据库中的字段进行兼容.
	 */
	public static Date getCurrentDate() {
		long timeMillis = System.currentTimeMillis() / 1000 * 1000 ;
		return new Date(timeMillis);
	}
	
	/**
	 * 返回指定月的日期数.
	 * @param year 
	 * @param month 
	 * @return
	 */
	public static int getMonthMaximumDate(int year,int month) {
	    Calendar calendar = Calendar.getInstance();
	    //设置为该月，日期随意
	    calendar.set(year,month,1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
    }
	
	//默认的处理开始时间，
	public static Date getDefaultStartDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}
	
	//默认的处理结束时间,当天的第days天
	public static Date getDefaultEndDate(int days){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND,  0);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	//默认的处理开始时间，结束时间移动days天
	public static Date getDefaultStartDate(Date finishEndDate,int days){
		Calendar cal = Calendar.getInstance();
		cal.setTime(finishEndDate);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}
	
}
