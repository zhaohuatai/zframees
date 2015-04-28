/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zht.framework.consts.GlobleConstant;
import com.zht.common.log.model.OperationLog;

public class LogUtil {
	 public static final Logger operationLog = LoggerFactory.getLogger(GlobleConstant.operation_log);
	
	 public static final Logger errorLog = LoggerFactory.getLogger(GlobleConstant.error_log);
	 private static Boolean isPersistToDataBase=ZStrUtil.convertToBool(ConfigUtil.getConfig("system.properties", "logFilter.isPersistToDataBase", String.valueOf(false)),false);


	 public static OperationLog genOperationLog(HttpServletRequest request) {
		 	String userName = (String) SecurityUtils.getSubject().getPrincipal();
		 	if(userName==null){
		 		userName="anonymous";
		 	}
	        String jsessionId = request.getRequestedSessionId();
	        String remoteIpAddr = IpUtil.getClientIpAddr(request);
	        String accept = request.getHeader("accept");
	        String userAgent = request.getHeader("User-Agent");//操作系统（包括版本号）浏览器（包括版本号）和用户个人偏好的代码。
	        String requestURI = request.getRequestURI();
	        String methed=request.getMethod();
	        String params = RequestUtil.getRequestParams(request);
	        String referer=request.getHeader("Referer");
	        
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append(genLogElement("userName:"+userName));
	        stringBuilder.append(genLogElement("jsessionId:"+jsessionId));
	        stringBuilder.append(genLogElement("requestURI:"+requestURI));
	        stringBuilder.append(genLogElement("methed:"+methed));
	        stringBuilder.append(genLogElement("params:"+params));
	        stringBuilder.append(genLogElement("remoteIpAddr:"+remoteIpAddr));
	        stringBuilder.append(genLogElement("accept:"+accept));
	        stringBuilder.append(genLogElement("userAgent:"+userAgent));
	        stringBuilder.append(genLogElement("referer:"+referer));
	        
//	        stringBuilder.append(genLogElement(userName));
//	        stringBuilder.append(genLogElement(jsessionId));
//	        stringBuilder.append(genLogElement(requestURI));
//	        stringBuilder.append(genLogElement(methed));
//	        stringBuilder.append(genLogElement(params));
//	        stringBuilder.append(genLogElement(remoteIpAddr));
//	        stringBuilder.append(genLogElement(accept));
//	        stringBuilder.append(genLogElement(userAgent));
//	        stringBuilder.append(genLogElement(referer));
	        operationLog.info(stringBuilder.toString());
	        if(isPersistToDataBase){
	        	OperationLog log=new OperationLog( 
	        					""+userName,""+jsessionId,
	        					   IpUtil.ipStrToLong(remoteIpAddr),
	        					""+accept,""+userAgent,""+requestURI,
	        					""+methed,  ""+params, ""+referer,
	        					OperationLog.TYPE_OPERATION);
	        	return log;
	        }
	        return null;
	 }
	  
	  public static void  genErrorLog(HttpServletRequest request,String message, Throwable e) {
		 	String userName = (String) SecurityUtils.getSubject().getPrincipal();
		 	if(userName==null){
		 		userName="anonymous";
		 	}
	        String jsessionId = request.getRequestedSessionId();
	        String remoteIpAddr = IpUtil.getClientIpAddr(request);
	        String accept = request.getHeader("accept");
	        String userAgent = request.getHeader("User-Agent");//操作系统（包括版本号）浏览器（包括版本号）和用户个人偏好的代码。
	        String requestURI = request.getRequestURI();
	        String methed=request.getMethod();
	        String params = RequestUtil.getRequestParams(request);
	        String referer=request.getHeader("Referer");
	        
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append(genLogElement("userName:"+userName));
	        stringBuilder.append(genLogElement("jsessionId:"+jsessionId));
	        stringBuilder.append(genLogElement("requestURI:"+requestURI));
	        stringBuilder.append(genLogElement("methed:"+methed));
	        stringBuilder.append(genLogElement("params:"+params));
	        stringBuilder.append(genLogElement("remoteIpAddr:"+remoteIpAddr));
	        stringBuilder.append(genLogElement("accept:"+accept));
	        stringBuilder.append(genLogElement("userAgent:"+userAgent));
	        stringBuilder.append(genLogElement("referer:"+referer));
	        
	        stringBuilder.append(genLogElement("exception"));
	        stringBuilder.append(genLogElement(message));
	        errorLog.error(stringBuilder.toString(), e);
	    }
	  public static void  genErrorLog(String message, Throwable e) {
		   String userName = (String) SecurityUtils.getSubject().getPrincipal();
		   if(userName==null){
		 		userName="anonymous";
		 	}
	        StringBuilder stringBuilder = new StringBuilder();
	        stringBuilder.append(genLogElement(userName));
	        
	        stringBuilder.append(genLogElement("exception"));
	        stringBuilder.append(genLogElement(message));
	        errorLog.error(stringBuilder.toString(), e);
	    }
	  
	 public static void main(String[] sd){
		 System.out.println(isPersistToDataBase);
	 }
	
	 
	private static String genLogElement(Object msg) {
		if (msg == null) {
			msg = "";
		}
		return "[" + msg.toString() + "]";
	}
}
