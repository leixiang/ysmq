package com.nsyun.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Shengzhao Li
 */
public class DateUtils {

	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_TIME_FORMAT = "yyMMddHHmmss";

	public static LocalDateTime now() {
		return LocalDateTime.now();
	}

	public static String toDateTime(LocalDateTime date) {
		return toDateTime(date, DEFAULT_DATE_TIME_FORMAT);
	}

	public static String toDateTime(LocalDateTime dateTime, String pattern) {
		return dateTime.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
	}

	public static String toDateTimeEx() {
		LocalDateTime dateTime = LocalDateTime.now();
		return dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.SIMPLIFIED_CHINESE));
	}

	public static String toDateText(LocalDate date, String pattern) {
		if (date == null || pattern == null) {
			return null;
		}
		return date.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
	}

	public static String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		return formatter.format(date);
	}
	
	public static String formatDateFormatFile(String random, String ext) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");

		String strFront =  formatter.format(new Date());
		
		return strFront + random + ext;
	}
	
	public static Date stringToDateAdd(String date, String time) {
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = date + time;
			return formatter.parse(str);
		}catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	public static Date stringToDate(String date) {
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			return formatter.parse(date);
		}catch(Exception e)
		{
			e.printStackTrace();
			
			return null;
		}
	}

	public static String dateTimeToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return formatter.format(date);
	}
	
	public static String dateTimeToSimple(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");

		return formatter.format(date);
	}
	
	public static Date dateCompareDistpatch() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		if(date.getHours() > 15)
		{
			date.setHours(17);
			date.setMinutes(0);
			
			calendar.setTime(date);
			calendar.add(calendar.DAY_OF_MONTH, 1);
			return calendar.getTime();
		}else
		{
			date.setHours(17);
			date.setMinutes(0);
			calendar.setTime(date);
			return calendar.getTime();
		}
	}
	
	public static Integer dateCompareDistpatchEx(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		if(date.getHours() > 15)
		{
			return 2;
		}else
		{
			return 1;
		}
	}
	
	public static Date addDate(Date date, Integer intTimes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, intTimes - 60 * 60 * 24*2);
		return calendar.getTime();

	}
	
	public static Date addDateMinute(Date date, Integer intTimes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, intTimes);
		return calendar.getTime();

	}
	
	public static Date addDateByDay(Date date, Integer nDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, nDays);
		return calendar.getTime();

	}

	/**
	 * int <=0 时间早
	 * 
	 * @param curDate
	 * @param dateHis
	 * @return
	 */
	public static int dateCompare(Date curDate, Date dateHis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(dateHis);
		
		return calendar.compareTo(calendar2);
	}
	
	
	public static int dateCompareHour(Date curDate, Date dateHis) {
		if(curDate.getTime() - dateHis.getTime() > 1000*60*60){
			return 1;
		}else{
			return 0;
		}
		
	}
	
	public static long getMin (Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long diff = nowDate.getTime() - endDate.getTime();
	    long min = diff % nd % nh / nm;
	    return min;
	}

}