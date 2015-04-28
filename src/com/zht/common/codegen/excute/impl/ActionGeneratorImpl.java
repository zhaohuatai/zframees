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
import com.zht.common.codegen.excute.ActionGenerator;
import com.zht.common.codegen.genpojoojo.ActionModel;
import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.model.GenEntityProperty;

public class ActionGeneratorImpl extends AbstractGenerator implements ActionGenerator{
	
	@Override
	public void generatorAction(String entityFullClassName,String controllerNameSpace,GenEntity genEntity,List<GenEntityProperty> genEntityPropertyList){
		
		ActionModel actionModel=new ActionModel();
		//实体名
		String entitySimpleName =StringUtils.substringAfterLast(entityFullClassName, ".");
		//路径
		String str=StringUtils.substringBeforeLast(entityFullClassName, ".");
			   str=StringUtils.substringBeforeLast(str, ".");
		String packageName=str+".web";
		String servicePackageName=str+".service";
		
		actionModel.setPackageName(packageName);
		actionModel.setEntitySimpleClassName(entitySimpleName);
		actionModel.setServicePackageName(servicePackageName);
		actionModel.setEntityFullClassName(entityFullClassName);
		actionModel.setControllerNameSpace(controllerNameSpace);
		
		actionModel.setGenEntityPropertyList(genEntityPropertyList);
		actionModel.setGenEntity(genEntity);
		
		String className=entitySimpleName+"Controller";
		
		 Map<String, Object> data = new HashMap<String, Object>();
	     data.put("model", actionModel);
	     String filePath=new String(GenConstant.project_path+"src/"+package2path(packageName)+"/"+className+".java");
	    if(genEntity==null||genEntityPropertyList==null){
	    	 super.generate(GenConstant.action_template_just_from_main_dir, data, filePath);
	    }else{
	    	 super.generate(GenConstant.action_template_dir, data, filePath);
	    }
	}

}
