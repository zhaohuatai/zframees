/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFactoryContextHolder {
	protected final Logger log = LoggerFactory.getLogger(SessionFactoryContextHolder.class);
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static void setSessionFactoryName(String sessionFactoryName) {
		contextHolder.set(sessionFactoryName);
	}

	public static String getSessionFactoryName() {
		return contextHolder.get();
	}

	public static void clearSessionFactoryName() {
		contextHolder.remove();
	}
}