/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.interceptors;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zht.framework.util.ConfigUtil;
import org.zht.framework.util.LogUtil;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.log.model.OperationLog;
import com.zht.common.log.service.IOperationLogService;

public class LogInterceptor extends HandlerInterceptorAdapter{
	private final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
	private static final PathMatcher pathMatcher =new AntPathMatcher();
	private static Boolean isPersistToDataBase=null;
	private static String ignorUrl=null;
	private static List<String> ignorUrlList=null;
	@Autowired
	private IOperationLogService operateLogService;
	

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
		if(ex!=null){
			LogUtil.genErrorLog("", ex);
		}
		super.afterCompletion(request, response, handler, ex);
		
	}



	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		init();
		String requestUrl = request.getServletPath();
		if (isIgnorUrl(requestUrl)) {
	         return super.preHandle(request, response, handler);
	     }
		OperationLog  operationLog=LogUtil.genOperationLog(request);
		 if(isPersistToDataBase){
			 if(operationLog!=null){
				 try{
					 operateLogService.$base_save(operationLog);
				 }catch(Exception e){
					 
				 }
			 }
		 }
		return super.preHandle(request, response, handler);
	            
	}
	 private boolean isIgnorUrl(String requestURL) {
	        for (String ignorUrl : ignorUrlList) {
	            if (pathMatcher.match(ignorUrl, requestURL)) {
	                logger.debug("url filter : ignor url list matches : [{}] match [{}] continue", requestURL, ignorUrl);
	                return true;
	            }
	        }
	        logger.debug("url filter : ignor url list not matches : [{}] not match [{}]",requestURL, ignorUrl);
	        return false;
	    }
	private static void init(){
		if(isPersistToDataBase==null){
			String isPersist =ConfigUtil.getConfig("system.properties", "logFilter.isPersistToDataBase", String.valueOf(false));
			isPersistToDataBase=ZStrUtil.convertToBool(isPersist,false);
		}
		if(ignorUrl==null){
			ignorUrl=ConfigUtil.getConfig("system.properties", "logFilter.ignorURL", "");
		}
		if(ignorUrlList==null){
			ignorUrlList=new ArrayList<String>();
			if(ignorUrl!=null&&ignorUrl.trim().length()>0){
				String[] urlArray = ignorUrl.trim().split("[,;\r\n]");
				for(String str:urlArray){
					str=ZStrUtil.trimToNull(str);
					if(str!=null){
						ignorUrlList.add(str);
					}
				}
			}
			
		}
	}
}
