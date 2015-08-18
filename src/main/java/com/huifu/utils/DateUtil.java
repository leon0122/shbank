package com.huifu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公用日期操作类
 * 上海汇付金融服务有限公司
 * 2015年4月17日 上午10:21:22
 * @author jack.liu
 */

public final class DateUtil {

	public static final String FORMAT8 = "yyyyMMdd";

	public static final String FORMAT14 = "yyyyMMddHHmmss";
	
	public static final String FORMAT19 ="yyyy-MM-dd HH:mm:ss";
	
	private static Pattern pattern = Pattern.compile("-|:|\\s");

	private static Matcher match;

	private DateUtil(){};
	
	public static String displayDateFormat(String strDate, String inputFormat,String outputFormat) throws ParseException{
        String strReturn = "";
        if (null == strDate || strDate.trim().equals("")) {
        	return strReturn;
        }else{ 	
        	SimpleDateFormat dbFormat = new SimpleDateFormat(inputFormat);		 
        	SimpleDateFormat displayFormat = new SimpleDateFormat(outputFormat);		 
        	strReturn = displayFormat.format(dbFormat.parse(strDate));      
        }   
        return strReturn;
    }
	
	/**
	 * 日期操作
	 * @param date
	 * @param field 需操作的时间字段,例如Calendar.YEAR
	 * @param value 正数为加,负数为减
	 * @return
	 * 
	 */
	public static Date add(Date date, int field, int value) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(field, value);
		return ca.getTime();

	}

	/**
	 * 获取日期,格式yyyyMMddHHmmss
	 * 
	 * @return 日期
	 */
	public static String getLongDate() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	/**
	 * 获取日期,格式yyyyMMdd
	 * 
	 * @return 日期
	 */
	public static String getShortDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	/**
	 * 获取前一日期 格式yyyyMMdd
	 * @return
	 */
	public static String getPreShortDate(){
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	
	/**
	 * 获取日期，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 日期
	 */
	public static String getFormateDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 获取日期，格式：yyyy-MM-dd HH:mm:ss
	 * @param formatStr 日期格式
	 * @return 日期
	 */
	public static String getFormateDate(String formatStr) {
		return new SimpleDateFormat(formatStr).format(new Date());
	}

	/**
	 * 获取日期时间精确到毫秒，格式：yyyyMMddHHmmssSSS
	 * 
	 * @return 日期
	 */
	public static String getFormateDateForLog() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

	/**
	 * 获取日期，格式：yyyyMMdd
	 * 
	 * @return 日期
	 */
	public static String getDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	

	/**
	 * 获取日期，格式：yyyyMMdd
	 * 
	 * @return 日期
	 */
	public static String getTm() {
		return new SimpleDateFormat("HHmmss").format(new Date());
	}


	/**
	 * 时间累加
	 * 
	 * @param date 当前日期 格式必须为 yyyyMMddHHmmss
	 * @param second 累加的时间 单位是秒
	 * @return 日期
	 * @throws ParseException 
	 */
	public static String dateAddSecond(String date, Long second) throws ParseException {

		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date((new SimpleDateFormat("yyyyMMddHHmmss").parse(date).getTime() + second * 1000)));

	}

	/**
	 * 时间相减
	 * @param date 当前日期 格式必须为 yyyyMMdd
	 * @param day 累加的时间 单位是天
	 * @return 日期
	 * @throws ParseException 
	 */
	public static String dateSub(String date, int day) throws ParseException {
		return new SimpleDateFormat("yyyyMMdd").format(new Date((new SimpleDateFormat("yyyyMMdd").parse(date).getTime() - Long.valueOf(day) * 24 * 60 * 60 * 1000)));
	}

	/**
	 * 时间相加
	 * @param date 当前日期 格式必须为 yyyyMMdd
	 * @param day 累加的时间 单位是天
	 * @return 日期
	 * @throws ParseException 
	 */
	public static String dateAdd(String date, int day) throws ParseException {
		return new SimpleDateFormat("yyyyMMdd").format(new Date((new SimpleDateFormat("yyyyMMdd").parse(date).getTime() + Long.valueOf(day) * 24 * 60 * 60 * 1000)));	
	}

	/**
	 * @param date 日期
	 * @return
	 * @throws ParseException 
	 */
	public static String getCoreDt(String date) throws ParseException {
		return "('" + dateSub(date, 1) + "','" + date + "','" + dateAdd(date, 1) + "')";
	}

	/**
	 * @param value 日期
	 * @return 
	 */
	public static String formateDate(String value) {
		if (value == null) {
			return null;
		}
		match = pattern.matcher(value);
		return match.replaceAll("");
	}

	/**
	 * @param value 日期
	 * @param a 位置
	 * @return 
	 */
	public static String formateDate(String value, int a) {		
		if (value == null) {	
			return null;
		}
		match = pattern.matcher(value);
		return match.replaceAll("").substring(0, a);
	}

	/**
	 * @param a 日期
	 * @param field 日期参数
	 * @param amount 日期参数
	 * @return
	 */
	public static Date addTime(Date a, int field, String amount) {	
		if (amount == null){
			return null;
		}

		try {	
			int am = Integer.parseInt(amount);	
			Calendar gre = Calendar.getInstance();	
			gre.setTime(a);		
			gre.add(field, am);	
			return gre.getTime();		
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * @param value  日期参数
	 * @param formate 日期格式
	 */
	public static Date formateStringToDate(String value, String formate) {	
		if (value == null){		
			return null;	
		}
		
		DateFormat format = new SimpleDateFormat(formate);
		try {	
			return format.parse(value);	
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date formateStringToDate(String formate) {	
		return formateStringToDate(getDate(),formate);
	}
	
	/**
	 * @param d 日期参数
	 * 
	 * @return 
	 */
	public static String formDateToString8(Date d) {
		if (d == null){	
			return null;	
		}
		return new SimpleDateFormat(FORMAT8).format(d);
	}

	/**
	 * @param d 日期参数
	 * @param format 日期格式
	 * @return
	 */
	public static String formDateToString(Date d, String format) {	
		if (d == null){		
			return null;
		}
		DateFormat formatDate = new SimpleDateFormat(format);
		return formatDate.format(d);
	}

	/**
	 * @param d 日期参数
	 * @return 
	 */
	public static String formDateToString14(Date d) {
		if (d == null){
			return null;
		}
		
		return new SimpleDateFormat(FORMAT14).format(d);
	}

	/**
	 * 返回格式化日期
	 * 
	 * @param format 日期格式
	 * 
	 * @return 日期
	 */
	public static String getFormatterDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
	
	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
    public static String getCurrentYear(){
        Calendar currentTime = Calendar.getInstance();
        Integer currentYear = currentTime.get(Calendar.YEAR);
        return currentYear.toString();
    }
    
    /**
     * 获取当前月份
     * @return
     */
    public static String getCurrentMonth() {
        Calendar currentTime = Calendar.getInstance();
        Integer currentMonth = currentTime.get(Calendar.MONTH) + 1;
        String month = currentMonth.toString();
        if(month.length() == 1){
            month = "0" + month;
        }
        return month;
    }
    
    /**
	 * 获取格式化的时间
	 * @param str
	 *            时间字符串(yyyyMMddHHmmss)
	 * @param fm
	 *            时间格式(yyyy-MM-dd或者HH:mm:ss)
	 * @return
	 * @since 2013-11-19
	 * @author lixw
	 * @create date 2013-11-19 下午3:03:17
	 */
	public static String getDateTime(String str, String fm) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SimpleDateFormat dateformat = new SimpleDateFormat(fm);
		return dateformat.format(date);
	}
	

	/**
	 * 获取日期，格式：
	 * @param format 如yyyyMMdd  yyyy-MM-dd
	 * @return
	 */
	public static String getFromatDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}
}