/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringCtxLoaderFromContextAware  implements ApplicationContextAware{
	private static ApplicationContext context = null;
	private static SpringCtxLoaderFromContextAware stools = null;
	public synchronized static SpringCtxLoaderFromContextAware init(){
	  if(stools == null){
	   stools = new SpringCtxLoaderFromContextAware();
	  }
	  return stools;
	}

	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
	  context = applicationContext;
	}

	public synchronized static Object getBean(String beanName) {
	  return context.getBean(beanName);
	}
}
