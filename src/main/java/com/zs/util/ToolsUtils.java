package com.zs.util;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


public class ToolsUtils {

	public static final String[] CAPTCHA_CHARS = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
	public static final SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmssS");
	public static final SimpleDateFormat formateSimple = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat formateSimple1 = new SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat formateSimple2 = new SimpleDateFormat("yyyy年MM月");
	public static final SimpleDateFormat formateSimple3 = new SimpleDateFormat("yyyy年");
	public static final String dateFormatePattern = "YYYYMMDDHHMMSS";
	public static final String formatePattern = "yyyy-MM-dd HH:mm:ss";
	public static final String formatePattern2 = "yyyy-MM-dd";
	public static final String formatePattern3 = "yyyy-MM";
	public static final String formatePattern4 = "yyyy";
	
	public static String getMapOrDefault(Map map,Object key) {
		if(map.containsKey(key) && map.get(key) != null) {
			return map.get(key).toString();
		} else {
			return null;
		}
	}
	
	public static boolean isBlank(String str){
			if(str == null || "".equals(str)){
				return true;
			}else{
				return false;
			}
		}
	 
	/**
	 * 产生六位随机数字验证码
	 * @return
	 */
	public static String generateCaptcha(){
		StringBuffer captcha = new StringBuffer();
		Random random = new Random();
		for(int i = 0; i< 6; i ++){
			captcha.append(CAPTCHA_CHARS[random.nextInt(10)]);
		}
		return captcha.toString();
	}
	
	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String replacer(String str) {
		String data = str;
		try {
			data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
			data = data.replaceAll("\\+", "%2B");
			data = URLDecoder.decode(data, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static String ifNull(String value) {
		if(value == null) 
			 return "";
		if("null".equals(value))
			return "";
		else
			return value;
	}
	
	public static String ifNullZore(String value) {
		if(value == null) 
			 return "0";
		if("null".equals(value))
			return "0";
		else
			return value;
	}
	
	// 获取指定年月的天数
	public static int getMonthDays(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONDAY, month - 1);
		c.set(Calendar.DATE, 1);
		c.roll(Calendar.DATE, -1);
		int maxDate = c.get(Calendar.DATE);
		return maxDate;
	}
}
