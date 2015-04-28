/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.excute.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zht.common.codegen.excute.Generator;
import com.zht.common.codegen.util.GenConfigurationHelper;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class AbstractGenerator implements Generator {

	/**
	 * 
	 */
	public void generate(String templateFileName, Map<?,?> data,String fileName) {
		try {
			String templateFileDir=templateFileName.substring(0, templateFileName.lastIndexOf("/"));
			String templateFile=templateFileName.substring(templateFileName.lastIndexOf("/")+1, templateFileName.length());
			
			String genFileDir=fileName.substring(0, fileName.lastIndexOf("/"));
	        Template template = GenConfigurationHelper.getConfiguration(templateFileDir).getTemplate(templateFile);
	        template.setEncoding("UTF-8");
	        org.apache.commons.io.FileUtils.forceMkdir(new File(genFileDir));
	        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8"));
	       // File output = new File(fileName);
	        //Writer writer = new FileWriter(output);
	        template.process(data, writer);
	        writer.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *包名转化为文件路径
	 * @param packageName
	 * @return
	 */
	protected String package2path(String packageName) {
		return packageName.replace('.', '/');
	}
	
	/**
	 * 
	 * @param filePath
	 * @return
	 */
	protected String  getFileName(String filePath) {
		String fileName=StringUtils.substringAfterLast(filePath, "/");
		 if(fileName.equals("")||fileName==null){
			 fileName=StringUtils.substringAfterLast(filePath, "\\");
		 }
		 return fileName;
	}

	/**
	 * 首字母大写
	 * @param string
	 * @return
	 */
	protected String capFirst(String string) {
		String s = String.valueOf(string.charAt(0)).toUpperCase();
		s = s + string.substring(1);
		return s;
	}

	/**
	 * 首字母变小写
	 * @param string
	 * @return
	 */
	protected String uncapFirst(String string) {
		String s = String.valueOf(string.charAt(0)).toLowerCase();
		s = s + string.substring(1);
		return s;
	}
}
