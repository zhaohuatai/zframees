/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.annos;
import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target; 
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 
 *
 * @ClassName :RepeatToken     
 * @Description :  在需要防止重复提交的位置添加本注解，通过springmvc的interceptor完成
 * @createTime :2015年4月5日上午1:28:45
 * @author zhaohuatai  
 * @version :1.0
 *
 */
public @interface RepeatToken {

}
