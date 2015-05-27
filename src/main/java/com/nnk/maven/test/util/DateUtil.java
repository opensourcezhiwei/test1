package com.nnk.maven.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class DateUtil {

	private static String dateFormatUnSigned = "yyyyMMddHHmmss";

	/**
	 * 获取当前时间
	 */
	public static String getCurrentTime() {
		return new SimpleDateFormat(dateFormatUnSigned).format(new Date(System.currentTimeMillis()));
	}
}
