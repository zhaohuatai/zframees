/**
 * Copyright (c) 2015 https://github.com/zhaohuatai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zht.common.codegen.service;


import java.util.List;
import java.util.Map;
import org.zht.framework.service.IBaseService;
import com.zht.common.codegen.model.GenEntity;

public interface IGenEntityService extends IBaseService<GenEntity>{

	public static final String all="all";
	public static final String jsp_all="jsp_all";
	public static final String jsp_list="jsp_list";
	public static final String jsp_add="jsp_add";
	public static final String jsp_edit="jsp_edit";
	
	public static final String java_model="java_model";
	public static final String java_all="java_all";	
	public static final String java_action="java_action";
	public static final String java_service="java_service";
	public static final String java_dao="java_dao";

	
	/**
	 * 
	 * @param genEntityId
	 * @param genType 
	 * "jsp_list","jsp_add","jsp_edit", "jsp-all"
	 * "java-action","java-service","java-dao","java-all"
	 */
	public void genEntityCRUD(Long genEntityId,String genType);
	
	public List<Map<String,String>>  loadPropertyTypeData();
}
