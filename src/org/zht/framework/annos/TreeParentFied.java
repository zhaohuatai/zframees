/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.annos;

import java.lang.annotation.Documented;  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target; 

@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到  
@Target({ElementType.FIELD,ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法  
@Documented//说明该注解将被包含在javadoc中  
/**
 * 如果是树形结构添加本注解，baseServce 保存时，自动校验树形结构
 *
 * @ClassName :TreeParentFied     
 * @Description :   
 * @createTime :2015年4月5日上午1:32:04
 * @author zhaohuatai 
 * @version :1.0
 *
 */
public @interface  TreeParentFied {
}
