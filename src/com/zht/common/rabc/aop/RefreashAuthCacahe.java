/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.rabc.aop;
import java.lang.annotation.*;
/**
 * AOP 注解，rbac部分改动时 重新加载shiro认证授权信息 
* @ClassName :RefreashAuthCacahe     
* @Description :   rbac部分改动时 重新加载shiro认证授权信息 
* @createTime :2015年4月7日  下午5:50:56   
* @author ：zhaohuatai   
* @version :1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface RefreashAuthCacahe {

}
