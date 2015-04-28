/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;
import org.zht.framework.util.ZBeanUtil;

import com.zht.common.codegen.dao.IGenEntityPropertyDao;
import com.zht.common.codegen.model.GenEntityProperty;
import com.zht.common.codegen.service.IGenEntityPropertyService;
import com.zht.common.codegen.util.UIEasyStrUtil;
import com.zht.common.codegen.util.HiberStrUtil;
@Service
@Transactional(rollbackFor=Exception.class)
public class GenEntityPropertyServiceImpl extends BaseServiceImpl<GenEntityProperty> implements IGenEntityPropertyService{
	@Autowired
	IGenEntityPropertyDao genEntityPropertyDao;
	
	private List<String> basicPropertyTypeList=Arrays.asList(new String[]{
			"java.lang.String",
			"java.lang.Long",
			"java.lang.Date",
			"java.lang.Time",
			"java.lang.DateTime",
			"java.sql.Date",
			"java.lang.Boolean",
			"java.lang.Integer",
			"java.lang.Double",
			"java.lang.Float",
			"java.lang.Short",
			"java.lang.Byte",
			"java.lang.Character",
			"java.lang.Byte[]",
			 "java.sql.Time",
			"java.sql.Timestamp",
			"java.math.BigDecimal"});
	@Override
	public void addOrUpdateGenEntityProperty(GenEntityProperty entityProperty) {
		if(entityProperty==null){
			throw new ServiceLogicalException("数据为空，请检查");
		}
		List<String> rTypeList=Arrays.asList(new String[]{"property","manytoone","onetomany","manytomanyMarster","manytomanySlaver","onetoonefk"});
		String rType=entityProperty.getRelationType();
		if(!rTypeList.contains(rType)){
			throw new ServiceLogicalException("关系类型不正确");
		}
		String pType=entityProperty.getPropertyType();
		if(Arrays.asList(new String[]{"manytoone","onetomany","manytomanyMarster","manytomanySlaver","onetoonefk"}).contains(rType)){
			if(basicPropertyTypeList.contains(pType)){
				throw new ServiceLogicalException("["+rType+"]与数据类型不对应");
			}
		}
		
		if(entityProperty.getId()==null){
			List<String> pList=genEntityPropertyDao.findPropertyNameListByEntityId(entityProperty.getGenEntity().getId());
			if(pList!=null&&pList.size()>0){
				if(pList.contains(entityProperty.getPropertyName())){
					throw new ServiceLogicalException("该实体中  ["+entityProperty.getPropertyName()+"]已经存在");
				}
			}
			List<String> cList=genEntityPropertyDao.findColumnNameListByEntityId(entityProperty.getGenEntity().getId());
			if(cList!=null&&cList.size()>0){
				if(cList.contains(entityProperty.getColumnName())){
					throw new ServiceLogicalException("该表中  ["+entityProperty.getColumnName()+"]已经存在");
				}
			}
			baseDaoImpl.saveOrUpdate(entityProperty);
		}else{
			GenEntityProperty ppp=baseDaoImpl.find(GenEntityProperty.class, entityProperty.getId());
			ZBeanUtil.copy(entityProperty, ppp, true);
			baseDaoImpl.saveOrUpdate(ppp);
			
		}
		
		
	}
	@Override
	public void updatePropertyDetail(GenEntityProperty entityProperty) {
		if(entityProperty==null||entityProperty.getId()==null){
			throw new ServiceLogicalException("数据为空，请检查");
		}
		GenEntityProperty targetProperty=baseDaoImpl.find(GenEntityProperty.class,entityProperty.getId());
		ZBeanUtil.copy(entityProperty, targetProperty, true);
		targetProperty.setLength(targetProperty.getMaxLength());
		String relationType=targetProperty.getRelationType();
		if("property".equals(relationType)){
			handlerProperty(targetProperty);
		}else if("onetomany".equals(relationType)){
			HiberStrUtil.handlerOnetomany(targetProperty);
		}else if("manytoone".equals(relationType)){
			HiberStrUtil.handlerManytoOne(targetProperty);
			UIEasyStrUtil.handlManyToOne(targetProperty);
		}else if("manytomanyMarster".equals(relationType)||"manytomanySlaver".equals(relationType)){
			HiberStrUtil.handlerManytoMany(targetProperty);
		}else if("onetoonefk".equals(relationType)){
			HiberStrUtil.handlerOneToOne(targetProperty);
		}else if("onetoone".equals(relationType)){
			
		}
		baseDaoImpl.saveOrUpdate(targetProperty);
	}
	
	
	private void handlerProperty( GenEntityProperty genEntityProperty){
		String propertyType=genEntityProperty.getPropertyType();
		if("java.lang.String".equals(propertyType)){
			UIEasyStrUtil.handlerProperty_String(genEntityProperty);
			HiberStrUtil.handlerProperty_String(genEntityProperty);
			
		}else if("java.lang.Long".equals(propertyType)||"java.lang.Integer".equals(propertyType)
				||"java.lang.Byte".equals(propertyType)||"java.lang.Short".equals(propertyType)){
			UIEasyStrUtil.handlerProperty_Long$Integer$Short$Byte(genEntityProperty);
			HiberStrUtil.handlerProperty_Long$Integer$Short$Byte(genEntityProperty);
			
		} else if ("java.lang.Double".equals(propertyType)||"java.lang.Float".equals(propertyType)
				||"java.math.BigDecimal".equals(propertyType)) {
			UIEasyStrUtil.handlerProperty_Double$Float$BigDecimal(genEntityProperty);
			HiberStrUtil.handlerProperty_Double$Float$BigDecimal(genEntityProperty);
		}
		else if ("java.lang.Boolean".equals(propertyType)) {
			UIEasyStrUtil.handlerProperty_Boolean(genEntityProperty);
			HiberStrUtil.handlerProperty_Boolean(genEntityProperty);
		} else if ("java.lang.Byte[]".equals(propertyType)) {
			UIEasyStrUtil.handlerProperty_String(genEntityProperty);
			HiberStrUtil.handlerProperty_Bytes(genEntityProperty);
		} else if ("java.util.Date".equals(propertyType)) {
			UIEasyStrUtil.handlerProperty_Date(genEntityProperty);
			HiberStrUtil.handlerProperty_Date(genEntityProperty);
		}else {
			throw new ServiceLogicalException("不支持的类型");
		}
		
	}
	@Override
	public List<Map<String, String>> loadPropertyNameByEntityName(
			String entityName) {
		if(entityName!=null){
			   entityName=entityName.replaceAll("_", ".");
		 }
		return genEntityPropertyDao.loadPropertyNameByEntityName(entityName);
	}
}