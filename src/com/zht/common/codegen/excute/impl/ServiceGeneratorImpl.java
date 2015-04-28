/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.excute.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zht.common.codegen.constant.GenConstant;
import com.zht.common.codegen.excute.ServiceGenerator;
import com.zht.common.codegen.genpojoojo.ServiceImplModel;
import com.zht.common.codegen.genpojoojo.ServiceInterfaceModel;

public class ServiceGeneratorImpl extends AbstractGenerator implements ServiceGenerator{
	@Override
	public void genServiceInterface(String entityFullClassName){
		
		ServiceInterfaceModel serviceModel=new ServiceInterfaceModel();
		//实体名
		String entitySimpleName =StringUtils.substringAfterLast(entityFullClassName, ".");
		//路径
		String str=StringUtils.substringBeforeLast(entityFullClassName, ".");
			   str=StringUtils.substringBeforeLast(str, ".");
		String packageName=str+".service";
	
		serviceModel.setPackageName(packageName);
		serviceModel.setEntitySimpleClassName(entitySimpleName);
		serviceModel.setEntityFullClassName(entityFullClassName);
		
		String className="I"+entitySimpleName+"Service";
		
		 Map<String, Object> data = new HashMap<String, Object>();
	     data.put("model", serviceModel);
	     String filePath=new String(GenConstant.project_path+"src/"+package2path(packageName)+"/"+className+".java");
	     super.generate(GenConstant.serviceInterface_template_dir, data, filePath);
	}
	@Override
	public void genServiceImpl(String entityFullClassName){
		
		ServiceImplModel serviceModel=new ServiceImplModel();
		
		String entitySimpleClassName =StringUtils.substringAfterLast(entityFullClassName, ".");
		
		String path=StringUtils.substringBeforeLast(entityFullClassName, ".");
				path=StringUtils.substringBeforeLast(path, ".");
		   
		String packageName=path+".service.impl";
		
		String interfacePackageName=path+".service";
		
		String className=entitySimpleClassName+"ServiceImpl";
		
		String daoPackageName=path+".dao";
		
		serviceModel.setPackageName(packageName);
		serviceModel.setEntitySimpleClassName(entitySimpleClassName);
		serviceModel.setInterfacePackageName(interfacePackageName);
		serviceModel.setEntityFullClassName(entityFullClassName);
		serviceModel.setDaoPackageName(daoPackageName);
		 Map<String, Object> data = new HashMap<String, Object>();
	     data.put("model", serviceModel);
	     String filePath=new String(GenConstant.project_path+"src/"+package2path(packageName)+"/"+className+".java");
	     super.generate(GenConstant.serviceImpl_template_dir, data, filePath);
	}
	
	
	public void genAll(String entityFullClassName) {
		genServiceInterface(entityFullClassName);
		genServiceImpl(entityFullClassName);
	}
	
}
