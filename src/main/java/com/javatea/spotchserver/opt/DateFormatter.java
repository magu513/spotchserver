package com.javatea.spotchserver.opt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日付型の変換に関するクラス
 */
public class DateFormatter {
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * 文字列からLocalDateTime型へ変換
	 * @param dateStr
	 * @return
	 */
	public static LocalDateTime stringToDateTime(String dateStr) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateStr,dtf);
		return localDateTime;
	}

	/**
	 * パターンを指定し、文字列からLocalDateTime型へ変換
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static LocalDateTime stringToDateTime(String dateStr, String pattern) {
		setPattern(pattern);
		return stringToDateTime(dateStr);
	}

	/**
	 * 文字列からLocalDate型へ変換
	 * @param dataStr
	 * @return
	 */
	public static LocalDate stringToDate(String dataStr) {
		return LocalDate.parse(dataStr,dtf);
	}

	/**
	 * 文字列からLocalDate型変換
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static LocalDate stringToDate(String dateStr,String pattern) {
		setPattern(pattern);
		return stringToDate(dateStr);
	}

	/**
	 * 変換パターンの指定
	 * @param pattern
	 */
	public static void setPattern(String pattern) {
		dtf = DateTimeFormatter.ofPattern(pattern);
	}

	/**
	 * LocalDateTime型を文字列へ変換
	 * @param date
	 * @return
	 */
	public static String dateTimeToString(LocalDateTime date) {
		return dtf.format(date);
	}

	/**
	 * 変換パターンを指定して、LocalDateTime型へ変換
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateTimeToString(LocalDateTime date,
								   String pattern) {
		setPattern(pattern);
		return dateTimeToString(date);
	}
}
