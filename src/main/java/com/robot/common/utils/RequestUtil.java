package com.robot.common.utils;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class RequestUtil {

	public static String getString(HttpServletRequest httpservletrequest,
			String s) {
		return getString(httpservletrequest, s, "", true);
	}
	
	public static String getString(HttpServletRequest httpservletrequest,
			String s, String s1, boolean flag) {
		String s2 = httpservletrequest.getParameter(s);
		if (StringUtils.isEmpty(s2))
			return s1;
		if (flag)
			return s2.replace("'", "''").trim();
		else
			return s2.trim();
	}

	
	public static int getInt(HttpServletRequest httpservletrequest, String s) {
		return getInt(httpservletrequest, s, 0);
	}

	public static int getInt(HttpServletRequest httpservletrequest, String s,
			int i) {
		String s1 = httpservletrequest.getParameter(s);
		if (StringUtils.isEmpty(s1))
			return i;
		else
			return Integer.parseInt(s1);
	}
	
	public static long getLong(HttpServletRequest httpservletrequest, String s) {
		return getLong(httpservletrequest, s, 0L);
	}
	
	public static long getLong(HttpServletRequest httpservletrequest, String s,
			long l) {
		String s1 = httpservletrequest.getParameter(s);
		if (StringUtils.isEmpty(s1) || "null".equals(s1))
			return l;
		else
			return Long.parseLong(s1.trim());
	}

}
