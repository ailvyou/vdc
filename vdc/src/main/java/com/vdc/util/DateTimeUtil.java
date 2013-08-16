package com.vdc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间工具类
 * 
 * @author daniel
 * 
 */
public class DateTimeUtil {
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DATE_PATTERN_SHORT = "yyyyMMdd";

	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_PATTERN_SHORT = "yyyyMMddHHmmss";
	public static final String DATETIME_PATTERN_LONG = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATETIME_PATTERN_LONG2 = "yyyyMMddHHmmssSSS";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(DateTimeUtil.getDateByDayOfWeek(new Date(), Calendar.WEDNESDAY));
	}

	/**
	 * 获取今天的日期字符串（默认格式）
	 * 
	 * @return
	 * @author daniel
	 */
	public static String getToday() {
		return formatDate(new Date());
	}

	/**
	 * 获取今天的日期字符串（自定义格式）
	 * 
	 * @param pattern
	 * @return
	 * @author daniel
	 */
	public static String getToday(String pattern) {
		return formatDateTime(new Date(), pattern);
	}

	/**
	 * 获取现在的日期字符串（默认格式）
	 * 
	 * @return
	 * @author daniel
	 */
	public static String getNow() {
		return formatDateTime(new Date());
	}

	/**
	 * 获取现在的日期字符串（自定义格式）
	 * 
	 * @param pattern
	 *            格式
	 * @return
	 * @author daniel
	 */
	public static String getNow(String pattern) {
		return formatDateTime(new Date(), pattern);
	}

	/**
	 * 将日期对象格式化成日期字符串（默认格式）
	 * 
	 * @param d
	 *            日期对象
	 * @return
	 * @author daniel
	 */
	public static String formatDate(Date d) {
		return formatDateTime(d, DATE_PATTERN);
	}

	/**
	 * 将日期对象格式化成日期时间字符串（默认格式）
	 * 
	 * @param d
	 *            日期对象
	 * @return
	 * @author daniel
	 */
	public static String formatDateTime(Date d) {
		return formatDateTime(d, DATETIME_PATTERN);
	}

	/**
	 * 将日期对象格式化成日期时间字符串（自定义格式）
	 * 
	 * @param d
	 *            日期对象
	 * @param pattern
	 *            格式
	 * @return
	 * @author daniel
	 */
	public static String formatDateTime(Date d, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}

	/**
	 * 将日期字符串转换成日期对象（默认格式）
	 * 
	 * @param str
	 *            日期字符串
	 * @return
	 * @author daniel
	 */
	public static Date parseDate(String str) {
		return parseDateTime(str, DATE_PATTERN);
	}

	/**
	 * 将日期时间字符串转换成日期对象（默认格式）
	 * 
	 * @param str
	 *            日期时间字符串
	 * @return
	 * @author daniel
	 */
	public static Date parseDateTime(String str) {
		return parseDateTime(str, DATETIME_PATTERN);
	}

	/**
	 * 将日期时间字符串转换成日期对象（自定义格式）
	 * 
	 * @param str
	 *            日期时间字符串
	 * @param pattern
	 *            格式
	 * @return
	 * @author daniel
	 */
	public static Date parseDateTime(String str, String pattern) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			d = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 比较两个日期
	 * 
	 * @param src
	 *            第一个日期对象
	 * @param dest
	 *            第二个日期对象
	 * @return -1表示小于，0表示等于，1表示大于
	 * @author daniel
	 */
	public static int compareTo(Date src, Date dest) {
		Date d1 = parseDateTime(formatDateTime(src, DATETIME_PATTERN), DATETIME_PATTERN);
		Date d2 = parseDateTime(formatDateTime(dest, DATETIME_PATTERN), DATETIME_PATTERN);
		int i = d1.compareTo(d2);
		return i;
	}

	/**
	 * 在给定的时间上向后追加多少分钟
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getDateAfter(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		} else {
			calendar.setTime(new Date());
		}
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 
	 * 输入一个日期，得到这个日期所在周的dayOfWeek的日期
	 * 
	 * @param date
	 * @param dayOfWeek
	 * @return
	 * @author Lynch
	 */
	public static String getDateByDayOfWeek(Date date, int dayOfWeek) {
		SimpleDateFormat format = new SimpleDateFormat(DateTimeUtil.DATE_PATTERN);
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setTime(date);
		// 关于DAY_OF_WEEK的说明
		// Field number for get and set indicating
		// the day of the week. This field takes values
		// SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
		// and SATURDAY
		// 如果是星期天，则加上7天
		if (dayOfWeek == Calendar.SUNDAY) {
			cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
			return format.format(new Date(cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000)));
		} else {
			cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		}
		return format.format(cal.getTime());
	}

	/**
	 * 
	 * 获取时间在当前月是第几天
	 * 
	 * @param date
	 * @return
	 * @author Lynch
	 */
	public static int getCurrentMonthOfDay(Date date) {
		// 获取当前月的第一天
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		Long day = DateTimeUtil.getDay(date, firstDate);
		return day.intValue();
	}

	/**
	 * 
	 * 获取当前月的第n天
	 * 
	 * @param date
	 * @param n
	 * @param format
	 * @return
	 * @author Lynch
	 */
	public static String getDayOfMonth(Date date, int n) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, n);
		return DateTimeUtil.formatDateTime(ca.getTime(), DATETIME_PATTERN);
	}

	/**
	 * 
	 * 获取当前月的第n天
	 * 
	 * @param date
	 * @param n
	 * @param format
	 * @return
	 * @author Lynch
	 */
	public static String getDayOfMonth(Date date, int n, String format) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, n);
		return DateTimeUtil.formatDateTime(ca.getTime(), format);
	}

	/**
	 * 
	 * 日期增加或减去某个时间单位数
	 * 
	 * @param millis
	 * @return
	 * @author Lynch
	 */
	public static Date getAddDate(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		return c.getTime();
	}

	/**
	 * 
	 * 返回两个日期天数差
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * @author Lynch
	 */
	public static long getDay(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return 0;
		}
		long ms = DateTimeUtil.getMilliSecond(d1, d2);
		return ms / 1000 / 60 / 60 / 24;
	}

	/**
	 * 返回两个日期毫秒差
	 */
	public static long getMilliSecond(Date d1, Date d2) {
		long d1MS = d1.getTime();
		long d2MS = d2.getTime();
		return Math.abs(d1MS - d2MS);
	}

	/**
	 * 
	 * 获取时间所在的月份
	 * 
	 * @param date
	 * @return
	 * @author Lynch
	 */
	public static int getMonthOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 
	 * 获取时间所在天数
	 * 
	 * @param date
	 * @return
	 * @author Lynch
	 */
	public static int getDayOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DATE);
	}

	/**
	 * 
	 * 获取当前时间所在的年
	 * 
	 * @param date
	 * @return
	 * @author Lynch
	 */
	public static int getYearOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
}
