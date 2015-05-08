
/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.filter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.shiro.session.dao.CustomShiroSessionDAO;
/*
 *后期 改成：db3中存储 用户session队列，实时监控，降低系统内存损耗，2015-05-06 zhaohuatai
 */
public class MaxSessionControlFororRedisFilter extends AdviceFilter{

	private  CustomShiroSessionDAO shiroSessionDAO;
	private String loginFaildUrl;
	@Override
	protected void postHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isAuthenticated() && !subject.isRemembered()) {
			  //如果没有登录， 直接进行之后的流程
			  WebUtils.redirectToSavedRequest(request, response, loginFaildUrl); ;
		}
		Session newLonginSession = subject.getSession();
		String username = (String) subject.getPrincipal();
		Serializable newLonginSessionId = newLonginSession.getId();
		
		Collection<Session> activeSessions = shiroSessionDAO.getActiveSessions();
		if(activeSessions!=null&&activeSessions.size()>0){
			for(Session session : activeSessions){
				if(null != session&&ZStrUtil.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)), username)){
					if(!session.getId().equals(newLonginSessionId)){
						shiroSessionDAO.delete(session);
					}
				}
				
			}
		}
		
		super.postHandle(request, response);
	}
	public CustomShiroSessionDAO getShiroSessionDAO() {
		return shiroSessionDAO;
	}
	public void setShiroSessionDAO(CustomShiroSessionDAO shiroSessionDAO) {
		this.shiroSessionDAO = shiroSessionDAO;
	}
	public String getLoginFaildUrl() {
		return loginFaildUrl;
	}
	public void setLoginFaildUrl(String loginFaildUrl) {
		this.loginFaildUrl = loginFaildUrl;
	}

	
	
	

}
