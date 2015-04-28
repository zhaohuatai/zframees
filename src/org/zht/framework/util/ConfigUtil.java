/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.util;

import java.util.Properties;

public class ConfigUtil {

	/**
	 * 获取配置文件信息
	 * 
	 * @param config
	 * @return 若不包含该属性，则返回为null
	 */
	public static String getConfig(String config) {
		return getConfig(config, "application.properties");
	}

	public static String getConfig(String configfile,String config ) {
		java.io.InputStream inputstream = ConfigUtil.class.getResourceAsStream("/" + configfile);
		Properties properties = new Properties();
		try {
			properties.load(inputstream);
		} catch (Exception e) {
			e.printStackTrace();

		}
		String s = properties.getProperty(config, null);
		return s;
	}
	public static String getConfig(String configfile,String configName, String defaultValue) {
		java.io.InputStream inputstream = ConfigUtil.class.getResourceAsStream("/" + configfile);
		Properties properties = new Properties();
		try {
			properties.load(inputstream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String s = properties.getProperty(configName, defaultValue);
		return s;
	}
	public static void main(String[] args) {

	}
}
