package com.conie.library.webcontroller.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;

public class DateTimeUtil {

	private static final String ASIA_MANILA = "Asia/Manila";

	public static String convertToDateInTz(Long date, String tzId){
		if(tzId == null || tzId == "" ){
			tzId = ASIA_MANILA;
		}
		TimeZone tz = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone(tzId));
		SimpleDateFormat df =  new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date);
		String format = df.format(cal.getTime());
		TimeZone.setDefault(tz);
		cal = null;
		tz=null;
		return format;
	}
	
	public static Date getEndOfDay(Date date) {
	    return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
	}

public static Date getStartOfDay(Date date) {
    return DateUtils.truncate(date, Calendar.DATE);
}
}
