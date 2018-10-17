/**
 * 
 */
package com.shab.artificon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

	static final String DATEFORMAT = "yyyy-MM-dd hh:mm:ss";

	public final static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;

	static ZoneId etZoneId = ZoneId.of("America/New_York");
	static ZoneId istZoneId = ZoneId.of("Asia/Kolkata");

	static DateTimeFormatter etFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mma 'ET'");

	public static Date convertToUTC() throws ParseException {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));

		// Local time zone
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

		// Time in GMT
		return dateFormatLocal.parse(dateFormatGmt.format(new Date()));
	}

	public static ZonedDateTime getUTCdatetimeAsString() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		ZonedDateTime currentISTime = currentDateTime.atZone(istZoneId); // India Time
		ZonedDateTime currentETime = currentISTime.withZoneSameInstant(etZoneId); // ET Time
		return currentETime;
	}

	public static String getETCdatetimeAsString() {
		final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("EST"));
		final String utcTime = sdf.format(new Date());
		return utcTime;
	}

	// to check if the given date is more than 24 hours
	public static boolean checkOn24Hours(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(date);
		boolean moreThanDay = Math.abs(date1.getTime() - new Date().getTime()) > MILLIS_PER_DAY;
		System.out.println(moreThanDay);
		return moreThanDay;
	}
	
	public static String onConvertToday() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(new Date());
	}

	public static void main(String[] args) throws ParseException {
		checkOn24Hours("2018-10-14");
	}
}
