package com.pxjy.common.lang;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtil {
	
	private final static String SPLIT_SIGN = "~@&!<./-%#";
	
	public static void checkString(String name, String value) {
		if (value == null) {
            throw new NullPointerException(name + " can't be null");
        }
        if ("".equals(value)) {
        	throw new IllegalArgumentException(name + " can't be empty");
        }
	}
 
	public static boolean isEmpty(String... value) {
		if (value == null || value.length <= 0) {
			return true;
		}
		for (String s : value) {
			if (s != null && s.length() > 0) {
				return false;
			}
		}
		return true;
	}
 
	public static boolean isNotEmpty(String... value) {
		if (isEmpty(value)) {
        	return false;
        }else {
			return true;
		}
	}
 
	public static boolean equals(String s1, String s2){
		if (isNotEmpty(s1)) {
			return s1.equals(s2);
		}else if (isNotEmpty(s2)) {
			return s2.equals(s1);
		}else {
			return true;
		}
	}
 
	public static String stackTrace2String(Throwable t) {
		StringWriter sw = new StringWriter(); 
		PrintWriter pw = new PrintWriter(sw); 
		t.printStackTrace(pw); 
		return sw.toString();
	}
	
	public static String[] toStringArray(String str, String delimiters) {
		if (str == null) {
			return null;
		}
		
		if (delimiters == null) {
            throw new NullPointerException("delimiters can't be null");
        }
		return str.split(delimiters);
	}
	
	public static int toInt(String str) {
		if (isEmpty(str)) {
			return 0;
		}
		return Integer.parseInt(str);
	}
	
	public static int countStr(String input, String regex) {
		checkString("input", input);
		checkString("regex", regex);
		int count = 0;
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (regex.equals(String.valueOf(c[i]))) {
				count++;
			}
		}
		return count;
	}
	
	public static String merge(String s1, String s2) {
		return s1 + SPLIT_SIGN + s2;
	}
	
	public static String[] split(String s) {
		return s.split(SPLIT_SIGN);
	}
}
