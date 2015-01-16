package com.conie.library.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class TimeConverter {
	private static final double SECONDS_DEVISOR=1000;
	private static final double MINUTES_DEVISOR=SECONDS_DEVISOR*60;
	private static final double HOURS_DEVISOR=MINUTES_DEVISOR*60;
	private static final double DAYS_DEVISOR=HOURS_DEVISOR*24;
	public static String convertMilliToDuration(int milli){
		
		StringBuilder builder = new StringBuilder();
		double remainingMilliseconds=milli;
		if((remainingMilliseconds/DAYS_DEVISOR) >1d){
			builder.append(format(((int)(remainingMilliseconds/DAYS_DEVISOR)))).append(" ");
			remainingMilliseconds=(remainingMilliseconds%DAYS_DEVISOR);
		}
		
		if((remainingMilliseconds/HOURS_DEVISOR) >1d){
			builder.append(format((int)(remainingMilliseconds/HOURS_DEVISOR))).append(":");
			remainingMilliseconds=(remainingMilliseconds%HOURS_DEVISOR);
		}else{
			builder.append("00:");
		}
		
		if((remainingMilliseconds/MINUTES_DEVISOR) >1d){
			builder.append(format((int)(remainingMilliseconds/MINUTES_DEVISOR))).append(":");
			remainingMilliseconds=(remainingMilliseconds%MINUTES_DEVISOR);
		}else {
			builder.append("00:");
		}
		
		if((remainingMilliseconds/SECONDS_DEVISOR) >1d){
			builder.append(format((int)(remainingMilliseconds/SECONDS_DEVISOR)));
			remainingMilliseconds=(remainingMilliseconds%HOURS_DEVISOR);
		}else {
			builder.append("00");
		}
		return builder.toString();
	}
	
	private static String format(int numToFormat) {
		NumberFormat numFormat = new DecimalFormat("00");
		return numFormat.format(numToFormat);
	}
}
