/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.RowMap;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FastjsonUtil;

import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.service.IGenEntityService;
@Controller
@RequestMapping(value="/common/generator/genEntity")
public class GenEntityController extends BaseController{
	
	private String jspPrefix="/common/generator/";
	@Autowired
	private IGenEntityService genEntityService;
	
	@RequestMapping(value="/enterGenEntityPage")
    public Object enterGenEntityPage(){
		//genEntityService.initAllModelMetaInfo();
		return jspPrefix+"genEntityDataGrid";
    }
	
    @RequestMapping(value="/loadGenEntityDataGrid")
    @ResponseBody 
    public Object loadGenEntityDataGrid(@ModelAttribute("paramObject") ParamObject paramObject){
    	DataSet dataGridView=genEntityService.$base_loadDataSetFromOneEntity(paramObject, new RowMap(GenEntity.class));
    	return FastjsonUtil.convert(dataGridView);
    }
    
    @RequestMapping(value="/enterGenEntityAdd")
    public String enterGenEntityAdd(){
        return jspPrefix+"genEntityAdd";
    }
  
    @RequestMapping(value="/genPropertyEdit")
    public String enterEditProperty(){
        return jspPrefix+"genPropertyEdit";
    }
    
   
    @RequestMapping(value="/updateGenEntity")
    @ResponseBody 
    public Object updateGenEntity(@Valid @ModelAttribute("genEntity")GenEntity genEntity,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	genEntityService.$base_save(genEntity);
		return ajaxDoneSuccess("数据修改成功 ");
    }
    
    @RequestMapping(value="/genEntityAdd")
    @ResponseBody 
    public Object genEntityAdd(@Valid @ModelAttribute("genEntity")GenEntity genEntity,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	genEntityService.$base_save(genEntity);
    	return ajaxDoneSuccess("数据添加成功 ");
    }
    
    
    @RequestMapping(value="/genEntityCRUD")
    @ResponseBody 
    public Object genEntityCRUD(Long genEntityId,String genType)throws Exception{
    	genEntityService.genEntityCRUD(genEntityId,genType);
    	return ajaxDoneSuccess("代码生成成功  ");
    }
    
    @RequestMapping(value="/loadPropertyTypeData")
    @ResponseBody 
    public Object loadPropertyTypeData(){
    	List<Map<String,String>> list=genEntityService.loadPropertyTypeData();
    	return FastjsonUtil.convert(list);
    }
}
