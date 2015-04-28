/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	protected final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

	public static void setDataSourceName(String dataSourceName) {
		contextHolder.set(dataSourceName);
	}

	public static String getDataSourceName() {
		return contextHolder.get();
	}

	public static void clearDataSourceName() {
		contextHolder.remove();
	}
}