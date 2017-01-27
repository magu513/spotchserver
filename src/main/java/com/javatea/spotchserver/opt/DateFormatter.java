package com.javatea.spotchserver.opt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
	private static DateTimeFormatter dtf;

	public static LocalDateTime stringToDateTime(String dateStr) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateStr,dtf);
		return localDateTime;
	}

	public static LocalDateTime stringToDateTime(String dateStr, String pattern) {
		setPattern(pattern);
		return stringToDateTime(dateStr);
	}

	public static void setPattern(String pattern) {
		dtf = DateTimeFormatter.ofPattern(pattern);
	}

	public static String dateTimeToString(LocalDateTime date) {
		return dtf.format(date);
	}

	public static String dateTimeToString(LocalDateTime date,
								   String pattern) {
		setPattern(pattern);
		return dateTimeToString(date);
	}
}
