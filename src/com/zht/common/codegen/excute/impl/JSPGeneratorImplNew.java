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
import org.zht.framework.util.ZStrUtil;

import com.zht.common.codegen.constant.GenConstant;
import com.zht.common.codegen.excute.JSPGeneratorNew;
import com.zht.common.codegen.genpojoojo.JSPModelNew;
import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.model.GenEntityProperty;
public class JSPGeneratorImplNew extends AbstractGenerator implements JSPGeneratorNew{

	@Override
	public void genAll(String entityFullClassName,String controllerNameSpace, GenEntity genEntity,List<GenEntityProperty> genEntityPropertyList) {
		genjsp_list(entityFullClassName, controllerNameSpace, genEntity, genEntityPropertyList);
		
	}

	@Override
	public void genjsp_list(String entityFullClassName,
			String controllerNameSpace, GenEntity genEntity,
			List<GenEntityProperty> genEntityPropertyList) {
		JSPModelNew jSPModel=new JSPModelNew();
		//实体名
		String entitySimpleClassName =StringUtils.substringAfterLast(entityFullClassName, ".");
		//路径
		String str=StringUtils.substringBeforeLast(entityFullClassName, ".");
			   str=StringUtils.substringBeforeLast(str, ".");
		String firstLower =ZStrUtil.toLowerCaseFirst(entitySimpleClassName);
		jSPModel.setControllerNameSpace(controllerNameSpace);
		jSPModel.setEntityFullClassName(entityFullClassName);
		jSPModel.setEntitySimpleClassName(entitySimpleClassName);
		jSPModel.setGenEntity(genEntity);
		jSPModel.setGenEntityPropertyList(genEntityPropertyList);
		
		
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("model", jSPModel);
	    String filePath=new String(GenConstant.project_path+"WebRoot/WEB-INF/jsp/"+controllerNameSpace+"/"+firstLower+"DataGrid.jsp");
	    if(true==(genEntity.getIsTree())){
	    	  filePath=new String(GenConstant.project_path+"WebRoot/WEB-INF/jsp/"+controllerNameSpace+"/"+firstLower+"TreeGrid.jsp");
	    	  super.generate(GenConstant.jsp_list_treeGrid_template_dir, data, filePath);
	    }else{
	    	super.generate(GenConstant.jsp_list_dataGrid_template_dir, data, filePath);
	    }
	    
	}

	@Override
	public void genjsp_add(String entityFullClassName,
			String controllerNameSpace, GenEntity genEntity,
			List<GenEntityProperty> genEntityPropertyList) {
		JSPModelNew jSPModel=new JSPModelNew();
		//实体名
		String entitySimpleClassName =StringUtils.substringAfterLast(entityFullClassName, ".");
		//路径
		String str=StringUtils.substringBeforeLast(entityFullClassName, ".");
			   str=StringUtils.substringBeforeLast(str, ".");
		String firstLower =ZStrUtil.toLowerCaseFirst(entitySimpleClassName);
		jSPModel.setControllerNameSpace(controllerNameSpace);
		jSPModel.setEntityFullClassName(entityFullClassName);
		jSPModel.setEntitySimpleClassName(entitySimpleClassName);
		jSPModel.setGenEntity(genEntity);
		jSPModel.setGenEntityPropertyList(genEntityPropertyList);
		
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("model", jSPModel);
	    String filePath=new String(GenConstant.project_path+"WebRoot/WEB-INF/jsp/"+controllerNameSpace+"/"+firstLower+"Add.jsp");
	    super.generate(GenConstant.jsp_add_template_dir, data, filePath);
		
	}

	@Override
	public void genjsp_update(String entityFullClassName,
			String controllerNameSpace, GenEntity genEntity,
			List<GenEntityProperty> genEntityPropertyList) {
		JSPModelNew jSPModel=new JSPModelNew();
		//实体名
		String entitySimpleClassName =StringUtils.substringAfterLast(entityFullClassName, ".");
		//路径
		String str=StringUtils.substringBeforeLast(entityFullClassName, ".");
			   str=StringUtils.substringBeforeLast(str, ".");
		String firstLower =ZStrUtil.toLowerCaseFirst(entitySimpleClassName);
		jSPModel.setControllerNameSpace(controllerNameSpace);
		jSPModel.setEntityFullClassName(entityFullClassName);
		jSPModel.setEntitySimpleClassName(entitySimpleClassName);
		jSPModel.setGenEntity(genEntity);
		jSPModel.setGenEntityPropertyList(genEntityPropertyList);
		
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("model", jSPModel);
	    String filePath=new String(GenConstant.project_path+"WebRoot/WEB-INF/jsp/"+controllerNameSpace+"/"+firstLower+"Update.jsp");
	    super.generate(GenConstant.jsp_update_template_dir, data, filePath);
		
	}

	@Override
	public void genjsp_listForLookUp(String entityFullClassName,
			String controllerNameSpace, GenEntity genEntity,
			List<GenEntityProperty> genEntityPropertyList) {
		// TODO Auto-generated method stub
		
	}


	
	
}
