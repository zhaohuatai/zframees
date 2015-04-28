/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.util;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import freemarker.template.Configuration;

public class GenConfigurationHelper {

	private static Configuration cfg = null;

	public static Configuration getConfiguration(String templateDir) throws IOException {
		if (null == cfg) {
			cfg = new Configuration();
	        File templateDirFile = new File(templateDir);
	        cfg.setDirectoryForTemplateLoading(templateDirFile);
	        cfg.setLocale(Locale.CHINA);
//	        cfg.setDefaultEncoding("GBK");
	        cfg.setDefaultEncoding("UTF-8");
		}
		return cfg;
	}
}
