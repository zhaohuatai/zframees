/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package org.zht.framework.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.zht.framework.data.ParamObject;
import org.zht.framework.exception.ParameterException;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.message.DJSONMessage;
import org.zht.framework.util.LogUtil;
import org.zht.framework.validate.ValidateHandler;

import com.zht.common.shiro.exception.ShiroSessionExpiredException;
public class CommonPageBaseController {


	
	//--------------------------------------------------AAAA----------------------------------------------
	protected static void setPageInfoAttribute(Model model, ParamObject paramObject) {
		if(model==null||paramObject==null){
			return;
		}
		model.addAttribute("webParams",paramObject.getWebParams()).addAttribute("paramObject",paramObject);
	}
	
	protected static void setDataAttribute(Model model, List<?> dataList,String dataName,Long totalCount,String countNnme) {
		if(model==null||dataList==null||dataName==null){
			return;
		}
		model.addAttribute(dataName,dataList).addAttribute(countNnme,totalCount==null?0L:totalCount);
	}
	
	protected static void setDataAttribute(Model model, List<?> dataList,String dataName,Long totalCount) {
		if(model==null||dataList==null||dataName==null){
			return;
		}
		model.addAttribute(dataName,dataList).addAttribute("totalCount",totalCount==null?0L:totalCount);
	}
	/**
	 * 
	 * @param model
	 * @param data
	 * @param dataName
	 */
	protected static void setDataAttribute(Model model, Object data,String dataName) {
		if(model==null||data==null||dataName==null){
			return;
		}
		model.addAttribute(dataName,data);
	}
	
	protected static void setDataAttributeAndPageInfo(Model model, List<?> dataList,String dataName,Long totalCount,String countNnme,ParamObject paramObject) {
		if(model==null||dataList==null||dataName==null||paramObject==null){
			return;
		}
		model.addAttribute(dataName,dataList).addAttribute(countNnme,totalCount==null?0L:totalCount);
		model.addAttribute("webParams",paramObject.getWebParams()).addAttribute("paramObject",paramObject);
	}
	
	protected static void setDataAttributeAndPageInfo(Model model, List<?> dataList,String dataName,Long totalCount,ParamObject paramObject) {
		if(model==null||dataList==null||dataName==null||paramObject==null){
			return;
		}
		model.addAttribute(dataName,dataList).addAttribute("totalCount",totalCount==null?0L:totalCount);
		model.addAttribute("webParams",paramObject.getWebParams()).addAttribute("paramObject",paramObject);
	}
	
//-----------------------------------------------------------------------------------------------------------	
	protected static void setPageInfoAttribute(ModelAndView modelView, ParamObject paramObject) {
		if(modelView==null||paramObject==null){
			return;
		}
		modelView.addObject("webParams",paramObject.getWebParams()).addObject("paramObject",paramObject);
	}
	
	protected static void setDataAttribute(ModelAndView modelView, List<?> dataList,String dataName,Long totalCount,String countNnme) {
		if(modelView==null||dataList==null||dataName==null){
			return;
		}
		modelView.addObject(dataName,dataList).addObject(countNnme,totalCount==null?0L:totalCount);
	}
	
	protected static void setDataAttribute(ModelAndView modelView, List<?> dataList,String dataName,Long totalCount) {
		if(modelView==null||dataList==null||dataName==null){
			return;
		}
		modelView.addObject(dataName,dataList).addObject("totalCount",totalCount==null?0L:totalCount);
	}
	
	protected static void setDataAttribute(ModelAndView modelView, Object data,String dataName) {
		if(modelView==null||data==null||dataName==null){
			return;
		}
		modelView.addObject(dataName,data);
	}
	
	protected static void setDataAttributeAndPageInfo(ModelAndView modelView, List<?> dataList,String dataName,Long totalCount,String countNnme,ParamObject paramObject) {
		if(modelView==null||dataList==null||dataName==null||paramObject==null){
			return;
		}
		modelView.addObject(dataName,dataList).addObject(countNnme,totalCount==null?0L:totalCount);
		modelView.addObject("webParams",paramObject.getWebParams()).addObject("paramObject",paramObject);
	}
	
	protected static void setDataAttributeAndPageInfo(ModelAndView modelView, List<?> dataList,String dataName,Long totalCount,ParamObject paramObject) {
		if(modelView==null||dataList==null||dataName==null||paramObject==null){
			return;
		}
		modelView.addObject(dataName,dataList).addObject("totalCount",totalCount==null?0L:totalCount);
		modelView.addObject("webParams",paramObject.getWebParams()).addObject("paramObject",paramObject);
	}
//-----------------------------------------------------------------------------------------------------------	
	
	
}
