package com.javatea.spotchserver.opt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static LocalDateTime stringToDateTime(String dateStr) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateStr,dtf);
		return localDateTime;
	}

	public static LocalDateTime stringToDateTime(String dateStr, String pattern) {
		setPattern(pattern);
		return stringToDateTime(dateStr);
	}

	public static LocalDate stringToDate(String dataStr) {
		return LocalDate.parse(dataStr,dtf);
	}

	public static LocalDate stringToDate(String dateStr,String pattern) {
		setPattern(pattern);
		return stringToDate(dateStr);
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
