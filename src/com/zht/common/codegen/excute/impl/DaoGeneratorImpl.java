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
import com.zht.common.codegen.excute.DaoGenerator;
import com.zht.common.codegen.genpojoojo.DaoImplModel;
import com.zht.common.codegen.genpojoojo.DaoInterfaceModel;

public class DaoGeneratorImpl extends AbstractGenerator implements DaoGenerator{
	//modules
	public void genDaoInterface(String entityFullClassName){
		DaoInterfaceModel daoModel=new DaoInterfaceModel();
		//实体名
		String entitySimpleName =StringUtils.substringAfterLast(entityFullClassName, ".");
		//路径
		String str=StringUtils.substringBeforeLast(entityFullClassName, ".");
			   str=StringUtils.substringBeforeLast(str, ".");
		String packageName=str+".dao";
	
		daoModel.setPackageName(packageName);
		daoModel.setEntitySimpleClassName(entitySimpleName);
		
		String className="I"+entitySimpleName+"Dao";
		
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("model", daoModel);
	    String filePath=new String(GenConstant.project_path+"src/"+package2path(packageName)+"/"+className+".java");
	    super.generate(GenConstant.daoInterface_template_dir, data, filePath);
	}
	public void genDaoImpl(String entityFullClassName){
		
		DaoImplModel daoModel=new DaoImplModel();
		
		String entitySimpleClassName =StringUtils.substringAfterLast(entityFullClassName, ".");
		
		String path=StringUtils.substringBeforeLast(entityFullClassName, ".");
				path=StringUtils.substringBeforeLast(path, ".");
		   
		String packageName=path+".dao.impl";
		
		String interfacePackageName=path+".dao";
		
		String className=entitySimpleClassName+"DaoImpl";
		
		daoModel.setPackageName(packageName);
		daoModel.setEntitySimpleClassName(entitySimpleClassName);
		daoModel.setInterfacePackageName(interfacePackageName);
		
		 Map<String, Object> data = new HashMap<String, Object>();
	     data.put("model", daoModel);
	     String filePath=new String(GenConstant.project_path+"src/"+package2path(packageName)+"/"+className+".java");
	     super.generate(GenConstant.daoImpl_template_dir, data, filePath);
	}
	public void genAll(String entityFullClassName) {
		genDaoInterface(entityFullClassName);
		genDaoImpl(entityFullClassName);
	}
}
