package com.shinhan.emp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String converToString(Date d1) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = sdf.format(d1);
		return str;
		
	}
	public static Date convertToDate(String str2) {
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d2 = null;
		try {
			d2 = sdf.parse(str2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d2 ; 
		
	}
	public static java.sql.Date convertToSQLDate(Date d){
		
		return new java.sql.Date(d.getTime());
		
	}

}
