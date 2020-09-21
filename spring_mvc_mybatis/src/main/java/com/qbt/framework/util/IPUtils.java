/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: IPUtils.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.qbt.framework.util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


/**<p>Class: IPUtils.java</p>
 * <p>Description: IP相关工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class IPUtils {

	private static final Pattern IP_REG = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

	/**
	 * IP类型转换
	 * <pre>
	 * 	 String类型的IP转成Long 类型
	 * </pre>
	 * @param strIp
	 * @return
	 */
	public static long ipToLong(String strIp) {
		if (strIp == null || strIp.length() < 4 || !IP_REG.matcher(strIp).matches()) {
			return -1l;
		}
		long ip[] = new long[4];
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	/**
	 * IP类型转换
	 * <pre>
	 * 		 Long类型的IP转成String 类型
	 * </pre>
	 * @param longIp
	 * @return
	 */
	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		sb.append(String.valueOf(longIp >>> 24));
		sb.append(".");
		sb.append(String.valueOf((longIp & 0xffffffL) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((longIp & 65535L) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(longIp & 255L));
		return sb.toString();
	}
	
	/**
	 * 获取客户端的IP地址
	 * <pre>
	 *    
	 * </pre>
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {      
        String ip = request.getHeader("x-forwarded-for");      
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getHeader("Proxy-Client-IP");      
        }     
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getHeader("WL-Proxy-Client-IP");     
        }     
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getRemoteAddr();      
        }   
        return ip;     
    }  
	
	/**
	 * 获取客户端的IP地址
	 * <pre></pre>
	 * @param request
	 * @return
	 */
	public static long getIpAddress(HttpServletRequest request) { 
		String str = getIpAddr(request);
		return ipToLong(str);
	}
	
}
