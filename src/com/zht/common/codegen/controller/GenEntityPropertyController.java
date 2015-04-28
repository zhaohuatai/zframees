/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zht.framework.data.DataSet;
import org.zht.framework.data.ParamItem;
import org.zht.framework.data.ParamObject;
import org.zht.framework.data.Querylogic;
import org.zht.framework.data.RowMap;
import org.zht.framework.web.controller.BaseController;
import org.zht.framework.web.utils.FastjsonUtil;

import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.model.GenEntityProperty;
import com.zht.common.codegen.service.IGenEntityPropertyService;
import com.zht.common.codegen.service.IGenEntityService;
@Controller
@RequestMapping(value="/common/generator/genEntityProperty")
public class GenEntityPropertyController extends BaseController{
	
	private String jspPrefix="/common/generator/";
	@Autowired
	private IGenEntityService genEntityService;
	@Autowired
	private IGenEntityPropertyService genEntityPropertyService;
	

	@RequestMapping(value="/enterEntityProperty")
    public Object enterEntityProperty(Model model, Long entityId){
		GenEntity genEntity =genEntityService.$base_find(entityId);
	    setDataAttribute(model,genEntity,"genEntity");
		return jspPrefix+"genEntityPropertyEdit";
    }
	
	private static final  RowMap rowMap=new RowMap(
			"id","id",
			"columnName","columnName",
			"propertyName","propertyName",
			"display","display",
			"propertyType","propertyType",
			"relationType","relationType");
	
    @RequestMapping(value="/loadGenEntityPropertyDataGrid")
    @ResponseBody 
    public Object loadGenEntityPropertyDataGrid(@ModelAttribute("paramObject") ParamObject paramObject,Long entityId){
       		paramObject.setIsOffset(false);
    		paramObject.addParamItem("genEntityid", new ParamItem("genEntity.id",entityId,Querylogic.L_equal));
    		DataSet dataGridView=genEntityPropertyService.$base_loadDataSetFromOneEntity(paramObject, rowMap);
            return FastjsonUtil.convert(dataGridView);
    }
    
    @RequestMapping(value="/genEntityPropertyAddOrUpdate")
    @ResponseBody 
    public Object genEntityPropertyAddOrUpdate(@Valid @ModelAttribute("genEntityProperty")GenEntityProperty genEntityProperty,BindingResult bindingResult){
    	processValidateResult(bindingResult);
    	genEntityPropertyService.addOrUpdateGenEntityProperty(genEntityProperty);
    	return ajaxDoneSuccess("数据修改成功 ");
    }
    
	@RequestMapping(value="/loadEntityPropertyDetail")
    public Object loadEntityPropertyDetail(Model model, Long id){
		try{
			GenEntityProperty genEntityProperty =genEntityPropertyService.$base_find(id);
		    setDataAttribute(model,genEntityProperty,"genEntityProperty");
		    String relationType=genEntityProperty.getRelationType();
		    if("manytoone".equals(relationType)){
		    	return jspPrefix+"detail_manytoone";
		    }else if("onetomany".equals(relationType)){
		    	return jspPrefix+"detail_onetomany";
		    }else if("manytomanyMarster".equals(relationType)){
		    	return jspPrefix+"detail_manytomany_slaver";
		    }else if("manytomanySlaver".equals(relationType)){
		    	return jspPrefix+"detail_manytomany_marster";
		    }else if("onetoonefk".equals(relationType)){
		    	return jspPrefix+"detail_onetoone_fk";
		    }else if("onetoone".equals(relationType)){
		    	return jspPrefix+"detail_onetoone";
		    }else if("property".equals(relationType)){
		    	 String type=genEntityProperty.getPropertyType();
				    if("java.lang.String".equals(type)){
				    	return jspPrefix+"propertyDetail_String";
				    }else if("java.lang.Long".equals(type)){
				    	return jspPrefix+"propertyDetail_Integer";
				    }else if("java.lang.Integer".equals(type)){
				    	return jspPrefix+"propertyDetail_Integer";
				    }else if("java.lang.Double".equals(type)){
				    	return jspPrefix+"propertyDetail_Decimal";
				    }else if("java.lang.Float".equals(type)){
				    	return jspPrefix+"propertyDetail_Decimal";
				    }else if("java.lang.Short".equals(type)){
				    	return jspPrefix+"propertyDetail_Integer";
				    }else if("java.lang.Byte".equals(type)){
				    	return jspPrefix+"propertyDetail_Integer";
				    }else if("java.lang.Boolean".equals(type)){
				    	return jspPrefix+"propertyDetail_Boolean";
				    }else if("java.lang.Byte[]".equals(type)){
				    	return jspPrefix+"propertyDetail_byte[]";
				    }else if("java.math.BigDecimal".equals(type)){
				    	return jspPrefix+"propertyDetail_Decimal";
				    }else if("java.util.Date".equals(type)){
				    	return jspPrefix+"propertyDetail_Date";
				    }else if("java.sql.Timestamp".equals(type)){
				    	return jspPrefix+"propertyDetail_Timestamp";
				    }else{
				    	return jspPrefix+"error";
				    }
		    }else{
		    	return jspPrefix+"error";
		    }
		   
			}catch(Exception e){
				e.printStackTrace();
			}
		return jspPrefix+"error";
    }
	@RequestMapping(value="/updatePropertyDetail")
	@ResponseBody 
    public Object updatePropertyDetail(Model model, GenEntityProperty genEntityProperty){
		genEntityPropertyService.updatePropertyDetail(genEntityProperty);
		return ajaxDoneSuccess("修改成功 ");
    }

	@RequestMapping(value = "/deleteProperty")
	@ResponseBody
	public Object deleteProperty(Long id) {
		genEntityPropertyService.$base_delete$Just(id);
		return ajaxDoneSuccess("删除成功 ");
	}
	   
	@RequestMapping(value = "/loadPropertyNameByEntityName")
	@ResponseBody
	public Object loadPropertyNameByEntityName(String entityName) {
		List<?> list = genEntityPropertyService.loadPropertyNameByEntityName(entityName);
		return FastjsonUtil.convert(list);
	}
}

