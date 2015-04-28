/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.zht.framework.spring.SpringUtils;

import com.zht.common.shiro.ShiroDbRealm;
/**
 * rbac部分改动时 重新加载shiro认证授权信息 
* @ClassName :RefreashShiroCacahe     
* @Description :  rbac部分改动时 重新加载shiro认证授权信息  
* @createTime :2015年4月7日  下午5:51:50   
* @author ：zhaohuatai   
* @version :1.0
 */
@Aspect  
@Component 
public class RefreashShiroCacahe {
	
	 @Pointcut("@annotation(com.zht.common.rabc.aop.RefreashAuthCacahe)")  
//	 @Pointcut("execution(* com.zht.common.rabc.*(..))")  
     public  void controllerAspect() { } 
	 
	 @After(value = "controllerAspect()")
	 public void excuteRefreash(JoinPoint jp){  
		SpringUtils.getBean(ShiroDbRealm.class).clearAllCachedAuthorizationInfo();  
	 }
	 
//	 	@Before("controllerAspect() && args(name)")  
//	    public void doAccessCheck(String name){  
//	        System.out.println(name);  
//	        System.out.println("前置通知");  
//	    }  
//	      
//	    @AfterReturning("controllerAspect()")  
//	    public void doAfter(){  
//	        System.out.println("后置通知");  
//	    }  
//	      
//	    @After("controllerAspect()")  
//	    public void after(){  
//	        System.out.println("最终通知");  
//	    }  
//	      
//	    @AfterThrowing("controllerAspect()")  
//	    public void doAfterThrow(){  
//	        System.out.println("例外通知");  
//	    }  
//	      
//	    @Around("controllerAspect()")  
//	    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
//	        System.out.println("进入环绕通知");  
//	        Object object = pjp.proceed();//执行该方法  
//	        System.out.println("退出方法");  
//	        return object;  
//	    }  
}
