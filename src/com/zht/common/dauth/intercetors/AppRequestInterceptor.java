/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.dauth.intercetors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zht.common.dauth.thread.CurrentReqestInfo;
import com.zht.common.dauth.thread.RequestTLUtil;
import com.zht.common.dauth.thread.RequestThreadLocal;
/**
 * springmvc拦截器，实现队请求的拦截，将请求信息放入RequestThreadLocal 中
 * @author zhaohuatai
 *
 */
public class AppRequestInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
		super.afterCompletion(request, response, handler, ex);
		try{
			RequestThreadLocal.clearCurrentReqestInfo();
		}catch(Exception e){}
		
	}

	@Override
	public void postHandle(HttpServletRequest request,	HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		try{
			RequestThreadLocal.clearCurrentReqestInfo();
		}catch(Exception e){}
		
	}
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		 if (handler instanceof HandlerMethod) {
	            HandlerMethod handlerMethod = (HandlerMethod) handler;
	            Method method = handlerMethod.getMethod();
	            String clazzName=method.getClass().getName();
	            String requestMethedFullName=""+method.getName()+"@"+clazzName;
	            String rquestPermissionCode="";
	            String questURI=""+request.getRequestURI();
	            RequiresPermissions requiresPermissions= method.getAnnotation(RequiresPermissions.class);
	            if (requiresPermissions != null) {
	            	 String[] permissionCodes=requiresPermissions.value(); 
	            	 if(permissionCodes!=null&&permissionCodes.length>0){
	            		 rquestPermissionCode=permissionCodes[0];
	            	 }
	            }
	            CurrentReqestInfo requestInfo=new CurrentReqestInfo(questURI,rquestPermissionCode,requestMethedFullName);
	            RequestTLUtil.setCurrentReqestInfo(requestInfo);
	            return true;
	        } else {
	            return super.preHandle(request, response, handler);
	        }
		
	}

}
