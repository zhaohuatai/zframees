/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.excute.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zht.common.codegen.constant.GenConstant;
import com.zht.common.codegen.excute.HiberModelgenerator;
import com.zht.common.codegen.genpojoojo.HIberEntityModel;
import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.model.GenEntityProperty;

public class HiberModelgeneratorImpl extends AbstractGenerator implements HiberModelgenerator{

	@Override
	public void generatorModel(String entityFullClassName,GenEntity genEntity, List<GenEntityProperty> genEntityPropertyList) {
		
		HIberEntityModel hiberModel=new HIberEntityModel();
		//实体名
		String entitySimpleName =StringUtils.substringAfterLast(entityFullClassName, ".");
		//路径
		String str=StringUtils.substringBeforeLast(entityFullClassName, ".");
			   str=StringUtils.substringBeforeLast(str, ".");
		String packageName=str+".model";
		
		hiberModel.setPackageName(packageName);
		hiberModel.setEntitySimpleClassName(entitySimpleName);
		hiberModel.setEntityFullClassName(entityFullClassName);
		
		hiberModel.setGenEntity(genEntity);
		
		hiberModel.setGenEntityPropertyList(genEntityPropertyList);
		
		String className=entitySimpleName;
		
		 Map<String, Object> data = new HashMap<String, Object>();
	     data.put("model", hiberModel);
	     String filePath=new String(GenConstant.project_path+"src/"+package2path(packageName)+"/"+className+".java");
	     super.generate(GenConstant.hiberModel_template_dir, data, filePath);
	}

}
