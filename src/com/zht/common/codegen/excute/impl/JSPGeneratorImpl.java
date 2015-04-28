/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.excute.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zht.framework.util.ZStrUtil;

import com.zht.common.codegen.constant.GenConstant;
import com.zht.common.codegen.excute.JSPGenerator;
import com.zht.common.codegen.genpojoojo.JSPModel;
public class JSPGeneratorImpl extends AbstractGenerator implements JSPGenerator{
	
	@Override
	public void genAll(String WEBINFPath,String nameSpace, String entityClassName,List<Map<String,String>> fieldListMap) {
		genjsp_list(WEBINFPath,nameSpace, entityClassName, fieldListMap);
		genjsp_update(WEBINFPath,nameSpace,entityClassName,fieldListMap);
		genjsp_add(WEBINFPath,nameSpace, entityClassName,fieldListMap);
		genjsp_listForLookUp(WEBINFPath,nameSpace, entityClassName, fieldListMap);
	}
	
	@Override
	public void genjsp_list(String WEBINFPath,String actionNameSpace,String entityClassName,List<Map<String,String>> fieldListMap){
		JSPModel jSPModel=new JSPModel();
		String firstLower =ZStrUtil.toLowerCaseFirst(entityClassName);
		jSPModel.setStr1("${");
		jSPModel.setStr2("}");
		jSPModel.setEntityClassName(entityClassName);
		jSPModel.setFieldListMap(fieldListMap);
		List<Map<String,String>> Paramlist=getQueyParamlist(fieldListMap);
		if(Paramlist!=null&&Paramlist.size()>0){
			
			jSPModel.setQueyParamlist(Paramlist);
		}
		jSPModel.setActionNameSpace(actionNameSpace);
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("model", jSPModel);
	    String filePath=new String(GenConstant.project_path+"WebRoot/WEB-INF/jsp/"+actionNameSpace+"/"+firstLower+"List.jsp");
	    super.generate(GenConstant.jsp_list_dataGrid_template_dir, data, filePath);
	}

	@Override
	public void genjsp_add(String WEBINFPath,String actionNameSpace, String entityClassName,List<Map<String,String>> fieldListMap) {
		JSPModel jSPModel=new JSPModel();
		String firstLower =ZStrUtil.toLowerCaseFirst(entityClassName);
		jSPModel.setStr1("${");
		jSPModel.setStr2("}");
		jSPModel.setEntityClassName(entityClassName);
		jSPModel.setFieldListMap(fieldListMap);
		jSPModel.setActionNameSpace(actionNameSpace);
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("model", jSPModel);
	    String filePath=new String(GenConstant.project_path+"WebRoot/WEB-INF/jsp/"+actionNameSpace+"/"+firstLower+"Add.jsp");
	    super.generate(GenConstant.jsp_add_template_dir, data, filePath);
		
	}

	@Override
	public void genjsp_update(String WEBINFPath,String actionNameSpace, String entityClassName,List<Map<String,String>> fieldListMap) {
		JSPModel jSPModel=new JSPModel();
		String firstLower =ZStrUtil.toLowerCaseFirst(entityClassName);
		jSPModel.setStr1("${");
		jSPModel.setStr2("}");
		jSPModel.setEntityClassName(entityClassName);
		jSPModel.setFieldListMap(fieldListMap);
		jSPModel.setActionNameSpace(actionNameSpace);
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("model", jSPModel);
	    String filePath=new String(GenConstant.project_path+"WebRoot/WEB-INF/jsp/"+actionNameSpace+"/"+firstLower+"Update.jsp");
	    super.generate(GenConstant.jsp_update_template_dir, data, filePath);
		
	}
	
	
	
	@Override
	public void genjsp_listForLookUp(String WEBINFPath,String actionNameSpace,String entityClassName,List<Map<String,String>> fieldListMap){
		JSPModel jSPModel=new JSPModel();
		String firstLower =ZStrUtil.toLowerCaseFirst(entityClassName);
		jSPModel.setStr1("${");
		jSPModel.setStr2("}");
		jSPModel.setEntityClassName(entityClassName);
		jSPModel.setFieldListMap(fieldListMap);
		List<Map<String,String>> Paramlist=getQueyParamlist(fieldListMap);
		if(Paramlist!=null&&Paramlist.size()>0){
			
			jSPModel.setQueyParamlist(Paramlist);
		}
		String bringBackValue=getLookUpBringBackValue(fieldListMap);
		if(bringBackValue!=null){
			jSPModel.setBringBackValue(bringBackValue);
		}
		String bringBackDispaly=getLookUpBringBackDispaly(fieldListMap);
		if(bringBackValue!=null){
			jSPModel.setBringBackDispaly(bringBackDispaly);
		}
		jSPModel.setActionNameSpace(actionNameSpace);
		Map<String, Object> data = new HashMap<String, Object>();
	    data.put("model", jSPModel);
	    String filePath=new String(GenConstant.project_path+"WebRoot/WEB-INF/jsp/"+actionNameSpace+"/"+firstLower+"ListForLookUp.jsp");
	    super.generate(GenConstant.jsp_listforlookup_template_dir, data, filePath);
	}
	
	private List<Map<String,String>> getQueyParamlist(List<Map<String, String>> list){
		if(list==null||list.size()==0){
			return null;
		}
		List<Map<String, String>> queyParamlist=new ArrayList<Map<String,String>>();
    	for(Map<String, String> map:list){
    		String isQuerypram= (String)map.get("isQuerypram");
    		if(isQuerypram!=null&&"true".equals(isQuerypram)){
    			queyParamlist.add(map);
    		}
    	}
    	
    	return queyParamlist;
	}
	
	
	private String getLookUpBringBackDispaly(List<Map<String, String>> list){
		if(list==null||list.size()==0){
			return null;
		}
		for(Map<String, String> map:list){
    		String bringBackDispaly= (String)map.get("bringBackDispaly");
    		if(bringBackDispaly!=null&&!"".equals(bringBackDispaly)){
    			return bringBackDispaly;
    		}
    	}
    	return null;
	}
	private String getLookUpBringBackValue(List<Map<String, String>> list){
		if(list==null||list.size()==0){
			return null;
		}
    	for(Map<String, String> map:list){
    		String bringBackValue= (String)map.get("bringBackValue");
    		if(bringBackValue!=null&&!"".equals(bringBackValue)){
    			return bringBackValue;
    		}
    	}
    	
    	return null;
	}
	
}
