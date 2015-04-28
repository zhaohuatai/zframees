/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.zht.framework.util.ConfigUtil;
import org.zht.framework.util.LogUtil;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.log.model.OperationLog;
import com.zht.common.log.service.IOperationLogService;
/**
 * '
 *
 * @ClassName :UserAccessLogFilter     
 * @Description :   
 * @createTime :2015年4月5日上午1:37:13
 * @author zhaohuatai 
 * @version :1.0
 *
 */
public class UserAccessLogFilter implements Filter{
//	private FilterConfig filtetConfig = null;//改为从配置文件读取
	private static final PathMatcher pathMatcher = new AntPathMatcher();
	private final Logger logger = LoggerFactory.getLogger("url.filter");
	
	private static List<String> ignorUrls=new ArrayList<String>();
	private static Boolean isPersistToDataBase;
	private static String ignorUrl;
	@Autowired
	private IOperationLogService operateLogService;
	
	public static void main(String[] sd){
		String sds="aaa;bbb"
				+ "ccc,";
		String[] asdsads=sds.split("");
		for(String ssss:asdsads){
			System.out.println(ssss);
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		doInit(filterConfig);
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestUrl = httpRequest.getServletPath();
		
		 if (isIgnorUrl(requestUrl)) {
			 filterChain.doFilter(request, response);
	         return;
	     }//如果在忽略的配置里，直接跳过
		OperationLog  operationLog=LogUtil.genOperationLog(httpRequest);
		 if(isPersistToDataBase){
			 if(operationLog!=null){
				 try{
					 operateLogService.$base_save(operationLog);
				 }catch(Exception e){
					 
				 }
				
			 }
		 }
		 filterChain.doFilter(request, response);
	}

	 private boolean isIgnorUrl(String requestURL) {
	        for (String ignorUrl : ignorUrls) {
	            if (pathMatcher.match(ignorUrl, requestURL)) {
	                logger.debug("url filter : ignor url list matches : [{}] match [{}] continue", requestURL, ignorUrl);
	                return true;
	            }
	        }
	        logger.debug("url filter : ignor url list not matches : [{}] not match [{}]",requestURL, ignorUrl);
	        return false;
	    }

	private void doInit(FilterConfig filterConfig){
//		this.filtetConfig = filterConfig;//改为从配置文件读取
//		ignorUrl = this.filtetConfig.getInitParameter("ignorUrl");
//		isPersistToDataBase = conversToBoolean(this.filtetConfig.getInitParameter("persistToDataBase"));
		
		String isPersist=ConfigUtil.getConfig("system.properties", "logFilter.isPersistToDataBase", String.valueOf(false));
		isPersistToDataBase=ZStrUtil.convertToBool(isPersist,false);
		ignorUrl=ConfigUtil.getConfig("system.properties", "logFilter.ignorURL", "");		
		if(ignorUrl==null||ignorUrl.trim().length()==0){
			ignorUrls=new ArrayList<String>();
		}
		String[] urlArray = ignorUrl.trim().split("[,;\r\n]");
		for(String str:urlArray){
			str=ZStrUtil.trimToNull(str);
			if(str!=null){
				ignorUrls.add(str);
			}
		}
	}
	@Override
	public void destroy() {}
	
}
