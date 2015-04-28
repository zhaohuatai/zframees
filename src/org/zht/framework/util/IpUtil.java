/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

    public static String getClientIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    public static long ipStrToLong(String ipaddress) {    
        long[] ip = new long[4];  
        int i = 0;  
        for(String ipStr : ipaddress.split("\\.")){  
            ip[i] = Long.parseLong(ipStr);  
            i++;  
        }    
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];    
    } 
    public static void main(String[] sd){
    	 System.out.println(ipStrToLong("127.0.0.1"));
		 System.out.println(iplongToIp(99L));
	 }
    public static String iplongToIp(long ipaddress) {    
        StringBuffer sb = new StringBuffer("");  
        sb.append(String.valueOf((ipaddress >>> 24)));  
        sb.append(".");  
        sb.append(String.valueOf((ipaddress & 0x00FFFFFF) >>> 16));  
        sb.append(".");  
        sb.append(String.valueOf((ipaddress & 0x0000FFFF) >>> 8));  
        sb.append(".");  
        sb.append(String.valueOf((ipaddress & 0x000000FF)));  
        return sb.toString();  
    }  
    public static String iplongToIp(Long ipaddr) { 
    	if(ipaddr==null){
    		return "";
    	}
        long y = ipaddr % 256; 
        long m = (ipaddr - y) / (256 * 256 * 256); 
        long n = (ipaddr - 256 * 256 *256 * m - y) / (256 * 256); 
        long x = (ipaddr - 256 * 256 *256 * m - 256 * 256 *n - y) / 256; 
        return m + "." + n + "." + x + "." + y; 
    } 
}
