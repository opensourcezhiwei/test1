package com.nnk.maven.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ����ʱ�乤����
 */
public class DateUtil {

	private static String dateFormatUnSigned = "yyyyMMddHHmmss";

	/**
	 * ��ȡ��ǰʱ��
	 */
	public static String getCurrentTime() {
		return new SimpleDateFormat(dateFormatUnSigned).format(new Date(System.currentTimeMillis()));
	}
}
