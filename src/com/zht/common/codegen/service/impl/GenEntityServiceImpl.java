/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zht.framework.exception.ServiceLogicalException;
import org.zht.framework.service.impl.BaseServiceImpl;
import org.zht.framework.util.ZStrUtil;

import com.zht.common.codegen.excute.ActionGenerator;
import com.zht.common.codegen.excute.DaoGenerator;
import com.zht.common.codegen.excute.HiberModelgenerator;
import com.zht.common.codegen.excute.JSPGeneratorNew;
import com.zht.common.codegen.excute.ServiceGenerator;
import com.zht.common.codegen.excute.impl.ActionGeneratorImpl;
import com.zht.common.codegen.excute.impl.DaoGeneratorImpl;
import com.zht.common.codegen.excute.impl.HiberModelgeneratorImpl;
import com.zht.common.codegen.excute.impl.JSPGeneratorImplNew;
import com.zht.common.codegen.excute.impl.ServiceGeneratorImpl;
import com.zht.common.codegen.model.GenEntity;
import com.zht.common.codegen.model.GenEntityProperty;
import com.zht.common.codegen.service.IGenEntityService;
@Service
@Transactional(rollbackFor=Exception.class)
public class GenEntityServiceImpl extends BaseServiceImpl<GenEntity> implements IGenEntityService{
	
	@Override
	public void genEntityCRUD(Long genEntityId,String genType) {
		
		GenEntity genEntity=baseDaoImpl.find(GenEntity.class, genEntityId);
		if(genEntity==null){
			throw new ServiceLogicalException("[系统错误]未找到该数据表");
		}
		if(ZStrUtil.trimToNull(genType)==null){
			throw new ServiceLogicalException("[genType]数据不允许为空");
		}
		if(ZStrUtil.trimToNull(genEntity.getControllerNameSpace())==null){
			throw new ServiceLogicalException("[controllerNameSpace]数据不允许为空");
		}
		if(ZStrUtil.trimToNull(genEntity.getEntityDisName())==null){
			throw new ServiceLogicalException("[entityDisName]数据不允许为空");
		}
		String entityFullClassName=genEntity.getName();//用于生成后台 各层的类
		DaoGenerator daoG=new DaoGeneratorImpl();
		ServiceGenerator serG=new ServiceGeneratorImpl();
		ActionGenerator actionG=new ActionGeneratorImpl();
		JSPGeneratorNew jspG=new JSPGeneratorImplNew();
		
		HiberModelgenerator hiberG=new HiberModelgeneratorImpl();
		//daoG.genAll(entityFullClassName);
		//serG.genAll(entityFullClassName);
		//action--jsp特殊
		String controllerNameSpace=genEntity.getControllerNameSpace();
		//String modelName=StringUtils.substringAfterLast(fullClassName, ".");
//		List<GenEntityProperty> genEntityDetailList=entityPropertyDao.findByHqlWhere(GenEntityProperty.class, "  where genEntity.id= "+genEntityId+" and (editFinalContentStr is not null)");
		List<GenEntityProperty> genEntityDetailList=baseDaoImpl.findByHqlWhere(GenEntityProperty.class, "  where genEntity.id= "+genEntityId+" ");
		
		//actionG.generatorAction(entityFullClassName,controllerNameSpace,genEntity,genEntityDetailList);
		
		//jspG.genAll(entityFullClassName, controllerNameSpace, genEntity, genEntityDetailList);
			if(IGenEntityService.all.equals(genType)){
				daoG.genAll(entityFullClassName);
				serG.genAll(entityFullClassName);
				actionG.generatorAction(entityFullClassName,controllerNameSpace,genEntity,genEntityDetailList);
				jspG.genAll(entityFullClassName, controllerNameSpace, genEntity, genEntityDetailList);
			}
			if(IGenEntityService.java_all.equals(genType)){
				daoG.genAll(entityFullClassName);
				serG.genAll(entityFullClassName);
				actionG.generatorAction(entityFullClassName,controllerNameSpace,genEntity,genEntityDetailList);
			}
			
			if(IGenEntityService.java_model.equals(genType)){
				hiberG.generatorModel(entityFullClassName,genEntity,genEntityDetailList);
			}
			if(IGenEntityService.java_action.equals(genType)){
				actionG.generatorAction(entityFullClassName,controllerNameSpace,genEntity,genEntityDetailList);
			}
			if(IGenEntityService.java_service.equals(genType)){
				serG.genAll(entityFullClassName);
			}
			if(IGenEntityService.java_dao.equals(genType)){
				daoG.genAll(entityFullClassName);
			}
			
			if(IGenEntityService.jsp_all.equals(genType)){
				jspG.genAll(entityFullClassName, controllerNameSpace, genEntity, genEntityDetailList);
			}
			if(IGenEntityService.jsp_list.equals(genType)){
				jspG.genjsp_list(entityFullClassName, controllerNameSpace, genEntity, genEntityDetailList);
			}
			if(IGenEntityService.jsp_add.equals(genType)){
				jspG.genjsp_add(entityFullClassName, controllerNameSpace, genEntity, genEntityDetailList);
			}
			if(IGenEntityService.jsp_edit.equals(genType)){
				jspG.genjsp_update(entityFullClassName, controllerNameSpace, genEntity, genEntityDetailList);
			}
			
	}
	@Override
	public List<Map<String, String>> loadPropertyTypeData() {
		List<Map<String,String>> resList=new ArrayList<Map<String,String>>();
		
		Map<String,String> poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.lang.String");
		poMap.put("displayz", "String");
		resList.add(poMap);
		
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.lang.Long");
		poMap.put("displayz", "Long");
		resList.add(poMap);
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.util.Date");
		poMap.put("displayz", "Date");
		resList.add(poMap);
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.lang.Boolean");
		poMap.put("displayz", "Boolean");
		resList.add(poMap);
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.lang.Integer");
		poMap.put("displayz", "Integer");
		resList.add(poMap);
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.lang.Double");
		poMap.put("displayz", "Double");
		resList.add(poMap);
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.lang.Float");
		poMap.put("displayz", "Float");
		resList.add(poMap);
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.lang.Short");
		poMap.put("displayz", "Short");
		resList.add(poMap);
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.lang.Byte");
		poMap.put("displayz", "Byte");
		resList.add(poMap);
		
//		poMap=new LinkedHashMap<>();
//		poMap.put("valuez", "java.lang.Character");
//		poMap.put("displayz", "Character");
//		resList.add(poMap);
		
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.lang.Byte[]");
		poMap.put("displayz", "Byte[]");
		resList.add(poMap);
//		poMap=new LinkedHashMap<>();
//		poMap.put("valuez", "java.sql.Time");
//		poMap.put("displayz", "Time");
//		resList.add(poMap);
//		poMap=new LinkedHashMap<>();
//		poMap.put("valuez", "java.sql.Timestamp");
//		poMap.put("displayz", "Timestamp");
//		resList.add(poMap);
		
		poMap=new LinkedHashMap<>();
		poMap.put("valuez", "java.math.BigDecimal");
		poMap.put("displayz", "BigDecimal");
		resList.add(poMap);
		String hql="select name from  GenEntity ";
		Query query = baseDaoImpl.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> list=query.list();
		if(list!=null&&list.size()>0){
			for(String str:list){
				Map<String,String> map=new HashMap<>();
				map.put("valuez", str);
				map.put("displayz", StringUtils.substringAfterLast(str, "."));
				resList.add(map);
			}
		}
		return resList;
	}

}
