package cn.com.newloading.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

	/*
	 * Date转String
	 */
	public static String dateToString(Date time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(time);
		return dateString;
	}

	/*
	 * String转Date
	 */
	public static Date stringToDate(String time) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mi:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 解析时间
	 */
	public static String getDateTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy K:m:s a", Locale.ENGLISH);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(time);
			time = formatter.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
}
