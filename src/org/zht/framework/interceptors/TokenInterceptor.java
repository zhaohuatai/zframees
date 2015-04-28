/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.interceptors;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.zht.framework.annos.RepeatToken;


public class TokenInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
		super.afterCompletion(request, response, handler, ex);
		HttpSession session=request.getSession(false);
		if(session!=null){
			String seesionId=session.getId();
	    	String uri=request.getRequestURI();
			session.removeAttribute("_Token"+seesionId+uri);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,	HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session=request.getSession(false);
		if(session!=null){
			String seesionId=session.getId();
	    	String uri=request.getRequestURI();
			session.removeAttribute("_Token"+seesionId+uri);
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		 if (handler instanceof HandlerMethod) {
	            HandlerMethod handlerMethod = (HandlerMethod) handler;
	            Method method = handlerMethod.getMethod();
	            RepeatToken annotation = method.getAnnotation(RepeatToken.class);
	            if (annotation != null) {
	            	HttpSession session=request.getSession(false);
	            	if(session==null){
	            		return true;
	            	}
	            	String seesionId=session.getId();
	            	String uri=request.getRequestURI();
	            	
	            	Boolean isPosted=(Boolean) session.getAttribute("_Token"+seesionId+uri);
	            	if(isPosted==null||isPosted==false){
	            		session.setAttribute("_Token"+seesionId+uri, true);
	            		return true;
	            	}else {
	            		//此处需要特殊处理
	            		return false;
	            	}
	            }
	            return true;
	        } else {
	            return super.preHandle(request, response, handler);
	        }
		
	}

}
