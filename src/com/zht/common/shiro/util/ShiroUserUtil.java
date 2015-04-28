/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.shiro.util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.zht.common.shiro.ShiroSessionUser;
/**
 * 
* @ClassName :ShiroUserUtil     
* @Description :   
* @createTime :2015年4月3日  下午4:18:41   
* @author ：zhaohuatai   
* @version :1.0
 */
public class ShiroUserUtil {
	public static final String SHIROSESSIONUSER="_SHIROSESSIONUSER_";
	
	
	public static String  getUserName(){
		Subject currentUser = SecurityUtils.getSubject();
		PrincipalCollection collection = currentUser.getPrincipals();
		if (null != collection && !collection.isEmpty()) {
			return (String) collection.iterator().next();
		}
		return null;
	}
	
	public static Session getShiroSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	public static ShiroSessionUser  getShiroSessionUser(){
		ShiroSessionUser shiroSessionUser = (ShiroSessionUser) SecurityUtils.getSubject().getSession().getAttribute(ShiroUserUtil.SHIROSESSIONUSER);
		if(shiroSessionUser!=null){
			return shiroSessionUser;
		}
		return null;
	}
	public static String  getCurrentRoleCode(){
		ShiroSessionUser shiroSessionUser = (ShiroSessionUser) SecurityUtils.getSubject().getSession().getAttribute(ShiroUserUtil.SHIROSESSIONUSER);
		if(shiroSessionUser!=null){
			return shiroSessionUser.getCurrentRoleCode();
		}
		return null;
	}
	
	public static Long  getCurrentRoleId(){
		ShiroSessionUser shiroSessionUser = (ShiroSessionUser) SecurityUtils.getSubject().getSession().getAttribute(ShiroUserUtil.SHIROSESSIONUSER);
		if(shiroSessionUser!=null){
			return shiroSessionUser.getCurrentRoleId();
		}
		return null;
	}
	public static Long  getCurrentDeptId(){
		ShiroSessionUser shiroSessionUser = (ShiroSessionUser) SecurityUtils.getSubject().getSession().getAttribute(ShiroUserUtil.SHIROSESSIONUSER);
		if(shiroSessionUser!=null){
			return shiroSessionUser.getCurrentDeptId();
		}
		return null;
	}
	public static Long  getCurrentGroupId(){
		ShiroSessionUser shiroSessionUser = (ShiroSessionUser) SecurityUtils.getSubject().getSession().getAttribute(ShiroUserUtil.SHIROSESSIONUSER);
		if(shiroSessionUser!=null){
			return shiroSessionUser.getCurrentGroupId();
		}
		return null;
	}
}
